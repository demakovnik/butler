package org.inofttech.butler.dao;


import org.inofttech.butler.dao.common.CommonRepository;
import org.inofttech.butler.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CommonRepository<User> {

    User findByUsername(String username);

}
