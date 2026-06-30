package com.MacThien.School.Project.service;


import com.MacThien.School.Project.entity.Student;
import com.MacThien.School.Project.enums.Status;
import com.MacThien.School.Project.repository.StudentRepository;
import com.MacThien.School.Project.dto.StudentRequest;
import com.MacThien.School.Project.dto.StudentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private static final StudentRepository studentRepository;

    public static StudentResponse createStudent(StudentRequest request) {
        if (studentRepository.existsByStudentCode(request.getStudentCode())) {
            throw new RuntimeException("Mã sinh viên đã tồn tại");
        }
        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email đã được sử dụng");
        }
        Student student = Student.builder()
                .studentCode(request.getStudentCode())
                .fullName(request.getFullName())
                .gender(request.getGender())
                .birthday(request.getBirthday())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .status(Status.ACTIVE)
                .build();

        Student savedStudent = StudentRepository.save(student);
        return mapToResponse(savedStudent);
    }
    private static StudentResponse mapToResponse(Student student) {
            return StudentResponse.builder()
                .id(student.getId())
                .studentCode(student.getStudentCode())
                .fullName(student.getFullName())
                .gender(student.getGender())
                .birthday(student.getBirthday())
                .email(student.getEmail())
                .phone(student.getPhone())
                .address(student.getAddress())
                .status(student.getStatus())
                .createdAt(student.getCreatedAt())
                .updatedAt(student.getUpdatedAt())
                .build();
    }

    public StudentResponse updateStudent() {
        return null;
    }
}
