package xin.dayukeji.wxofficial.entity.wechat.template;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-25 11:34
 * @Version 1.0
 * @description 所属行业
 */
@Data
public class Industry {
    /**
     * 公众号模板消息所属行业编号:主营
     */
    private String industry_id1;
    /**
     * 公众号模板消息所属行业编号:副营
     */
    private String industry_id2;
}
