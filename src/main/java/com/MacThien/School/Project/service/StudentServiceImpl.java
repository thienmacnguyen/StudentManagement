package com.MacThien.School.Project.service;


import com.MacThien.School.Project.entity.Student;
import com.MacThien.School.Project.enums.Status;
import com.MacThien.School.Project.repository.StudentRepository;
import com.MacThien.School.Project.dto.StudentRequest;
import com.MacThien.School.Project.dto.StudentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentResponse createStudent(StudentRequest request) {
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

        Student savedStudent = studentRepository.save(student);
        return mapToResponse(savedStudent);
    }
    private StudentResponse mapToResponse(Student student) {
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

    public StudentResponse updateStudent(Long id, StudentRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy id" + id));

        if (studentRepository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new RuntimeException("Email đã được dùng bởi sinh viên khác");
        }

        // .student.setStudentCode(getStudentCode());
        student.setFullName(student.getFullName());
        student.setGender(student.getGender());
        student.setBirthday(student.getBirthday());
        student.setEmail(student.getEmail());
        student.setPhone(student.getPhone());
        student.setAddress(student.getAddress());

        Student updatedStudent = studentRepository.save(student);
                return mapToResponse(updatedStudent);
    }

    public void softDeleteStudent (Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy id" + id));
        student.setStatus(Status.INACTIVE);
        studentRepository.save(student);
    }

    public StudentResponse getStudentByID(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy id" + id));
        return mapToResponse(student);
    }

    public Page<StudentResponse> searchStudents(String keyword, Status status, Pageable pageable) {
        Page<Student> students = studentRepository.searchStudents(keyword, status, pageable);
        return students.map(this::mapToResponse);
    }
}
