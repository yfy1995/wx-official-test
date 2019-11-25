package xin.dayukeji.wxofficial.entity.wechat;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-23 18:42
 * @Version 1.0
 * @description 描述
 */
@Data
public class AccessToken {
    /**
     * 网页授权接口调用凭证
     */
    private String accessToken;
    /**
     * access_token接口调用凭证超时时间，单位（秒）
     */
    private Integer expiresin;
}
