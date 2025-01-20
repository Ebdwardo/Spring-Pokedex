package com.example.Pokedex.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Pokedex")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

//Using Lombok to remove boilerplate code (getters, setters, etc..)
public class Entry {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

}
