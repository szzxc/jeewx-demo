/**
 * 
 */
package com.kcfy.techservicemarket.core.dto;

/**
 * @author zhengzw@fengyuntec.com
 *
 */
public class TokenDto extends BaseSupportObject {
	
	private String action = "getAllSubscriptions";
	
	/**
	 * @return
	 */
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
