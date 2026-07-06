package com.MacThien.School.Project.controller;

import com.MacThien.School.Project.dto.StudentRequest;
import com.MacThien.School.Project.dto.StudentResponse;
import com.MacThien.School.Project.enums.Status;
import com.MacThien.School.Project.service.StudentServiceImpl;
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
    private final StudentServiceImpl studentServiceImpl;
    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentRequest request) {
        return new ResponseEntity<>(studentServiceImpl.createStudent(request), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequest request
    ) {
        return ResponseEntity.ok(studentServiceImpl.updateStudent(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentServiceImpl.softDeleteStudent(id);
        return ResponseEntity.ok("Xóa mềm học sinh thành công");
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentByID(@PathVariable Long id) {
        return ResponseEntity.ok(studentServiceImpl.getStudentByID(id));
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
        return ResponseEntity.ok(studentServiceImpl.searchStudents(keyword, status, pageable));
    }

    @PostMapping("/v2")
    public ResponseEntity<Page<StudentResponse>> getStudents(
            @RequestBody StudentRequest request) {
        Sort sort = request.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(request.getSortBy).ascending() : Sort.by(request.getSortBy).descending();
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        return ResponseEntity.ok(studentServiceImpl.searchStudents(request.getKeyword(), request.getStatus(), pageable));
    }
}
