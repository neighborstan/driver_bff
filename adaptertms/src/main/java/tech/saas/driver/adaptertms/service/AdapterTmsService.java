package tech.saas.driver.adaptertms.service;

import tech.saas.driver.adaptertms.entity.tms.payload.TmsPayload;

public interface AdapterTmsService {

    void sendTmsPayloadToQueue(TmsPayload tmsPayload);
}
