package saas.tasks.adapter;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import saas.tasks.adapter.config.AdapterConfig;


@SpringBootApplication
public class AdapterRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AdapterRunner.class, AdapterConfig.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

}
