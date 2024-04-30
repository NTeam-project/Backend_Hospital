package com.Nteam.backend.hospital.util;

import com.Nteam.backend.hospital.entity.Nation;
import com.Nteam.backend.hospital.repository.NationRepository;
import com.Nteam.backend.hospital.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.Nteam.backend.hospital.entity.Hospital;

@Slf4j
@Component
public class HospitalCsvImporter {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private NationRepository nationRepository;

    public void importCsv() {
        String filePath = "src/main/resources/hospital.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // 헤더 처리

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                log.info("data = {}", (Object) data);

                String hospitalKey = data[0];
                String hospitalRegisterNum = data[1];
                String hospitalName = data[2];
                String hospitalCategory = data[3];
                String hospitalSi = data[4];
                String hospitalGu = data[5];
                String hospitalDong = data[6];
                String hospitalCeo = data[7];
                String hospitalAddress = data[8];

                Hospital hospital = new Hospital();
                hospital.setHospital_key(hospitalKey);
                hospital.setHospital_register_num(hospitalRegisterNum);
                hospital.setHospital_name(hospitalName);
                hospital.setHospital_category(hospitalCategory);
                hospital.setHospital_si(hospitalSi);
                hospital.setHospital_gu(hospitalGu);
                hospital.setHospital_dong(hospitalDong);
                hospital.setHospital_ceo(hospitalCeo);

                // 주소에서 큰따옴표가 있는 경우 처리
                hospitalAddress = hospitalAddress.replaceAll("^\"|\"$", "");

                hospital.setHospital_address(hospitalAddress);

                // 국가 정보가 있는 경우 처리
                if (data.length > 9) {
                    String[] nationNames = data[9].split(",");

                    // 마지막 요소가 공백인 경우 제거
                    if (nationNames.length > 0) {
                        nationNames = Arrays.copyOf(nationNames, nationNames.length - 1);
                    }

                    for (String nationName : nationNames) {
                        // 큰따옴표 제거
                        nationName = nationName.replaceAll("^\"|\"$", "").trim();
                        Nation nation = nationRepository.findByName(nationName);
                        if (nation == null) {
                            // 미리 정의된 국가에 존재하지 않는 경우 새로 생성하여 저장
                            nation = new Nation();
                            nation.setName(nationName);
                            nation = nationRepository.save(nation);
                        }
                        hospital.getHospital_nations().add(nation);
                    }
                }


                hospitalService.saveHospitalAndNations(hospital);
            }
        } catch (IOException e) {
            log.error("CSV 파일을 읽는 중 오류가 발생했습니다: {}", e.getMessage());
            e.printStackTrace();
        }
    }



}
