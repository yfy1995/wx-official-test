package xin.dayukeji.wxofficial.entity.pojo.message;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-23 12:27
 * @Version 1.0
 * @description 视频消息
 */
@Data
public class VoiceMessage extends BaseMessage {
    /**
     * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据
     */
    private String MediaId;
    /**
     * 语音格式，如amr，speex等
     */
    private String Format;
    /**
     * 语音识别结果，使用UTF8编码
     */
    private String Recognition;
}
