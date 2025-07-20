package com.ahadi.concurrent_banking_system.service.impl;

import com.ahadi.concurrent_banking_system.config.core.BaseServiceImpl;
import com.ahadi.concurrent_banking_system.config.core.BusinessException;
import com.ahadi.concurrent_banking_system.entity.UserAccountEntity;
import com.ahadi.concurrent_banking_system.mapper.UserAccountMapper;
import com.ahadi.concurrent_banking_system.model.UserAccountModel;
import com.ahadi.concurrent_banking_system.repository.UserAccountRepository;
import com.ahadi.concurrent_banking_system.service.UserAccountService;
import com.ahadi.concurrent_banking_system.validator.UserAccountValidator;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

@Service
public class UserAccountServiceImpl extends BaseServiceImpl<UserAccountModel, UserAccountEntity> implements UserAccountService {

    final UserAccountRepository userAccountRepository;
    final UserAccountMapper userAccountMapper;
    final UserAccountValidator userAccountValidator;
    public UserAccountServiceImpl( UserAccountValidator userAccountValidator,UserAccountMapper userAccountMapper,UserAccountRepository userAccountRepository) {
        super(userAccountValidator, userAccountMapper, userAccountRepository);
        this.userAccountRepository = userAccountRepository;
        this.userAccountMapper = userAccountMapper;
        this.userAccountValidator = userAccountValidator;
    }

    @Override
    public UserAccountModel findByUserAccountNumber(String userAccountNumber) throws BusinessException, ConstraintViolationException {
        userAccountValidator.findByAccountNumber(userAccountNumber);
        return userAccountMapper.convertToModel(userAccountRepository.findFirstByAccountNumber(userAccountNumber));
    }

    @Override
    @Transactional
    public  synchronized  UserAccountModel update(UserAccountModel userAccountModel) throws BusinessException, ConstraintViolationException {
        return super.update(userAccountModel);
    }
}
