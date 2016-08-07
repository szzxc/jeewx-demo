package weixin.guanjia.core.util;

public class WeChatWebSetting extends WeChatSetting {

    public static String SCOPE_BASE = "snsapi_base";
    public static String SCOPE_USERINFO = "snsapi_userinfo";

    private static String RESPONSE_TYPE = "code";
    private static String GRANT_TYPE_AUTHRIZATION_CODE = "authorization_code ";
    private static String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";
    /**
     *
     * @param redirectUrl
     * @param scope
     * @param status
     * @return
     */
    public static String OAUTH2_CODE_URL(String redirectUrl, String scope, String status) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
        stringBuffer.append(APPID);
        stringBuffer.append("&redirect_uri=");
        stringBuffer.append(redirectUrl);
        stringBuffer.append("&response_type=");
        stringBuffer.append(RESPONSE_TYPE);
        stringBuffer.append("&scope=");
        stringBuffer.append(scope);
        stringBuffer.append("&status=");
        stringBuffer.append(status);
        stringBuffer.append("#wechat_redirect");
        return  stringBuffer.toString();
    }

    public static String OAUTH2_ACCESS_TOKEN_URL(String code) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=");
        stringBuffer.append(APPID);
        stringBuffer.append("&secret=");
        stringBuffer.append(APPSECRET);
        stringBuffer.append("&code=");
        stringBuffer.append(code);
        stringBuffer.append("&grant_type=");
        stringBuffer.append(GRANT_TYPE_AUTHRIZATION_CODE);
        return  stringBuffer.toString();
    }

    public static String OAUTH2_REFRESH_TOKEN_URL(String refreshToken) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=");
        stringBuffer.append(APPID);
        stringBuffer.append("&grant_type=");
        stringBuffer.append(GRANT_TYPE_REFRESH_TOKEN);
        stringBuffer.append("&refresh_token=");
        stringBuffer.append(refreshToken);
        return  stringBuffer.toString();
    }

    public static String OAUTH2_USER_INFO_URL(String accessToken, String openId){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("https://api.weixin.qq.com/sns/userinfo?access_token=");
        stringBuffer.append(accessToken);
        stringBuffer.append("&openid=");
        stringBuffer.append(openId);
        stringBuffer.append("OPENID&lang=");
        stringBuffer.append(LANG);
        return  stringBuffer.toString();
    }
}
