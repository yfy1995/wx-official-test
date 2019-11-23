package xin.dayukeji.wxofficial.entity.pojo.output;

import lombok.Data;
import xin.dayukeji.wxofficial.util.MessageType;

import java.util.List;

/**
 * @Author: yfy
 * @Date: 2019-11-23 14:30
 * @Version 1.0
 * @description 提供了获取多条图文消息信息
 */
@Data
public class NewsOutputMessage extends BaseOutMessage {
    /**
     * 图文消息个数，限制为10条以内
     */
    private int ArticleCount;
    /**
     * 多条图文消息信息，默认第一个item为大图
     */
    private List<Articles> Articles;

    @Override
    public String getMsgType() {
        return MessageType.RESP_MESSAGE_TYPE_NEWS;
    }
}
