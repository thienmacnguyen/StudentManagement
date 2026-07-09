package com.MacThien.School.Project.service;


import com.MacThien.School.Project.dto.TeacherRequest;
import com.MacThien.School.Project.dto.TeacherResponse;
import com.MacThien.School.Project.entity.Teacher;
import com.MacThien.School.Project.enums.Status;
import com.MacThien.School.Project.repository.TeacherRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRespository teacherRespository;

    @Override
    public TeacherResponse createTeacher(TeacherRequest request) {
        if (teacherRespository.existsByTeacherCode(request.getTeacherCode())) {
            throw new RuntimeException("Mã giáo viên đã tồn tại");
        }
        if (teacherRespository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }
        Teacher teacher = new Teacher();
        teacher.setTeacherCode(request.getTeacherCode());
        teacher.setFullName(request.getFullName());
        teacher.setEmail(request.getEmail());
        teacher.setPhone(request.getPhone());
        teacher.setDegree(request.getDegree());
        teacher.setDepartment(request.getDepartment());
        teacher.setStatus(Status.ACTIVE);

        Teacher savedTeacher = teacherRespository.save(teacher);
        return mapToResponse(savedTeacher);
    }

    @Override
    public TeacherResponse updateTeacher(Long id, TeacherRequest request) {
        Teacher teacher = teacherRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy id" + id));
        if (teacherRespository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new RuntimeException("Email đã được sử dụng");
        }
        teacher.setTeacherCode(request.getTeacherCode());
        teacher.setFullName(request.getFullName());
        teacher.setEmail(request.getEmail());
        teacher.setPhone(request.getPhone());
        teacher.setDegree(request.getDegree());
        teacher.setDepartment(request.getDepartment());
        Teacher updatedTeacher = teacherRespository.save(teacher);
        return mapToResponse(updatedTeacher);
    }

    @Override
    public void softDeleteTeacher(Long id) {
        Teacher teacher = teacherRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy id" + id));
        teacher.setStatus(Status.INACTIVE);
        teacherRespository.save(teacher);
    }

    @Override
    public TeacherResponse getTeacherByID(Long id) {
        Teacher teacher = teacherRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy id" + id));
        return mapToResponse(teacher);
    }

    @Override
    public Page<TeacherResponse> searchTeachers(String keyword, Status status, Pageable pageable) {
        Page<Teacher> teachers = teacherRespository.searchTeachers(keyword, status, pageable);
        return teachers.map(this::mapToResponse);
    }

    public TeacherResponse mapToResponse(Teacher teacher) {
        TeacherResponse response = new TeacherResponse();
        response.setId(teacher.getId());
        response.setFullName(teacher.getFullName());
        response.setEmail(teacher.getEmail());
        response.setPhone(teacher.getPhone());
        response.setDegree(teacher.getDegree());
        response.setDepartment(teacher.getDepartment());
        response.setCreatedAt(teacher.getCreatedAt());
        response.setUpdatedAt(teacher.getUpdatedAt());

        return response;
    }
}
