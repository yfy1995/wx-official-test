package xin.dayukeji.wxofficial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import xin.dayukeji.Env;

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
}
