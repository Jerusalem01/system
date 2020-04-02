package com.jerusalem.system.admin.controller;


import org.springframework.stereotype.Controller;
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
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "admin/index";
    }


}
