package xin.dayukeji.wxofficial.entity.wechat;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-25 18:57
 * @Version 1.0
 * @description 描述
 */
@Data
public class WebAccessToken {
    /**
     * 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     */
    private String access_token;
    /**
     * access_token接口调用凭证超时时间，单位（秒）
     */
    private Integer expires_in;
    /**
     * 用户刷新access_token
     */
    private String refresh_token;
    /**
     * 用户唯一标识
     */
    private String openid;
    /**
     * 用户授权的作用域，使用逗号（,）分隔
     */
    private String scope;
}
