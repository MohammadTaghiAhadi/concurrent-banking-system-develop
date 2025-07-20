package com.ahadi.concurrent_banking_system.config.core;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BaseValidator<Model>{

    default void createValidate(Model model) throws BusinessException{}

    default void updateValidate(Model model) throws BusinessException {}

    default void batchUpdateValidate(List<Model> model) throws BusinessException {}

    default void batchCreateValidate(List<Model> model) throws BusinessException {}

    default void findByIdValidate(Long id) throws BusinessException {}

    default void deleteValidate(Model model) throws BusinessException{}

    default void batchDeleteValidate(List<Model> model) throws BusinessException{}

}
