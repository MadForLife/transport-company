package org.informatics.dao;

import org.informatics.entity.Employee;

public interface EmployeeDao {
    Employee findById(long id);
    void save(Employee employee);
    void update(Employee employee);
    void delete(Employee employee);
}
