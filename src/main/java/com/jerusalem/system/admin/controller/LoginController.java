package com.jerusalem.system.admin.controller;

import com.jerusalem.system.admin.entity.Admin;
import com.jerusalem.system.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

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
public class LoginController {

    @Autowired
    private AdminService adminService;

    // 跳转到登录页面
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    // 实现登录功能
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession httpSession,
                        RedirectAttributes redirectAttributes) {
        Admin admin = adminService.checkAdmin(username, password);
        if (admin != null) {
            admin.setPassword(null);
            httpSession.setAttribute("admin", admin);
            return "admin/index";
        }
        else {
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:/admin";
        }
    }

    // 退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("admin");
        return "redirect:/admin";
    }
}
