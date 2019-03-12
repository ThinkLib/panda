package com.yukong.panda.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yukong.panda.common.base.controller.BaseController;
import com.yukong.panda.common.util.ApiResult;
import com.yukong.panda.user.model.entity.SysDict;
import com.yukong.panda.user.model.query.SysDictQuery;
import com.yukong.panda.user.service.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yukong
 * @date 2019-01-23 10:58
 */
@RestController
@RequestMapping("/dict")
@Api(value = "字典controller", tags = {"系统字典操作接口"})
public class SysDictController extends BaseController<SysDictService, SysDict, Integer, SysDictQuery> {

    @Autowired
    private SysDictService sysDictService;

    @GetMapping("/parent/{parentId}")
    @ApiOperation(value = "根据parentId查询", httpMethod = "GET")
    public ApiResult<List<SysDict>> getByParentId(@PathVariable Integer parentId){
        return new ApiResult<>(sysDictService.getSubDictListByParentId(parentId));
    }

    @GetMapping("/top")
    @ApiOperation(value = "查询顶层字典集合", httpMethod = "GET")
    public ApiResult<List<SysDict>> getTopDictList(){
        return new ApiResult<>(sysDictService.getTopDictList());
    }

    @GetMapping("/list")
    public ApiResult<List<SysDict>> getByQuery(SysDictQuery sysDictQuery) {
        return  new ApiResult<>(sysDictService.getDictListByQuery(sysDictQuery));
    }

    @DeleteMapping("/id/parent/{id}")
    public ApiResult<Boolean> deleteDictAndSubDict(@PathVariable Integer id){
        return new ApiResult<>(sysDictService.deleteDictAndSubDict(id));
    }
}
