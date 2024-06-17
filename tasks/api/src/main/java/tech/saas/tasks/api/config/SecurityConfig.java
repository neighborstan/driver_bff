package tech.saas.tasks.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import java.net.URI;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           ObjectMapper mapper,
                                           @Value("${server.error.path:${error.path:/error}}") String error) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(
                                        "/actuator/**",
                                        "/spec/**",
                                        "/error"
                                )
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(withDefaults())
                )
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.accessDeniedHandler((request, response, ex) -> {
                            var status = HttpStatus.FORBIDDEN;
                            response.setStatus(status.value());
                            var body = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
                            body.setType(URI.create("traffic:exception"));
                            body.setTitle(String.valueOf(status));

                            response.sendError(status.value(), mapper.writeValueAsString(body));
                        }).authenticationEntryPoint((request, response, ex) -> {
                            var status = HttpStatus.FORBIDDEN;
                            response.setStatus(status.value());
                            var body = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
                            body.setType(URI.create("traffic:exception"));
                            body.setTitle(String.valueOf(status));

                            response.sendError(status.value(), mapper.writeValueAsString(body));
                        })
                );

        return http.build();
    }
}
