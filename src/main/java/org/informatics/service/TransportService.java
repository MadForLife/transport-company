package org.informatics.service;

import org.informatics.entity.Transport;
import org.informatics.exception.EmployeeDoesNotMeetQualificationRequirements;

public interface TransportService {

    void saveTransport(Transport transport) throws EmployeeDoesNotMeetQualificationRequirements;
}
