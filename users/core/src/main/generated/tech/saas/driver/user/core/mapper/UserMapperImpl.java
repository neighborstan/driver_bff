package tech.saas.driver.user.core.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tech.saas.driver.common.core.user.UserDomain;
import tech.saas.driver.user.core.entity.UserEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-21T20:07:59+0300",
    comments = "version: 1.5.0.Final, compiler: javac, environment: Java 21.0.3 (BellSoft)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toEntity(UserDomain userDomain) {
        if ( userDomain == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUserUID( userDomain.getUserUID() );
        userEntity.setUserFullName( userDomain.getUserFullName() );
        userEntity.setRole( userDomain.getRole() );
        userEntity.setPhoneNumber( userDomain.getPhoneNumber() );
        userEntity.setAccess( userDomain.isAccess() );
        userEntity.setDeleted( userDomain.isDeleted() );
        userEntity.setSystemSource( userDomain.getSystemSource() );

        return userEntity;
    }
}
