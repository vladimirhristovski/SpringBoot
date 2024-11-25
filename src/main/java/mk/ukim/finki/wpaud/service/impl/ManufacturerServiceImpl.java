package mk.ukim.finki.wpaud.service.impl;

import mk.ukim.finki.wpaud.model.Manufacturer;
import mk.ukim.finki.wpaud.repository.InMemoryManufacturerRepository;
import mk.ukim.finki.wpaud.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final InMemoryManufacturerRepository inMemoryManufacturerRepository;

    public ManufacturerServiceImpl(InMemoryManufacturerRepository inMemoryManufacturerRepository) {
        this.inMemoryManufacturerRepository = inMemoryManufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return inMemoryManufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return inMemoryManufacturerRepository.findById(id);
    }

    @Override
    public Optional<Manufacturer> save(String name, String address) {
        return inMemoryManufacturerRepository.save(name, address);
    }

    @Override
    public Boolean deleteById(Long id) {
        return inMemoryManufacturerRepository.deleteById(id);
    }
}
