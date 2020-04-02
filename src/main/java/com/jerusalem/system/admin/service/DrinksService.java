package com.jerusalem.system.admin.service;

import com.jerusalem.system.admin.entity.Drinks;
import com.jerusalem.system.vo.DrinksQuery;
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
public interface DrinksService{

    Page<Drinks> listDrinks(Pageable pageable);
    Page<Drinks> listDrinks(Pageable pageable, DrinksQuery drinksQuery);
    Drinks saveDrinks(Drinks drinks);
    Drinks getDrinks(Long id);
    Drinks updateDrinks(Long id,Drinks drinks);
    void deleteDrinks(Long id);

}
