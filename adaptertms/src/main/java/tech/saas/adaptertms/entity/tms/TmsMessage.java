package tech.saas.adaptertms.entity.tms;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;

@XmlRootElement(name = "tmsMessage", namespace = "http://global.dellin.ru/bus/data-receiving")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class TmsMessage {

    @XmlElement(name = "iBusData", namespace = "http://global.dellin.ru/bus/data-receiving")
    private IBusData iBusData;
}