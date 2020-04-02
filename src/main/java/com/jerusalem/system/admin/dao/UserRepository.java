package com.jerusalem.system.admin.dao;

import com.jerusalem.system.admin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

        User findByUsernameAndPassword(String username, String password);
}
