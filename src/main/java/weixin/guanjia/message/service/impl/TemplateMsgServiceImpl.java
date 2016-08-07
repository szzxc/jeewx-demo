package weixin.guanjia.message.service.impl;


import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import weixin.guanjia.core.util.HttpRequestUtil;
import weixin.guanjia.core.util.WeChatSetting;
import weixin.guanjia.message.service.TemplateMsgService;
import weixin.guanjia.message.util.TemplateMsgUtil;

/**
 * Created by Administrator on 2016/2/16.
 */
@Service
public class TemplateMsgServiceImpl extends CommonServiceImpl implements TemplateMsgService {
    private static final Logger LOG = LoggerFactory.getLogger(TemplateMsgService.class);


    @Override
    public String setIndustry(TemplateMsgUtil.IndustryCode industry1, TemplateMsgUtil.IndustryCode industry2) {
        String postStr = "{\"industry_id1\":\"" + industry1.getCode() + "\",\"industry_id2\":\"" + industry2.getCode() + "\"}";
        //String result = HttpRequestUtil.executeRquest(TemplateMsgSetting.SET_INDUSTRY_URL(), postStr, false, null, null, "utf-8", null, null, null);
       // LOG.info(result);
       // return result;
        return "";
    }

    @Override
    public String getIndustry() {
        //String result = HttpRequestUtil.executeRquest(TemplateMsgSetting.GET_INDUSTRY_URL(), null, true, null, null, "utf-8", null, null, null);
        //LOG.info(result);
       // return result;
        return "";
    }

    @Override
    public String getTemplate() {
        String accessToken = WeChatSetting.getACCESS_TOKEN();
        String url = TemplateMsgUtil.GET_ALL_TEMPLATE_URL.replace("ACCESS_TOKEN", accessToken);
        String result = HttpRequestUtil.executeRquest(url, null, "POST", null, null, "utf-8", null, null, null);
        LOG.info(result);
        return result;
    }

    @Override
    public String addTemplate(String templateId) {
        String postStr = "{\"template_id_short\":\"" + templateId + "\"}";
//        String result = HttpRequestUtil.executeRquest(TemplateMsgSetting.ADD_TEMPLATE_URL(), postStr, false, null, null, "utf-8", null, null, null);
//        LOG.info(result);
//        return result;
        return "";
    }

    @Override
    public String delTemplate(String templateId) {
        String postStr = "{\"template_id\":\"" + templateId + "\"}";
//        String result = HttpRequestUtil.executeRquest(TemplateMsgSetting.DEL_TEMPLATE_URL(), postStr, false, null, null, "utf-8", null, null, null);
//        LOG.info(result);
//        return result;
        return "";
    }

    @Override
    public String send(String postStr) {
        String accessToken = WeChatSetting.getACCESS_TOKEN();
        String result = HttpRequestUtil.executeRquest(TemplateMsgUtil.SEND_TEMPLATE_MSG_URL.replace("ACCESS_TOKEN", accessToken), postStr, "POST", null, null, "utf-8", null, null, null);
        LOG.info(result);
        return result;
    }


}
