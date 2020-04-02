package com.jerusalem.system.admin.service.impl;

import com.jerusalem.system.admin.dao.AdminRepository;
import com.jerusalem.system.admin.entity.Admin;
import com.jerusalem.system.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jerusalem
 * @since 2019-12-24
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Transactional
    @Override
    public Admin checkAdmin(String username, String password) {
        Admin admin = adminRepository.findByUsernameAndPassword(username,password);
        return admin;
    }
}
