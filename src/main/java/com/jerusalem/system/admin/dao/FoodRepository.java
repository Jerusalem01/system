package com.jerusalem.system.admin.dao;

import com.jerusalem.system.admin.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food,Long> {

    Food findByNameAndManufacturerAndBrand(String name,String manufacturer,String brand);

    Page<Food> findAll(Specification<Food> foodSpecification, Pageable pageable);
}
