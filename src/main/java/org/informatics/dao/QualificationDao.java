package org.informatics.dao;

import org.informatics.entity.Qualification;

public interface QualificationDao {
    Qualification findById(long id);
    void save(Qualification qualification);
    void update(Qualification qualification);
    void delete(Qualification qualification);
}
