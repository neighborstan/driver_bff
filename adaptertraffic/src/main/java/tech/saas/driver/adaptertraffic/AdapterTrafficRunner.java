package tech.saas.driver.adaptertraffic;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import tech.saas.driver.adaptertraffic.config.AdapterTrafficConfig;

@SpringBootApplication
public class AdapterTrafficRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AdapterTrafficRunner.class, AdapterTrafficConfig.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
