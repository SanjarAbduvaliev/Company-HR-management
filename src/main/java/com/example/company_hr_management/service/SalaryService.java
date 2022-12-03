package com.example.company_hr_management.service;

import com.example.company_hr_management.entity.MonthEmployed;
import com.example.company_hr_management.entity.Salary;
import com.example.company_hr_management.entity.User;
import com.example.company_hr_management.payload.ApiResponse;
import com.example.company_hr_management.payload.SalaryDto;
import com.example.company_hr_management.repository.MonthEmployedRepository;
import com.example.company_hr_management.repository.SalaryRepository;
import com.example.company_hr_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class SalaryService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MonthEmployedRepository monthEmployedRepository;
    @Autowired
    SalaryRepository salaryRepository;
    public ApiResponse addSalary(SalaryDto salaryDto){
        Salary salary=new Salary();
        salary.setAmound(salaryDto.getAmount());
        salary.setComment(salaryDto.getComment());
        salary.setGetSalaryWorker(new Date());
        Optional<User> optionalUser = userRepository.findById(salaryDto.getUserId());
        if (optionalUser.isEmpty()){
            return new ApiResponse("Bunday xodim mavjud emas",false);
        }
        Optional<MonthEmployed> monthEmployedOptional = monthEmployedRepository.findById(salaryDto.getMonthId());

        salary.setMonthEmployed(monthEmployedOptional.get());

        salaryRepository.save(salary);
        return new ApiResponse("Oylik maosh saqlandi",true);

    }
    public ResponseEntity<?> getSalaryInfo(SalaryDto salaryDto){
        Set<Salary> salarySet = salaryRepository.findAllByUser_Id(salaryDto.getUserId());
        return ResponseEntity.ok(salarySet);
    }
}
