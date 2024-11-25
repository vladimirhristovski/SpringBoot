package mk.ukim.finki.wpaud.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wpaud.model.ShoppingCart;
import mk.ukim.finki.wpaud.model.User;
import mk.ukim.finki.wpaud.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error, HttpServletRequest request, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        User user = (User) request.getSession().getAttribute("user");
        ShoppingCart shoppingCart = shoppingCartService.getActiveShoppingCart(user.getUsername());
        model.addAttribute("products", shoppingCartService.listAllProductsInShoppingCart(shoppingCart.getId()));
        return "shopping-cart";
    }

    @PostMapping("/add-product/{id}")
    public String addProductToShoppingCart(@PathVariable Long id, HttpServletRequest request) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            ShoppingCart shoppingCart = shoppingCartService.addProductToShoppingCart(user.getUsername(), id);
            return "redirect:/shopping-cart";
        } catch (RuntimeException e) {
            return "redirect:/shopping-cart?error=" + e.getMessage();
        }
    }
}
