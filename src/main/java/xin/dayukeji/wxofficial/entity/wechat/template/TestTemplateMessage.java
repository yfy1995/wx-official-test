package xin.dayukeji.wxofficial.entity.wechat.template;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-25 12:22
 * @Version 1.0
 * @description 测试模板消息实体类
 */
@Data
public class TestTemplateMessage extends TemplateMessageData {
    private TemplateVariable name;
    private TemplateVariable price;
    private TemplateVariable time;
}
