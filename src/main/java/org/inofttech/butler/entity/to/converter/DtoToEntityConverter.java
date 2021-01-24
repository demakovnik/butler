package org.inofttech.butler.entity.to.converter;

import org.inofttech.butler.entity.AbstractEntity;

public abstract class DtoToEntityConverter {

    public abstract AbstractEntity getEntity(Object dtoObject);
}
