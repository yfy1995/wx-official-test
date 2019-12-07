package xin.dayukeji.wxofficial.entity.wechat.template;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-12-02 17:04
 * @Version 1.0
 * @description 描述
 */
@Data
public class Test2TemplateMessage extends TemplateMessageData {
    private TemplateVariable name;
    private TemplateVariable hosipital;
    private TemplateVariable department;
    private TemplateVariable remark;

}
