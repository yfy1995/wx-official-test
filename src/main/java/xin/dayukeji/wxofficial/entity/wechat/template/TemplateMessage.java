package xin.dayukeji.wxofficial.entity.wechat.template;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-25 12:20
 * @Version 1.0
 * @description 模板消息实体类
 */
@Data
public class TemplateMessage {
    /**
     * 接收者openid
     */
    private String touser;
    /**
     * 模板ID
     */
    private String template_id;
    /**
     * 模板跳转链接(海外帐号没有跳转能力)
     */
    private String url;
    /**
     * 模板数据
     */
    private TemplateMessageData data;
    private MiniProgram miniprogram;


    @Data
    public static class MiniProgram {
        /**
         * 所需跳转到的小程序appId
         */
        private String appid;
        /**
         * 所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），要求该小程序已发布
         */
        private String pagepath;
    }
}
