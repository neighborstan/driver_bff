package tech.saas.adaptertms.service;

import jakarta.xml.bind.JAXBException;
import tech.saas.adaptertms.entity.tms.payload.TmsPayload;

public interface TmsMessagePayloadParser {
    TmsPayload convertBase64XmlToUserData(String xmlString) throws JAXBException;
}
