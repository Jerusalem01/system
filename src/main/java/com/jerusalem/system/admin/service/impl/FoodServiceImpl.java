package com.jerusalem.system.admin.service.impl;

import com.jerusalem.system.NotFoundException;
import com.jerusalem.system.admin.dao.FoodRepository;
import com.jerusalem.system.admin.entity.Food;
import com.jerusalem.system.admin.service.FoodService;
import com.jerusalem.system.vo.FoodQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jerusalem
 * @since 2019-12-24
 */
@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Transactional
    @Override
    public Page<Food> listFood(Pageable pageable) {
        return  foodRepository.findAll(pageable);
    }

    @Override
    public Page<Food> listFood(Pageable pageable, FoodQuery foodQuery) {
        return foodRepository.findAll(new Specification<Food>() {
            @Override
            public Predicate toPredicate(Root<Food> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(foodQuery.getName()) && foodQuery.getName() != null) {
                    predicates.add(cb.like(root.<String>get("name"), "%"+foodQuery.getName()+"%"));
                }
                if (!"".equals(foodQuery.getManufacturer()) && foodQuery.getManufacturer() != null) {
                    predicates.add(cb.like(root.<String>get("manufacturer"), "%"+foodQuery.getManufacturer()+"%"));
                }
                if (!"".equals(foodQuery.getBrand()) && foodQuery.getBrand() != null) {
                    predicates.add(cb.like(root.<String>get("brand"), "%"+foodQuery.getBrand()+"%"));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Transactional
    @Override
    public Food saveFood(Food food) {
        return foodRepository.save(food);
    }

    @Transactional
    @Override
    public Food getFood(Long id) {
        return foodRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Food updateFood(Long id, Food food){
        Food f = foodRepository.findById(id).orElse(null);
        if (f == null){
            throw new NotFoundException("不存在该食品");
        }
        BeanUtils.copyProperties(food,f);
        return foodRepository.save(f);
    }

    @Transactional
    @Override
    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }

}
