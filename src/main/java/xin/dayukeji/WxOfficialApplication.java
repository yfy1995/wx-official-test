package xin.dayukeji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import xin.dayukeji.wxofficial.WxOfficialEnv;
import xin.dayukeji.wxofficial.constant.CacheConstant;
import xin.dayukeji.wxofficial.constant.CustomMessageConstant;
import xin.dayukeji.wxofficial.entity.wechat.message.TextCustomMessage;
import xin.dayukeji.wxofficial.util.ApplicationContextUtils;
import xin.dayukeji.wxofficial.util.WeixinUtil;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

@EnableScheduling
@EnableCaching
@ServletComponentScan
@EnableJpaAuditing
@EnableJpaRepositories
@EnableAsync
@ImportResource(locations = {
        "classpath:druid-bean.xml"
})
@SpringBootApplication
public class WxOfficialApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(WxOfficialApplication.class);
        springApplication.addListeners(new ApplicationPidFileWriter());
        Env.ctx = springApplication.run(args);
    }

    @Bean
    public MessageListener messageListener() {
        return new TopicMessageListener();
    }

    /**
     * Redis消息监听器容器
     * 这个容器加载了RedisConnectionFactory和消息监听器
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListener messageListener) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(messageListener, new ChannelTopic("__keyevent@1__:expired"));
        return container;
    }

    public static class TopicMessageListener implements MessageListener {

        @Override
        public void onMessage(Message message, byte[] bytes) {
            if (Env.ctx != null) {
                byte[] body = message.getBody();
                String itemValue;
                itemValue = new String(body, StandardCharsets.UTF_8);
                System.out.println(itemValue);

                if (Pattern.matches(CacheConstant.ATTENTION_AFTER_SEND_CUSTOM_MESSAGE + ".*", itemValue)) {
                    String[] infos = itemValue.split(":");
                    String openId = infos[infos.length - 1];
                    //发送客服消息
                    TextCustomMessage customMessage = new TextCustomMessage();
                    customMessage.setTouser(openId);
                    customMessage.setMsgtype(CustomMessageConstant.TEXT);
                    TextCustomMessage.Text text = new TextCustomMessage.Text();
                    text.setContent("再次谢谢～～～·");
                    customMessage.setText(text);
                    WxOfficialEnv wxOfficialEnv = ApplicationContextUtils.get(WxOfficialEnv.class);
                    WeixinUtil.sendCustomMessage(customMessage, WeixinUtil.getAccessToken(wxOfficialEnv.getUserAppid(), wxOfficialEnv.getUserSecret()).getAccessToken());
                }
            }
        }
    }
}
