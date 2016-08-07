package weixin.guanjia.message.controller;

import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.openkoala.koala.commons.InvokeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.WeChatSetting;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.guanjia.message.entity.TemplatemsgIndustry;
import weixin.guanjia.message.service.TemplatemsgIndustryServiceI;
import weixin.guanjia.message.util.TemplateMsgUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Controller
 * @Description: 消息模板行业设置
 * @date 2016-05-04 13:41:47
 */
@Controller
@RequestMapping("/templatemsgIndustryController")
public class TemplatemsgIndustryController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(TemplatemsgIndustryController.class);

    @Autowired
    private TemplatemsgIndustryServiceI weixinTemplatemsgIndustryService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private WeixinAccountServiceI weixinAccountService;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * 消息模板行业设置列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "templatemsgIndustry")
    public ModelAndView templatemsgIndustry(HttpServletRequest request) {
        return new ModelAndView("weixin/templateMsg/templatemsgIndustryList");
    }

    /**
     * easyui AJAX请求数据
     *
     * @param request
     * @param response
     * @param dataGrid
     */

    @RequestMapping(params = "datagrid")
    public void datagrid(TemplatemsgIndustry weixinTemplatemsgIndustry, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TemplatemsgIndustry.class, dataGrid);
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinTemplatemsgIndustry, request.getParameterMap());
        try {
            //自定义追加查询条件
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        cq.add();
        this.weixinTemplatemsgIndustryService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 删除消息模板行业设置
     *
     * @return
     */
    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(TemplatemsgIndustry weixinTemplatemsgIndustry, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        weixinTemplatemsgIndustry = systemService.getEntity(TemplatemsgIndustry.class, weixinTemplatemsgIndustry.getId());
        message = "消息模板行业设置删除成功";
        try {
            weixinTemplatemsgIndustryService.delete(weixinTemplatemsgIndustry);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "消息模板行业设置删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 批量删除消息模板行业设置
     *
     * @return
     */
    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "消息模板行业设置删除成功";
        try {
            for (String id : ids.split(",")) {
                TemplatemsgIndustry weixinTemplatemsgIndustry = systemService.getEntity(TemplatemsgIndustry.class,
                        id
                );
                weixinTemplatemsgIndustryService.delete(weixinTemplatemsgIndustry);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "消息模板行业设置删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 添加消息模板行业设置
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(TemplatemsgIndustry weixinTemplatemsgIndustry, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "消息模板行业设置添加成功";
        try {
            weixinTemplatemsgIndustryService.save(weixinTemplatemsgIndustry);
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "消息模板行业设置添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 更新消息模板行业设置
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(TemplatemsgIndustry weixinTemplatemsgIndustry, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "消息模板行业设置更新成功";
        TemplatemsgIndustry t = weixinTemplatemsgIndustryService.get(TemplatemsgIndustry.class, weixinTemplatemsgIndustry.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(weixinTemplatemsgIndustry, t);
            weixinTemplatemsgIndustryService.saveOrUpdate(t);
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "消息模板行业设置更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 消息模板行业设置新增页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(TemplatemsgIndustry weixinTemplatemsgIndustry, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(weixinTemplatemsgIndustry.getId())) {
            weixinTemplatemsgIndustry = weixinTemplatemsgIndustryService.getEntity(TemplatemsgIndustry.class, weixinTemplatemsgIndustry.getId());
            req.setAttribute("weixinTemplatemsgIndustryPage", weixinTemplatemsgIndustry);
        }
        return new ModelAndView("weixin/templateMsg/templatemsgIndustry-add");
    }

    /**
     * 消息模板行业设置编辑页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(TemplatemsgIndustry weixinTemplatemsgIndustry, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(weixinTemplatemsgIndustry.getId())) {
            weixinTemplatemsgIndustry = weixinTemplatemsgIndustryService.getEntity(TemplatemsgIndustry.class, weixinTemplatemsgIndustry.getId());
            req.setAttribute("weixinTemplatemsgIndustryPage", weixinTemplatemsgIndustry);
        }
        return new ModelAndView("weixin/templateMsg/templatemsgIndustry-update");
    }

    @ResponseBody
    @RequestMapping("/setWeixin")
    public InvokeResult setWeixin(HttpServletRequest request) throws IOException {
        String json = IOUtils.toString(request.getInputStream(), "utf-8");
        logger.info("Industry:" + json);
        String accessToken = WeChatSetting.getACCESS_TOKEN();
        String url = TemplateMsgUtil.SET_INDUSTRY_URL.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", json);
        LogUtil.info(jsonObject);
        InvokeResult invokeResult = null;
        if (jsonObject != null) {
            if (0 == jsonObject.getInt("errcode")) {
                invokeResult = InvokeResult.success("设置行业成功！");
            } else {
                invokeResult = InvokeResult.failure("设置行业失败！错误码为：" + jsonObject.getInt("errcode") + "错误信息为：" + jsonObject.getString("errmsg"));
            }
        } else {
            invokeResult = InvokeResult.failure("设置行业失败");
        }
        return invokeResult;
    }
}
