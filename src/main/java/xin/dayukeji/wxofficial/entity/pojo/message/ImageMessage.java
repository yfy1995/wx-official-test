package xin.dayukeji.wxofficial.entity.pojo.message;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-23 12:26
 * @Version 1.0
 * @description 图片消息
 */
@Data
public class ImageMessage extends BaseMessage {
    /**
     * 图片链接
     */
    private String PicUrl;
    /**
     * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据
     */
    private String MediaId;
}
