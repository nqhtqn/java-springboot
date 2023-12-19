package com.example.javaproject.repositories;

import com.example.javaproject.entity.LivreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreRepository extends CrudRepository<LivreEntity, Long> {
}
