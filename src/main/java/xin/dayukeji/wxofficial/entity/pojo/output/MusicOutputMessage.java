package xin.dayukeji.wxofficial.entity.pojo.output;

import lombok.Data;
import xin.dayukeji.wxofficial.util.MessageType;

/**
 * @Author: yfy
 * @Date: 2019-11-23 14:29
 * @Version 1.0
 * @description 回复音乐消息
 */
@Data
public class MusicOutputMessage extends BaseOutMessage {
    private Music Music;

    @Override
    public String getMsgType() {
        return MessageType.RESP_MESSAGE_TYPE_MUSIC;
    }
}
