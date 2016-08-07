package weixin.guanjia.user.service;


import org.jeecgframework.core.common.service.CommonService;
import weixin.dto.UserRole;
import weixin.guanjia.user.entity.WeixinUserEntity;

import java.io.Serializable;

public interface WeixinUserServiceI extends CommonService {
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param
	 * @return
	 */
 	public boolean doAddSql(WeixinUserEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param
	 * @return
	 */
 	public boolean doUpdateSql(WeixinUserEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param
	 * @return
	 */
 	public boolean doDelSql(WeixinUserEntity t);

	public WeixinUserEntity obtainWeixinUserInfo(String openId);

	public WeixinUserEntity findBindByOpenId(String openId);

	void updateWeixinUserId(String openid,String userId);
}
