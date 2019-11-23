package xin.dayukeji.wxofficial.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xin.dayukeji.wxofficial.entity.pojo.AccessToken;
import xin.dayukeji.wxofficial.entity.pojo.menu.Button;
import xin.dayukeji.wxofficial.entity.pojo.menu.CommonButton;
import xin.dayukeji.wxofficial.entity.pojo.menu.ComplexButton;
import xin.dayukeji.wxofficial.entity.pojo.menu.Menu;
import xin.dayukeji.wxofficial.util.WeixinUtil;

/**
 * @Author: yfy
 * @Date: 2019-11-23 18:39
 * @Version 1.0
 * @description 菜单管理器类
 */
public class MenuManager {
    private static Logger log = LoggerFactory.getLogger(MenuManager.class);

    public static void main(String[] args) {
        // 第三方用户唯一凭证
        String appId = "wx33e5389d1a2cde19";
        // 第三方用户唯一凭证密钥
        String appSecret = "fa132a5549b1ca9dc92081e752d12340";

        // 调用接口获取access_token
        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

        if (null != at) {
            // 调用接口创建菜单
            int result = WeixinUtil.createMenu(getMenu(), at.getAccessToken());

            // 判断菜单创建结果
            if (0 == result) {
                log.info("菜单创建成功！");
            } else {
                log.info("菜单创建失败，错误码：" + result);
            }
        }
    }

    /**
     * 组装菜单数据
     *
     * @return
     */
    private static Menu getMenu() {
        CommonButton btn11 = new CommonButton();
        btn11.setName("搜索");
        btn11.setType("view");
        btn11.setKey("11");
        btn11.setUrl("http://www.soso.com/");

        CommonButton btn12 = new CommonButton();
        btn12.setName("公交查询");
        btn12.setType("click");
        btn12.setKey("12");

        CommonButton btn13 = new CommonButton();
        btn13.setName("周边搜索");
        btn13.setType("click");
        btn13.setKey("13");

        CommonButton btn14 = new CommonButton();
        btn14.setName("历史上的今天");
        btn14.setType("click");
        btn14.setKey("14");

        CommonButton btn21 = new CommonButton();
        btn21.setName("java资料");
        btn21.setType("click");
        btn21.setKey("21");

        CommonButton btn22 = new CommonButton();
        btn22.setName("python资料");
        btn22.setType("click");
        btn22.setKey("22");

        CommonButton btn23 = new CommonButton();
        btn23.setName("架构师资料");
        btn23.setType("click");
        btn23.setKey("23");

        CommonButton btn31 = new CommonButton();
        btn31.setName("系统拍照发图");
        btn31.setType("pic_sysphoto");
        btn31.setKey("31");

        CommonButton btn32 = new CommonButton();
        btn32.setName("拍照或者相册发图");
        btn32.setType("pic_photo_or_album");
        btn32.setKey("32");

        CommonButton btn33 = new CommonButton();
        btn33.setName("微信相册发图");
        btn33.setType("pic_weixin");
        btn33.setKey("33");


        /**
         * 微信：  mainBtn1,mainBtn2,mainBtn3底部的三个一级菜单。
         */

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("菜单");
        //一级下有4个子菜单
        mainBtn1.setSub_button(new CommonButton[]{btn11, btn12, btn13, btn14});


        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("视频资料");
        mainBtn2.setSub_button(new CommonButton[]{btn21, btn22, btn23});


        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("发图");
        mainBtn3.setSub_button(new CommonButton[]{btn31, btn32, btn33});


        /**
         * 封装整个菜单
         */
        Menu menu = new Menu();
        menu.setButton(new Button[]{mainBtn1, mainBtn2, mainBtn3});

        return menu;
    }
}
