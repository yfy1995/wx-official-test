package xin.dayukeji.wxofficial.entity.wechat.message;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-23 12:02
 * @Version 1.0
 * @description 文本消息
 */
@Data
public class TextMessage extends BaseMessage{
    /**
     * 文本消息内容
     */
    private String content;
}
