package xin.dayukeji.wxofficial.entity.wechat.menu;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-23 18:27
 * @Version 1.0
 * @description 子菜单项 :没有子菜单的菜单项，有可能是二级菜单项，也有可能是不含二级菜单的一级菜单
 */
@Data
public class CommonButton extends Button {
    private String type;
    private String key;
    private String url;
}
