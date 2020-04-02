package com.jerusalem.system.admin.service;

import com.jerusalem.system.admin.entity.User;
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

public interface UserService {

    Page<User> listUser(Pageable pageable);
    User saveUser(User user);
    User getUser(Long id);
    User updateUser(Long id,User user);
    void deleteUser(Long id);
}
