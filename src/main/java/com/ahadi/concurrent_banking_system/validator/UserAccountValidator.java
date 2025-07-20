package com.ahadi.concurrent_banking_system.validator;

import com.ahadi.concurrent_banking_system.config.core.BaseValidator;
import com.ahadi.concurrent_banking_system.config.core.BusinessException;
import com.ahadi.concurrent_banking_system.model.UserAccountModel;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserAccountValidator implements BaseValidator<UserAccountModel> {



    @Override
    public void createValidate(UserAccountModel model) throws BusinessException {
        if (model == null)
            throw new BusinessException("data.is.null");
    }

    @Override
    public void updateValidate(UserAccountModel model) throws BusinessException {
    }

    @Override
    public void batchUpdateValidate(List<UserAccountModel> list) throws BusinessException {
    }

    @Override
    public void findByIdValidate(Long id) throws BusinessException {
    }

    @Override
    public void deleteValidate(UserAccountModel model) throws BusinessException {
    }

    public void findByAccountNumber(String accountNumber) throws BusinessException{
        if (accountNumber == null)
            throw new BusinessException("account.number.must.be.not.null");
    }

}
