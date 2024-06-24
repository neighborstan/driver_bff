package tech.saas.driver.user.core.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.saas.driver.common.SystemSource;
import tech.saas.driver.common.core.user.UserDomain;
import tech.saas.driver.user.core.entity.UserEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserEntityMapperTest {

    private final UserEntityMapper userEntityMapper = Mappers.getMapper(UserEntityMapper.class);

    @Test
    void should_mapped_userDomain_to_userEntity_correct() {
        // Arrange
        UserDomain userDomain = new UserDomain();
        userDomain.setUserUID("testUID");
        userDomain.setUserFullName("Test User");
        userDomain.setRole("role");
        userDomain.setPhoneNumber("1234567890");
        userDomain.setAccess(true);
        userDomain.setDeleted(false);
        userDomain.setSystemSource(SystemSource.TMS);

        // Act
        UserEntity userEntity = userEntityMapper.toEntity(userDomain);

        // Assert
        assertEquals(userDomain.getUserUID(), userEntity.getUserUID());
        assertEquals(userDomain.getUserFullName(), userEntity.getUserFullName());
        assertEquals(userDomain.getRole(), userEntity.getRole());
        assertEquals(userDomain.getPhoneNumber(), userEntity.getPhoneNumber());
        assertEquals(userDomain.isAccess(), userEntity.isAccess());
        assertEquals(userDomain.isDeleted(), userEntity.isDeleted());
        assertEquals(userDomain.getSystemSource(), userEntity.getSystemSource());
    }
}
