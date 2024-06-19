package tech.saas.adaptertms.service;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import tech.saas.adaptertms.TestUtils;
import tech.saas.adaptertms.entity.tms.payload.TmsPayload;
import tech.saas.adaptertms.entity.tms.payload.UserData;
import tech.saas.adaptertms.service.impl.TmsMessagePayloadParserImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TmsMessagePayloadParserTest {
    TmsMessagePayloadParser userMessageParsingService = new TmsMessagePayloadParserImpl();

    private static final String TEST_MESSAGE_PATH = "service/tms_user_message.xml";

    @Test
    void should_convert_user_message_successfully() throws IOException, JAXBException {
        UserData expected = new UserData();
        expected.setUserUID("50924320-c4bd-11e1-a38d-001a649655bd");
        expected.setRole("Водитель");
        expected.setUserFullName("Гослинг Райан");
        expected.setPhoneNumber("79996661369");
        expected.setAccess(true);
        expected.setDeleted(false);

        String message = TestUtils.getJsonTestObjectAsString(TEST_MESSAGE_PATH);
        byte[] decodedBytes = Base64.getEncoder().encode(message.getBytes());
        String base64String = new String(decodedBytes, StandardCharsets.UTF_8);

        TmsPayload result = userMessageParsingService.convertBase64XmlToUserData(base64String);

        assertEquals(expected, result);
    }
}
