package com.yukong.panda.user.service;

import com.yukong.panda.common.base.service.BaseService;
import com.yukong.panda.user.model.entity.SysDict;
import com.yukong.panda.user.model.query.SysDictQuery;

import java.util.List;

/**
 * @author yukong
 * @date 2019-01-23 10:56
 */
public interface SysDictService extends BaseService<SysDict, SysDictQuery> {

    /**
     * 获取顶层字典集合
     * @return 返回所有parentId=-1的字典集合
     */
    List<SysDict> getTopDictList();

    /**
     * 查询所有的parentId为参数的dict集合
     * @param parentId 父id
     * @return dict集合
     */
    List<SysDict>  getSubDictListByParentId(Integer parentId);

    /**
     * 条件查询
     * @param sysDictQuery 条件
     * @return 结果集合
     */
    List<SysDict> getDictListByQuery(SysDictQuery sysDictQuery);

    /**
     * 删除id为形参的字典以及parentId为形参的字典
     * @param id id
     * @return 成功与否
     */
    Boolean deleteDictAndSubDict(Integer id);
}
