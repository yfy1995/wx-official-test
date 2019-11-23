package xin.dayukeji.wxofficial.entity.pojo.output;

import lombok.Data;
import xin.dayukeji.wxofficial.util.MessageType;

/**
 * @Author: yfy
 * @Date: 2019-11-23 14:32
 * @Version 1.0
 * @description 图片输出内容
 */
@Data
public class ImageOutputMessage extends BaseOutMessage {
    private Image Image;

    @Override
    public String getMsgType() {
        return MessageType.RESP_MESSAGE_TYPE_IMAGE;
    }
}
