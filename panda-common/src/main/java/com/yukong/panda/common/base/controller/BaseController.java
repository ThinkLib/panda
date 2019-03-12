package com.yukong.panda.common.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yukong.panda.common.base.service.BaseService;
import com.yukong.panda.common.util.ApiResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

/**
 * 基础Controller 继承即可产生接口
 * （ 泛型：K 是 BaseService 对象，T 是实体 ，P是注解类型，Q 是查询对象
 * @author yukong
 * @date 2019-01-23 10:24
 */
public class BaseController<K extends BaseService<T, Q>, T, P extends Serializable, Q extends IPage<T>> {

    @Autowired
    private K baseService;



    @ApiOperation(value = "添加", httpMethod = "POST")
    @PostMapping
    public ApiResult<Boolean> save(@RequestBody T t){
        return new ApiResult<>(baseService.save(t));
    }

    @ApiOperation(value = "修改", httpMethod = "PUT")
    @PutMapping
    public ApiResult<Boolean> update(@RequestBody T t){
        return new ApiResult<>(baseService.updateById(t));
    }

    @ApiOperation(value = "删除", httpMethod = "DELETE")
    @DeleteMapping("/id/{id}")
    public ApiResult<Boolean> delete(@PathVariable("id") P id){
        return new ApiResult<>(baseService.removeById(id));
    }

    @ApiOperation(value = "主键查询", notes = "主键查询", httpMethod = "GET")
    @GetMapping("/id/{id}")
    public ApiResult<T> getById(@PathVariable("id") P id){
        return new ApiResult<>(baseService.getById(id));
    }

    @ApiOperation(value = "分页查询", notes = "分页查询", httpMethod = "GET")
    @GetMapping("/page")
    public ApiResult<Q> pageByQuery(Q sysRoleQuery){
        return new ApiResult<Q>(baseService.pageByQuery(sysRoleQuery));
    }

    @ApiOperation(value = "查询所有信息", notes = "查询所有信息", httpMethod = "GET")
    @GetMapping
    public ApiResult<Collection<T>> selectAll(){
        return new ApiResult<>(baseService.listByMap(new HashMap<>()));
    }


}
