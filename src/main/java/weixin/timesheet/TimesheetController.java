package weixin.timesheet;

import com.kcfy.techservicemarket.core.generate.TokenGen;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openkoala.koala.commons.InvokeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import weixin.dto.TimesheetDTO;
import weixin.dto.UserRole;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.HttpRequestUtil;
import weixin.guanjia.core.util.JacksonUtil;
import weixin.guanjia.core.util.WeChatWebSetting;
import weixin.guanjia.user.entity.WeixinUserEntity;
import weixin.guanjia.user.service.WeixinUserServiceI;
import weixin.util.WSO2Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/4/28.
 */
@Controller
@RequestMapping("/timesheet")
public class TimesheetController {
    private static final Log LOG = LogFactory.getLog(TimesheetController.class);
    @Autowired
    private WeixinAccountServiceI weixinAccountService;
    @Autowired
    private WeixinUserServiceI weixinUserService;


    @RequestMapping(params = "add", method = RequestMethod.GET)
    public ModelAndView add(HttpServletRequest request) {
        return new ModelAndView("weixin/timesheet/timesheet-add");
    }
    @RequestMapping(params = "add", method = RequestMethod.POST)
    public ModelAndView addSave(ModelMap model, TimesheetDTO timesheetDTO, HttpServletRequest request) {
        InvokeResult invokeResult = WSO2Util.saveTimesheet(timesheetDTO);
        if(!invokeResult.isSuccess()) {
            LOG.warn(invokeResult.getErrorMessage());
            model.addAttribute("errmsg", invokeResult.getErrorMessage());
            return new ModelAndView("weixin/timesheet/error");
        }
        return new ModelAndView("weixin/timesheet/success");
    }
    @RequestMapping(params = "update", method = RequestMethod.GET)
    public ModelAndView update(String id, ModelMap model, HttpServletRequest request) {
        TimesheetDTO timesheet = WSO2Util.getTimesheet(id);
        model.addAttribute("timesheet",timesheet);
        return new ModelAndView("weixin/timesheet/timesheet-add");
    }
    @RequestMapping(params = "delete", method = RequestMethod.GET)
    public ModelAndView delete(String id, ModelMap model, HttpServletRequest request) {
        InvokeResult invokeResult = WSO2Util.deleteTimesheet(id);
        if(!invokeResult.isSuccess()) {
            LOG.warn(invokeResult.getErrorMessage());
            model.addAttribute("errmsg", invokeResult.getErrorMessage());
            return new ModelAndView("weixin/timesheet/error");
        }
        return new ModelAndView("redirect:/timesheet.do?getByUserId");
    }
    @RequestMapping(params = "getByUserId", method = RequestMethod.GET)
    public ModelAndView getByUserId(HttpSession session, ModelMap model) {
        model.addAttribute("timesheetList", WSO2Util.getTimesheetByUserId(session.getAttribute("userId").toString()));
        return new ModelAndView("weixin/timesheet/timesheet-list");
    }
    @RequestMapping(params = "demo")
    public ModelAndView demo(HttpServletRequest request) {
        return new ModelAndView("weixin/timesheet/demoui");
    }
    @RequestMapping(params = "weixinBind")
    public ModelAndView weixinBind(ModelMap model, HttpServletRequest request) {
        String code = request.getParameter("code");
        String webAccessTokenUrl = WeChatWebSetting.OAUTH2_ACCESS_TOKEN_URL(code);
        String webAccessTokenInfo = HttpRequestUtil.executeRquest(webAccessTokenUrl, null, HttpRequestUtil.GET, null, null, "utf-8", null, null, null);
        try {
            HashMap webAccessInfoTokenMap = JacksonUtil.fromObject(webAccessTokenInfo, HashMap.class);
            String webAccessToken = String.valueOf(webAccessInfoTokenMap.get("access_token"));
            String openId = String.valueOf(webAccessInfoTokenMap.get("openid"));

            String userInfoUrl = WeChatWebSetting.OAUTH2_USER_INFO_URL(webAccessToken, openId);
            String userInfo = HttpRequestUtil.executeRquest(userInfoUrl, null, HttpRequestUtil.GET, null, null, "utf-8", null, null, null);
            HashMap userInfoMap = JacksonUtil.fromObject(userInfo, HashMap.class);
            String nickName = String.valueOf(userInfoMap.get("nickname"));
            String sex = String.valueOf(userInfoMap.get("sex"));
            String province = String.valueOf(userInfoMap.get("province"));
            String city = String.valueOf(userInfoMap.get("city"));
            String country = String.valueOf(userInfoMap.get("country"));
            String headImgUrl = String.valueOf(userInfoMap.get("headimgurl"));
            String unionId = String.valueOf(userInfoMap.get("unionid"));
            model.addAttribute("userName", nickName);
            model.addAttribute("addr", country + province + city);
            model.addAttribute("headImgUrl", headImgUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView("weixin/timesheet/weixinBind");
    }
    @RequestMapping(params = "weixinBindBase", method = RequestMethod.GET)
    public ModelAndView weixinBindBase(ModelMap model, HttpServletRequest request, HttpSession session) {
        String code = request.getParameter("code");
        String webAccessTokenUrl = WeChatWebSetting.OAUTH2_ACCESS_TOKEN_URL(code);
        String webAccessTokenInfo = HttpRequestUtil.executeRquest(webAccessTokenUrl, null, HttpRequestUtil.GET, null, null, "utf-8", null, null, null);
        try {
            HashMap webAccessInfoTokenMap = JacksonUtil.fromObject(webAccessTokenInfo, HashMap.class);
            if(webAccessInfoTokenMap.get("errcode") != null) {
                LOG.warn(webAccessInfoTokenMap.get("errcode").toString() + webAccessInfoTokenMap.get("errmsg").toString());
                model.addAttribute("errmsg", webAccessInfoTokenMap.get("errmsg").toString() + "，请重新从微信菜单进入。");
                return new ModelAndView("weixin/timesheet/error");
            }
            String openId = String.valueOf(webAccessInfoTokenMap.get("openid"));
            WeixinUserEntity weixinUserEntity = weixinUserService.findBindByOpenId(openId);
            session.setAttribute("headImgUrl",weixinUserEntity.getHeadimgurl());
            if(StringUtils.isNotEmpty(weixinUserEntity.getUserId())) {
                session.setAttribute("userId", weixinUserEntity.getUserId());
                return new ModelAndView("redirect:/home.do?index");
            }
            model.addAttribute("openid", openId);
            model.addAttribute("userName", weixinUserEntity.getNickname());
            model.addAttribute("addr", weixinUserEntity.getCountry() + "  " + weixinUserEntity.getProvince() + "  " + weixinUserEntity.getCity());
            model.addAttribute("headImgUrl", weixinUserEntity.getHeadimgurl());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ModelAndView("weixin/timesheet/weixinBind");
    }
    @RequestMapping(params = "bind")
    public ModelAndView bind(String openid, String email, ModelMap model, HttpServletRequest request, HttpSession session) {
        InvokeResult invokeResult = WSO2Util.bindUser(openid, email);
        if(!invokeResult.isSuccess()) {
            LOG.warn(invokeResult.getErrorMessage());
            model.addAttribute("errmsg", invokeResult.getErrorMessage());
            return new ModelAndView("weixin/timesheet/error");
        }
        UserRole userRole = (UserRole)invokeResult.getData();
        weixinUserService.updateWeixinUserId(openid,userRole.getUserId());

        model.addAttribute("userRole",userRole);
        session.setAttribute("userId", userRole.getUserId());
        return new ModelAndView("weixin/timesheet/weixinBindSuccess");
    }


}
