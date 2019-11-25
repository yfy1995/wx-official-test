package xin.dayukeji.wxofficial.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xin.dayukeji.wxofficial.entity.wechat.AccessToken;
import xin.dayukeji.wxofficial.entity.wechat.QrCode;
import xin.dayukeji.wxofficial.entity.wechat.QrCodeInfo;
import xin.dayukeji.wxofficial.util.WeixinUtil;
import xin.dayukeji.wxofficial.util.qrcode.QRCodeUtil;

/**
 * @Author: yfy
 * @Date: 2019-11-25 16:37
 * @Version 1.0
 * @description 二维码管理器类
 */
public class QrCodeManager {
    private static Logger log = LoggerFactory.getLogger(QrCodeManager.class);

    public static void main(String[] args) {
        // 第三方用户唯一凭证
        String appId = "wx33e5389d1a2cde19";
        // 第三方用户唯一凭证密钥
        String appSecret = "fa132a5549b1ca9dc92081e752d12340";
        // 调用接口获取access_token
        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

        QrCodeInfo qrCode = WeixinUtil.createQrCode(getQrCode(), at.getAccessToken());
        if (qrCode != null) {
            String url = qrCode.getUrl();
            try {
                QRCodeUtil.encode(url, "/Users/apple/test.jpg");
            } catch (Exception e) {
                log.info("生成二维码失败:" + e);
            }
        }
    }

    private static QrCode getQrCode() {
        QrCode qrCode = new QrCode();
        qrCode.setAction_name("QR_LIMIT_STR_SCENE");
        QrCode.SceneInfo sceneInfo = new QrCode.SceneInfo();
        QrCode.Scene scene = new QrCode.Scene();
        scene.setScene_str("123");
        sceneInfo.setScene(scene);
        qrCode.setAction_info(sceneInfo);

        return qrCode;
    }
}
