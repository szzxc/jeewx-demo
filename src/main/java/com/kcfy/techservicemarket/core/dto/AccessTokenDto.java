/**
 * 
 */
package com.kcfy.techservicemarket.core.dto;

import com.kcfy.techservicemarket.core.generate.PropertyGen;
import com.kcfy.techservicemarket.core.util.Constants;

/**
 * @author zhengzw
 *
 */
public class AccessTokenDto extends BaseSupportObject {
	
	private String grant_type = Constants.GRANT_TYPE;
	private String username = PropertyGen.prop.getProperty(Constants.LOGIN_NAME);
	private String password = PropertyGen.prop.getProperty(Constants.LOGIN_PWD);
	
	public String getGrant_type() {
		return grant_type;
	}
	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
