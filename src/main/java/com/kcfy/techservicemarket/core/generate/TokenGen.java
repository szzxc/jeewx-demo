/**
 * 
 */
package com.kcfy.techservicemarket.core.generate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.logging.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.kcfy.techservicemarket.core.dto.AccessTokenDto;
import com.kcfy.techservicemarket.core.dto.LoginDto;
import com.kcfy.techservicemarket.core.dto.TokenDto;
import com.kcfy.techservicemarket.core.json.JSON;
import com.kcfy.techservicemarket.core.json.JSONArray;
import com.kcfy.techservicemarket.core.json.JSONObject;
import com.kcfy.techservicemarket.core.log.LevelEnum;
import com.kcfy.techservicemarket.core.log.LoggerFactory;
import com.kcfy.techservicemarket.core.util.Constants;
import com.kcfy.techservicemarket.core.util.HttpUtils;

import sun.misc.BASE64Encoder;

/**
 * @author zhengzw@fengyuntec.com token生成器
 */
public class TokenGen {

	private static Logger logger = LoggerFactory.getLogger(LevelEnum.INFO);

	/** 消费密钥 **/
	private static String consumer_key;

	/** 消费机密 **/
	private static String consumer_sec;
	
	/** refresh token 暂时先不适用吧 **/
	private static String refresh_token;

	public static void main(String[] args) {
		System.out.println(getToken());
	}
	
	/**
	 * 公布给其他地方使用的方法
	 * @return
	 */
	public static String getToken() {
		return _getToken();
	}
	
	/**
	 * 返回refreshToken
	 * 这是一个不安全的方法，此方法不一定会返回一个有效的refresh token
	 * 目前先暂时这么实现吧，后期再进行调整
	 * @return
	 */
	public static String getRefreshToken() {
		return refresh_token;
	}
	
	private static String _getToken() {
		String token = Constants.EMPTY;
		
		try{
			// 为确保获取token时能正确读取properties文件,每次取token时都需要判断配置文件是否加载
			if (PropertyGen.prop.isEmpty()) {
				PropertyGen.loadProperties();
			}
			
			// 绕过wso2证书问题
			initHttpsUrlConnection();
			
			// 由于获取token时需要根据消费秘钥和消费机密,所以如果存在消费秘钥和消费机密，则直接获取token
			if (null == consumer_key || Constants.EMPTY.equals(consumer_key) 
					|| null == consumer_sec || Constants.EMPTY.equals(consumer_sec)) {
				
				// 获取消费秘钥和机密
				synchronized (TokenGen.class) {
					if (null == consumer_key || Constants.EMPTY.equals(consumer_key) 
							|| null == consumer_sec || Constants.EMPTY.equals(consumer_sec)) {
						
						setConsumerKeyAndSec();
					}
				}
			}
			
			token = getTokenFromServer();
			
			if (null == token || Constants.EMPTY.equals(token)) {
				throw new Exception("get token from server return null!");
			}
			
		}catch(Exception e){
			logger.severe("get token error ! " + e);
		}
		
		return token;
	}
	
	/**
	 * 设置wso2的消费秘钥和消费机密
	 * @throws Exception
	 */
	private static void setConsumerKeyAndSec() throws Exception {
		
		logger.info("INFO:**********Thread [" + Thread.currentThread().getId() + "] "
				+ "set token consumer key and secret start! **********");
		
		String sessionId = loginTokenServer();
		setConsumerKeyAndSec(sessionId);
		
		logger.info("INFO:**********Thread [" + Thread.currentThread().getId() + "] "
				+ "set token consumer key and secret finish! **********");
	}
	
