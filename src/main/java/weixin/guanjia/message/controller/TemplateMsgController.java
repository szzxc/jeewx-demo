package weixin.guanjia.message.controller;

/**
 * Created by Administrator on 2016/2/16.
 */

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import weixin.guanjia.message.service.TemplateMsgService;
import weixin.guanjia.message.util.TemplateMsgUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/templateMsg")
public class TemplateMsgController extends BaseController {
    private static final Logger LOG = Logger.getLogger(TemplatemsgIndustryController.class);

    @Autowired
    private TemplateMsgService templateMsgService;

    @RequestMapping(params = "setIndustry")
    @ResponseBody
    public String setIndustry(HttpServletRequest request) {
        return templateMsgService.setIndustry(TemplateMsgUtil.IndustryCode.IT_SOFTWARE, TemplateMsgUtil.IndustryCode.IT_NETWORK);
    }

    @RequestMapping(params = "getIndustry")
    public ModelAndView getIndustry(HttpServletRequest request) {
         templateMsgService.getIndustry();
        return new ModelAndView("weixin/templateMsg/industry");
    }

    @RequestMapping("/getTemplate")
    @ResponseBody
    public String getTemplate(HttpServletRequest request) {
        return templateMsgService.getTemplate();
    }

    @RequestMapping(params = "addTemplate")
    @ResponseBody
    public String addTemplate(HttpServletRequest request) {
        String templateId = "TM00015";
        return templateMsgService.addTemplate(templateId);
    }

    @RequestMapping(params = "delTemplate")
    @ResponseBody
    public String delTemplate(HttpServletRequest request) {
        String templateId = "Aw22-qn8a6qd9u_-_1mvD1LEZ34584j7z3RisAnwbg8";
        return templateMsgService.delTemplate(templateId);
    }

    @RequestMapping("/send")
    @ResponseBody
    public String send(HttpServletRequest request) throws IOException {
        String json = IOUtils.toString(request.getInputStream(), "utf-8");
        LOG.info("template message json:" + json);
        return templateMsgService.send(json);
    }

}
