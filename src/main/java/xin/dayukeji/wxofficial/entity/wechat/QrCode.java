package xin.dayukeji.wxofficial.entity.wechat;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-25 16:32
 * @Version 1.0
 * @description 二维码实体类
 */
@Data
public class QrCode {
    /**
     * 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒
     */
    private Integer expire_seconds;
    /**
     * 二维码类型，QR_SCENE为临时的整型参数值，QR_STR_SCENE为临时的字符串参数值，
     * QR_LIMIT_SCENE为永久的整型参数值，QR_LIMIT_STR_SCENE为永久的字符串参数值
     */
    private String action_name;
    /**
     * 二维码详细信息
     */
    private SceneInfo action_info;

    @Data
    public static class SceneInfo {
        /**
         * 场景值
         */
        private Scene scene;
    }

    @Data
    public static class Scene {
        /**
         * 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
         */
        private Integer scene_id;
        /**
         * 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
         */
        private String scene_str;
    }
}
