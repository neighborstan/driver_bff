package tech.saas.driver.user.core.mapper;

import org.mapstruct.Mapper;
import tech.saas.driver.common.core.user.UserDomain;
import tech.saas.driver.common.scaffold.persistance.IEntityMapper;
import tech.saas.driver.user.core.entity.UserEntity;

@Mapper
public interface UserEntityMapper extends IEntityMapper<UserDomain, UserEntity> {

}
