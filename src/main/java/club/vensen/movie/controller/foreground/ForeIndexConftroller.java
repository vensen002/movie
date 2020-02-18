package club.vensen.movie.controller.foreground;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author by VENSEN
 * @Classname ForeIndexConftroller
 * @Description TODO()
 * @Date 2020/2/16 18:58
 */
@Controller
public class ForeIndexConftroller {

    @GetMapping("/")
    public String index() {
        return "foreground/index";
    }
}
