package com.jerusalem.system.admin.service;

import com.jerusalem.system.admin.entity.Cosmetics;
import com.jerusalem.system.vo.CosmeticsQuery;
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
public interface CosmeticsService {

    Page<Cosmetics> listCosmetics(Pageable pageable);
    Page<Cosmetics> listCosmetics(Pageable pageable, CosmeticsQuery cosmeticsQuery);
    Cosmetics saveCosmetics(Cosmetics cosmetics);
    Cosmetics getCosmetics(Long id);
    Cosmetics updateCosmetics(Long id,Cosmetics cosmetics);
    void deleteCosmetics(Long id);

}
