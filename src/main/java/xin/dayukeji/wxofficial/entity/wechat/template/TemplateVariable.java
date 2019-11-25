package xin.dayukeji.wxofficial.entity.wechat.template;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-25 12:23
 * @Version 1.0
 * @description 模板变量
 */
@Data
public class TemplateVariable {
    /**
     * 变量值
     */
    private String value;
    /**
     * 模板内容字体颜色，不填默认为黑色 eg: #173177
     */
    private String color;
}
