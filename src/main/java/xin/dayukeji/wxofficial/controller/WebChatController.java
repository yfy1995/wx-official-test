package xin.dayukeji.wxofficial.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
@Slf4j
@RestController
@RequestMapping("/wx/yfy")
public class WebChatController {
    /**
     * 开发者接入验证 确认请求来自微信服务器
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping
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
    public void post(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("调用post请求");

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
            //处理输入消息，返回结果的xml
            String xml;
            if (MessageType.REQ_MESSAGE_TYPE_EVENT.equals(msgType)) {
                xml = WebChatService.parseEvent(map);
            } else {
                xml = WebChatService.parseMessage(map);
            }
            //返回封装的xml
            response.getWriter().write(xml);
        } catch (Exception ex) {
            response.getWriter().write("");
        }
    }
}
