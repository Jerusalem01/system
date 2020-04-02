package com.jerusalem.system.admin.controller;


import com.jerusalem.system.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jerusalem
 * @since 2019-12-24
 */
@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    // 跳转到用户管理页面
    @GetMapping("/user")
    public String user(@PageableDefault(size = 8,sort = {"id"},direction = Sort.Direction.DESC)Pageable pageable,
                       Model model){
        model.addAttribute("page",userService.listUser(pageable));
        return "admin/user";
    }
}
