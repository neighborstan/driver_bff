package tech.saas.driver.adaptertms.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.saas.driver.adaptertms.entity.tms.payload.TmsPayload;
import tech.saas.driver.adaptertms.exception.TmsPayloadHandlerNotFoundException;
import tech.saas.driver.adaptertms.service.AdapterTmsService;
import tech.saas.driver.adaptertms.service.TmsPayloadHandler;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdapterTmsServiceImpl implements AdapterTmsService {

    private final Map<Class<? extends TmsPayload>, TmsPayloadHandler<? extends TmsPayload>> tmsPayloadHandlers;

    @SuppressWarnings("unchecked")
    @Override
    public void sendTmsPayloadToQueue(TmsPayload tmsPayload) {
        Optional.ofNullable(tmsPayloadHandlers.get(tmsPayload.getClass()))
            .map(handler -> (TmsPayloadHandler<TmsPayload>) handler)
            .orElseThrow(() -> new TmsPayloadHandlerNotFoundException("Обработчик для типа " + tmsPayload.getClass().getSimpleName() + " не найден"))
            .handle(tmsPayload);
    }
}

