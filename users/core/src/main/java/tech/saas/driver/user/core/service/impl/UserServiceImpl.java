package tech.saas.driver.user.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.saas.driver.common.core.domain.UserDomain;
import tech.saas.driver.user.core.entity.UserEntity;
import tech.saas.driver.user.core.mapper.UserMapper;
import tech.saas.driver.user.core.repository.UserRepository;
import tech.saas.driver.user.core.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void saveUser(UserDomain userDomain) {
        UserEntity userEntity = userMapper.toEntity(userDomain);
        userRepository.save(userEntity);
    }
}
