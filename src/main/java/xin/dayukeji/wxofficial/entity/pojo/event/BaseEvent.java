package xin.dayukeji.wxofficial.entity.pojo.event;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-23 14:12
 * @Version 1.0
 * @description 事件消息
 */
@Data
public abstract class BaseEvent {
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
     * 消息类型
     */
    private String MsgType;
    /**
     * 事件类型
     */
    private String Event;
}
