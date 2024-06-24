package tech.saas.driver.adaptertms.service.impl;

import jakarta.jms.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.lang.NonNull;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import tech.saas.driver.adaptertms.entity.tms.payload.TmsPayload;
import tech.saas.driver.adaptertms.service.AdapterTmsService;
import tech.saas.driver.adaptertms.service.TmsMessagePayloadParser;

@Slf4j
@Service
@RequiredArgsConstructor
public class TmsMessageHandler {
    private static final String QUEUE_DESTINATION = "${ibm.mq.queue-destination}";

    private final TmsMessagePayloadParser tmsMessagePayloadParser;
    private final AdapterTmsService adapterTmsService;


    @JmsListener(destination = QUEUE_DESTINATION)
    public void receiveMessage(@NonNull String xmlMessage, @NonNull Session session, @Header(JmsHeaders.MESSAGE_ID) @NonNull String messageId) {
        try {
            TmsPayload tmsPayload = tmsMessagePayloadParser.convertBase64XmlToUserData(xmlMessage);
            log.info("Получено сообщение: {}", tmsPayload);

            adapterTmsService.sendTmsPayloadToQueue(tmsPayload);

            session.commit();
        } catch (Exception e) {
            handleProcessingError(session, messageId, xmlMessage, e);
        }
    }

    private void handleProcessingError(@NonNull Session session, @NonNull String messageId, @NonNull String xmlMessage, Exception e) {
        log.error("Ошибка обработки: ", e);
        try {
            log.error("Недопустимое сообщение с идентификатором {}: содержимое '{}'", messageId, xmlMessage);
            session.commit();
        } catch (Exception commitException) {
            log.error("Ошибка при фиксации неверного сообщения из Шины:", commitException);
            rollbackSession(session);
        }
    }

    private void rollbackSession(@NonNull Session session) {
        try {
            session.rollback();
        } catch (Exception rollbackException) {
            log.error("Ошибка отката сеанса обработки сообщения Шины:", rollbackException);
        }
    }
}
