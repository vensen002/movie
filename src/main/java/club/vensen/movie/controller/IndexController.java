package club.vensen.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author by VENSEN
 * @Classname IndexController
 * @Description TODO()
 * @Date 2020/2/9 13:46
 */
@Controller
@RequestMapping("/manager")
public class IndexController {

    @GetMapping({"/", "/login"})
    public ModelAndView login() {
//        ModelAndView mv = new ModelAndView("management/login");
        ModelAndView mv = new ModelAndView("views/index");
        return mv;
    }

    @GetMapping("/console")
    public String console() {
        return "views/home/console";
    }

    @GetMapping("/personalpage")
    public String personalPage() {
        return "views/home/personalpage";
    }


}
