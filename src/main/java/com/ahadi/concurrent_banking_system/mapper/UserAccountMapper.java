package com.ahadi.concurrent_banking_system.mapper;


import com.ahadi.concurrent_banking_system.config.core.BaseMapper;
import com.ahadi.concurrent_banking_system.entity.UserAccountEntity;
import com.ahadi.concurrent_banking_system.model.UserAccountModel;
import com.ahadi.concurrent_banking_system.util.DateUtil;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {DateUtil.class})
public interface UserAccountMapper extends BaseMapper<UserAccountEntity, UserAccountModel> {
}
