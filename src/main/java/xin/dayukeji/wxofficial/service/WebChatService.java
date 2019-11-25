package xin.dayukeji.wxofficial.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xin.dayukeji.wxofficial.entity.pojo.User;
import xin.dayukeji.wxofficial.entity.wechat.UserInfo;
import xin.dayukeji.wxofficial.entity.wechat.output.Articles;
import xin.dayukeji.wxofficial.entity.wechat.output.NewsOutputMessage;
import xin.dayukeji.wxofficial.entity.wechat.output.TextMessage;
import xin.dayukeji.wxofficial.repository.UserRepository;
import xin.dayukeji.wxofficial.util.MessageType;
import xin.dayukeji.wxofficial.util.ReplyMessageUtil;
import xin.dayukeji.wxofficial.util.WeixinUtil;
import xin.dayukeji.wxofficial.util.XmlUtil;

import java.sql.Timestamp;
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
@Service
@Transactional
public class WebChatService {
    private static Logger logger = LoggerFactory.getLogger(WebChatService.class);
    @Autowired
    private UserRepository userRepository;

    /**
     * 处理微信发来的请求 map 消息业务处理分发
     *
     * @param map
     * @return
     */
    public String parseMessage(Map<String, String> map) {
        String respXml = null;
        try {
            // 发送方帐号
            String fromUserName = map.get("FromUserName");
            // 开发者微信号
            String toUserName = map.get("ToUserName");
            // 取得消息类型
            String MsgType = map.get("MsgType");
            // 发现直接把要返回的信息直接封装成replyMap集合，然后转换成 xml文件，是不是实体类可以不用了
            Map<String, String> replyMap = getReplyMap(fromUserName, toUserName);
            if (MsgType.equals(MessageType.TEXT_MESSAGE)) {
                // 封装文本返回消息
                TextMessage textMessage = new TextMessage();
                textMessage.setToUserName(fromUserName);
                textMessage.setFromUserName(toUserName);
                textMessage.setCreateTime(System.currentTimeMillis() / 1000);
                textMessage.setContent("您发送的是文本消息");
                textMessage.getMsgType();
                textMessage.setMsgId(Long.valueOf(map.get("MsgId")));
                respXml = ReplyMessageUtil.sendTextMessage(textMessage);
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
            } else if (MsgType.equals(MessageType.SHORT_VIDEO_MESSAGE)) {
                replyMap.put("MsgType", MessageType.RESP_MESSAGE_TYPE_TEXT);
                replyMap.put("Content", "您发送的是小视频消息");
                replyMap.put("MsgId", map.get("MsgId"));
                respXml = XmlUtil.xmlFormat(replyMap, true);
            } else if (MsgType.equals(MessageType.POSITION_MESSAGE)) {
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

    private Map<String, String> getReplyMap(String fromUserName, String toUserName) {
        Map<String, String> replyMap = new HashMap<>();
        replyMap.put("ToUserName", fromUserName);
        replyMap.put("FromUserName", toUserName);
        replyMap.put("CreateTime", String.valueOf(System.currentTimeMillis() / 1000));
        return replyMap;
    }

    /**
     * 事件消息业务分发
     *
     * @param map
     * @return
     */
    public String parseEvent(Map<String, String> map) {
        String respXml = null;
        try {
            // 发送方帐号
            String fromUserName = map.get("FromUserName");
            // 开发者微信号
            String toUserName = map.get("ToUserName");
            //获取事件类型
            String eventType = map.get("Event");

            logger.info("eventType:" + eventType);

            // 把要返回的信息直接封装成replyMap集合，然后转换成xml文件
            Map<String, String> replyMap = getReplyMap(fromUserName, toUserName);
            if (eventType.equals(MessageType.EVENT_TYPE_SUBSCRIBE)) {
                // 关注
                // 用map集合封装
                replyMap.put("MsgType", MessageType.RESP_MESSAGE_TYPE_TEXT);
                replyMap.put("Content", "谢谢你关注我～～～");
                respXml = XmlUtil.xmlFormat(replyMap, true);
            }
            if (eventType.equals(MessageType.EVENT_TYPE_UNSUBSCRIBE)) {
                // 取消关注
                logger.info("用户取消关注了");
                return "";
            }
            if (eventType.equals(MessageType.EVENT_TYPE_SCAN)) {
                // 用户已关注时的扫描带参数二维码
                logger.info("用户已关注时的扫描带参数二维码");
                return "";
            }
            if (eventType.equals(MessageType.EVENT_TYPE_LOCATION)) {
                // 上报地理位置
                logger.info("用户上报地理位置");
                return "";
            }
            if (eventType.equals(MessageType.EVENT_SKIP_LINK)) {
                //点击菜单跳转链接
                logger.info("用户点击菜单跳转链接");
                return "";
            }
            if (eventType.equals(MessageType.EVENT_TYPE_CLICK)) {
                logger.info("进入自定义菜单逻辑");
                // 自定义菜单
                String eventKey = map.get("EventKey");

                logger.info("eventKey:" + eventKey);
                switch (eventKey) {
                    case "12":
                        replyMap.put("MsgType", MessageType.RESP_MESSAGE_TYPE_TEXT);
                        replyMap.put("Content", "公交查询～～～");
                        respXml = XmlUtil.xmlFormat(replyMap, true);
                        break;
                    case "13":
                        replyMap.put("MsgType", MessageType.RESP_MESSAGE_TYPE_TEXT);
                        replyMap.put("Content", "周边搜索～～～");
                        respXml = XmlUtil.xmlFormat(replyMap, true);
                        break;
                    case "14":
                        replyMap.put("MsgType", MessageType.RESP_MESSAGE_TYPE_TEXT);
                        replyMap.put("Content", "历史上的今天～～～");
                        respXml = XmlUtil.xmlFormat(replyMap, true);
                        break;
                    case "21":
                        replyMap.put("MsgType", MessageType.RESP_MESSAGE_TYPE_TEXT);
                        replyMap.put("Content", "java资料～～～");
                        respXml = XmlUtil.xmlFormat(replyMap, true);
                        break;
                    case "22":
                        replyMap.put("MsgType", MessageType.RESP_MESSAGE_TYPE_TEXT);
                        replyMap.put("Content", "python资料～～～");
                        respXml = XmlUtil.xmlFormat(replyMap, true);
                        break;
                    case "23":
                        replyMap.put("MsgType", MessageType.RESP_MESSAGE_TYPE_TEXT);
                        replyMap.put("Content", "架构师资料～～～");
                        respXml = XmlUtil.xmlFormat(replyMap, true);
                        break;
                    default:
                        return "";
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respXml;
    }

    /**
     * 注册用户
     *
     * @param openId
     */
    public void register(String openId) {
        String accessToken = "27_ri52KzmQi1x9gwiH41IrWlwePlY_HSf0n6EkGenGGNl-4oql2S2jroPk9YVGXjohxGvmgGcj_Qgix_fBlwF9V9Gh2-jOE8KJJ_RqJ7G25fm1PDVrwM7AEIMb_swjhCLSPr9Pdqn76At9t0UVYBUgABAQTM";
        User user = userRepository.findByOpenId(openId);
        if (user == null) {
            user = new User();
            user.setOpenId(openId);
            UserInfo userInfo = WeixinUtil.getUserInfo(accessToken, openId);
            logger.info("userInfo:" + userInfo);
            if (userInfo.getSubscribe_time() != null) {
                packUser(user, userInfo);
                userRepository.save(user);
                logger.info("新用户注册===openId:" + openId);
            }
        }
    }

    /**
     * 封装用户信息
     *
     * @param user
     * @param userInfo
     */
    private void packUser(User user, UserInfo userInfo) {
        BeanUtils.copyProperties(userInfo, user, User.class);
        user.setSubscribeTime(new Timestamp(userInfo.getSubscribe_time() * 1000));
        user.setAvatar(userInfo.getHeadimgurl());
    }
}