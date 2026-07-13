package com.MacThien.School.Project.controller;

import com.MacThien.School.Project.dto.ClassroomRequest;
import com.MacThien.School.Project.dto.ClassroomResponse;
import com.MacThien.School.Project.dto.StudentResponse;
import com.MacThien.School.Project.dto.AddStudentToClassRequest;
import com.MacThien.School.Project.service.ClassroomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classrooms")
@RequiredArgsConstructor
public class ClassroomController {
    private final ClassroomService classroomService;

    @PostMapping
    public ResponseEntity<ClassroomResponse> createClassroom(@Valid @RequestBody ClassroomRequest request) {
        return new ResponseEntity<>(classroomService.createClassroom(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassroomResponse> updateClassroom(
            @PathVariable Long id,
            @Valid @RequestBody ClassroomRequest request) {
        return ResponseEntity.ok(classroomService.updateClassroom(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClassroom(@PathVariable Long id) {
        classroomService.deleteClassroom(id);
        return ResponseEntity.ok("Xóa cứng lớp học thành công vĩnh viễn!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomResponse> getClassroomById(@PathVariable Long id) {
        return ResponseEntity.ok(classroomService.getClassroomById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ClassroomResponse>> getClassrooms(
            @RequestParam(required = false) String keyword,
            Pageable pageable
    ) {
        return ResponseEntity.ok(classroomService.searchClassrooms(keyword, pageable));
    }

    @PutMapping("/add-students")
    public ResponseEntity<List<StudentResponse>> addStudentToClass(
            @Valid @RequestBody AddStudentToClassRequest request
    ) {
        classroomService.addStudentToClass(request);
        return ResponseEntity.ok(classroomService.addStudentToClass(request));
    }
}
