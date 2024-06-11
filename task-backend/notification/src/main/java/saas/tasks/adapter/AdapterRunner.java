package saas.tasks.notification;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import saas.tasks.notification.config.NotificationConfig;


@SpringBootApplication
public class NotificationRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(NotificationRunner.class, NotificationConfig.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

}
