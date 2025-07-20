package com.ahadi.concurrent_banking_system.service;

import com.ahadi.concurrent_banking_system.config.core.BaseService;
import com.ahadi.concurrent_banking_system.config.core.BusinessException;
import com.ahadi.concurrent_banking_system.entity.UserAccountEntity;
import com.ahadi.concurrent_banking_system.model.UserAccountModel;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;

@Service
public interface UserAccountService extends BaseService<UserAccountModel, UserAccountEntity>  {

    UserAccountModel findByUserAccountNumber(String userAccountNumber) throws BusinessException, ConstraintViolationException;
}
