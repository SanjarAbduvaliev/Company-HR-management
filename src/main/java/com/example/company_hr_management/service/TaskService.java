package com.example.company_hr_management.service;

import com.example.company_hr_management.entity.Tasks;
import com.example.company_hr_management.entity.User;
import com.example.company_hr_management.payload.ApiResponse;
import com.example.company_hr_management.payload.TaskDto;
import com.example.company_hr_management.repository.TaskRepository;
import com.example.company_hr_management.repository.UserRepository;
import com.example.company_hr_management.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {

    @Autowired
    AuthService authService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TaskRepository taskRepository;
    public ApiResponse addTask(TaskDto taskDto){
        Tasks tasks=new Tasks();
        tasks.setCreatedTask(new Date());
        tasks.setDeadline(taskDto.getDeadline());
        tasks.setName(taskDto.getName());
        tasks.setActive(taskDto.isActive());
        tasks.setTaskAbout(taskDto.getTaskAbout());
        Set<User> userSet = userRepository.findAllById(taskDto.getUserId());
        tasks.setUsers(userSet);
        taskRepository.save(tasks);
        for (User user : userSet) {
            mailSender(user.getEmail(),taskDto.getTaskAbout());
        }

        return new ApiResponse("Vazifa qo'shildi",true);

    }
    public List<Tasks> getTask(TaskDto taskDto){
        List<Tasks> tasksList = taskRepository.findAll();
        for (Tasks tasks : tasksList) {
            for (User user : tasks.getUsers()) {
                boolean b = user.getId() == taskDto.getUserId();
                if (b){
                   return new ArrayList<>(List.of(tasks));
                }
            }
        }
        return new ArrayList<>();
    }
    public ResponseEntity<?> getTaskId(UUID id){
        Optional<Tasks> tasksOptional = taskRepository.findById(id);
        if (tasksOptional.isPresent()) return ResponseEntity.status(200).body(tasksOptional.get());
        return ResponseEntity.status(404).body("Vazifa topilmadi");
    }
    public ResponseEntity<?> editTask(UUID id, TaskDto taskDto){
        Optional<Tasks> optionalTasks = taskRepository.findById(id);
         if(optionalTasks.isEmpty()) return ResponseEntity.status(404).body("Bunday Vazifa mavjud emas");
        Tasks tasks = optionalTasks.get();
        tasks.setTaskAbout(taskDto.getTaskAbout());
        tasks.setActive(taskDto.isActive());
        tasks.setName(taskDto.getName());
        tasks.setDeadline(taskDto.getDeadline());
        taskRepository.save(tasks);
        return ResponseEntity.status(201).body("Vazifa tahrirlandi");
    }
    public ResponseEntity<?> deleteTask(UUID id){
        taskRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Vazifa o'chirilid");
    }

    public boolean mailSender(String email, String taskText){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("sandjarbeek@gmail.com");
            mailMessage.setTo(email);
            mailMessage.getReplyTo();
            mailMessage.setSubject("Sizga biriktirilgan vazifa bilan tanishing");
            mailMessage.setText(taskText);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

}
