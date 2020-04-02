package com.jerusalem.system.admin.dao;

import com.jerusalem.system.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {

        Admin findByUsernameAndPassword(String username,String password);
}
