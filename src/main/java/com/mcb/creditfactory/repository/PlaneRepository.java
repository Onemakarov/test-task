package com.mcb.creditfactory.repository;

import com.mcb.creditfactory.model.Plane;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneRepository extends CrudRepository<Plane, Long> {
}
