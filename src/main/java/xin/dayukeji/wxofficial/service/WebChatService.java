package xin.dayukeji.wxofficial.service;

import lombok.extern.slf4j.Slf4j;
import xin.dayukeji.wxofficial.entity.pojo.output.Articles;
import xin.dayukeji.wxofficial.entity.pojo.output.NewsOutputMessage;
import xin.dayukeji.wxofficial.entity.pojo.output.TextMessage;
import xin.dayukeji.wxofficial.util.MessageType;
import xin.dayukeji.wxofficial.util.ReplyMessageUtil;
import xin.dayukeji.wxofficial.util.XmlUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yfy
 * @Date: 2019-11-23 14:39
 * @Version 1.0
 * @description 处理接收信息和回复消息的服务类接口
 */
@Slf4j
public class WebChatService {
    /**
     * 处理微信发来的请求 map 消息业务处理分发
     *
     * @param map
     * @return
     */
    public static String parseMessage(Map<String, String> map) {
        String respXml = null;
        try {
            // 发送方帐号
            String fromUserName = map.get("FromUserName");
            // 开发者微信号
            String toUserName = map.get("ToUserName");
            // 取得消息类型
            String MsgType = map.get("MsgType");
            // 发现直接把要返回的信息直接封装成replyMap集合，然后转换成 xml文件，是不是实体类可以不用了
            Map<String, String> replyMap = new HashMap<>();
            replyMap.put("ToUserName", fromUserName);
            replyMap.put("FromUserName", toUserName);
            replyMap.put("CreateTime", String.valueOf(System.currentTimeMillis() / 1000));
            if (MsgType.equals(MessageType.TEXT_MESSAGE)) {
                log.info("发送文本信息"+map.get("Content"));

                // 封装文本返回消息
                TextMessage textMessage = new TextMessage();
                textMessage.setToUserName(fromUserName);
                textMessage.setFromUserName(toUserName);
                textMessage.setCreateTime(System.currentTimeMillis() / 1000);
                textMessage.setContent("您发送的是文本消息");
                textMessage.getMsgType();
                textMessage.setMsgId(Long.valueOf(map.get("MsgId")));
                respXml = ReplyMessageUtil.sendTextMessage(textMessage);

                //用map集合封装
//                replyMap.put("MsgType", MessageType.RESP_MESSAGE_TYPE_TEXT);
//                replyMap.put("Content", "您发送的是文本消息");
//                replyMap.put("MsgId", map.get("MsgId"));
//                respXml = XmlUtil.xmlFormat(replyMap, true);
            } else if (MsgType.equals(MessageType.IMAGE_MESSAGE)) {
                // 这里回复图片 或者图文消息 以图文消息为例
                NewsOutputMessage message = new NewsOutputMessage();
                message.setMsgId(Long.valueOf(map.get("MsgId")));
                message.setToUserName(fromUserName);
                message.setFromUserName(toUserName);
                message.setCreateTime(System.currentTimeMillis() / 1000);
                message.getMsgType();

                Articles article = new Articles();
                // 图文消息的描述
                article.setDescription("图文消息 ");
                // 图文消息图片地址
                article.setPicUrl("https://p4.ssl.cdn.btime.com/dmfd/192_108_/t019d0b65e33000f8a0.jpg?size=458x240");
                // 图文消息标题
                article.setTitle("图文消息 ");
                // 图文 url 链接
                article.setUrl("http://www.baidu.com");
                List<Articles> list = new ArrayList<>();
                // 这里发送的是单图文，如果需要发送多图文则在这里 list 中加入多个Articles！
                list.add(article);

                message.setArticleCount(list.size());
                message.setArticles(list);
                respXml = ReplyMessageUtil.sendImageTextMessage(message);
            } else if (MsgType.equals(MessageType.VOICE_MESSAGE)) {
                // 以下方式根据需要来操作
                replyMap.put("MsgType", MessageType.RESP_MESSAGE_TYPE_TEXT);
                replyMap.put("Content", "您发送的是语音消息");
                replyMap.put("MsgId", map.get("MsgId"));
                respXml = XmlUtil.xmlFormat(replyMap, true);
            } else if (MsgType.equals(MessageType.VIDEO_MESSAGE)) {
                replyMap.put("MsgType", MessageType.RESP_MESSAGE_TYPE_TEXT);
                replyMap.put("Content", "您发送的是视频消息");
                replyMap.put("MsgId", map.get("MsgId"));
                respXml = XmlUtil.xmlFormat(replyMap, true);
            } else if (MsgType.equals(MessageType.SHORTVIDEO_MESSAGE)) {
                replyMap.put("MsgType", MessageType.RESP_MESSAGE_TYPE_TEXT);
                replyMap.put("Content", "您发送的是小视频消息");
                replyMap.put("MsgId", map.get("MsgId"));
                respXml = XmlUtil.xmlFormat(replyMap, true);
            } else if (MsgType.equals(MessageType.POSOTION_MESSAGE)) {
                replyMap.put("MsgType", MessageType.RESP_MESSAGE_TYPE_TEXT);
                replyMap.put("Content", "您发送的是地理位置消息");
                replyMap.put("MsgId", map.get("MsgId"));
                respXml = XmlUtil.xmlFormat(replyMap, true);
            } else if (MsgType.equals(MessageType.LINK_MESSAGE)) {
                replyMap.put("MsgType", MessageType.RESP_MESSAGE_TYPE_TEXT);
                replyMap.put("Content", "您发送的是链接消息");
                replyMap.put("MsgId", map.get("MsgId"));
                respXml = XmlUtil.xmlFormat(replyMap, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respXml;
    }

    /**
     * 事件消息业务分发
     *
     * @param map
     * @return
     */
    public static String parseEvent(Map<String, String> map) {
        String respXml = null;
        try {
            // 发送方帐号
            String fromUserName = map.get("FromUserName");
            // 开发者微信号
            String toUserName = map.get("ToUserName");
            // 取得消息类型
            String msgType = map.get("MsgType");
            //获取事件类型
            String eventType = map.get("Event");

            // 发现直接把要返回的信息直接封装成replyMap集合，然后转换成 xml文件，是不是实体类可以不用了
            Map<String, String> replyMap = new HashMap<>();
            replyMap.put("ToUserName", fromUserName);
            replyMap.put("FromUserName", toUserName);
            replyMap.put("CreateTime", String.valueOf(System.currentTimeMillis() / 1000));
            if (eventType.equals(MessageType.EVENT_TYPE_SUBSCRIBE)) {
                // 关注
                // 用map集合封装
                replyMap.put("MsgType", MessageType.RESP_MESSAGE_TYPE_TEXT);
//                replyMap.put("Content", MessageType.menuText());
                respXml = XmlUtil.xmlFormat(replyMap, true);
            }
            if (eventType.equals(MessageType.EVENT_TYPE_UNSUBSCRIBE)) {
                // 取消关注

            }
            if (eventType.equals(MessageType.EVENT_TYPE_SCAN)) {
                // 用户已关注时的扫描带参数二维码

            }
            if (eventType.equals(MessageType.EVENT_TYPE_LOCATION)) {
                // 上报地理位置

            }
            if (eventType.equals(MessageType.EVENT_TYPE_CLICK)) {
                // 自定义菜单

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respXml;
    }
}