package org.inofttech.butler.controller.common;

import org.inofttech.butler.entity.AbstractEntity;
import org.inofttech.butler.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractController<E extends AbstractEntity, S extends CommonService<E>>
        implements CommonController<E> {

    protected final S service;

    @Autowired
    protected AbstractController(S service) {
        this.service = service;
    }
}
