package org.informatics.exception;

import org.informatics.entity.Employee;

public class EmployeeDoesNotMeetQualificationRequirements extends Exception {

    private Employee employee;

    public EmployeeDoesNotMeetQualificationRequirements(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "EmployeeDoesNotMeetQualificationRequirements{" +
                "employee=" + employee +
                '}';
    }
}
