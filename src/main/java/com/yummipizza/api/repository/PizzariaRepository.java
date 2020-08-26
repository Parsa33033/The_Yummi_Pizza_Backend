package com.yummipizza.api.repository;

import com.yummipizza.api.domain.Pizzaria;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Pizzaria entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PizzariaRepository extends JpaRepository<Pizzaria, Long> {
}
