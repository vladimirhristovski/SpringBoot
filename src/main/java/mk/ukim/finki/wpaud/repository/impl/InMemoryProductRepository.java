package mk.ukim.finki.wpaud.repository.impl;

import mk.ukim.finki.wpaud.bootstrap.DataHolder;
import mk.ukim.finki.wpaud.model.Category;
import mk.ukim.finki.wpaud.model.Manufacturer;
import mk.ukim.finki.wpaud.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProductRepository {

    public List<Product> findAll() {
        return DataHolder.products;
    }

    public Optional<Product> findById(Long id) {
        return DataHolder.products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Optional<Product> save(String name, Double price, Integer quantity, Category category, Manufacturer manufacturer) {
        DataHolder.products.removeIf(p -> p.getName().equals(name));
        Product product = new Product(name, price, quantity, category, manufacturer);
        DataHolder.products.add(product);
        return Optional.of(product);
    }

    public void deleteById(Long id) {
        DataHolder.products.removeIf(p -> p.getId().equals(id));
    }

    public Optional<Product> findByName(String name) {
        return DataHolder.products.stream().filter(p -> p.getName().equals(name)).findFirst();
    }

}
