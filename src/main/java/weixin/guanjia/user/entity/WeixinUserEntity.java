package weixin.guanjia.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;
import org.jeecgframework.core.common.entity.IdEntity;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 微信用户
 * @author onlineGenerator
 * @date 2016-05-05 14:58:33
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_user")
public class WeixinUserEntity extends IdEntity implements java.io.Serializable {
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**修改人名称*/
	private java.lang.String updateName;
	/**修改日期*/
	private java.util.Date updateDate;
	/**关注*/
	@Excel(exportName="关注")
	private java.lang.String subscribe;
	/**openid*/
	@Excel(exportName="openid")
	private java.lang.String openid;
	/**加密openid*/
	@Excel(exportName="加密openid")
	private java.lang.String wechatUrlToken;
	/**昵称*/
	@Excel(exportName="昵称")
	private java.lang.String nickname;
	/**性别*/
	@Excel(exportName="性别")
	private java.lang.String sex;
	/**城市*/
	@Excel(exportName="城市")
	private java.lang.String city;
	/**语言*/
	@Excel(exportName="语言")
	private java.lang.String language;
	/**省份*/
	@Excel(exportName="省份")
	private java.lang.String province;
	/**国家*/
	@Excel(exportName="国家")
	private java.lang.String country;
	/**头像*/
	@Excel(exportName="头像")
	private java.lang.String headimgurl;
	/**关注时间*/
	@Excel(exportName="关注时间")
	private java.lang.String subscribe_time;
	/**备注*/
	@Excel(exportName="备注")
	private java.lang.String remark;
	/**用户id*/
	@Excel(exportName="用户id")
	private java.lang.String userId;
	/**组id*/
	@Excel(exportName="组id")
	private java.lang.String groupid;
	/**标签*/
	@Excel(exportName="标签")
	private java.lang.String[] tagid_list;
	/**绑定时间*/
	@Excel(exportName="绑定时间")
	private java.util.Date wechatBindTime;

	/**
	 * 错误代码
	 */
	@JsonProperty("errcode")
	private String errcode;

	/**
	 * 错误描述
	 */
	@JsonProperty("errmsg")
	private String errmsg;

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人名称
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  修改日期
	 */
	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  修改日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  关注
	 */
	@Column(name ="SUBSCRIBE",nullable=true,length=10)
	public java.lang.String getSubscribe(){
		return this.subscribe;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  关注
	 */
	public void setSubscribe(java.lang.String subscribe){
		this.subscribe = subscribe;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  openid
	 */
	@Column(name ="OPENID",nullable=true,length=100)
	public java.lang.String getOpenid(){
		return this.openid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  openid
	 */
	public void setOpenid(java.lang.String openid){
		this.openid = openid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  加密openid
	 */
	@Column(name ="WECHAT_URL_TOKEN",nullable=true,length=500)
	public java.lang.String getWechatUrlToken(){
		return this.wechatUrlToken;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  加密openid
	 */
	public void setWechatUrlToken(java.lang.String wechatUrlToken){
		this.wechatUrlToken = wechatUrlToken;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  昵称
	 */
	@Column(name ="NICKNAME",nullable=true,length=50)
	public java.lang.String getNickname(){
		return this.nickname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  昵称
	 */
	public void setNickname(java.lang.String nickname){
		this.nickname = nickname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别
	 */
	@Column(name ="SEX",nullable=true,length=10)
	public java.lang.String getSex(){
		return this.sex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别
	 */
	public void setSex(java.lang.String sex){
		this.sex = sex;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  城市
	 */
	@Column(name ="CITY",nullable=true,length=20)
	public java.lang.String getCity(){
		return this.city;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  城市
	 */
	public void setCity(java.lang.String city){
		this.city = city;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  语言
	 */
	@Column(name ="LANGUAGE",nullable=true,length=10)
	public java.lang.String getLanguage(){
		return this.language;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  语言
	 */
	public void setLanguage(java.lang.String language){
		this.language = language;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  省份
	 */
	@Column(name ="PROVINCE",nullable=true,length=20)
	public java.lang.String getProvince(){
		return this.province;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  省份
	 */
	public void setProvince(java.lang.String province){
		this.province = province;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  国家
	 */
	@Column(name ="COUNTRY",nullable=true,length=20)
	public java.lang.String getCountry(){
		return this.country;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  国家
	 */
	public void setCountry(java.lang.String country){
		this.country = country;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  头像
	 */
	@Column(name ="HEADIMGURL",nullable=true,length=500)
	public java.lang.String getHeadimgurl(){
		return this.headimgurl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  头像
	 */
	public void setHeadimgurl(java.lang.String headimgurl){
		this.headimgurl = headimgurl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  关注时间
	 */
	@Column(name ="SUBSCRIBE_TIME",nullable=true,length=20)
	public java.lang.String getSubscribe_time(){
		return this.subscribe_time;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  关注时间
	 */
	public void setSubscribe_time(java.lang.String subscribe_time){
		this.subscribe_time = subscribe_time;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARK",nullable=true,length=200)
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户id
	 */
	@Column(name ="USER_ID",nullable=true,length=40)
	public java.lang.String getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户id
	 */
	public void setUserId(java.lang.String userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  组id
	 */
	@Column(name ="GROUP_ID",nullable=true,length=50)
	public java.lang.String getGroupid(){
		return this.groupid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  组id
	 */
	public void setGroupid(java.lang.String groupid){
		this.groupid = groupid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标签
	 */
	@Column(name ="TAGID_LIST")
	public java.lang.String[] getTagid_list(){
		return this.tagid_list;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标签
	 */
	public void setTagid_list(java.lang.String[] tagid_list){
		this.tagid_list = tagid_list;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  绑定时间
	 */
	@Column(name ="WECHAT_BIND_TIME",nullable=true,length=32)
	public java.util.Date getWechatBindTime(){
		return this.wechatBindTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  绑定时间
	 */
	public void setWechatBindTime(java.util.Date wechatBindTime){
		this.wechatBindTime = wechatBindTime;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
