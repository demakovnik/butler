package org.inofttech.butler.service;



import org.inofttech.butler.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getAllEmployees();

    public List<Employee> getAllByName(String name);

    public void saveEmployee(Employee employee);

    public Employee getEmployeeById(int id);

    void deleteEmployeeById(int id);
}
