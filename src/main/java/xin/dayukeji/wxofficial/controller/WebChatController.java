package xin.dayukeji.wxofficial.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.dayukeji.common.annotation.ExcludeInterceptor;
import xin.dayukeji.wxofficial.service.WebChatService;
import xin.dayukeji.wxofficial.util.MessageType;
import xin.dayukeji.wxofficial.util.WebChatUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @Author: yfy
 * @Date: 2019-11-23 10:59
 * @Version 1.0
 * @description 描述
 */
@RestController
@RequestMapping("/wx/yfy")
public class WebChatController {
    private Logger logger = LoggerFactory.getLogger(WebChatController.class);
    @Autowired
    private WebChatService webChatService;

    /**
     * 开发者接入验证 确认请求来自微信服务器
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping
    @ExcludeInterceptor
    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //消息来源可靠性验证
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        //成为开发者验证
        String echostr = request.getParameter("echostr");
        //确认此次GET请求来自微信服务器，原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败
        PrintWriter out = response.getWriter();
        if (WebChatUtil.checkSignature(signature, timestamp, nonce)) {
            System.out.println("=======请求校验成功======" + echostr);
            out.print(echostr);
        }
        out.close();
    }


    @PostMapping
    @ExcludeInterceptor
    public void post(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("微信调用post请求");

        //消息来源可靠性验证
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        //确认此次GET请求来自微信服务器，原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败
        if (!WebChatUtil.checkSignature(signature, timestamp, nonce)) {
            //消息不可靠，直接返回
            response.getWriter().write("");
            return;
        }


        //用户每次向公众号发送消息、或者产生自定义菜单点击事件时，响应URL将得到推送
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/xml");
            //调用parseXml方法解析请求消息
            Map<String, String> map = MessageType.parseXml(request, response);
            String msgType = map.get("MsgType");
            String openId = map.get("FromUserName");

            //判断该用户是否存在，不存在则注册
            webChatService.register(openId);

            //处理输入消息，返回结果的xml
            String xml;
            if (MessageType.REQ_MESSAGE_TYPE_EVENT.equals(msgType)) {
                xml = webChatService.parseEvent(map);
            } else {
                xml = webChatService.parseMessage(map);
            }
            //返回封装的xml
            response.getWriter().write(xml);
        } catch (Exception ex) {
            response.getWriter().write("");
        }
    }
}
