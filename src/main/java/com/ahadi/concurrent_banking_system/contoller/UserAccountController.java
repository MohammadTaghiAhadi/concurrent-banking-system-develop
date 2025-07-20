package com.ahadi.concurrent_banking_system.contoller;

import com.ahadi.concurrent_banking_system.config.core.BaseController;
import com.ahadi.concurrent_banking_system.entity.UserAccountEntity;
import com.ahadi.concurrent_banking_system.model.UserAccountModel;
import com.ahadi.concurrent_banking_system.service.UserAccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-account")
public class UserAccountController extends BaseController<UserAccountModel, UserAccountEntity> {


    final UserAccountService userAccountService;

    public UserAccountController( UserAccountService userAccountService) {
        super(userAccountService);
        this.userAccountService = userAccountService;
    }
}
