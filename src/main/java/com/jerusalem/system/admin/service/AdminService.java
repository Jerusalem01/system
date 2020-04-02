package com.jerusalem.system.admin.service;

import com.jerusalem.system.admin.entity.Admin;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jerusalem
 * @since 2019-12-24
 */
public interface AdminService {

    Admin checkAdmin(String username,String password);
}
