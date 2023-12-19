package com.example.javaproject.repositories;

import com.example.javaproject.entity.TacheEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacheRepository extends CrudRepository<TacheEntity, Long> {
}
