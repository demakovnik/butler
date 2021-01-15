package org.inofttech.butler.service.common;

import org.inofttech.butler.dao.common.CommonRepository;
import org.inofttech.butler.entity.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractService<E extends AbstractEntity, R extends CommonRepository<E>>
        implements CommonService<E> {

    protected final R repository;

    @Autowired
    public AbstractService(R repository) {
        this.repository = repository;
    }



}
