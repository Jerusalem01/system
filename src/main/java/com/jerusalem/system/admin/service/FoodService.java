package com.jerusalem.system.admin.service;

import com.jerusalem.system.admin.entity.Food;
import com.jerusalem.system.vo.FoodQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jerusalem
 * @since 2019-12-24
 */
public interface FoodService {

     Page<Food> listFood(Pageable pageable);
     Page<Food> listFood(Pageable pageable, FoodQuery foodQuery);
     Food saveFood(Food food);
     Food getFood(Long id);
     Food updateFood(Long id,Food food);
     void deleteFood(Long id);
}
