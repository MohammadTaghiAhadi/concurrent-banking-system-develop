package com.ahadi.concurrent_banking_system.contoller;

import com.ahadi.concurrent_banking_system.config.core.BaseController;
import com.ahadi.concurrent_banking_system.entity.TransferAmountEntity;
import com.ahadi.concurrent_banking_system.model.TransferAmountModel;
import com.ahadi.concurrent_banking_system.service.TransferAmountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer-amount")
public class TransferAmountController extends BaseController<TransferAmountModel, TransferAmountEntity> {


    final TransferAmountService transferAmountService;

    public TransferAmountController(TransferAmountService transferAmountService) {
        super(transferAmountService);
        this.transferAmountService = transferAmountService;
    }
}
