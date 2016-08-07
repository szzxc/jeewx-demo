package weixin.guanjia.message.util;

/**
 * Created by zhouxc on 2016/5/3.
 */
public class TemplateMsgUtil {
    // 设置行业可在MP中完成，每月可修改行业1次，账号仅可使用所属行业中相关的模板
    public static String SET_INDUSTRY_URL = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
    public static String GET_INDUSTRY_URL = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";
    public static String ADD_TEMPLATE_URL =  "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";
    public static String GET_ALL_TEMPLATE_URL= "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";
    public static String DEL_TEMPLATE_URL ="https://api,weixin.qq.com/cgi-bin/template/del_private_template?access_token=ACCESS_TOKEN";
    // 当前每个账号的模板消息的日调用上限为10万次，单个模板没有特殊限制。
    public static String SEND_TEMPLATE_MSG_URL ="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    public enum IndustryCode {

        IT_NETWORK("IT科技","互联网/电子商务", 1),
        IT_SOFTWARE("IT科技","IT软件与服务", 2),
        IT_HARDWARE("IT科技","IT硬件与设备",3),
        IT_ELECTRONIC("IT科技","电子技术",4),
        IT_COMMUNICATE("IT科技","通信与运营商",5),
        IT_GAME("IT科技","网络游戏",6);


        private String primary;
        private String second;
        private int code;

        public String getPrimary() {
            return primary;
        }

        public void setPrimary(String primary) {
            this.primary = primary;
        }

        public String getSecond() {
            return second;
        }

        public void setSecond(String second) {
            this.second = second;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        private IndustryCode(String primary, String second, Integer code) {
            this.primary = primary;
            this.second = second;
            this.code = code;
        }

        public static IndustryCode valueOf(Integer code) {
            if (code == null) {
                return null;
            } else {
                for (IndustryCode item : values()) {
                    if (item.getCode() == code) {
                        return item;
                    }
                }
                return null;
            }
        }
    }
}
