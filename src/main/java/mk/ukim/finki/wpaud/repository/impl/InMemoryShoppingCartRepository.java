package mk.ukim.finki.wpaud.repository.impl;

import mk.ukim.finki.wpaud.bootstrap.DataHolder;
import mk.ukim.finki.wpaud.model.ShoppingCart;
import mk.ukim.finki.wpaud.model.enumerations.ShoppingCartStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryShoppingCartRepository {

    public Optional<ShoppingCart> findById(Long shoppingCartId) {
        return DataHolder.shoppingCarts.stream().filter(c -> c.getId().equals(shoppingCartId)).findFirst();
    }

    public ShoppingCart save(ShoppingCart shoppingCart) {
        DataHolder.shoppingCarts.removeIf(c -> c.getUser().getUsername().equals(shoppingCart.getUser().getUsername()));
        DataHolder.shoppingCarts.add(shoppingCart);
        return shoppingCart;
    }

    public Optional<ShoppingCart> findByUsernameAndStatus(String username, ShoppingCartStatus status) {
        return DataHolder.shoppingCarts.stream()
                .filter(s -> s.getUser().getUsername().equals(username) && s.getStatus().equals(status))
                .findFirst();
    }

}
