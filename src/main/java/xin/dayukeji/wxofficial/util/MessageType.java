package xin.dayukeji.wxofficial.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yfy
 * @Date: 2019-11-23 14:05
 * @Version 1.0
 * @description 描述
 */
public class MessageType {
    /**
     * 文本消息
     */
    public static final String TEXT_MESSAGE = "text";
    /**
     * 图片消息
     */
    public static final String IMAGE_MESSAGE = "image";
    /**
     * 语音消息
     */
    public static final String VOICE_MESSAGE = "voice";
    /**
     * 视频消息
     */
    public static final String VIDEO_MESSAGE = "video";
    /**
     * 小视频消息消息
     */
    public static final String SHORT_VIDEO_MESSAGE = "shortvideo";
    /**
     * 地理位置消息
     */
    public static final String POSITION_MESSAGE = "location";
    /**
     * 链接消息
     */
    public static final String LINK_MESSAGE = "link";
    /**
     * 音乐消息
     */
    public static final String MUSIC_MESSAGE = "music";
    /**
     * 图文消息
     */
    public static final String IMAGE_TEXT_MESSAGE = "news";
    /**
     * 请求消息类型：事件推送
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";
    /**
     * 事件类型：subscribe(订阅)
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    /**
     * 事件类型：unsubscribe(取消订阅)
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "UNSUBSCRIBE";
    /**
     * 事件类型：scan(用户已关注时的扫描带参数二维码)
     */
    public static final String EVENT_TYPE_SCAN = "SCAN";
    /**
     * 事件类型：LOCATION(上报地理位置)
     */
    public static final String EVENT_TYPE_LOCATION = "LOCATION";
    /**
     * 事件类型：VIEW(点击菜单跳转链接)
     */
    public static final String EVENT_SKIP_LINK = "VIEW";
    /**
     * 事件类型：CLICK(自定义菜单)
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";
    /**
     * 响应消息类型：文本
     */
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";
    /**
     * 响应消息类型：图片
     */
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
    /**
     * 响应消息类型：语音
     */
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
    /**
     * 响应消息类型：视频
     */
    public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
    /**
     * 响应消息类型：音乐
     */
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
    /**
     * 响应消息类型：图文
     */
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Title parseXml
     * @Description 将用户的xml消息提取成map key value 类型
     */
    public static Map<String, String> parseXml(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<>();
        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        // 遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        // 释放资源
        inputStream.close();
        return map;
    }
}
