package com.ahadi.concurrent_banking_system.config.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

//@RestController
public abstract class BaseController<Model,Entity>{

    public Logger logger = LoggerFactory.getLogger(BaseController.class);


    BaseService<Model,Entity> baseService;


    public BaseController(BaseService<Model,Entity> baseService) {
        this.baseService = baseService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseModel create(@RequestBody @Valid Model model) throws BusinessException {
        Model res = baseService.create(model);
        return new ResponseModel(res);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseModel update(@RequestBody @Valid Model model) throws BusinessException {
        Model res = baseService.update(model);
        return new ResponseModel(res);
    }

    @RequestMapping(value = "/batchUpdate", method = RequestMethod.PATCH)
    public ResponseModel update(@RequestBody @Valid List<Model> modelList) throws BusinessException {
        List<Model> res = baseService.batchUpdate(modelList);
        return new ResponseModel(res);
    }

    @RequestMapping(value = "/batchCreate", method = RequestMethod.PATCH)
    public ResponseModel batchCreate(@RequestBody @Valid List<Model> modelList) throws BusinessException {
        List<Model> res = baseService.batchCreate(modelList);
        return new ResponseModel(res);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseModel get(@RequestParam @Valid Long id) throws BusinessException {
        Model res = baseService.findById(id);
        return new ResponseModel(res);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseModel get() throws BusinessException {
        List<Model> res = baseService.findAll();
        return new ResponseModel(res);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseModel delete(@RequestBody @Valid Model model) throws BusinessException {
        baseService.delete(model);
        return new ResponseModel("");
    }
}
