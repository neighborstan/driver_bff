package tech.saas.driver.user.api.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.saas.driver.common.core.user.UserDomain;
import tech.saas.driver.common.scaffold.api.IPresenter;
import tech.saas.driver.user.api.model.UserMobileDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public interface UsersPresenter extends IPresenter<UserDomain> {

    @GetMapping("/")
    List<UserMobileDto> listAll();
}
