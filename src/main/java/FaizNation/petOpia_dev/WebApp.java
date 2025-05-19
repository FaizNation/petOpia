package FaizNation.petOpia_dev;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebApp {

    @GetMapping("/index")
    public String showIndex () {
        return "index";
    }

     @GetMapping("/shop")
    public String showShop () {
        return "shop/product";
    }
} 
 
 