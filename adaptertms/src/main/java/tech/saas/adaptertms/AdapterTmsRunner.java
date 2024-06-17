package tech.saas.adaptertms;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import tech.saas.adaptertms.config.AdapterTmsConfig;

@SpringBootApplication
public class AdapterTmsRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AdapterTmsRunner.class, AdapterTmsConfig.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
