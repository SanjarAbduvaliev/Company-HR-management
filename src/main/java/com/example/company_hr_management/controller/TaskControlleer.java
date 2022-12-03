package com.example.company_hr_management.controller;

import com.example.company_hr_management.entity.Tasks;
import com.example.company_hr_management.payload.TaskDto;
import com.example.company_hr_management.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/task")
public class TaskControlleer {
    @Autowired
    TaskService taskService;

    @PreAuthorize(value = "hasAnyRole('DERECTOR','HR_MANAGER')")
    @PostMapping
    public ResponseEntity<?> add(@RequestBody TaskDto taskDto){
        return ResponseEntity.ok(taskService.addTask(taskDto));
    }
    @PreAuthorize(value = "hasAnyRole('DERECTOR','HR_MANAGER')")
    @GetMapping
    public ResponseEntity<?> getAll(@RequestBody TaskDto taskDto){
        List<Tasks> task = taskService.getTask(taskDto);
        return ResponseEntity.ok(task);
    }
    @PreAuthorize(value = "hasAnyRole('DERECTOR','HR_MANAGER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getId(@PathVariable UUID id){
        return ResponseEntity.ok(taskService.getTaskId(id));
    }
    @PreAuthorize(value = "hasAnyRole('DERECTOR','HR_MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable UUID id, @RequestBody TaskDto taskDto ){
        return ResponseEntity.ok(taskService.editTask(id,taskDto));
    }
    @PreAuthorize(value = "hasAnyRole('DERECTOR','HR_MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        return ResponseEntity.ok(taskService.deleteTask(id));
    }
}
