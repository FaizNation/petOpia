package FaizNation.petOpia_dev;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebApp {

    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String showIndex () {
        return "index";
    }

    @GetMapping("/shop")
    public String showShop () {
        return "shop/product"; 
    }    // Remove showAllPets method as this functionality is now in ShopController
}

