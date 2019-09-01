package life.majiang.community.controller;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by hp on 2019/8/16 8:37
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "pn",required = false,defaultValue = "1")Integer pn,
                        @RequestParam(name = "size",required = false,defaultValue = "5")Integer size){
        PaginationDTO<QuestionDTO> pagination = questionService.list(pn,size);
        model.addAttribute("pagination",pagination);
        return "index";
    }

    @GetMapping("/index2")
    public String index2(){
        return "index2";
    }
}
