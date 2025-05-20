package FaizNation.petOpia_dev.controller;

import FaizNation.petOpia_dev.models.*;
import FaizNation.petOpia_dev.services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.Arrays;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/pets")
    public String managePets(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/auth/login";
        }

        model.addAttribute("pets", services.getAllPets());
        return "admin/pets";
    }

    @GetMapping("/pets/add")
    public String showAddPetForm(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/auth/login";
        }

        model.addAttribute("categories", Arrays.asList("Kucing", "Anjing", "Burung", "Ikan"));
        return "admin/pet-form";
    }

    @PostMapping("/pets/add")
    public String addPet(@RequestParam String name,
            @RequestParam String category,
            @RequestParam double price,
            @RequestParam double discount,
            @RequestParam int stock,
            @RequestParam MultipartFile image,
            HttpSession session) throws IOException {
        if (!isAdmin(session)) {
            return "redirect:/auth/login";
        }

        // Save image
        if (!image.isEmpty()) {
            String fileName = name.toLowerCase().replace(" ", "") + ".png";
            Path imagePath = Paths.get("src/main/resources/static/asset/" + fileName);
            Files.copy(image.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
        }

        // Create pet based on category
        petList newPet;
        switch (category.toLowerCase()) {
            case "kucing":
                newPet = new kucing(name, price, stock, category, discount, "Unknown", "■■■■-", LocalDate.now(),
                        "Pending");
                services.listKucing.add((kucing) newPet);
                break;
            case "anjing":
                newPet = new anjing(name, price, stock, category, discount, "Unknown", "■■■■-", LocalDate.now(),
                        "Pending");
                services.listAnjing.add((anjing) newPet);
                break;
            case "burung":
                newPet = new burung(name, price, stock, category, discount, "Unknown", "Unknown", LocalDate.now(),
                        "Pending");
                services.listBurung.add((burung) newPet);
                break;
            case "ikan":
                newPet = new ikan(name, price, stock, category, discount, "Unknown", "Unknown", LocalDate.now(),
                        "Pending");
                services.listIkan.add((ikan) newPet);
                break;
            default:
                throw new IllegalArgumentException("Invalid category: " + category);
        }

        return "redirect:/admin/pets";
    }

    @GetMapping("/pets/edit/{name}")
    public String showEditPetForm(@PathVariable String name, Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/auth/login";
        }

        petList pet = services.findPetByName(name);
        if (pet == null) {
            return "redirect:/admin/pets";
        }

        model.addAttribute("pet", pet);
        model.addAttribute("categories", Arrays.asList("Kucing", "Anjing", "Burung", "Ikan"));
        return "admin/pet-form";
    }

    @PostMapping("/pets/edit/{name}")
    public String editPet(@PathVariable String name,
            @RequestParam String newName,
            @RequestParam String category,
            @RequestParam double price,
            @RequestParam double discount,
            @RequestParam int stock,
            @RequestParam(required = false) MultipartFile image,
            HttpSession session) throws IOException {
        if (!isAdmin(session)) {
            return "redirect:/auth/login";
        }

        // Update image if provided
        if (image != null && !image.isEmpty()) {
            String fileName = newName.toLowerCase().replace(" ", "") + ".png";
            Path imagePath = Paths.get("src/main/resources/static/asset/" + fileName);
            Files.copy(image.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
        }

        // Update pet details
        petList pet = services.findPetByName(name);
        if (pet != null) {
            pet.setrasPet(newName);
            pet.setHargaPet(price);
            pet.setStokPet(stock);
            pet.setjenisPet(category);
            pet.setDiskonPet(discount);
        }

        return "redirect:/admin/pets";
    }

    @PostMapping("/pets/delete/{name}")
    public String deletePet(@PathVariable String name, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/auth/login";
        }

        services.deletePetByName(name);
        return "redirect:/admin/pets";
    }

    private boolean isAdmin(HttpSession session) {
        Object user = session.getAttribute("loggedInUser");
        return user != null && "admin".equals(user.toString());
    }
}
