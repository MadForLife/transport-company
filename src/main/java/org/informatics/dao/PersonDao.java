package org.informatics.dao;

import org.informatics.entity.Person;

public interface PersonDao {
    Person findById(long id);
    void save(Person person);
    void update(Person person);
    void delete(Person person);
}
