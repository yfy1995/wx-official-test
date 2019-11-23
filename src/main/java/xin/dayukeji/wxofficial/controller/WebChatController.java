package xin.dayukeji.wxofficial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.dayukeji.wxofficial.util.WebChatUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: yfy
 * @Date: 2019-11-23 10:59
 * @Version 1.0
 * @description 描述
 */
@RestController
@RequestMapping("/wx/test")
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
}
