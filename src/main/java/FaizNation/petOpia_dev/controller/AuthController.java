package FaizNation.petOpia_dev.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import FaizNation.petOpia_dev.services.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AdminService userService;

    @GetMapping("/login")
    public String loginForm() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request, Model model) {
        if (userService.login(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", username);
            if (userService.isAdmin(username)) {
                return "redirect:admin/dashboard";
            }
            return "redirect:/index";
        }
        model.addAttribute("error", "Username atau password salah!");
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/auth/login";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, Model model) {
        if (userService.register(username, password)) {
            return "redirect:/auth/login";
        }
        model.addAttribute("error", "Username sudah ada!");
        return "auth/register";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("loggedInUser");
        if (user == null || !userService.isAdmin(user.toString())) {
            return "redirect:/auth/login";
        }
        return "admin/dashboard";
    }
}