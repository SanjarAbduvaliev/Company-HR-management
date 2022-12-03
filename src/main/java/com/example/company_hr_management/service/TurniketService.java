package com.example.company_hr_management.service;

import com.example.company_hr_management.entity.turniket.InputWorker;
import com.example.company_hr_management.entity.turniket.OutputWorker;
import com.example.company_hr_management.entity.turniket.Turniket;
import com.example.company_hr_management.payload.turniketdto.InputOutpuDto;
import com.example.company_hr_management.repository.InputRepository;
import com.example.company_hr_management.repository.OutputRepository;
import com.example.company_hr_management.repository.TurniketRepository;
import com.example.company_hr_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;

@Service
public class TurniketService {
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    InputRepository inputRepository;
    @Autowired
    TurniketRepository turniketRepository;
    @Autowired
    UserRepository userRepository;
    private Timestamp localTime;

    public ResponseEntity<?> addTurniket(InputOutpuDto inputOutpuDto){
        Turniket turniket=new Turniket();
        InputWorker inputWorker=new InputWorker();
        inputWorker.setTimestamp(localTime);
        if (inputOutpuDto.isInputActive()) {
            InputWorker savedInput = inputRepository.save(inputWorker);
            turniket.setInputsWorker(Collections.singleton(savedInput));
            return ResponseEntity.ok(new Date()+" kirilgan");
        }
        if ((inputOutpuDto.isOutputActive())){
            OutputWorker outputWorker=new OutputWorker();
            outputWorker.setTimestamp(localTime);
            OutputWorker savedOutput = outputRepository.save(outputWorker);

            turniket.setOutputsWorker(Collections.singleton(savedOutput));
            return ResponseEntity.ok(localTime+" chiqildi");
        }
        return ResponseEntity.status(404).body("Error!");
    }
}
