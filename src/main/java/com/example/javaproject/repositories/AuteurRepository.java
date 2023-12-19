package com.example.javaproject.repositories;

import com.example.javaproject.entity.AuteurEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuteurRepository extends CrudRepository<AuteurEntity, Long> {
}
