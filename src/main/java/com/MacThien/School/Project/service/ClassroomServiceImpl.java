package com.MacThien.School.Project.service;
import com.MacThien.School.Project.dto.ClassroomRequest;
import com.MacThien.School.Project.dto.ClassroomResponse;
import com.MacThien.School.Project.dto.StudentResponse;
import com.MacThien.School.Project.dto.TeacherResponse;
import com.MacThien.School.Project.dto.AddStudentToClassRequest;
import com.MacThien.School.Project.entity.Classroom;
import com.MacThien.School.Project.entity.Student;
import com.MacThien.School.Project.entity.Teacher;
import com.MacThien.School.Project.repository.ClassroomRepository;
import com.MacThien.School.Project.repository.StudentRepository;
import com.MacThien.School.Project.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Override
    public ClassroomResponse createClassroom(ClassroomRequest request) {
        if (classroomRepository.existsByClassName(request.getClassName())) {
            throw new RuntimeException("Tên lớp học này đã tồn tại");
        }

        if (request.getHomeroomTeacherId() != null) {
            if (!teacherRepository.existsById(request.getHomeroomTeacherId())) {
                throw new RuntimeException("Không tìm thấy giáo viên với ID: " + request.getHomeroomTeacherId());
            }
            if (classroomRepository.existsByHomeroomTeacherId(request.getHomeroomTeacherId())) {
                throw new RuntimeException("Giáo viên này đang chủ nhiệm một lớp học khác");
            }
        }

        Classroom classroom = Classroom.builder()
                .className(request.getClassName())
                .numberOfDesks(request.getNumberOfDesks())
                .homeroomTeacherId(request.getHomeroomTeacherId())
                .build();

        Classroom saved = classroomRepository.save(classroom);
        return mapToResponse(saved);
    }

    @Override
    public ClassroomResponse updateClassroom(Long id, ClassroomRequest request) {
        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lớp học có ID: " + id));

        if (request.getHomeroomTeacherId() != null) {
            if (!teacherRepository.existsById(request.getHomeroomTeacherId())) {
                throw new RuntimeException("Không tìm thấy giáo viên với ID: " + request.getHomeroomTeacherId());
            }
            if (classroomRepository.existsByHomeroomTeacherIdAndIdNot(request.getHomeroomTeacherId(), id)) {
                throw new RuntimeException("Giáo viên này đang chủ nhiệm lớp học khác");
            }
        }

        classroom.setClassName(request.getClassName());
        classroom.setNumberOfDesks(request.getNumberOfDesks());
        classroom.setHomeroomTeacherId(request.getHomeroomTeacherId());

        Classroom updated = classroomRepository.save(classroom);
        return mapToResponse(updated);
    }

    @Override
    public void deleteClassroom(Long id) {
        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lớp học có ID: " + id));

        List<Student> studentsInClass = studentRepository.findByClassroomId(id);
        for (Student student : studentsInClass) {
            student.setClassroomId(null);
        }
        studentRepository.saveAll(studentsInClass);

        classroomRepository.delete(classroom);
    }

    @Override
    public ClassroomResponse getClassroomById(Long id) {
        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lớp học"));
        return mapToResponse(classroom);
    }

    @Override
    public Page<ClassroomResponse> searchClassrooms(String keyword, Pageable pageable) {
        Page<Classroom> classrooms = classroomRepository.searchClassrooms(keyword, pageable);
        return classrooms.map(this::mapToResponse);
    }

    @Override
    public List<StudentResponse> addStudentToClass(AddStudentToClassRequest request) {
        if (!classroomRepository.existsById(request.getClassroomId())) {
            throw new RuntimeException("Không tìm thấy lớp học có ID: " + request.getClassroomId());
        }
        List<Student> students = studentRepository.findAllById(request.getStudentIds());
        if (students.isEmpty()) {
            throw new RuntimeException("Không tìm thấy học sinh nào trong danh sách ID gửi lên");
        }
        for (Student student : students) {
            student.setClassroomId(request.getClassroomId());
        }
        List<Student> addedStudents = studentRepository.saveAll(students);
        return addedStudents.stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ClassroomResponse mapToResponse(Classroom classroom) {
        TeacherResponse teacherResponse = null;

        if (classroom.getHomeroomTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(classroom.getHomeroomTeacherId()).orElse(null);
            if (teacher != null) {
                teacherResponse = TeacherResponse.builder()
                        .id(teacher.getId())
                        .teacherCode(teacher.getTeacherCode())
                        .fullName(teacher.getFullName())
                        .email(teacher.getEmail())
                        .phone(teacher.getPhone())
                        .degree(teacher.getDegree())
                        .department(teacher.getDepartment())
                        .status(teacher.getStatus())
                        .build();
            }
        }

        return ClassroomResponse.builder()
                .id(classroom.getId())
                .className(classroom.getClassName())
                .numberOfDesks(classroom.getNumberOfDesks())
                .homeroomTeacher(teacherResponse)
                .createdAt(classroom.getCreatedAt())
                .updatedAt(classroom.getUpdatedAt())
                .build();
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
}
