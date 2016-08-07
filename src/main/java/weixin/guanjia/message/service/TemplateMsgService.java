package weixin.guanjia.message.service;

import org.jeecgframework.core.common.service.CommonService;
import weixin.guanjia.message.util.TemplateMsgUtil;

/**
 * Created by Administrator on 2016/2/16.
 */
public interface TemplateMsgService extends CommonService {
    public String setIndustry(TemplateMsgUtil.IndustryCode industry1, TemplateMsgUtil.IndustryCode industry2);

    public String getIndustry();

    public String getTemplate();

    public String addTemplate(String templateId);

    public String delTemplate(String templateId);

    public String send(String postStr);
}
