package com.MacThien.School.Project.controller;

import com.MacThien.School.Project.dto.StudentRequest;
import com.MacThien.School.Project.dto.StudentResponse;
import com.MacThien.School.Project.enums.Status;
import com.MacThien.School.Project.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @DeleteMapping
    public ResponseEntity<String> deleteStudent(
            @RequestParam (required = true) Long id
    ) {
        studentService.softDeleteStudent(id);
        return ResponseEntity.ok("Xóa học sinh thành công");
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentByID(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentByID(id));
    }

    @GetMapping("/v1")
    public ResponseEntity<Page<StudentResponse>> getStudents(
            @RequestParam (required = false) String keyword,
            @RequestParam (defaultValue = "ACTIVE") Status status,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size,
            @RequestParam (defaultValue = "id") String sortBy,
            @RequestParam (defaultValue = "asc") String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                    Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(studentService.searchStudents(keyword, status, pageable));
    }

    @PostMapping("/v2")
    public ResponseEntity<Page<StudentResponse>> getStudents(
            @RequestBody StudentRequest request) {
        Sort sort = request.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(request.getSortBy).ascending() : Sort.by(request.getSortBy).descending();
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        return ResponseEntity.ok(studentService.searchStudents(request.getKeyword(), request.getStatus(), pageable));
    }
}
