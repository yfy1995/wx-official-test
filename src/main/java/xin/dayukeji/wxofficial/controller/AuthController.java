package xin.dayukeji.wxofficial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xin.dayukeji.common.annotation.ExcludeInterceptor;
import xin.dayukeji.wxofficial.WxOfficialEnv;
import xin.dayukeji.wxofficial.service.WebChatService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * @Author: yfy
 * @Date: 2019-11-25 18:34
 * @Version 1.0
 * @description 描述
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private WxOfficialEnv wxOfficialEnv;
    @Autowired
    private WebChatService webChatService;

    /**
     * 模拟触发用户授权
     */
    @GetMapping("/wechat")
    @ResponseBody
    @ExcludeInterceptor
    public void GuideServlet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        /**
         * 第一步：用户同意授权，获取code:https://open.weixin.qq.com/connect/oauth2/authorize
         * ?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE
         * &state=STATE#wechat_redirect
         */
        // 授权后重定向的回调链接地址，请使用urlencode对链接进行处理（文档要求）
        String redirect_uri = URLEncoder.encode(
                wxOfficialEnv.getAccessUrl(), "UTF-8");
        // 按照文档要求拼接访问地址
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
                + wxOfficialEnv.getUserAppid()
                + "&redirect_uri="
                + redirect_uri
                + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        // 跳转到要访问的地址
        response.sendRedirect(url);
    }

    @GetMapping("/login")
    @ResponseBody
    @ExcludeInterceptor
    public void post(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String code = request.getParameter("code");
        System.out.println("终于获取到CODE了:" + code);
        /**
         * 第三步：通过code换取网页授权access_token
         */
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/xml");

            webChatService.registerByWeb(code);

            response.getWriter().write("");
        } catch (Exception ex) {
            response.getWriter().write("");
        }

    }
}
