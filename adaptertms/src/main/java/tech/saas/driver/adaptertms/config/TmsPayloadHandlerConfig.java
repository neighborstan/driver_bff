package tech.saas.driver.adaptertms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.saas.driver.adaptertms.entity.tms.payload.TmsPayload;
import tech.saas.driver.adaptertms.service.TmsPayloadHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class TmsPayloadHandlerConfig {

    /*
     *  Мапа обработчиков для сущностей из TMS
     *  Map<UserData.class, UserDataTmsPayloadHandler>
     *  Map<TaskData.class, TaskDataTmsPayloadHandler>
     *  Map<...>
     */
    @Bean
    public Map<Class<? extends TmsPayload>, TmsPayloadHandler<? extends TmsPayload>> tmsPayloadHandlers(List<TmsPayloadHandler<? extends TmsPayload>> handlers) {
        return handlers.stream()
            .collect(Collectors.toMap(TmsPayloadHandler::getTmsPayloadType, handler -> handler));
    }
}

