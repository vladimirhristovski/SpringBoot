package mk.ukim.finki.wpaud.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Category {
    private String name;

    @Column(length = 4000)
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
