package com.jerusalem.system.admin.service.impl;

import com.jerusalem.system.admin.dao.UserRepository;
import com.jerusalem.system.admin.entity.User;
import com.jerusalem.system.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jerusalem
 * @since 2019-12-24
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public Page<User> listUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public User updateUser(Long id, User user) {
        User user1 = userRepository.findById(id).orElse(null);
        return userRepository.save(user1);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }
}
