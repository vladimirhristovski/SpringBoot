package mk.ukim.finki.wpaud.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wpaud.model.*;
import mk.ukim.finki.wpaud.model.enumerations.Role;
import mk.ukim.finki.wpaud.repository.jpa.CategoryRepository;
import mk.ukim.finki.wpaud.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.wpaud.repository.jpa.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Category> categories = null;
    public static List<User> users = null;
    public static List<Manufacturer> manufacturers = null;
    public static List<Product> products = null;
    public static List<ShoppingCart> shoppingCarts = null;

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final PasswordEncoder passwordEncoder;

    public DataHolder(UserRepository userRepository, CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {

        categories = new ArrayList<>();
        if (this.categoryRepository.count() == 0) {
            categories.add(new Category("Books", "Books category"));
            categories.add(new Category("Movies", "Movies category"));
            categories.add(new Category("Software", "Software category"));
            this.categoryRepository.saveAll(categories);
        }

        users = new ArrayList<>();
        if (this.userRepository.count() == 0) {
            users.add(new User("vladimir.hristovski", passwordEncoder.encode("vh"), "Vladimir", "Hristovski", Role.ROLE_USER));
            users.add(new User("matea.manova", passwordEncoder.encode("mm"), "Matea", "Manova", Role.ROLE_USER));
            users.add(new User("admin", passwordEncoder.encode("admin"), "admin", "admin", Role.ROLE_ADMIN));
            this.userRepository.saveAll(users);
        }

        manufacturers = new ArrayList<>();
        if (this.manufacturerRepository.count() == 0) {
            manufacturers.add(new Manufacturer("Nike", "USA"));
            manufacturers.add(new Manufacturer("Coca Cola", "USA"));
            manufacturers.add(new Manufacturer("Literatura", "MK"));
            this.manufacturerRepository.saveAll(manufacturers);
        }

        shoppingCarts = new ArrayList<>();

    }

}
