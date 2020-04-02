package com.jerusalem.system.admin.service.impl;

import com.jerusalem.system.NotFoundException;
import com.jerusalem.system.admin.dao.DrinksRepository;
import com.jerusalem.system.admin.entity.Drinks;
import com.jerusalem.system.admin.entity.Food;
import com.jerusalem.system.admin.service.DrinksService;
import com.jerusalem.system.vo.DrinksQuery;
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
public class DrinksServiceImpl implements DrinksService {

    @Autowired
    private DrinksRepository drinksRepository;

    @Transactional
    @Override
    public Page<Drinks> listDrinks(Pageable pageable) {
        return drinksRepository.findAll(pageable);
    }

    @Override
    public Page<Drinks> listDrinks(Pageable pageable, DrinksQuery drinksQuery) {
        return drinksRepository.findAll(new Specification<Drinks>() {
            @Override
            public Predicate toPredicate(Root<Drinks> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(drinksQuery.getName()) && drinksQuery.getName() != null) {
                    predicates.add(cb.like(root.<String>get("name"), "%"+drinksQuery.getName()+"%"));
                }
                if (!"".equals(drinksQuery.getManufacturer()) && drinksQuery.getManufacturer() != null) {
                    predicates.add(cb.like(root.<String>get("manufacturer"), "%"+drinksQuery.getManufacturer()+"%"));
                }
                if (!"".equals(drinksQuery.getBrand()) && drinksQuery.getBrand() != null) {
                    predicates.add(cb.like(root.<String>get("brand"), "%"+drinksQuery.getBrand()+"%"));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Transactional
    @Override
    public Drinks saveDrinks(Drinks drinks) {
        return drinksRepository.save(drinks);
    }

    @Transactional
    @Override
    public Drinks getDrinks(Long id) {
        return drinksRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Drinks updateDrinks(Long id, Drinks drinks) {
        Drinks d = drinksRepository.findById(id).orElse(null);
        if(d == null){
            throw new NotFoundException("不存在该饮料");
        }
        BeanUtils.copyProperties(drinks,d);
        return drinksRepository.save(d);
    }

    @Transactional
    @Override
    public void deleteDrinks(Long id) {
        drinksRepository.deleteById(id);
    }
}