	/**
	 * 根据登陆获取的sesseionId去获取subscription
	 * 
	 * @param sessionId
	 *            登录wso2服务获取session信息
	 * @return
	 */
	private static void setConsumerKeyAndSec(String sessionId) throws Exception {
		String tokenUrl = PropertyGen.prop.getProperty(Constants.TOKEN_URL);
		
		logger.info("INFO:**********Thread [" + Thread.currentThread().getId() + "] set consumer key and secret with url :"
				+ tokenUrl + " **********");
		
		TokenDto get = new TokenDto();
		String params = HttpUtils.getGetParameter(get);
		
		String json = HttpsGet(tokenUrl, params, sessionId);
		
		JSONObject appObject = JSON.parseObject(json);
		
		JSONObject subscriptions = (JSONObject) appObject.get(Constants.RTN_KEY_SUBSCRIPTIONS);
		JSONArray applications = (JSONArray) subscriptions.get(Constants.RTN_KEY_APPLICATIONS);
		
		JSONArray subs = null;
		JSONObject subscriptionobject = null;
		
		String app = PropertyGen.prop.getProperty(Constants.TOKEN_APP);
		
		for(Object application : applications){
			subs = ((JSONObject) application).getJSONArray(Constants.RTN_KEY_SUBSCRIPTIONS);
			
			for (Object subscription : subs) {
				subscriptionobject = (JSONObject) JSON.toJSON(subscription);

				if (app.equals(subscriptionobject.getString(Constants.RTN_KEY_NAME))) {
					
					consumer_key = subscriptionobject.getString(Constants.PRODCONSUMERKEY);
					consumer_sec = subscriptionobject.getString(Constants.PRODCONSUMERSECRET);
					
					break;
				}
			}
		}
	}
	
	/**
	 * 登录wso2服务器
	 * 
	 * @return
	 */
	private static String loginTokenServer() throws Exception {
		
		HttpsURLConnection urlCon = null;
		
		try{
			String url = PropertyGen.prop.getProperty(Constants.LOGIN_URL);
			
			logger.info("INFO:**********Thread [" + Thread.currentThread().getId() + "] login token server with url :"
					+ url + " **********");
			
			LoginDto login = new LoginDto();
			String params = HttpUtils.getPostParameter(login);
			
			urlCon = httpsPost(urlCon, url, params);
			
			return urlCon.getHeaderField(Constants.SET_COOKIE);
			
		}catch(Exception e){
			logger.severe("ERROR:***************Thread [" + Thread.currentThread().getId() 
					+ "] login token server error! ***************");
			
			throw e;
		}finally{
			if(null != urlCon){
				urlCon.disconnect();
			}
		}
	}
	
	/**
	 * 从wso2上直接获取token
	 * 
	 * @return
	 */
	private static String getTokenFromServer() throws Exception {
		
		String url = PropertyGen.prop.getProperty(Constants.TOKEN_ACCESS_URL);
		
		logger.info("INFO:**********Thread [" + Thread.currentThread().getId()
				+ "] find token expiration and renew token from server with url :" + url + " **********");
		
		AccessTokenDto access = new AccessTokenDto();
		
		String params = HttpUtils.getPostParameter(access);
		
		StringBuilder sb = new StringBuilder();
		sb.append(consumer_key).append(Constants.CONSUMER_SPLIT).append(consumer_sec);
		
		// 由于jdk base64编码bug 在使用httpurlconnection时，必须这么写
		String authorization = new BASE64Encoder() {
			@Override
			protected int bytesPerLine(){
				return 9999;
			}
		}.encode(sb.toString().getBytes());
		
		authorization = Constants.BASIC + authorization;
		
		logger.info("INFO:**********Thread [" + Thread.currentThread().getId()
				+ "] Authorization is :" + authorization + " **********");
		
		
		String json = httpsPost(url, params, authorization);
		
		JSONObject tokenAccess = JSON.parseObject(json);
		
		refresh_token = tokenAccess.getString(Constants.REFRESH_TOKEN);
		
		return tokenAccess.getString(Constants.ACCESS_TOKEN);
	}
	
	/**
	 * 发送https post 请求
	 * @param urlCon httpurlconnenction
	 * @param url url
	 * @param params 参数
	 * @return
	 * @throws Exception
	 */
	private static HttpsURLConnection httpsPost(HttpsURLConnection urlCon, String url, String params) throws Exception {
		
		try {
			urlCon = (HttpsURLConnection) (new URL(url)).openConnection();
			urlCon.setDoInput(true);
			urlCon.setDoOutput(true);
			urlCon.setRequestMethod(Constants.METHOD_POST);
			urlCon.setRequestProperty(Constants.CONTENT_TYPE, Constants.FORM_URLENCODE);
			
			urlCon.setUseCaches(false);

			urlCon.getOutputStream().write(params.getBytes(Constants.CODE));
			urlCon.getOutputStream().flush();
			urlCon.getOutputStream().close();

		} catch (Exception e) {
			logger.severe("ERROR:***************Thread [" + Thread.currentThread().getId() 
				+ "] get HttpURLConnection error! ***************");
			
			throw e;
		}
		
		return urlCon;
	}
	
