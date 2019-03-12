package com.yukong.panda.common.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yukong.panda.common.base.mapper.BaseMapper;
import com.yukong.panda.common.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * <p>
 * BaseService 实现类（ 泛型：K 是 mapper 对象，T 是实体 ，Q 是查询对象 ）
 * </p>
 * @author yukong
 * @date 2019-01-23 09:48
 */
public class BaseServiceImpl<K extends BaseMapper<T, Q>, T, Q extends IPage<T>> extends ServiceImpl<K , T> implements BaseService<T, Q> {

    @Autowired
    protected K baseMapper;

    /**
     * 分页条件查询
     * @param query
     * @return
     */
    @Override
    public Q pageByQuery(Q query) {
        return baseMapper.pageByQuery(query);
    }

}
