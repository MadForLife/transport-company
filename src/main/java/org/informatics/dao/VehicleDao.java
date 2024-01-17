package org.informatics.dao;

import org.informatics.entity.Vehicle;

public interface VehicleDao {
    Vehicle findById(long id);
    void save(Vehicle vehicle);
    void update(Vehicle vehicle);
    void delete(Vehicle vehicle);
}
