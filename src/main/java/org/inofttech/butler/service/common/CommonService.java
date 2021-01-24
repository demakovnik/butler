package org.inofttech.butler.service.common;

import org.inofttech.butler.entity.AbstractEntity;

import java.util.List;

public interface CommonService<E extends AbstractEntity> {

    public E save(E entity);

    public List<E> getAll();

    public E getById(long id);

    public void deleteById(long id);


}
