package xin.dayukeji.wxofficial;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import xin.dayukeji.Env;

/**
 * @ClassName P19014Env
 * @Description 你好机车项目配置类
 * @Author zhangfg
 * @Date 2019-08-20 19:41
 * @Version 1.0
 */
@Component
@Scope(scopeName = "singleton")
@ConfigurationProperties(prefix = "env-config")
@ConditionalOnMissingClass
public class WxOfficialEnv extends Env {
    private String userAppid;
    private String userSecret;
    private String accessUrl;


    public String getUserAppid() {
        return userAppid;
    }

    public void setUserAppid(String userAppid) {
        this.userAppid = userAppid;
    }

    public String getUserSecret() {
        return userSecret;
    }

    public void setUserSecret(String userSecret) {
        this.userSecret = userSecret;
    }

    public String getAccessUrl() {
        return accessUrl;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }
}
