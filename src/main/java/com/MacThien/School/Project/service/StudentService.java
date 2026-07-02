package com.MacThien.School.Project.service;

import com.MacThien.School.Project.dto.StudentRequest;
import com.MacThien.School.Project.dto.StudentResponse;
import com.MacThien.School.Project.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {
    StudentResponse createStudent(StudentRequest request);
    StudentResponse updateStudent(Long id, StudentRequest request);
    void softDeleteStudent (Long id);
    StudentResponse getStudentByID(Long id);
    Page<StudentResponse> searchStudents(String keyword, Status status, Pageable pageable);
}
