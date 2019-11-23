package xin.dayukeji.wxofficial.entity.pojo.event;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-23 14:14
 * @Version 1.0
 * @description 扫描带参数二维码事件
 */
@Data
public class QRCodeEvent extends BaseEvent {
    /**
     * 事件KEY值
     */
    private String EventKey;
    /**
     * 用于换取二维码图片
     */
    private String Ticket;
}
