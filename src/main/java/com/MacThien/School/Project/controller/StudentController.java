package com.MacThien.School.Project.controller;

import com.MacThien.School.Project.dto.StudentRequest;
import com.MacThien.School.Project.dto.StudentResponse;
import com.MacThien.School.Project.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentRequest request) {
        return new ResponseEntity<>(studentService.createStudent(request), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequest request
    ) {
        return ResponseEntity.ok(studentService.updateStudent(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.softDeleteStudent(id);
        return ResponseEntity.ok("Xóa mềm học sinh thành công");
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentByID(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentByID(id));
    }
}
