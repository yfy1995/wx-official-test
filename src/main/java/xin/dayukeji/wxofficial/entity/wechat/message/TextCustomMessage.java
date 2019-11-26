package xin.dayukeji.wxofficial.entity.wechat.message;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-26 19:00
 * @Version 1.0
 * @description 客服消息：文本消息
 */
@Data
public class TextCustomMessage extends CustomMessage {
    private Text text;

    @Data
    public static class Text {
        private String content;
    }
}
