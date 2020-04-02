package com.jerusalem.system.admin.service.impl;

import com.jerusalem.system.NotFoundException;
import com.jerusalem.system.admin.dao.CosmeticsRepository;
import com.jerusalem.system.admin.entity.Cosmetics;
import com.jerusalem.system.admin.service.CosmeticsService;
import com.jerusalem.system.vo.CosmeticsQuery;
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
public class CosmeticsServiceImpl implements CosmeticsService {

    @Autowired
    private CosmeticsRepository cosmeticsRepository;

    @Transactional
    @Override
    public Page<Cosmetics> listCosmetics(Pageable pageable) {
        return cosmeticsRepository.findAll(pageable);
    }

    @Override
    public Page<Cosmetics> listCosmetics(Pageable pageable, CosmeticsQuery cosmeticsQuery) {
        return cosmeticsRepository.findAll(new Specification<Cosmetics>(){
            @Override
            public Predicate toPredicate(Root<Cosmetics> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(cosmeticsQuery.getName()) && cosmeticsQuery.getName() != null) {
                    predicates.add(cb.like(root.<String>get("name"), "%"+cosmeticsQuery.getName()+"%"));
                }
                if (!"".equals(cosmeticsQuery.getManufacturer()) && cosmeticsQuery.getManufacturer() != null) {
                    predicates.add(cb.like(root.<String>get("manufacturer"), "%"+cosmeticsQuery.getManufacturer()+"%"));
                }
                if (!"".equals(cosmeticsQuery.getBrand()) && cosmeticsQuery.getBrand() != null) {
                    predicates.add(cb.like(root.<String>get("brand"), "%"+cosmeticsQuery.getBrand()+"%"));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Transactional
    @Override
    public Cosmetics saveCosmetics(Cosmetics cosmetics) {
        return cosmeticsRepository.save(cosmetics);
    }

    @Transactional
    @Override
    public Cosmetics getCosmetics(Long id) {
        return cosmeticsRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Cosmetics updateCosmetics(Long id, Cosmetics cosmetics) {
        Cosmetics c = cosmeticsRepository.findById(id).orElse(null);
        if (c == null){
            throw new NotFoundException("不存在该化妆品");
        }
        BeanUtils.copyProperties(cosmetics,c);
        return cosmeticsRepository.save(c);
    }

    @Transactional
    @Override
    public void deleteCosmetics(Long id) {
        cosmeticsRepository.deleteById(id);
    }
}
