package mk.ukim.finki.wpaud.repository.jpa;

import mk.ukim.finki.wpaud.model.Product;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaSpecificationRepository<Product, Long> {
    Optional<Product> findByName(String name);

    void deleteByName(String name);
}
