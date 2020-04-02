package com.jerusalem.system.admin.controller;


import com.jerusalem.system.admin.entity.Drinks;
import com.jerusalem.system.admin.service.DrinksService;
import com.jerusalem.system.vo.DrinksQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;


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
public class DrinksController {

    @Autowired
    private DrinksService drinksService;

    // 跳转到饮料页面
    @GetMapping("/drinks")
    public String drinks(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                         Model model){
        model.addAttribute("page",drinksService.listDrinks(pageable));
        return "admin/drinks";
    }

    // 实现搜索功能
    @PostMapping("/drinks/search")
    public String search(@PageableDefault(size = 8,sort = {"id"},direction = Sort.Direction.DESC)Pageable pageable,
                         DrinksQuery drinksQuery, Model model){
        model.addAttribute("page",drinksService.listDrinks(pageable,drinksQuery));
        return "admin/drinks";
    }

    // 跳转到新增饮料页面
    @GetMapping("/drinks/input")
    public String input(Model model){
        model.addAttribute("drinks",new Drinks());
        return "admin/drinks-input";
    }

    // 实现饮料的新增
    @PostMapping("/drinks")
    public String post(@Valid Drinks drinks, RedirectAttributes attributes){
        drinksService.saveDrinks(drinks);
        attributes.addFlashAttribute("message","新增成功");
        return "redirect:/admin/drinks";
    }


    // 跳转到编辑饮料页面
    @GetMapping("/drinks/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("drinks",drinksService.getDrinks(id));
        return "admin/drinks-input";
    }

    // 实现饮料的编辑
    @PostMapping("/drinks/{id}")
    public String editPost(@Valid Drinks drinks, BindingResult result, @PathVariable Long id, RedirectAttributes attributes){
        Drinks drinks1 = drinksService.updateDrinks(id,drinks);
        if (drinks1 == null){
            attributes.addFlashAttribute("message","编辑失败");
        }else {
            attributes.addFlashAttribute("message","编辑成功");
        }
        return "redirect:/admin/drinks";
    }

    // 实现食品的删除
    @GetMapping("/drinks/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        drinksService.deleteDrinks(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/drinks";
    }
}
