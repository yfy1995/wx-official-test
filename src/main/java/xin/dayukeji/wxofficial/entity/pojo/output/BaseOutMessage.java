package xin.dayukeji.wxofficial.entity.pojo.output;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-23 14:20
 * @Version 1.0
 * @description 回复消息的公共字段类
 */
@Data
public abstract class BaseOutMessage {
    /**
     * 接收方帐号（收到的OpenID）
     */
    private String ToUserName;
    /**
     * 开发者微信号
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

    /**
     * 获取消息类型
     *
     * @return
     */
    public abstract String getMsgType();
}
