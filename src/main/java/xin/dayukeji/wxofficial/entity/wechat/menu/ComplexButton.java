package xin.dayukeji.wxofficial.entity.wechat.menu;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-23 18:28
 * @Version 1.0
 * @description 父菜单项 :包含有二级菜单项的一级菜单。
 * 这类菜单项包含有二个属性：name和sub_button，而sub_button以是一个子菜单项数组
 */
@Data
public class ComplexButton extends Button {
    private Button[] sub_button;
}
