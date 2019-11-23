package xin.dayukeji.wxofficial.entity.pojo.message;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-23 12:29
 * @Version 1.0
 * @description 地理位置消息
 */
@Data
public class LocationMessage extends BaseMessage {
    /**
     * 地理位置维度
     */
    private String Location_X;
    /**
     * 地理位置经度
     */
    private String Location_Y;
    /**
     * 地图缩放大小
     */
    private Long Scale;
    /**
     * 地理位置信息
     */
    private String Label;
}
