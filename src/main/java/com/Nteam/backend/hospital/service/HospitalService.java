package com.Nteam.backend.hospital.service;

import com.Nteam.backend.hospital.entity.Nation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import com.Nteam.backend.hospital.entity.Hospital;
import com.Nteam.backend.hospital.repository.HospitalRepository;
import com.Nteam.backend.hospital.repository.NationRepository;

import java.util.Set;

@Slf4j
@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private NationRepository nationRepository;

    public void saveHospitalAndNations(Hospital hospital) {
        hospitalRepository.save(hospital);

        Set<Nation> nations = hospital.getHospital_nations();
        if (nations != null && !nations.isEmpty()) {
            nationRepository.saveAll(nations);
        }
    }
}
