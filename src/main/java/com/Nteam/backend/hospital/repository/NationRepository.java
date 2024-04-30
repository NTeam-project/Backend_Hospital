package com.Nteam.backend.hospital.repository;

import com.Nteam.backend.hospital.entity.Nation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationRepository extends JpaRepository<Nation, Long> {
    Nation findByName(String name);
}
