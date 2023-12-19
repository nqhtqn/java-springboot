package com.example.javaproject.repositories;

import com.example.javaproject.entity.MembreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembreRepository extends CrudRepository<MembreEntity, Long> {
}
