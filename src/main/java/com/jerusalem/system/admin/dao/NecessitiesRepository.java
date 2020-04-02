package com.jerusalem.system.admin.dao;

import com.jerusalem.system.admin.entity.Necessities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NecessitiesRepository extends JpaRepository<Necessities,Long> {

    Page<Necessities> findAll(Specification<Necessities> necessitiesSpecification, Pageable pageable);
}
