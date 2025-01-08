package mk.ukim.finki.wpaud.service.impl;

import mk.ukim.finki.wpaud.model.Category;
import mk.ukim.finki.wpaud.model.Manufacturer;
import mk.ukim.finki.wpaud.model.Product;
import mk.ukim.finki.wpaud.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.wpaud.model.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.wpaud.repository.jpa.CategoryRepository;
import mk.ukim.finki.wpaud.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.wpaud.repository.jpa.ProductRepository;
import mk.ukim.finki.wpaud.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static mk.ukim.finki.wpaud.service.specifications.FieldFilterSpecification.filterContainsText;
import static mk.ukim.finki.wpaud.service.specifications.FieldFilterSpecification.filterEquals;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findPage(String name, Long categoryId, Long manufacturerId, Integer pageNum, Integer pageSize) {
        Specification<Product> specification = Specification
                .where(filterContainsText(Product.class, "name", name))
                .and(filterEquals(Product.class, "category.id", categoryId))
                .and(filterEquals(Product.class, "manufacturer.id", manufacturerId));

        return this.productRepository.findAll(specification,PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Direction.DESC, "name")));
    }


    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId).orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        this.productRepository.deleteByName(name);

        return Optional.of(productRepository.save(new Product(name, price, quantity, category, manufacturer)));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
