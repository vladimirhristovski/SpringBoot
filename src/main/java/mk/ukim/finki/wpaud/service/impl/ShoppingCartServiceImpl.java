package mk.ukim.finki.wpaud.service.impl;

import mk.ukim.finki.wpaud.model.Product;
import mk.ukim.finki.wpaud.model.ShoppingCart;
import mk.ukim.finki.wpaud.model.User;
import mk.ukim.finki.wpaud.model.enumerations.ShoppingCartStatus;
import mk.ukim.finki.wpaud.model.exceptions.ProductAlreadyInShoppingCartException;
import mk.ukim.finki.wpaud.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.wpaud.model.exceptions.ShoppingCartNotFoundException;
import mk.ukim.finki.wpaud.model.exceptions.UserNotFoundException;
import mk.ukim.finki.wpaud.repository.jpa.ShoppingCartRepository;
import mk.ukim.finki.wpaud.repository.jpa.UserRepository;
import mk.ukim.finki.wpaud.service.ProductService;
import mk.ukim.finki.wpaud.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductService productService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if (shoppingCartRepository.findById(cartId).isEmpty()) {
            throw new ShoppingCartNotFoundException(cartId);
        }
        return shoppingCartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {

        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.shoppingCartRepository
                .findByUserAndStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return shoppingCartRepository.save(cart);
                });

    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart = getActiveShoppingCart(username);
        Product product = productService
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        if (shoppingCart.getProducts().stream().anyMatch(p -> p.getId().equals(productId))) {
            throw new ProductAlreadyInShoppingCartException(productId, username);
        }
        shoppingCart.getProducts().add(product);
        return shoppingCartRepository.save(shoppingCart);
    }
}
