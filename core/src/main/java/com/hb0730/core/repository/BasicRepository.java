package com.hb0730.core.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.repository.CrudRepository;
import com.hb0730.core.entity.BaseEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 基础 Repository
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/5/21
 */
public class BasicRepository<Id extends Serializable, E extends BaseEntity, M extends BaseMapper<E>> extends CrudRepository<M,
        E> implements IBasicRepository<Id, E> {

    @Override
    public E getById(Serializable id) {
        if (id == null) {
            return null;
        }
        return getBaseMapper().selectById(id);
    }

    @Override
    public List<E> listByIds(Collection<? extends Serializable> idList) {
        if (idList == null || idList.isEmpty()) {
            return List.of();
        }
        return getBaseMapper().selectBatchIds(idList);
    }

    @Override
    public List<E> listEntity() {
        return getBaseMapper().selectList(null);
    }

    @Override
    public boolean save(E entity) {
        if (entity == null) {
            return false;
        }
        return getBaseMapper().insert(entity) > 0;
    }

    @Override
    public boolean saveBatch(Collection<E> entities) {
        if (entities == null || entities.isEmpty()) {
            return false;
        }
        return saveBatch(entities, IBasicRepository.DEFAULT_BATCH_SIZE);
    }

    @Override
    public boolean updateBatchById(Collection<E> entities) {
        if (entities == null || entities.isEmpty()) {
            return false;
        }
        return updateBatchById(entities, IBasicRepository.DEFAULT_BATCH_SIZE);
    }

    @Override
    public boolean updateById(E entity) {
        if (entity == null) {
            return false;
        }
        return getBaseMapper().updateById(entity) > 0;
    }


    @Override
    public boolean deleteById(Id id) {
        return getBaseMapper().deleteById(id) > 0;
    }

    @Override
    public boolean deleteByIds(Collection<Id> ids) {
        if (ids == null || ids.isEmpty()) {
            return false;
        }
        return getBaseMapper().deleteByIds(ids) > 0;
    }

    @Override
    public boolean saveOrUpdate(E entity) {
        return super.saveOrUpdate(entity);
    }
}
