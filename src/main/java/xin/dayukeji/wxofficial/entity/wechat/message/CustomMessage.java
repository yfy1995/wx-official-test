package xin.dayukeji.wxofficial.entity.wechat.message;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-26 18:57
 * @Version 1.0
 * @description 客户消息实体类
 */
@Data
public class CustomMessage {
    /**
     * 用户微信标识
     */
    private String touser;
    /**
     * 消息类型，文本为text，图片为image，语音为voice，视频消息为video，
     * 音乐消息为music，图文消息（点击跳转到外链）为news，图文消息（点击跳转到图文消息页面）为mpnews，
     * 卡券为wxcard，小程序为miniprogrampage
     */
    private String msgtype;
}
