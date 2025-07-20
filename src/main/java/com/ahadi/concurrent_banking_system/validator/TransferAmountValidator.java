package com.ahadi.concurrent_banking_system.validator;

import com.ahadi.concurrent_banking_system.config.core.BaseValidator;
import com.ahadi.concurrent_banking_system.config.core.BusinessException;
import com.ahadi.concurrent_banking_system.model.TransferAmountModel;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class TransferAmountValidator implements BaseValidator<TransferAmountModel> {



    @Override
    public void createValidate(TransferAmountModel model) throws BusinessException {
    }

    @Override
    public void updateValidate(TransferAmountModel model) throws BusinessException {
    }

    @Override
    public void batchUpdateValidate(List<TransferAmountModel> list) throws BusinessException {
    }

    @Override
    public void findByIdValidate(Long id) throws BusinessException {
    }

    @Override
    public void deleteValidate(TransferAmountModel model) throws BusinessException {
    }

}
