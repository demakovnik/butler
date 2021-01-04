package org.inofttech.butler.dao;





import org.inofttech.butler.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository // не является необходимым
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    public List<Employee> findAllByName(String name);

}
