package tech.saas.driver.adaptertms.utils;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class CDATAAdapter extends XmlAdapter<String, String> {
    @Override
    public String marshal(String v) {
        return "<![CDATA[" + v + "]]>";
    }

    @Override
    public String unmarshal(String v) {
        return v;
    }
}
