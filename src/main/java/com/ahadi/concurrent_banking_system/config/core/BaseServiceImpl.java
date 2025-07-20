package com.ahadi.concurrent_banking_system.config.core;

import javax.validation.ConstraintViolationException;
import java.util.List;

public abstract class BaseServiceImpl<Model,Entity> implements BaseService<Model,Entity>{


    BaseValidator<Model> baseValidator;

    BaseMapper<Entity,Model> baseMapper;

    BaseRepository<Entity> baseRepository;

    public
    BaseServiceImpl(BaseValidator<Model> baseValidator, BaseMapper<Entity,Model> baseMapper, BaseRepository<Entity> baseRepository) {
        this.baseValidator = baseValidator;
        this.baseMapper = baseMapper;
        this.baseRepository = baseRepository;
    }

    @Override
    public Model create(Model model) throws BusinessException, ConstraintViolationException {
        baseValidator.createValidate(model);
        return (Model) baseMapper.convertToModel(baseRepository.save(baseMapper.convertToEntity(model)));
    }

    @Override
    public Model update(Model model) throws BusinessException,ConstraintViolationException {
        baseValidator.updateValidate(model);
        return (Model) baseMapper.convertToModel(baseRepository.save(baseMapper.convertToEntity(model)));
    }

    @Override
    public List<Model> batchUpdate(List<Model> models) throws BusinessException {
        baseValidator.batchUpdateValidate(models);
        return (List<Model>) baseMapper.convertToModels(baseRepository.saveAll(baseMapper.convertToEntities(models)));
    }

    @Override
    public List<Model> batchCreate(List<Model> models) throws BusinessException {
        baseValidator.batchCreateValidate(models);
        return (List<Model>) baseMapper.convertToModels(baseRepository.saveAll(baseMapper.convertToEntities(models)));
    }

    @Override
    public Model findById(Long id) throws BusinessException {
        baseValidator.findByIdValidate(id);
        return (Model) baseMapper.convertToModel(baseRepository.findById(id).get());
    }

    @Override
    public List<Model> findAll() throws BusinessException {
        return (List<Model>) baseMapper.convertToModels(baseRepository.findAll());
    }

    @Override
    public void delete(Model model) throws BusinessException {
        baseValidator.deleteValidate(model);
        baseRepository.delete(baseMapper.convertToEntity(model));
    }

    @Override
    public void batchDelete(List<Model> modelList) throws BusinessException {
        baseValidator.batchDeleteValidate(modelList);
        baseRepository.deleteInBatch(baseMapper.convertToEntities(modelList));
    }
}
