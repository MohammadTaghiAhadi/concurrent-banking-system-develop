package com.ahadi.concurrent_banking_system.config.core;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Component
public interface BaseService<Model,Entity>{

    Model create(Model model) throws BusinessException,ConstraintViolationException;

    Model update(Model model) throws BusinessException,ConstraintViolationException;

    List<Model> batchCreate(List<Model> modelList) throws BusinessException;

    List<Model> batchUpdate(List<Model> modelList) throws BusinessException;

    Model findById(Long id) throws BusinessException;

    List<Model> findAll() throws BusinessException;

    void delete(Model id) throws BusinessException;

    void batchDelete(List<Model> modelList) throws BusinessException;
}
