package tech.saas.user.watcher;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import tech.saas.user.watcher.config.WatcherConfig;

@SpringBootApplication
public class WatcherRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(WatcherRunner.class, WatcherConfig.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
