package com.jerusalem.system.admin.controller;

import com.jerusalem.system.admin.entity.Food;
import com.jerusalem.system.admin.service.FoodService;
import com.jerusalem.system.vo.FoodQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
public class FoodController {

    @Autowired
    private FoodService foodService;

    // 跳转到食品管理页面
    @GetMapping("/food")
    public String food(@PageableDefault(size = 8,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                       Model model){
        model.addAttribute("page",foodService.listFood(pageable));
        return "admin/food";
    }

    // 实现搜索功能
    @PostMapping("/food/search")
    public String search(@PageableDefault(size = 8,sort = {"id"},direction = Sort.Direction.DESC)Pageable pageable,
                         FoodQuery foodQuery,Model model){
        model.addAttribute("page",foodService.listFood(pageable,foodQuery));
        return "admin/food";
    }

    // 跳转到新增食品页面
    @GetMapping("/food/input")
    public String input(Model model){
        model.addAttribute("food",new Food());
        return "admin/food-input";
    }

    // 实现食品的新增
    @PostMapping("/food")
    public String post(@Valid Food food,RedirectAttributes attributes){
        foodService.saveFood(food);
        attributes.addFlashAttribute("message","新增成功");
        return "redirect:/admin/food";
    }

    // 跳转到编辑食品页面
    @GetMapping("/food/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("food",foodService.getFood(id));
        return "admin/food-input";
    }

    // 实现食品的编辑
    @PostMapping("/food/{id}")
    public String editPost(@Valid Food food, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){
        Food food1 = foodService.updateFood(id,food);
        if (food1 == null){
            attributes.addFlashAttribute("message","编辑失败");
        }else {
            attributes.addFlashAttribute("message","编辑成功");
        }
        return "redirect:/admin/food";
    }

    // 实现食品的删除
    @GetMapping("/food/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        foodService.deleteFood(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/food";
    }
}
