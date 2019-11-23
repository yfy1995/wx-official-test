package xin.dayukeji.wxofficial.entity.pojo.output;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-23 14:30
 * @Version 1.0
 * @description 图文消息实体类对象
 */
@Data
public class Articles {
    private String Title;
    /**
     * 图文消息描述
     */
    private String Description;
    /**
     * 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80
     */
    private String PicUrl;
    /**
     * 点击图文消息跳转链接
     */
    private String Url;
}
