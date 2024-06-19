package tech.saas.driver.adaptertms.service;

import jakarta.xml.bind.JAXBException;
import tech.saas.driver.adaptertms.entity.tms.payload.TmsPayload;

public interface TmsMessagePayloadParser {
    TmsPayload convertBase64XmlToUserData(String xmlString) throws JAXBException;
}
