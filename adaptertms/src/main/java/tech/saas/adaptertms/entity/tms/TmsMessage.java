package tech.saas.adaptertms.entity.tms;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;

import static tech.saas.adaptertms.entity.tms.TmsMessage.NAMESPACE;

@XmlRootElement(name = "tmsMessage", namespace = NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class TmsMessage {

    static final String NAMESPACE = "http://global.dellin.ru/bus/data-receiving";

    @XmlElement(name = "iBusData", namespace = NAMESPACE)
    private IBusData iBusData;
}