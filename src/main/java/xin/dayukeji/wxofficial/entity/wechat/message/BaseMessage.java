package xin.dayukeji.wxofficial.entity.wechat.message;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-23 12:24
 * @Version 1.0
 * @description 描述
 */
@Data
public abstract class BaseMessage {
    /**
     * 开发者微信号
     */
    private String ToUserName;
    /**
     * 发送方帐号（一个OpenID）
     */
    private String FromUserName;
    /**
     * 消息创建时间
     */
    private long CreateTime;
    /**
     * 消息id，64位整型
     */
    private long MsgId;
}
