package com.jerusalem.system.admin.dao;

import com.jerusalem.system.admin.entity.Cosmetics;
import com.jerusalem.system.admin.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CosmeticsRepository extends JpaRepository<Cosmetics,Long> {

    Page<Cosmetics> findAll(Specification<Cosmetics> cosmeticsSpecification, Pageable pageable);
}
