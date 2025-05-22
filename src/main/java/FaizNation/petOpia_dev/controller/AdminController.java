package FaizNation.petOpia_dev.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import FaizNation.petOpia_dev.models.anjing;
import FaizNation.petOpia_dev.models.burung;
import FaizNation.petOpia_dev.models.ikan;
import FaizNation.petOpia_dev.models.kucing;
import FaizNation.petOpia_dev.models.petList;
import FaizNation.petOpia_dev.services.services;
import jakarta.servlet.http.HttpSession;

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
    public String addPet(
            @RequestParam String name,
            @RequestParam String category,
            @RequestParam double price,
            @RequestParam double discount,
            @RequestParam int stock,
            @RequestParam(required = false) String mantelBulu,
            @RequestParam(required = false) String keramahan,
            @RequestParam(required = false) String tinggi,
            @RequestParam(required = false) String latihan,
            @RequestParam(required = false) String ilmiah,
            @RequestParam(required = false) String suhuAir,
            @RequestParam(required = false) String berat,
            @RequestParam(required = false) String kiacuan,
            @RequestParam(required = false) String vaccineStatus,
            @RequestParam(required = false) String lastCheckup,
            @RequestParam MultipartFile image,
            HttpSession session) throws IOException {
        if (!isAdmin(session)) {
            return "redirect:/auth/login";
        }

        if (!image.isEmpty()) {
            String fileName = name.toLowerCase().replace(" ", "") + ".png";
            Path imagePath = Paths.get("src/main/resources/static/asset/" + fileName);
            Files.copy(image.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
        }

        petList newPet;
        LocalDate checkupDate = null;
        if (lastCheckup != null && !lastCheckup.isEmpty()) {
            checkupDate = LocalDate.parse(lastCheckup);
        } else {
            checkupDate = LocalDate.now();
        }
        if (vaccineStatus == null) vaccineStatus = "Pending";

        switch (category.toLowerCase()) {
            case "kucing":
                newPet = new kucing(name, price, stock, category, discount,
                        mantelBulu != null ? mantelBulu : "Unknown",
                        keramahan != null ? keramahan : "■■■■-",
                        checkupDate, vaccineStatus);
                services.listKucing.add((kucing) newPet);
                break;
            case "anjing":
                newPet = new anjing(name, price, stock, category, discount,
                        tinggi != null ? tinggi : "Unknown",
                        latihan != null ? latihan : "■■■■-",
                        checkupDate, vaccineStatus);
                services.listAnjing.add((anjing) newPet);
                break;
            case "burung":
                newPet = new burung(name, price, stock, category, discount,
                        berat != null ? berat : "Unknown",
                        kiacuan != null ? kiacuan : "Unknown",
                        checkupDate, vaccineStatus);
                services.listBurung.add((burung) newPet);
                break;
            case "ikan":
                newPet = new ikan(name, price, stock, category, discount,
                        ilmiah != null ? ilmiah : "Unknown",
                        suhuAir != null ? suhuAir : "Unknown",
                        checkupDate, vaccineStatus);
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
            @RequestParam(required = false) String mantelBulu,
            @RequestParam(required = false) String keramahan,
            @RequestParam(required = false) String tinggi,
            @RequestParam(required = false) String latihan,
            @RequestParam(required = false) String ilmiah,
            @RequestParam(required = false) String suhuAir,
            @RequestParam(required = false) String berat,
            @RequestParam(required = false) String kiacuan,
            @RequestParam(required = false) String vaccineStatus,
            @RequestParam(required = false) String lastCheckup,
            HttpSession session) throws IOException {
        if (!isAdmin(session)) {
            return "redirect:/auth/login";
        }

        if (image != null && !image.isEmpty()) {
            String fileName = newName.toLowerCase().replace(" ", "") + ".png";
            Path imagePath = Paths.get("src/main/resources/static/asset/" + fileName);
            Files.copy(image.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
        }

        petList pet = services.findPetByName(name);
        if (pet != null) {
            pet.setrasPet(newName);
            pet.setHargaPet(price);
            pet.setStokPet(stock);
            pet.setjenisPet(category);
            pet.setDiskonPet(discount);

            switch (category) {
                case "Kucing":
                    if (pet instanceof FaizNation.petOpia_dev.models.kucing) {
                        ((FaizNation.petOpia_dev.models.kucing) pet).setMantelBulu(mantelBulu);
                        ((FaizNation.petOpia_dev.models.kucing) pet).setKeramahan(keramahan);
                    }
                    break;
                case "Anjing":
                    if (pet instanceof FaizNation.petOpia_dev.models.anjing) {
                        ((FaizNation.petOpia_dev.models.anjing) pet).setTinggi(tinggi);
                        ((FaizNation.petOpia_dev.models.anjing) pet).setLatihan(latihan);
                    }
                    break;
                case "Ikan":
                    if (pet instanceof FaizNation.petOpia_dev.models.ikan) {
                        ((FaizNation.petOpia_dev.models.ikan) pet).setIlmiah(ilmiah);
                        ((FaizNation.petOpia_dev.models.ikan) pet).setSuhuAir(suhuAir);
                    }
                    break;
                case "Burung":
                    if (pet instanceof FaizNation.petOpia_dev.models.burung) {
                        ((FaizNation.petOpia_dev.models.burung) pet).setBerat(berat);
                        ((FaizNation.petOpia_dev.models.burung) pet).setKicauan(kiacuan);
                    }
                    break;
            }
            // Update vaccine status and last checkup if provided
            if (pet.getHealthRecord() != null) {
                if (vaccineStatus != null && !vaccineStatus.isEmpty()) {
                    pet.getHealthRecord().setVaccineStatus(vaccineStatus);
                }
                if (lastCheckup != null && !lastCheckup.isEmpty()) {
                    try {
                        pet.getHealthRecord().setLastCheckup(java.time.LocalDate.parse(lastCheckup));
                    } catch (Exception e) {
                    }
                }
            }
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
