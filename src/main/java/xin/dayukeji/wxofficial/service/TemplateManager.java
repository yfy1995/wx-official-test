package xin.dayukeji.wxofficial.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xin.dayukeji.wxofficial.entity.wechat.AccessToken;
import xin.dayukeji.wxofficial.entity.wechat.template.Industry;
import xin.dayukeji.wxofficial.entity.wechat.template.TemplateMessage;
import xin.dayukeji.wxofficial.entity.wechat.template.TemplateVariable;
import xin.dayukeji.wxofficial.entity.wechat.template.TestTemplateMessage;
import xin.dayukeji.wxofficial.util.WeixinUtil;

/**
 * @Author: yfy
 * @Date: 2019-11-25 11:45
 * @Version 1.0
 * @description 模板管理器类
 */
public class TemplateManager {
    private static Logger log = LoggerFactory.getLogger(TemplateManager.class);

    public static void main(String[] args) {
        // 第三方用户唯一凭证
        String appId = "wx33e5389d1a2cde19";
        // 第三方用户唯一凭证密钥
        String appSecret = "fa132a5549b1ca9dc92081e752d12340";
        // accessToken （通过接口获取获取access_token每日限制2000次）

        // 调用接口获取access_token
        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

        System.out.println(at.getAccessToken());

//        setBelongIndustry(getIndustry(), at);

//        MyIndustry myIndustry = WeixinUtil.getBelongIndustry(at.getAccessToken());
//        System.out.println(myIndustry);

//        Template template = WeixinUtil.getTemplateList(at.getAccessToken());
//        for (Template.TemplateEntity entity : template.getTemplate_list()) {
//            System.out.println(entity);
//        }

//        WeixinUtil.sendTemplateMessage(getTemplateMessage(), at.getAccessToken());

    }

    /**
     * 设置所属行业
     *
     * @param industry
     * @param at
     */
    public static void setBelongIndustry(Industry industry, AccessToken at) {
        if (null != at) {
            // 调用接口创建菜单
            int result = WeixinUtil.setBelongIndustry(industry, at.getAccessToken());

            // 判断菜单创建结果
            if (0 == result) {
                log.info("行业设置成功！");
            } else {
                log.info("行业设置失败，错误码：" + result);
            }
        }

    }

    /**
     * 组装行业数据
     *
     * @return
     */
    private static Industry getIndustry() {
        Industry industry = new Industry();
        industry.setIndustry_id1("22");
        industry.setIndustry_id2("24");
        return industry;
    }

    /**
     * 组装模板消息数据
     *
     * @return
     */
    private static TemplateMessage getTemplateMessage() {
        TemplateMessage message = new TemplateMessage();
        message.setTouser("o-LH3wuM1m07GrivgnqXmB3kU3Lc");
        message.setTemplate_id("PhMKvy6XP6V2bALNomsZrjZjRVS7fuN6ZJy_XdkZb30");
//        message.setUrl();
        TestTemplateMessage testTemplateMessage = new TestTemplateMessage();
        TemplateVariable name = new TemplateVariable();
        name.setColor("#173177");
        name.setValue("巧克力");
        testTemplateMessage.setName(name);
        TemplateVariable price = new TemplateVariable();
        price.setColor("#173177");
        price.setValue("520.0元");
        testTemplateMessage.setPrice(price);
        TemplateVariable time = new TemplateVariable();
        time.setColor("#173177");
        time.setValue("2019年5月20日");
        testTemplateMessage.setTime(time);
        message.setData(testTemplateMessage);

        return message;
    }
}
