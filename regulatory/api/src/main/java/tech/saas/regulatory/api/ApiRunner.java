package tech.saas.regulatory.api;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import tech.saas.regulatory.api.config.ApiConfig;

@SpringBootApplication
public class ApiRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApiRunner.class, ApiConfig.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
