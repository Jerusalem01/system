package com.jerusalem.system.admin.controller;


import com.jerusalem.system.admin.entity.Cosmetics;
import com.jerusalem.system.admin.service.CosmeticsService;
import com.jerusalem.system.vo.CosmeticsQuery;
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
public class CosmeticsController {

    @Autowired
    private CosmeticsService cosmeticsService;

    // 跳转到化妆品页面
    @GetMapping("/cosmetics")
    public String cosmetics(@PageableDefault(size = 8,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                            Model model){
        model.addAttribute("page",cosmeticsService.listCosmetics(pageable));
        return "admin/cosmetics";
    }

    // 跳转到新增化妆品页面
    @GetMapping("/cosmetics/input")
    public String input(Model model){
        model.addAttribute("cosmetics",new Cosmetics());
        return "admin/cosmetics-input";
    }

    // 实现搜索功能
    @PostMapping("/cosmetics/search")
    public String search(@PageableDefault(size = 8,sort = {"id"},direction = Sort.Direction.DESC)Pageable pageable,
                         CosmeticsQuery cosmeticsQuery, Model model){
        model.addAttribute("page",cosmeticsService.listCosmetics(pageable,cosmeticsQuery));
        return "admin/cosmetics";
    }

    // 实现化妆品的新增
    @PostMapping("/cosmetics")
    public String post(@Valid Cosmetics cosmetics,RedirectAttributes attributes){
        cosmeticsService.saveCosmetics(cosmetics);
        attributes.addFlashAttribute("message","新增成功");
        return "redirect:/admin/cosmetics";
    }


    // 跳转到编辑化妆品页面
    @GetMapping("/cosmetics/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("cosmetics",cosmeticsService.getCosmetics(id));
        return "admin/cosmetics-input";
    }

    // 实现化妆品的编辑
    @PostMapping("/cosmetics/{id}")
    public String editPost(@Valid Cosmetics cosmetics, BindingResult result, @PathVariable Long id, RedirectAttributes attributes){
        Cosmetics cosmetics1 = cosmeticsService. updateCosmetics(id,cosmetics);
        if (cosmetics1 == null){
            attributes.addFlashAttribute("message","编辑失败");
        }else {
            attributes.addFlashAttribute("message","编辑成功");
        }
        return "redirect:/admin/cosmetics";
    }

    // 实现化妆品的删除
    @GetMapping("/cosmetics/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        cosmeticsService.deleteCosmetics(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/cosmetics";
    }
}
