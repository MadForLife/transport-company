package org.informatics.dao;

import org.informatics.entity.VehicleType;

public interface VehicleTypeDao {
    VehicleType findById(long id);
    void save(VehicleType vehicleType);
    void update(VehicleType vehicleType);
    void delete(VehicleType vehicleType);
}
