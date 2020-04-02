package com.jerusalem.system.admin.controller;

import com.jerusalem.system.admin.entity.Food;
import com.jerusalem.system.admin.entity.Necessities;
import com.jerusalem.system.admin.service.NecessitiesService;
import com.jerusalem.system.vo.NecessitiesQuery;
import javassist.NotFoundException;
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
public class NecessitiesController {

    @Autowired
    private NecessitiesService necessitiesService;

    // 跳转到日用品页面
    @GetMapping("/necessities")
    public String necessities(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                              Model model){
        model.addAttribute("page",necessitiesService.listNecessities(pageable));
        return "admin/necessities";
    }

    // 实现搜索功能
    @PostMapping("/necessities/search")
    public String search(@PageableDefault(size = 8,sort = {"id"},direction = Sort.Direction.DESC)Pageable pageable,
                         NecessitiesQuery necessitiesQuery, Model model){
        model.addAttribute("page",necessitiesService.listNecessities(pageable,necessitiesQuery));
        return "admin/necessities";
    }

    // 跳转到新增日用品页面
    @GetMapping("/necessities/input")
    public String input(Model model){
        model.addAttribute("necessities",new Necessities());
        return "admin/necessities-input";
    }

    // 实现日用品的新增
    @PostMapping("/necessities")
    public String post(@Valid Necessities necessities, RedirectAttributes attributes){
        necessitiesService.saveNecessities(necessities);
        attributes.addFlashAttribute("message","新增成功");
        return "redirect:/admin/necessities";
    }

    // 跳转到编辑日用品页面
    @GetMapping("/necessities/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("necessities",necessitiesService.getNecessities(id));
        return "admin/necessities-input";
    }

    // 实现日用品的编辑
    @PostMapping("/necessities/{id}")
    public String editPost(@Valid Necessities necessities, BindingResult result, @PathVariable Long id, RedirectAttributes attributes){
        Necessities necessities1 = necessitiesService.updateNecessities(id,necessities);
        if (necessities1 == null){
            attributes.addFlashAttribute("message","编辑失败");
        }else {
            attributes.addFlashAttribute("message","编辑成功");
        }
        return "redirect:/admin/necessities";
    }

    // 实现日用品的删除
    @GetMapping("/necessities/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        necessitiesService.deleteNecessities(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/necessities";
    }
}
