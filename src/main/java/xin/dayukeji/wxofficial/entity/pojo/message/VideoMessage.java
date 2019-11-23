package xin.dayukeji.wxofficial.entity.pojo.message;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-23 14:35
 * @Version 1.0
 * @description 视频消息
 */
@Data
public class VideoMessage {
    /**
     * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据
     */
    private String MediaId;
    /**
     * 视频消息 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
     */
    private String ThumbMediaId;
}
