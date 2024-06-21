package tech.saas.driver.adaptertms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.saas.driver.adaptertms.entity.tms.payload.UserData;
import tech.saas.driver.common.core.domain.UserDomain;

@Mapper
public interface UserMapper {

    @Mapping(target = "systemSource", constant = "TMS")
    UserDomain toDomain(UserData userData);
}
