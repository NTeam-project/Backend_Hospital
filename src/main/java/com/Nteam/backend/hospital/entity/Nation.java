package com.Nteam.backend.hospital.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
@Getter
public class Nation {
    @Id
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "nations")
    private Set<Hospital> hospitals = new HashSet<>();
}
