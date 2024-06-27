package tech.saas.driver.user.api.rest.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tech.saas.driver.user.api.model.UserMobileDto;
import tech.saas.driver.user.api.model.UserMobileDtoMapper;
import tech.saas.driver.user.api.rest.UserPresenter;
import tech.saas.driver.user.core.uc.GetUsersUseCase;

import java.util.List;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Component
public class UserPresenterImpl implements UserPresenter {

    private final GetUsersUseCase getUsersInteractor;
    private final UserMobileDtoMapper mapper;

    @Override
    public List<UserMobileDto> listAll() {
        return StreamSupport.stream(
            mapper.toDtos(getUsersInteractor.listAll()).spliterator(), false).toList();
    }
}
