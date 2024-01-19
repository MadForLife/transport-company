package org.informatics.dao;

import org.informatics.dto.EmployeeQualificationDto;
import org.informatics.dto.EmployeeSalaryDto;
import org.informatics.entity.Employee;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeDao {
    Employee findById(long id);
    void save(Employee employee);
    void update(Employee employee);
    void delete(Employee employee);

    List<EmployeeQualificationDto> sortByQualificationASC();
    List<EmployeeQualificationDto> filterByQualificationASC(String qualificationSignature);

    List<EmployeeSalaryDto> sortBySalaryASC();
    List<EmployeeSalaryDto> filterBySalaryGreaterOrEqualTo(BigDecimal minimumSalary);
    List<EmployeeSalaryDto> filterBySalaryLessOrEqualTo(BigDecimal minimumSalary);
}
