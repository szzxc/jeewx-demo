/**
 * 
 */
package com.kcfy.techservicemarket.core.util;

/**
 * @author zhengzw
 *
 */
public class Constants {
	public static String EMPTY = "";
	public static int CODE_401 = 401;
	public static int max_times = 3;
	
	/** wso2 server return JSON key **/
	public static String RTN_KEY_SUBSCRIPTIONS = "subscriptions";
	public static String RTN_KEY_APPLICATIONS = "applications";
	public static String RTN_KEY_NAME = "name";
	public static String PRODCONSUMERKEY = "prodConsumerKey";
	public static String PRODCONSUMERSECRET = "prodConsumerSecret";
	public static String REFRESH_TOKEN = "refresh_token";
	public static String ACCESS_TOKEN = "access_token";
	
	
	/** tokenGen **/
	public static String BASIC = "Basic ";  // 此处一定要有一个空格
	public static String BEARER = "Bearer "; // 此处一定要有一个空格
	public static String CONSUMER_SPLIT = ":";
	public static String GRANT_TYPE = "password";
	
	
	/** http-client.properties begin **/
	public static String LOGIN_URL = "token.login.url";
	public static String LOGIN_NAME = "token.login.username";
	public static String LOGIN_PWD = "token.login.password";
	public static String TOKEN_URL = "token.url";
	public static String TOKEN_APP = "token.app";
	public static String TOKEN_ACCESS_URL = "token.access.url";
	public static String TECH_SERVICE_URL = "tech.servicemarket.url";
	
	/****/
	public static String METHOD_POST = "POST";
	public static String METHOD_GET = "GET";
	public static String CONTENT_TYPE = "Content-Type";
	public static String FORM_URLENCODE = "application/x-www-form-urlencoded";
	public static String AUTHORIZATION = "Authorization";
	public static String ACCEPT = "Accept";
	public static String APPJSON = "application/json";
	
	
	public static String CODE = "utf-8";
	public static String SET_COOKIE = "Set-Cookie";
	public static String COOKIE = "Cookie";
	
	
	public static String HASERROR = "hasErrors";
	public static String FALSE = "false";
	public static String ERRORMESSAGE = "errorMessage";
	public static String T = "T";
	public static String F = "F";
}
