package com.yummipizza.api.repository;

import com.yummipizza.api.domain.CustomerMessage;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CustomerMessage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerMessageRepository extends JpaRepository<CustomerMessage, Long> {
}
