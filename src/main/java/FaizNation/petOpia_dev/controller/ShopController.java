package FaizNation.petOpia_dev.controller;

import FaizNation.petOpia_dev.models.petList;
import FaizNation.petOpia_dev.services.services;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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
        
        // Initialize pets list
        initializePets();
        
        // Apply filters
        List<petList> filteredPets = new ArrayList<>(allPets);
        
        // Apply search filter
        if (search != null && !search.isEmpty()) {
            filteredPets = filteredPets.stream()
                    .filter(p -> p.getrasPet().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        }
        
        // Apply category filter
        if (category != null && !category.isEmpty()) {
            filteredPets = filteredPets.stream()
                    .filter(p -> p.getjenisPet().equalsIgnoreCase(category))
                    .collect(Collectors.toList());
        }
        
        // Apply sorting
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
        // Reduce stock immediately
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
        return "redirect:/shop/cart";
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

        // Initialize pets
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

    @GetMapping("/checkout")
    public String showCheckout(Model model, HttpSession session) {
        if (!validateCart(session)) {
            return "redirect:/shop/cart";
        }
        return "shop/checkout";
    }

    @PostMapping("/payment")
    public String showPayment(@RequestParam String fullName,
                            @RequestParam String address,
                            @RequestParam String city,
                            @RequestParam String postalCode,
                            @RequestParam String phone,
                            Model model,
                            HttpSession session) {
        if (!validateCart(session)) {
            return "redirect:/shop/cart";
        }
        // Store shipping details in session
        Map<String, String> shipping = new HashMap<>();
        shipping.put("fullName", fullName);
        shipping.put("address", address);
        shipping.put("city", city);
        shipping.put("postalCode", postalCode);
        shipping.put("phone", phone);
        session.setAttribute("shipping", shipping);
        // Pass shipping info to payment page if needed
        model.addAttribute("shipping", shipping);
        return "shop/payment";
    }

    @PostMapping("/confirm")
    public String showConfirmation(@RequestParam String paymentMethod,
                                 Model model,
                                 HttpSession session) {
        if (!validateCart(session)) {
            return "redirect:/shop/cart";
        }
        // Save payment method in session for later use
        session.setAttribute("payment", paymentMethod);
        // Get cart and shipping details
        @SuppressWarnings("unchecked")
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        @SuppressWarnings("unchecked")
        Map<String, String> shipping = (Map<String, String>) session.getAttribute("shipping");
        // Calculate total
        initializePets();
        List<petList> cartPets = allPets.stream()
                .filter(p -> cart.containsKey(p.getrasPet()))
                .collect(Collectors.toList());
        double total = cartPets.stream()
                .mapToDouble(pet -> pet.getHargaPet() * (1 - pet.getDiskonPet()) * cart.get(pet.getrasPet()))
                .sum();
        model.addAttribute("cartPets", cartPets);
        model.addAttribute("cart", cart);
        model.addAttribute("shipping", shipping);
        model.addAttribute("payment", paymentMethod);
        model.addAttribute("total", total);
        return "shop/confirm";
    }

    @PostMapping("/complete")
    public String completeOrder(HttpSession session) {
        if (!validateCart(session)) {
            return "redirect:/shop/cart";
        }
        // Build order object (for future persistence or display)
        @SuppressWarnings("unchecked")
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        @SuppressWarnings("unchecked")
        Map<String, String> shipping = (Map<String, String>) session.getAttribute("shipping");
        initializePets();
        List<petList> cartPets = allPets.stream()
                .filter(p -> cart.containsKey(p.getrasPet()))
                .collect(Collectors.toList());
        List<FaizNation.petOpia_dev.models.OrderItem> items = new java.util.ArrayList<>();
        double total = 0;
        for (petList pet : cartPets) {
            int qty = cart.get(pet.getrasPet());
            double price = pet.getHargaPet();
            double discount = pet.getDiskonPet();
            items.add(new FaizNation.petOpia_dev.models.OrderItem(pet.getrasPet(), pet.getjenisPet(), qty, price, discount));
            total += price * (1 - discount) * qty;
        }
        FaizNation.petOpia_dev.models.Order order = new FaizNation.petOpia_dev.models.Order();
        order.setCustomerName(shipping.get("fullName"));
        order.setAddress(shipping.get("address"));
        order.setCity(shipping.get("city"));
        order.setPostalCode(shipping.get("postalCode"));
        order.setPhone(shipping.get("phone"));
        order.setPaymentMethod((String) session.getAttribute("payment"));
        order.setItems(items);
        order.setTotalAmount(total);
        // Optionally: save order to DB or session
        session.setAttribute("lastOrder", order);
        // Clear cart and shipping info
        session.removeAttribute("cart");
        session.removeAttribute("shipping");
        session.removeAttribute("payment");
        return "redirect:/shop/success";
    }

    @GetMapping("/success")
    public String showSuccess() {
        return "shop/success";
    }

    private boolean validateCart(HttpSession session) {
        @SuppressWarnings("unchecked")
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        return cart != null && !cart.isEmpty();
    }
}
