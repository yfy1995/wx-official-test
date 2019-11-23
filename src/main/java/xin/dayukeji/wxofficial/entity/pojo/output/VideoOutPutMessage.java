package xin.dayukeji.wxofficial.entity.pojo.output;

import lombok.Data;
import xin.dayukeji.wxofficial.util.MessageType;

/**
 * @Author: yfy
 * @Date: 2019-11-23 14:27
 * @Version 1.0
 * @description 描述
 */
@Data
public class VideoOutPutMessage extends BaseOutMessage {
    private Video Video;

    @Override
    public String getMsgType() {
        return MessageType.RESP_MESSAGE_TYPE_VIDEO;
    }
}
