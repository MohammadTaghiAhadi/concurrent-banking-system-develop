package com.ahadi.concurrent_banking_system.config.core;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public interface BaseMapper<Entity,Model> {

    Model convertToModel(Entity entity);

    Entity convertToEntity(Model model);

    default List<Model> convertToModels(List<Entity> entityList)
    {
        List<Model> modelList = new ArrayList<>();
        if (entityList != null)
        {
            entityList.forEach(entity -> {
                assert false;
                modelList.add(convertToModel(entity));
            });
        }
        return modelList;
    }

    default List<Entity> convertToEntities(List<Model> models)
    {
        List<Entity> entityList = new ArrayList<>();
        if (models != null)
        {
            models.forEach(model -> {
                assert false;
                entityList.add(convertToEntity(model));
            });
        }
        return entityList;
    }
}
