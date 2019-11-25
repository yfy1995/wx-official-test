package xin.dayukeji.wxofficial.entity.wechat.template;

import lombok.Data;

/**
 * @Author: yfy
 * @Date: 2019-11-25 11:36
 * @Version 1.0
 * @description 公众号的所属行业
 */
@Data
public class MyIndustry {
    /**
     * 帐号设置的主营行业
     */
    private Info primary_industry;
    /**
     * 帐号设置的副营行业
     */
    private Info secondary_industry;

    @Data
    public static class Info {
        private String first_class;
        private String second_class;
    }
}
