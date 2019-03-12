package com.yukong.panda.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yukong.panda.common.base.service.impl.BaseServiceImpl;
import com.yukong.panda.user.mapper.SysDictMapper;
import com.yukong.panda.user.model.entity.SysDict;
import com.yukong.panda.user.model.query.SysDictQuery;
import com.yukong.panda.user.service.SysDictService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author yukong
 * @date 2019-01-23 10:57
 */
@Service
public class SysDictServiceImpl extends BaseServiceImpl<SysDictMapper, SysDict, SysDictQuery> implements SysDictService {

    @Override
    public List<SysDict> getTopDictList() {
       return getSubDictListByParentId(-1);
    }

    @Override
    public List<SysDict> getSubDictListByParentId(Integer parentId) {
        QueryWrapper<SysDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysDict::getParentId, parentId);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<SysDict> getDictListByQuery(SysDictQuery sysDictQuery) {
        QueryWrapper<SysDict> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .lambda()
                .eq(StringUtils.isNotBlank(sysDictQuery.getDesc()),SysDict::getDesc, sysDictQuery.getDesc())
                .eq(StringUtils.isNotBlank(sysDictQuery.getDelFlag()), SysDict::getDelFlag, sysDictQuery.getDelFlag())
                .eq(Objects.nonNull(sysDictQuery.getParentId()), SysDict::getParentId, sysDictQuery.getParentId());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteDictAndSubDict(Integer id) {
        // 先删除自己
        baseMapper.deleteById(id);
        // 删除parentId为当前id的
        QueryWrapper<SysDict> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysDict::getParentId, id);
        baseMapper.delete(wrapper);
        return true;
    }
}
