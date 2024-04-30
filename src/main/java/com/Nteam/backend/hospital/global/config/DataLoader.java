package com.Nteam.backend.hospital.global.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.Nteam.backend.hospital.util.HospitalCsvImporter;

@Component
public class DataLoader implements CommandLineRunner {

    private final HospitalCsvImporter hospitalCsvImporter;

    public DataLoader(HospitalCsvImporter hospitalCsvImporter) {
        this.hospitalCsvImporter = hospitalCsvImporter;
    }

    @Override
    public void run(String... args) throws Exception {
        hospitalCsvImporter.importCsv();
    }
}

