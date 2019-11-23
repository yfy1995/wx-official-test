package xin.dayukeji.wxofficial.entity.pojo.output;

import lombok.Data;
import xin.dayukeji.wxofficial.util.MessageType;

/**
 * @Author: yfy
 * @Date: 2019-11-23 14:24
 * @Version 1.0
 * @description 语音回复消息
 */
@Data
public class VoiceOutputMessage extends BaseOutMessage {
    private Voice voice;

    @Override
    public String getMsgType() {
        return MessageType.RESP_MESSAGE_TYPE_VOICE;
    }
}
