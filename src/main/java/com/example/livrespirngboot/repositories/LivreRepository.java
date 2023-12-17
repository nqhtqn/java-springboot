package com.example.livrespirngboot.repositories;

import com.example.livrespirngboot.entity.LivreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreRepository extends CrudRepository<LivreEntity, Long> {
}
