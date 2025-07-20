package com.ahadi.concurrent_banking_system.mapper;


import com.ahadi.concurrent_banking_system.config.core.BaseMapper;
import com.ahadi.concurrent_banking_system.entity.TransferAmountEntity;
import com.ahadi.concurrent_banking_system.model.TransferAmountModel;
import com.ahadi.concurrent_banking_system.util.DateUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring",uses = {DateUtil.class})
public interface TransferAmountMapper extends BaseMapper<TransferAmountEntity, TransferAmountModel> {

    @Override
    @Mappings({
            @Mapping(source = "depositor.id", target = "depositorId"),
            @Mapping(source = "depositor.holderName", target = "depositorHolderName"),
            @Mapping(source = "depositor.accountNumber", target = "depositorAccountNumber"),
            @Mapping(source = "withdrawer.accountNumber", target = "withdrawerAccountNumber"),
            @Mapping(source = "withdrawer.holderName", target = "withdrawerHolderName"),
            @Mapping(source = "withdrawer.id", target = "withdrawalId")
    })
    TransferAmountModel convertToModel(TransferAmountEntity entity);

    @Override
    @Mappings({
            @Mapping(source = "depositorId", target = "depositor.id"),
            @Mapping(source = "withdrawalId", target = "withdrawer.id"),
    })
    TransferAmountEntity convertToEntity(TransferAmountModel model);
}
