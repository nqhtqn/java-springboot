package com.example.livrespirngboot.repositories;

import com.example.livrespirngboot.entity.AuteurEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuteurRepository extends CrudRepository<AuteurEntity, Long> {
}
