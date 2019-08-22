package life.majiang.community.controller;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.entity.User;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hp on 2019/8/21 20:49
 */
@Controller
public class ProfileController {


    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action, Model model, HttpServletRequest request,
                          @RequestParam(name = "pn", required = false, defaultValue = "1") Integer pn,
                          @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "我的回复");
        }
        PaginationDTO<QuestionDTO> paginationDTO = questionService.list(user.getId(), pn, size);
        model.addAttribute("paginationDTO",paginationDTO);
        return "profile";
    }
}
