package weixin.guanjia.user.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.dto.UserRole;
import weixin.guanjia.core.service.impl.WechatService;
import weixin.guanjia.core.util.HttpRequestUtil;
import weixin.guanjia.core.util.JacksonUtil;
import weixin.guanjia.core.util.WeChatSetting;
import weixin.guanjia.core.util.WeChatStatusCode;
import weixin.guanjia.user.entity.WeixinUserEntity;
import weixin.guanjia.user.service.WeixinUserServiceI;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Service("weixinUserService")
@Transactional
public class WeixinUserServiceImpl extends CommonServiceImpl implements WeixinUserServiceI {
    private static final Log LOG = LogFactory.getLog(WeixinUserServiceImpl.class);
    @Autowired
    private WechatService wechatServicel;


    public <T> void delete(T entity) {
        super.delete(entity);
        //执行删除操作配置的sql增强
        this.doDelSql((WeixinUserEntity) entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        //执行新增操作配置的sql增强
        this.doAddSql((WeixinUserEntity) entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        //执行更新操作配置的sql增强
        this.doUpdateSql((WeixinUserEntity) entity);
    }

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param
     * @return
     */
    public boolean doAddSql(WeixinUserEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @param
     * @return
     */
    public boolean doUpdateSql(WeixinUserEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param
     * @return
     */
    public boolean doDelSql(WeixinUserEntity t) {
        return true;
    }

    /**
     * 替换sql中的变量
     *
     * @param sql
     * @return
     */
    public String replaceVal(String sql, WeixinUserEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{update_name}", String.valueOf(t.getUpdateName()));
        sql = sql.replace("#{update_date}", String.valueOf(t.getUpdateDate()));
        sql = sql.replace("#{subscribe}", String.valueOf(t.getSubscribe()));
        sql = sql.replace("#{openid}", String.valueOf(t.getOpenid()));
        sql = sql.replace("#{wechat_url_token}", String.valueOf(t.getWechatUrlToken()));
        sql = sql.replace("#{nickname}", String.valueOf(t.getNickname()));
        sql = sql.replace("#{sex}", String.valueOf(t.getSex()));
        sql = sql.replace("#{city}", String.valueOf(t.getCity()));
        sql = sql.replace("#{language}", String.valueOf(t.getLanguage()));
        sql = sql.replace("#{province}", String.valueOf(t.getProvince()));
        sql = sql.replace("#{country}", String.valueOf(t.getCountry()));
        sql = sql.replace("#{headimgurl}", String.valueOf(t.getHeadimgurl()));
        sql = sql.replace("#{subscribe_time}", String.valueOf(t.getSubscribe_time()));
        sql = sql.replace("#{remark}", String.valueOf(t.getRemark()));
        sql = sql.replace("#{user_id}", String.valueOf(t.getUserId()));
        sql = sql.replace("#{group_id}", String.valueOf(t.getGroupid()));
        sql = sql.replace("#{tagid_list}", String.valueOf(t.getTagid_list()));
        sql = sql.replace("#{wechat_bind_time}", String.valueOf(t.getWechatBindTime()));
        sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
        return sql;
    }

    @Override
    public WeixinUserEntity obtainWeixinUserInfo(String openId) {
        // TODO test
//        if (true) {
//            WechatUserBind wechatUserBind = new WechatUserBind();
//            wechatUserBind.setOpenid(openId);
//            wechatUserBind.setSubscribe("1");
//            return wechatUserBind;
//        }
        WeixinUserEntity user = null;
        WeChatStatusCode.GlobalStatus status = null;
        int i = 1;
        //重复关注OpenId 不会改变
        do {
            String ret = HttpRequestUtil.executeRquest(WeChatSetting.USER_INFO_URL(openId), null, HttpRequestUtil.GET,
                    null, null, "utf-8", null, null, null);
            LOG.debug("获得的用户信息：" + ret);
            try {
                user = JacksonUtil.fromObject(ret, WeixinUserEntity.class);
                status = wechatServicel.executeStatus(ret);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //status = weChatService.executeStatus(ret);
            LOG.debug("重试次数：" + i);
            ++i;
        } while (i < WeChatSetting.MAX_TRY_TIMES &&
                (WeChatStatusCode.GlobalStatus.TOKEN_ERROR.equals(status) ||
                        WeChatStatusCode.GlobalStatus.PARAMETER_ACCESSTOKEN_TIMEOUT.equals(status)));
        if (user.getErrcode() != null) {
            WeChatStatusCode.GlobalStatus st = WeChatStatusCode.GlobalStatus.valueOf(Integer.parseInt(user.getErrcode()));
            LOG.error("Obtain user info failed, error is ：" + st.getKey() + " - " + st.desc());
        }
        return user;
    }

    @Override
    public WeixinUserEntity findBindByOpenId(String openId) {
        List list = this.findByProperty(WeixinUserEntity.class, "openid", openId);
        WeixinUserEntity wechatUserBind = null;
        if (list != null && list.size() > 0) {
            wechatUserBind = (WeixinUserEntity) list.get(0);
        }
        return wechatUserBind;
    }

    @Override
    public void updateWeixinUserId(String openid, String userId) {
        List list = this.findByProperty(WeixinUserEntity.class, "openid", openid);
        WeixinUserEntity wechatUserBind = null;
        if (list != null && list.size() > 0) {
            wechatUserBind = (WeixinUserEntity) list.get(0);
            wechatUserBind.setUserId(userId);
            this.saveOrUpdate(wechatUserBind);
        }
    }
}