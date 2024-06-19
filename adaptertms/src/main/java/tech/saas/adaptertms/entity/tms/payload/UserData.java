package tech.saas.adaptertms.entity.tms.payload;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

import static tech.saas.adaptertms.entity.tms.payload.UserData.NAMESPACE;

@XmlRootElement(name = "userPersonalADAccountAccess", namespace = NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class UserData implements TmsPayload {

    static final String NAMESPACE = "http://local.dellin.ru/acdc/users/2023/1";

    @XmlElement(required = true, namespace = NAMESPACE)
    private String userUID;

    @XmlElement(required = true, namespace = NAMESPACE)
    private String userFullName;

    @XmlElement(required = true, namespace = NAMESPACE)
    private String role;

    @XmlElement(required = true, namespace = NAMESPACE)
    private String phoneNumber;

    @XmlElement(required = true, namespace = NAMESPACE)
    private boolean access;

    @XmlElement(required = true, namespace = NAMESPACE)
    private boolean deleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData that = (UserData) o;
        return access == that.access && deleted == that.deleted && Objects.equals(userUID, that.userUID) && Objects.equals(userFullName, that.userFullName) && Objects.equals(role, that.role) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userUID, userFullName, role, phoneNumber, access, deleted);
    }
}
