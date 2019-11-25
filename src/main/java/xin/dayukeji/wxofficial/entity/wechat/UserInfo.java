package xin.dayukeji.wxofficial.entity.wechat;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author: yfy
 * @Date: 2019-11-25 15:09
 * @Version 1.0
 * @description 微信用户信息类
 */
@Data
public class UserInfo {
    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息
     */
    private String subscribe;
    /**
     * 用户的标识
     */
    private String openid;
    /**
     * 用户的昵称
     */
    private String nickname;
    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private Integer sex;
    /**
     * 用户所在城市
     */
    private String city;
    /**
     * 用户所在国家
     */
    private String country;
    /**
     * 用户所在省份
     */
    private String province;
    /**
     * 用户头像
     */
    private String headimgurl;
    /**
     * 用户关注时间
     */
    private Timestamp subscribe_time;
    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
     */
    private String unionid;
    /**
     * 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     */
    private String remark;
    /**
     * 用户所在的分组ID
     */
    private String groupid;
    /**
     * 用户被打上的标签ID列表
     */
    private String tagid_list;
    /**
     * 返回用户关注的渠道来源，
     * ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，
     * ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，
     * ADD_SCENE_PROFILE_ LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，
     * ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
     */
    private String subscribe_scene;
    /**
     * 二维码扫码场景（开发者自定义）
     */
    private String qr_scene;
    /**
     * 二维码扫码场景描述（开发者自定义）
     */
    private String qr_scene_str;
}
