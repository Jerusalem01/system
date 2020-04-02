package com.jerusalem.system.admin.dao;

import com.jerusalem.system.admin.entity.Drinks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinksRepository extends JpaRepository<Drinks,Long> {

    Page<Drinks> findAll(Specification<Drinks> drinksSpecification, Pageable pageable);
}
