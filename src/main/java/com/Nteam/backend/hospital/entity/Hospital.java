package com.Nteam.backend.hospital.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
@Getter
public class Hospital {
    @Id
    private String hospital_key;

    private String hospital_register_num;

    private String hospital_name;

    private String hospital_category;

    private String hospital_si;

    private String hospital_gu;

    private String hospital_dong;

    private String hospital_ceo;

    private String hospital_address;

    @ManyToMany
    @JoinTable(
            name = "hospital_nation",
            joinColumns = @JoinColumn(name = "hospital_key"),
            inverseJoinColumns = @JoinColumn(name = "nation_id")
    )
    private Set<Nation> nations = new HashSet<>();
}
