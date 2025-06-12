package com.hb0730.core.service;

import com.hb0730.core.repository.IBasicRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/5/21
 */
public class BasicService<Id extends Serializable, E extends Serializable,
        Repository extends IBasicRepository<Id, E>> implements IBasicService<Id, E> {
//    protected abstract IBasicRepository<Id, E> getRepository();

    @Autowired
    protected Repository repository;

    public Repository getRepository() {
        return repository;
    }

    @Override
    public boolean deleteById(Id id) {
        return getRepository().deleteById(id);
    }

    @Override
    public boolean deleteByIds(Collection<Id> ids) {
        return getRepository().deleteByIds(ids);
    }

    @Override
    public E getById(Id id) {
        return getRepository().getById(id);
    }

    @Override
    public List<E> listEntity() {
        return getRepository().listEntity();
    }

    @Override
    public List<E> listByIds(Collection<? extends Serializable> ids) {
        return getRepository().listByIds(ids);
    }

    @Override
    public boolean save(E entity) {
        return getRepository().save(entity);
    }

    @Override
    public boolean saveBatch(Collection<E> entities) {
        return getRepository().saveBatch(entities);
    }

    @Override
    public boolean updateById(E entity) {
        return getRepository().updateById(entity);
    }

    @Override
    public boolean updateBatchById(Collection<E> entities) {
        return getRepository().updateBatchById(entities);
    }

    @Override
    public boolean saveOrUpdate(E entity) {
        return getRepository().saveOrUpdate(entity);
    }
}
