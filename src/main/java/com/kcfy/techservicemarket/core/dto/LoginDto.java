/**
 * 
 */
package com.kcfy.techservicemarket.core.dto;

import com.kcfy.techservicemarket.core.generate.PropertyGen;
import com.kcfy.techservicemarket.core.util.Constants;

/**
 * @author zhengzw@fengyuntec.com
 *
 */
public class LoginDto extends BaseSupportObject {
	
	private String action = "login";
	private String username = PropertyGen.prop.getProperty(Constants.LOGIN_NAME);
	private String password = PropertyGen.prop.getProperty(Constants.LOGIN_PWD);
	
	/**
	 * @return
	 */
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	/**
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
