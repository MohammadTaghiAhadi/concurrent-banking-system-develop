package com.ahadi.concurrent_banking_system.validator;

import com.ahadi.concurrent_banking_system.config.core.BaseValidator;
import com.ahadi.concurrent_banking_system.config.core.BusinessException;
import com.ahadi.concurrent_banking_system.model.TransactionModel;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class TransactionValidator implements BaseValidator<TransactionModel> {



    @Override
    public void createValidate(TransactionModel model) throws BusinessException {
    }

    @Override
    public void updateValidate(TransactionModel model) throws BusinessException {
    }

    @Override
    public void batchUpdateValidate(List<TransactionModel> list) throws BusinessException {
    }

    @Override
    public void findByIdValidate(Long id) throws BusinessException {
        if (id == null)
            throw new BusinessException("transaction.id.is.null");
    }

    @Override
    public void deleteValidate(TransactionModel model) throws BusinessException {
    }

    public void findAllByUserAccountIdValidate(Long id) throws BusinessException {
        if (id == null)
            throw new BusinessException("user.account.id.is.null");
    }
}
