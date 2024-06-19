package tech.saas.adaptertms.service.impl;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tech.saas.adaptertms.entity.tms.TmsMessage;
import tech.saas.adaptertms.entity.tms.payload.TmsPayload;
import tech.saas.adaptertms.entity.tms.payload.UserData;
import tech.saas.adaptertms.exception.TmsMessageParsingException;
import tech.saas.adaptertms.service.TmsMessagePayloadParser;
import tech.saas.adaptertms.entity.tms.IBusData;

import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@Slf4j
public class TmsMessagePayloadParserImpl implements TmsMessagePayloadParser {

    private static final String USER_ACCOUNT_TYPE_NAME = "typeUserPersonalADAccountAccess";

    @Override
    public TmsPayload convertBase64XmlToUserData(String xml) throws JAXBException {
        String xmlString = decodeBase64XmlMessage(xml);
        if (xmlString.isEmpty() || xmlString.isBlank()) {
            throw new TmsMessageParsingException("Получено пустое сообщение");
        }
        TmsMessage tmsMessage = processTmsMessageFromXMLString(xmlString);
        if (tmsMessage.getIBusData() == null) {
            return null;
        }
        IBusData iBusData = tmsMessage.getIBusData();

        if (StringUtils.isEmpty(iBusData.getTypeName())) {
            throw new TmsMessageParsingException("Сообщение " + iBusData.getRequestUID()
                    + " не содержит typeName и не может быть обработано");
        }

        switch (tmsMessage.getIBusData().getTypeName()) {
            case USER_ACCOUNT_TYPE_NAME -> {
                return processUserDataFromIBusData(iBusData);
            }
            default -> throw new TmsMessageParsingException("Сообщение " + iBusData.getRequestUID()
                        + " имеет недопустимый тип '" + iBusData.getMessageKind() + "'");
        }
    }

    private String decodeBase64XmlMessage(String base64Xml) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64Xml);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }

    private TmsMessage processTmsMessageFromXMLString(String xmlString) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(TmsMessage.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader reader = new StringReader(xmlString);
        return (TmsMessage) unmarshaller.unmarshal(reader);
    }

    private UserData processUserDataFromIBusData(IBusData iBusData) throws JAXBException {
        if (StringUtils.isEmpty(iBusData.getData())) {
            throw new TmsMessageParsingException("Provided TMS message doesn't contain user data");
        }
        String userDataStr = iBusData.getData();
        String cdataContent = userDataStr.substring(userDataStr.indexOf("<![CDATA[") + "<![CDATA[".length(),
                userDataStr.indexOf("]]>"));

        JAXBContext userDataContext = JAXBContext.newInstance(UserData.class);
        Unmarshaller userUnmarshaller = userDataContext.createUnmarshaller();

        StringReader userDataStringReader = new StringReader(cdataContent);

        return (UserData) userUnmarshaller.unmarshal(userDataStringReader);
    }

}
