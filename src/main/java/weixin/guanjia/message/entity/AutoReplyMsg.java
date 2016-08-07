package weixin.guanjia.message.entity;


import org.jeecgframework.core.common.entity.IdEntity;

/**
 * Created by Administrator on 2016/2/26.
 */
public class AutoReplyMsg extends IdEntity {

    private String msg_key;
    private Integer weight;
    private String reply_value;
    private String enable;

    public String getMsg_key() {
        return msg_key;
    }

    public void setMsg_key(String msg_key) {
        this.msg_key = msg_key;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getReply_value() {
        return reply_value;
    }

    public void setReply_value(String reply_value) {
        this.reply_value = reply_value;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}
