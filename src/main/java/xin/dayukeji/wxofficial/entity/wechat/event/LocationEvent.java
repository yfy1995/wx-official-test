package xin.dayukeji.wxofficial.entity.wechat.event;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-23 14:15
 * @Version 1.0
 * @description 上报地理位置事件
 */
@Data
public class LocationEvent {
    /**
     * 地理位置纬度
     */
    private String Latitude;
    /**
     * 地理位置经度
     */
    private String Longitude;
    /**
     * 地理位置精度
     */
    private String Precision;
}
