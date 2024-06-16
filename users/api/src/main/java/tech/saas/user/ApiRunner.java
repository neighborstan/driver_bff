package tech.saas.user;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import tech.saas.user.api.config.UserConfig;

@SpringBootApplication
public class ApiRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApiRunner.class, UserConfig.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
