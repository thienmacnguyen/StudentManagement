package com.MacThien.School.Project.Service;


import com.MacThien.School.Project.Repository.StudentRepository;
import com.MacThien.School.Project.dto.StudentRequest;
import com.MacThien.School.Project.dto.StudentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentResponse createStudent(StudentRequest request) {
        if (studentRepository.existsByStudentCode(request.getStudentCode())) {
            throw new RuntimeException("Mã sinh viên đã tồn tại");
        }
        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email đã được sử dụng");
        }

    }
}
