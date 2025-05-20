package FaizNation.petOpia_dev.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import FaizNation.petOpia_dev.models.petList;
import FaizNation.petOpia_dev.services.services;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/shop")
public class ShopController {
    private static final List<String> categories = Arrays.asList("Kucing", "Anjing", "Burung", "Ikan");
    private final List<petList> allPets = new ArrayList<>();
    private static boolean petsInitialized = false;

    private void initializePets() {
        if (!petsInitialized) {
            services.listPet();
            allPets.clear();
            allPets.addAll(services.listKucing);
            allPets.addAll(services.listAnjing);
            allPets.addAll(services.listBurung);
            allPets.addAll(services.listIkan);
            petsInitialized = true;
        }
    }

    @GetMapping("/all-pet")
    public String allPetPage(Model model,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "category", required = false) String category,
            HttpSession session) {
        
        initializePets();
        
        List<petList> filteredPets = new ArrayList<>(allPets);
        
        if (search != null && !search.isEmpty()) {
            filteredPets = filteredPets.stream()
                    .filter(p -> p.getrasPet().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        }
        
        if (category != null && !category.isEmpty()) {
            filteredPets = filteredPets.stream()
                    .filter(p -> p.getjenisPet().equalsIgnoreCase(category))
                    .collect(Collectors.toList());
        }
        
        if ("price_desc".equals(sort)) {
            filteredPets.sort((a, b) -> Double.compare(b.getHargaPet(), a.getHargaPet()));
        } else if ("price_asc".equals(sort)) {
            filteredPets.sort(Comparator.comparingDouble(petList::getHargaPet));
        }
        
        model.addAttribute("pets", filteredPets);
        model.addAttribute("categories", categories);
        model.addAttribute("search", search);
        model.addAttribute("sort", sort);
        model.addAttribute("category", category);
        
        Object cartMessage = session.getAttribute("cartMessage");
        if (cartMessage != null) {
            model.addAttribute("cartMessage", cartMessage);
            session.removeAttribute("cartMessage");
        }
        
        return "shop/all-pet";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam String rasPet, @RequestParam int qty, HttpSession session) {
        @SuppressWarnings("unchecked")
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }
        initializePets();
        for (petList pet : allPets) {
            if (pet.getrasPet().equals(rasPet)) {
                int available = pet.getStokPet();
                int toAdd = Math.min(qty, available);
                if (toAdd > 0) {
                    pet.setStokPet(available - toAdd);
                    cart.put(rasPet, cart.getOrDefault(rasPet, 0) + toAdd);
                    session.setAttribute("cartMessage", "Berhasil menambahkan " + toAdd + " '" + rasPet + "' ke keranjang!");
                } else {
                    session.setAttribute("cartMessage", "Stok untuk '" + rasPet + "' tidak mencukupi!");
                }
                break;
            }
        }
        return "redirect:/shop/all-pet";
    }

    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        @SuppressWarnings("unchecked")
        Map<String, Integer> cartFromSession = (Map<String, Integer>) session.getAttribute("cart");
        final Map<String, Integer> cart;
        if (cartFromSession == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        } else {
            cart = cartFromSession;
        }

        initializePets();

        List<petList> cartPets = allPets.stream()
                .filter(p -> cart.containsKey(p.getrasPet()))
                .collect(Collectors.toList());

        model.addAttribute("cart", cart);
        model.addAttribute("cartPets", cartPets);
        Object cartMessage = session.getAttribute("cartMessage");
        if (cartMessage != null) {
            model.addAttribute("cartMessage", cartMessage);
            session.removeAttribute("cartMessage");
        }
        return "shop/cart";
    }

    @PostMapping("/remove-from-cart")
    public String removeFromCart(@RequestParam String rasPet, HttpSession session) {
        @SuppressWarnings("unchecked")
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        if (cart != null) {
            cart.remove(rasPet);
        }
        return "redirect:/shop/cart";
    }

    @PostMapping("/update-cart")
    public String updateCart(@RequestParam String rasPet, @RequestParam int qty, HttpSession session) {
        @SuppressWarnings("unchecked")
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        if (cart != null && qty > 0) {
            initializePets();
            for (petList pet : allPets) {
                if (pet.getrasPet().equals(rasPet)) {
                    int currentInCart = cart.getOrDefault(rasPet, 0);
                    int available = pet.getStokPet() + currentInCart; // restore previous cart qty to stock
                    int toSet = Math.min(qty, available);
                    pet.setStokPet(available - toSet);
                    cart.put(rasPet, toSet);
                    session.setAttribute("cartMessage", "Jumlah '" + rasPet + "' di keranjang diubah menjadi " + toSet + ".");
                    break;
                }
            }
        }
        return "redirect:/shop/cart";
    }

    @PostMapping("/checkout")
    public String checkout(HttpSession session) {
        @SuppressWarnings("unchecked")
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        if (cart != null) {
            initializePets();

            for (petList pet : allPets) {
                if (cart.containsKey(pet.getrasPet())) {
                    int qty = cart.get(pet.getrasPet());
                    if (pet.getStokPet() >= qty) {
                        pet.setStokPet(pet.getStokPet() - qty);
                    }
                }
            }
            session.removeAttribute("cart");
        }
        return "redirect:/shop/all-pet";
    }
}
