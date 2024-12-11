package mk.ukim.finki.wpaud.web.rest;

import mk.ukim.finki.wpaud.model.Manufacturer;
import mk.ukim.finki.wpaud.service.ManufacturerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturers")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public List<Manufacturer> getManufacturers() {
        return manufacturerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> findById(@PathVariable Long id) {
        return manufacturerService.findById(id).map(m -> ResponseEntity.ok().body(m)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Manufacturer> save(@RequestParam String name, @RequestParam String address) {
        return manufacturerService.save(name, address).map(manufacturer -> ResponseEntity.ok().body(manufacturer)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.manufacturerService.deleteById(id);
        if (this.manufacturerService.findById(id).isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();

    }
}
