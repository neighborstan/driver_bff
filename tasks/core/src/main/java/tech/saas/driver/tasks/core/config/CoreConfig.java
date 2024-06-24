package tech.saas.driver.tasks.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Configuration
@ComponentScan({
        "tech.saas.driver.tasks.core",
        "tech.saas.driver.common",
})
public class CoreConfig {

    @Bean
    public RestTemplate rest() {
        var tmp = new RestTemplate();
        tmp.setErrorHandler(
                new ResponseErrorHandler() {
                    @Override
                    public boolean hasError(ClientHttpResponse response) throws IOException {
                        return false;
                    }

                    @Override
                    public void handleError(ClientHttpResponse response) throws IOException {

                    }
                }
        );

        return tmp;
    }
}