package tech.saas.driver.user.core;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

/**
 * Абстрактный класс для тестирования Spring-контекста.
 * <p>Наследуемые классы будут запускать Spring-контекст при запуске тестов.</p>
 */
@SpringBootTest(classes = BasicSpringTest.class)
@SpringBootConfiguration
@ComponentScan()
@EnableAutoConfiguration
@EnableConfigurationProperties
public abstract class BasicSpringTest {
}
