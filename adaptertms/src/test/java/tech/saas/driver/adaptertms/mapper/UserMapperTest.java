package tech.saas.driver.adaptertms.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.saas.driver.adaptertms.entity.tms.payload.UserData;
import tech.saas.driver.common.SystemSource;
import tech.saas.driver.common.core.user.UserDomain;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    void should_mapped_userData_to_userDomain_correct() {
        // Arrange
        UserData userData = new UserData();
        userData.setUserUID("testUID");
        userData.setUserFullName("Test User");
        userData.setRole("role");
        userData.setPhoneNumber("1234567890");
        userData.setAccess(true);
        userData.setDeleted(false);

        // Act
        UserDomain userDomain = userMapper.toDomain(userData);

        // Assert
        assertEquals(SystemSource.TMS, userDomain.getSystemSource());
        assertEquals(userData.getUserUID(), userDomain.getUserUID());
        assertEquals(userData.getUserFullName(), userDomain.getUserFullName());
        assertEquals(userData.getRole(), userDomain.getRole());
        assertEquals(userData.getPhoneNumber(), userDomain.getPhoneNumber());
        assertEquals(userData.isAccess(), userDomain.isAccess());
        assertEquals(userData.isDeleted(), userDomain.isDeleted());
    }
}
