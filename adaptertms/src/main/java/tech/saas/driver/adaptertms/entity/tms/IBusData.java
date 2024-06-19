package tech.saas.driver.adaptertms.entity.tms;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import tech.saas.driver.adaptertms.utils.CDATAAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IBusDataType", propOrder = {
        "requestUID",
        "messageKind",
        "sender",
        "senderDateTime",
        "typeName",
        "currentSenderApplication",
        "data"
})
@Getter
public class IBusData {

    static final String NAMESPACE = "http://global.dellin.ru/bus/data-receiving";

    @XmlElement(name = "requestUID", namespace = NAMESPACE, required = true)
    private String requestUID;

    @XmlElement(name = "messageKind", namespace = NAMESPACE, required = true)
    private String messageKind;

    @XmlElement(name = "sender", namespace = NAMESPACE, required = true)
    private String sender;

    @XmlElement(name = "senderDateTime", namespace = NAMESPACE, required = true)
    private String senderDateTime;

    @XmlElement(name = "typeName", namespace = NAMESPACE, required = true)
    private String typeName;

    @XmlElement(name = "currentSenderApplication", namespace = NAMESPACE, required = true)
    private String currentSenderApplication;

    @XmlElement(name = "data", namespace = NAMESPACE, required = true)
    @XmlJavaTypeAdapter(CDATAAdapter.class)
    private String data;
}
