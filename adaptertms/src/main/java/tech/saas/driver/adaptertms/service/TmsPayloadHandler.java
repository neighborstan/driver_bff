package tech.saas.driver.adaptertms.service;

import tech.saas.driver.adaptertms.entity.tms.payload.TmsPayload;

/**
 * Интерфейс для обработчика сущностей из TMS
*/
public interface TmsPayloadHandler<T extends TmsPayload> {

    void handle(T payload);

    Class<T> getTmsPayloadType();
}
