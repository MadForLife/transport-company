package org.informatics.service;

import org.informatics.dao.TransportDao;
import org.informatics.entity.Qualification;
import org.informatics.entity.Transport;
import org.informatics.exception.EmployeeDoesNotMeetQualificationRequirements;

import java.util.List;
import java.util.Set;

public class TransportServiceImpl implements TransportService {

    private TransportDao transportDao;

    public TransportServiceImpl(TransportDao transportDao) {
        this.transportDao = transportDao;
    }

    @Override
    public void saveTransport(Transport transport) throws EmployeeDoesNotMeetQualificationRequirements {

        Qualification requiredQualification = transport.getAssignedVehicle().getRequiredQualification();
        Set<Qualification> employeeQualifications = transport.getAssignedEmployee().getQualifications();

        if (employeeQualifications.contains(requiredQualification)) {
            transportDao.save(transport);
        } else {
            throw new EmployeeDoesNotMeetQualificationRequirements(transport.getAssignedEmployee());
        }
    }
}
