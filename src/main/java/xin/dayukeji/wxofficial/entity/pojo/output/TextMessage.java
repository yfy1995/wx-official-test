package xin.dayukeji.wxofficial.entity.pojo.output;

import lombok.Data;
import xin.dayukeji.wxofficial.util.MessageType;

/**
 * @Author: yfy
 * @Date: 2019-11-23 14:21
 * @Version 1.0
 * @description 文本回复消息
 */
@Data
public class TextMessage extends BaseOutMessage {
    /**
     * 文本消息
     */
    private String Content;

    @Override
    public String getMsgType() {
        return MessageType.RESP_MESSAGE_TYPE_TEXT;
    }
}
