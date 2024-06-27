package tech.saas.driver.user.core.uc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.saas.driver.common.SystemSource;
import tech.saas.driver.common.core.user.UserDomain;
import tech.saas.driver.user.core.entity.UserEntity;
import tech.saas.driver.user.core.integration.KeycloakClient;
import tech.saas.driver.user.core.mapper.UserEntityMapper;
import tech.saas.driver.user.core.repository.UserJdbcRepository;
import tech.saas.driver.user.core.repository.impl.UserJdbcPersistenceAdapter;
import tech.saas.driver.user.core.uc.impl.CreateUserUseCaseImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseTest {

    @Mock
    private KeycloakClient keycloakApiClient;

    @Mock
    private UserJdbcRepository userJdbcRepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    @InjectMocks
    private UserJdbcPersistenceAdapter userJdbcPersistenceAdapter;

    @Captor
    private ArgumentCaptor<UserDomain> userDomainCaptor;

    @Captor
    private ArgumentCaptor<UserEntity> userEntityCaptor;

    private CreateUserUseCaseImpl createUserUC;

    @BeforeEach
    void setUp() {
        createUserUC = new CreateUserUseCaseImpl(userJdbcPersistenceAdapter, keycloakApiClient);
    }

    @Test
    void testCreateUser() {
        // Arrange
        UserDomain userDomain = getUserDomain();
        UserEntity userEntity = getUserEntity();
        Mockito.when(userEntityMapper.toEntity(userDomain)).thenReturn(userEntity);
        Mockito.when(userJdbcRepository.save(userEntity)).thenReturn(userEntity);
        Mockito.when(userEntityMapper.fromEntity(userEntity)).thenReturn(userDomain);

        // Act
        createUserUC.create(userDomain);

        // Assert
        Mockito.verify(keycloakApiClient, Mockito.times(1)).create(userDomainCaptor.capture());
        Mockito.verify(userJdbcRepository, Mockito.times(1)).save(userEntityCaptor.capture());

        UserDomain capturedUserDomain = userDomainCaptor.getValue();
        UserEntity capturedUserEntity = userEntityCaptor.getValue();
        assertEquals(userDomain, capturedUserDomain);
        assertEquals(userEntity, capturedUserEntity);
    }


    private static UserEntity getUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUserUID("testuser");
        userEntity.setUserFullName("Test User");
        userEntity.setRole("user");
        userEntity.setAccess(true);
        userEntity.setDeleted(false);
        userEntity.setPhoneNumber("1234567890");
        userEntity.setSystemSource(SystemSource.TMS);
        return userEntity;
    }

    private static UserDomain getUserDomain() {
        UserDomain userDomain = new UserDomain();
        userDomain.setUserUID("testuser");
        userDomain.setUserFullName("Test User");
        userDomain.setRole("user");
        userDomain.setAccess(true);
        userDomain.setDeleted(false);
        userDomain.setPhoneNumber("1234567890");
        userDomain.setSystemSource(SystemSource.TMS);
        return userDomain;
    }
}




