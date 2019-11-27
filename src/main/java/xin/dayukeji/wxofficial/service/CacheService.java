package xin.dayukeji.wxofficial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import xin.dayukeji.wxofficial.constant.CacheConstant;

import java.util.concurrent.TimeUnit;

/**
 * @Author: yfy
 * @Date: 2019-11-26 19:21
 * @Version 1.0
 * @description 描述
 */
@Service
public class CacheService {
    @Autowired
    private RedisTemplate<String, Object> template;

    /**
     * 用户关注后发送客服消息
     *
     * @param openId
     * @return
     */
    public void attentionAfterSendCustomMessage(String openId) {
        template.opsForValue().set(CacheConstant.ATTENTION_AFTER_SEND_CUSTOM_MESSAGE + openId, 1, 1, TimeUnit.SECONDS);
    }
}
