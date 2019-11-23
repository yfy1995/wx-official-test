package xin.dayukeji.wxofficial.entity.pojo.output;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-23 14:26
 * @Version 1.0
 * @description 视频model
 */
@Data
public class Video {
    /**
     * 媒体文件id
     */
    private String MediaId;
    /**
     * 缩略图的媒体id
     */
    private String ThumbMediaId;
    /**
     * 视频消息的标题
     */
    private String Title;
    /**
     * 视频消息的描述
     */
    private String Description;
}
