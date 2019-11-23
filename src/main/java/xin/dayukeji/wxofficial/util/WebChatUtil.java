package xin.dayukeji.wxofficial.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: yfy
 * @Date: 2019-11-23 11:00
 * @Version 1.0
 * @description 描述
 */
public class WebChatUtil {
    private static final String TOKEN = "yfy_web";

    /**
     * 验证签名的方法
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        List<String> params = new ArrayList<>();
        params.add(TOKEN);
        params.add(timestamp);
        params.add(nonce);
        // 1. 将token、timestamp、nonce三个参数进行字典序排序
        Collections.sort(params, String::compareTo);
        // 2.将三个参数字符串拼接成一个字符串进行sha1加密
        String temp = SHA1.encode(params.get(0) + params.get(1) + params.get(2));
        // 3. 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return temp.equals(signature);
    }
}
