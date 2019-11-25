package xin.dayukeji.wxofficial.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xin.dayukeji.wxofficial.entity.wechat.*;
import xin.dayukeji.wxofficial.entity.wechat.menu.Menu;
import xin.dayukeji.wxofficial.entity.wechat.template.Industry;
import xin.dayukeji.wxofficial.entity.wechat.template.MyIndustry;
import xin.dayukeji.wxofficial.entity.wechat.template.Template;
import xin.dayukeji.wxofficial.entity.wechat.template.TemplateMessage;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @Author: yfy
 * @Date: 2019-11-23 18:38
 * @Version 1.0
 * @description 公众平台通用接口工具类
 */
public class WeixinUtil {

    private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

    /**
     * 获取用户信息（GET）
     */
    public static String get_user_info = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    /**
     * 获取access_token的接口地址（GET）
     */
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    /**
     * 获取web_access_token的接口地址（GET）
     */
    public final static String web_access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=APPSECRET&code=CODE&grant_type=authorization_code";

    /**
     * 菜单创建（POST）
     */
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    /**
     * 设置所属行业（POST）
     */
    public static String set_belong_industry = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
    /**
     * 获取所属行业（GET）
     */
    public static String get_belong_industry = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";
    /**
     * 获取模板列表（GET）
     */
    public static String get_template_list = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";
    /**
     * 发送模板消息（POST）
     */
    public static String send_template_message = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
    /**
     * 生成永久二维码（POST）
     */
    public static String generate_qr_code = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";

    /**
     * 获取用户信息
     *
     * @param accessToken 有效的access_token
     * @return
     */
    public static UserInfo getUserInfo(String accessToken, String openId) {
        UserInfo userInfo = null;

        String url = get_user_info.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        JSONObject jsonObject = httpRequest(url, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                userInfo = JSONObject.toJavaObject(jsonObject, UserInfo.class);
            } catch (JSONException e) {
                // 获取token失败
                log.error("获取用户信息失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return userInfo;
    }

    /**
     * 设置所属行业
     *
     * @param industry    所属行业实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int setBelongIndustry(Industry industry, String accessToken) {
        int result = 0;
        // 拼装创建菜单的url
        String url = set_belong_industry.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonIndustry = JSONObject.toJSON(industry).toString();
        // 调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "POST", jsonIndustry);
        if (null != jsonObject) {
            if (0 != jsonObject.getInteger("errcode")) {
                result = jsonObject.getInteger("errcode");
                log.error("设置所属行业失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
            }
        }

        return result;
    }

    /**
     * 获取所属行业
     *
     * @param accessToken 有效的access_token
     * @return
     */
    public static MyIndustry getBelongIndustry(String accessToken) {
        MyIndustry myIndustry = null;

        String url = get_belong_industry.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonObject = httpRequest(url, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                myIndustry = JSONObject.toJavaObject(jsonObject, MyIndustry.class);
            } catch (JSONException e) {
                // 获取token失败
                log.error("获取所属行业失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return myIndustry;
    }

    /**
     * 获取模板列表
     *
     * @param accessToken 有效的access_token
     * @return
     */
    public static Template getTemplateList(String accessToken) {
        Template template = null;

        String url = get_template_list.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonObject = httpRequest(url, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                template = JSONObject.toJavaObject(jsonObject, Template.class);
            } catch (JSONException e) {
                // 获取token失败
                log.error("获取模板列表失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return template;
    }

    /**
     * 发送模板消息
     *
     * @param templateMessage 所属行业实例
     * @param accessToken     有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int sendTemplateMessage(TemplateMessage templateMessage, String accessToken) {
        int result = 0;
        // 拼装创建菜单的url
        String url = send_template_message.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonTemplateMessage = JSONObject.toJSON(templateMessage).toString();
        // 调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "POST", jsonTemplateMessage);
        if (null != jsonObject) {
            if (0 != jsonObject.getInteger("errcode")) {
                result = jsonObject.getInteger("errcode");
                log.error("发送模板消息失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
            }
        }

        return result;
    }

    /**
     * 创建菜单
     *
     * @param menu        菜单实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int createMenu(Menu menu, String accessToken) {
        int result = 0;
        // 拼装创建菜单的url
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.toJSON(menu).toString();
        System.out.println("jsonMenu" + jsonMenu);
        // 调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);
        if (null != jsonObject) {
            if (0 != jsonObject.getInteger("errcode")) {
                result = jsonObject.getInteger("errcode");
                log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
            }
        }

        return result;
    }

    /**
     * 创建永久二维码
     *
     * @param qrCode      二维码实例
     * @param accessToken 有效的access_token
     */
    public static QrCodeInfo createQrCode(QrCode qrCode, String accessToken) {
        QrCodeInfo qrCodeInfo = null;

        // 拼装创建菜单的url
        String url = generate_qr_code.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonQrCode = JSONObject.toJSON(qrCode).toString();
        // 调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "POST", jsonQrCode);
        if (null != jsonObject) {
            try {
                qrCodeInfo = JSONObject.toJavaObject(jsonObject, QrCodeInfo.class);
            } catch (JSONException e) {
                // 获取token失败
                log.error("创建永久二维码失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return qrCodeInfo;
    }


    /**
     * 获取access_token
     *
     * @param appid     凭证
     * @param appsecret 密钥
     * @return
     */
    public static AccessToken getAccessToken(String appid, String appsecret) {
        AccessToken accessToken = null;

        String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setAccessToken(jsonObject.getString("access_token"));
                accessToken.setExpiresin(jsonObject.getInteger("expires_in"));
            } catch (JSONException e) {
                accessToken = null;
                // 获取token失败
                log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return accessToken;
    }

    /**
     * 获取web_access_token
     *
     * @param appid     凭证
     * @param appsecret 密钥
     * @param code      票据
     * @return
     */
    public static WebAccessToken getWebAccessToken(String appid, String appsecret, String code) {
        WebAccessToken webAccessToken = null;

        String requestUrl = web_access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret).replace("CODE", code);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                webAccessToken = JSONObject.toJavaObject(jsonObject, WebAccessToken.class);
            } catch (JSONException e) {
                // 获取token失败
                log.error("获取web_access_token失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return webAccessToken;
    }


    /**
     * 描述:  发起https请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get ( key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);

            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpUrlConn.connect();
            }

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes(StandardCharsets.UTF_8));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.parseObject(buffer.toString());
        } catch (ConnectException ce) {
            log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            log.error("https request error:{}", e);
        }
        return jsonObject;
    }
}
