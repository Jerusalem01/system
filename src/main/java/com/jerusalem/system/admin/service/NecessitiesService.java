package com.jerusalem.system.admin.service;

import com.jerusalem.system.admin.entity.Necessities;
import com.jerusalem.system.vo.NecessitiesQuery;
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
public interface NecessitiesService {

    Page<Necessities> listNecessities(Pageable pageable);
    Page<Necessities> listNecessities(Pageable pageable, NecessitiesQuery necessitiesQuery);
    Necessities saveNecessities(Necessities necessities);
    Necessities getNecessities(Long id);
    Necessities updateNecessities(Long id,Necessities Necessities);
    void deleteNecessities(Long id);

}