	/**
	 * 发送https post 请求
	 * @param url url 
	 * @param params 参数
	 * @param authorization 认证
	 * @return
	 */
	private static String httpsPost(String url, String params, String authorization) throws Exception {
		return httpsPost(url, params, authorization, null);
	}
	
	
	/**
	 * 发送https post 请求
	 * @param url url 
	 * @param params 参数
	 * @param authorization 认证
	 * @param sessionId 
	 * @return
	 */
	private static String httpsPost(String url, String params, String authorization, String sessionId) throws Exception {
		
		HttpsURLConnection urlCon = null;

		try {
			urlCon = (HttpsURLConnection) (new URL(url)).openConnection();
			urlCon.setDoInput(true);
			urlCon.setDoOutput(true);
			urlCon.setRequestMethod(Constants.METHOD_POST);
			urlCon.setRequestProperty(Constants.CONTENT_TYPE, Constants.FORM_URLENCODE);
			
			if(null != authorization && !Constants.EMPTY.equals(authorization)) {
				urlCon.setRequestProperty(Constants.AUTHORIZATION, authorization);
			}
			
			if(null != sessionId && !Constants.EMPTY.equals(sessionId)) {
				urlCon.setRequestProperty(Constants.COOKIE, sessionId);
			}
			
			urlCon.setUseCaches(false);

			urlCon.getOutputStream().write(params.getBytes(Constants.CODE));
			urlCon.getOutputStream().flush();
			urlCon.getOutputStream().close();

			BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));

			String line;
			StringBuffer result = new StringBuffer();

			while ((line = in.readLine()) != null) {
				result.append(line);
			}

			return result.toString();

		} catch (Exception e) {
			
			int code = urlCon.getResponseCode();
			
			// 如果涉及到权限问题时,直接重新登录获取消费秘钥和消费机密
			if(code == Constants.CODE_401) {
				setConsumerKeyAndSec();
			}
			
			logger.severe("ERROR:***************Thread [" + Thread.currentThread().getId() 
				+ "] send https post error! ***************" + e);
		} finally {
			if (null != urlCon) {
				urlCon.disconnect();
			}
		}
		
		return "";
	}
	
	/**
	 * 发送https get 请求
	 * @param url 请求URL
	 * @param params 请求参数
	 * @param sessionId
	 * @return
	 */
	private static String HttpsGet(String url, String params, String sessionId) {
		return HttpsGet(url, params, null, sessionId);
	}
	
	/**
	 * 发送https get 请求
	 * @param url 请求URL
	 * @param params 请求参数
	 * @param authorization 认证
	 * @param sessionId
	 * @return
	 */
	private static String HttpsGet(String url, String params, String authorization, String sessionId) {
		
		// 获取get方式url
		StringBuffer sb = new StringBuffer();
		sb.append(url).append(params);
		
		HttpsURLConnection urlCon = null;
		
		try{
			urlCon = (HttpsURLConnection) (new URL(sb.toString())).openConnection();
			urlCon.setDoInput(true);
			urlCon.setDoOutput(true);
			urlCon.setRequestMethod(Constants.METHOD_GET);
			urlCon.setRequestProperty(Constants.COOKIE, sessionId);
			urlCon.setUseCaches(false);

			urlCon.getOutputStream().flush();
			urlCon.getOutputStream().close();

			BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));

			String line;
			StringBuffer result = new StringBuffer();

			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			
			return result.toString();
			
		}catch(Exception e){
			logger.severe("ERROR:***************Thread [" + Thread.currentThread().getId() 
					+ "] send https get error! ***************" + e);
		}finally{
			if(null != urlCon){
				urlCon.disconnect();
			}
		}
		
		return "";
	}
	
	/**
	 * 绕过wso2证书
	 * @throws Exception
	 */
	private static void initHttpsUrlConnection() throws Exception {
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		});

		X509TrustManager tm = new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};

		SSLContext context = SSLContext.getInstance("TLS");

		context.init(null, new TrustManager[] { tm }, new SecureRandom());

		HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
	}
}
