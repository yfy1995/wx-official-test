package xin.dayukeji.wxofficial.entity.wechat.template;

import lombok.Data;

import java.util.List;

/**
 * @Author: yfy
 * @Date: 2019-11-25 12:12
 * @Version 1.0
 * @description 模板列表
 */
@Data
public class Template {
    private List<TemplateEntity> template_list;

    @Data
    public static class TemplateEntity {
        /**
         * 模板ID
         */
        private String template_id;
        /**
         * 模板标题
         */
        private String title;
        /**
         * 模板所属行业的一级行业
         */
        private String primary_industry;
        /**
         * 模板所属行业的二级行业
         */
        private String deputy_industry;
        /**
         * 模板内容
         */
        private String content;
        /**
         * 模板示例
         */
        private String example;
    }
}
