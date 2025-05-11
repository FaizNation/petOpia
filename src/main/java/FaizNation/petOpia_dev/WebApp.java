package FaizNation.petOpia_dev;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebApp {

    @GetMapping("/")
    public String showIndex () {
        return "index";
    }
}