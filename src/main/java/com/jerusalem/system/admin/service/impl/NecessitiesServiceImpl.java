package com.jerusalem.system.admin.service.impl;

import com.jerusalem.system.NotFoundException;
import com.jerusalem.system.admin.dao.NecessitiesRepository;
import com.jerusalem.system.admin.entity.Food;
import com.jerusalem.system.admin.entity.Necessities;
import com.jerusalem.system.admin.service.NecessitiesService;
import com.jerusalem.system.vo.NecessitiesQuery;
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
public class NecessitiesServiceImpl implements NecessitiesService {

    @Autowired
    private NecessitiesRepository necessitiesRepository;

    @Transactional
    @Override
    public Page<Necessities> listNecessities(Pageable pageable) {
        return necessitiesRepository.findAll(pageable);
    }

    @Override
    public Page<Necessities> listNecessities(Pageable pageable, NecessitiesQuery necessitiesQuery) {
        return necessitiesRepository.findAll(new Specification<Necessities>() {
            @Override
            public Predicate toPredicate(Root<Necessities> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(necessitiesQuery.getName()) && necessitiesQuery.getName() != null) {
                    predicates.add(cb.like(root.<String>get("name"), "%"+necessitiesQuery.getName()+"%"));
                }
                if (!"".equals(necessitiesQuery.getManufacturer()) && necessitiesQuery.getManufacturer() != null) {
                    predicates.add(cb.like(root.<String>get("manufacturer"), "%"+necessitiesQuery.getManufacturer()+"%"));
                }
                if (!"".equals(necessitiesQuery.getBrand()) && necessitiesQuery.getBrand() != null) {
                    predicates.add(cb.like(root.<String>get("brand"), "%"+necessitiesQuery.getBrand()+"%"));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Transactional
    @Override
    public Necessities saveNecessities(Necessities necessities) {
        return necessitiesRepository.save(necessities);
    }

    @Transactional
    @Override
    public Necessities getNecessities(Long id) {
        return necessitiesRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Necessities updateNecessities(Long id, Necessities necessities) {
        Necessities n = necessitiesRepository.findById(id).orElse(null);
        if (n == null){
            throw new NotFoundException("不存在该日用品");
        }
        BeanUtils.copyProperties(necessities,n);
        return necessitiesRepository.save(n);
    }

    @Transactional
    @Override
    public void deleteNecessities(Long id) {
        necessitiesRepository.deleteById(id);
    }
}
