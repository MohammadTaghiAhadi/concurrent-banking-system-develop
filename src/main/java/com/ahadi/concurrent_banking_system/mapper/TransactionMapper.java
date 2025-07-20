package com.ahadi.concurrent_banking_system.mapper;


import com.ahadi.concurrent_banking_system.config.core.BaseMapper;
import com.ahadi.concurrent_banking_system.entity.TransactionEntity;
import com.ahadi.concurrent_banking_system.model.TransactionModel;
import com.ahadi.concurrent_banking_system.util.DateUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring",uses = {DateUtil.class})
public interface TransactionMapper extends BaseMapper<TransactionEntity, TransactionModel> {

    @Override
    @Mappings({
            @Mapping(source = "userAccount.accountNumber", target = "userAccountNumber"),
            @Mapping(source = "userAccount.holderName", target = "accountHolderName"),
            @Mapping(source = "userAccount.id", target = "userAccountId")
    })
    TransactionModel convertToModel(TransactionEntity entity);

    @Override
    @Mappings({
            @Mapping(source = "userAccountId", target = "userAccount.id")
    })
    TransactionEntity convertToEntity(TransactionModel model);
}
