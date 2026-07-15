package com.MacThien.School.Project.controller;

import com.MacThien.School.Project.dto.TeacherRequest;
import com.MacThien.School.Project.dto.TeacherResponse;
import com.MacThien.School.Project.enums.Status;
import com.MacThien.School.Project.service.TeacherService;
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
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<TeacherResponse> createTeacher(@Valid @RequestBody TeacherRequest request) {
        return new ResponseEntity<>(teacherService.createTeacher(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponse> updateTeacher(@PathVariable Long id, @Valid @RequestBody TeacherRequest request) {
        return ResponseEntity.ok(teacherService.updateTeacher(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> softDeleteTeacher(@PathVariable Long id) {
        teacherService.softDeleteTeacher(id);
        return ResponseEntity.ok("Xóa giáo viên thành công");
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponse> getTeacherByID(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getTeacherByID(id));
    }

    @GetMapping
    public ResponseEntity<Page<TeacherResponse>> getTeacher(
            @RequestParam (required = false) String keyword,
            @RequestParam (defaultValue = "ACTIVE") Status status,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size,
            @RequestParam (defaultValue = "id") String sortBy,
            @RequestParam (defaultValue = "asc") String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(teacherService.searchTeachers(keyword, status, pageable));
    }
}
