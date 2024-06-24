package tech.saas.driver.user.api.model;

import org.mapstruct.Mapper;
import tech.saas.driver.common.core.user.UserDomain;
import tech.saas.driver.common.scaffold.api.IMobileDtoMapper;

@Mapper
public interface UserMobileDtoMapper extends IMobileDtoMapper<UserDomain, UserMobileDto> {

}
