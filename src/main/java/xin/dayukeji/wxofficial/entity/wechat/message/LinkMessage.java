package xin.dayukeji.wxofficial.entity.wechat.message;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-23 12:30
 * @Version 1.0
 * @description 链接消息
 */
@Data
public class LinkMessage extends BaseMessage {
    /**
     * 消息标题
     */
    private String Title;
    /**
     * 消息描述
     */
    private String Description;
    /**
     * 消息链接
     */
    private String Url;
}
