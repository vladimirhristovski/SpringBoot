package mk.ukim.finki.wpaud.model;

import lombok.Data;
import mk.ukim.finki.wpaud.model.enumerations.ShoppingCartStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCart {
    private Long id;
    private LocalDateTime dateCreated;
    private User user;
    private List<Product> products;
    private ShoppingCartStatus status;

    public ShoppingCart(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.status = ShoppingCartStatus.CREATED;
        this.products = new ArrayList<>();
        this.id = (long) (Math.random() * 1000);
    }

    public ShoppingCart() {
        this.id = (long) (Math.random() * 1000);
    }
}
