package xin.dayukeji.wxofficial.entity.pojo.event;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-23 14:16
 * @Version 1.0
 * @description 自定义菜单事件
 */
@Data
public class MenuEvent extends BaseEvent {
    /**
     * 事件KEY值，与自定义菜单接口中KEY值对应
     */
    private String EventKey;
}
