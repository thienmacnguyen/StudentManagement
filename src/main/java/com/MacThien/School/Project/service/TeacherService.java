package com.MacThien.School.Project.service;

import com.MacThien.School.Project.dto.TeacherRequest;
import com.MacThien.School.Project.dto.TeacherResponse;
import com.MacThien.School.Project.entity.Teacher;
import com.MacThien.School.Project.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherService {
    TeacherResponse createTeacher(TeacherRequest request);
    TeacherResponse updateTeacher(Long id, TeacherRequest request);
    void softDeleteTeacher(Long id);
    TeacherResponse getTeacherByID(Long id);
    Page<TeacherResponse> searchTeachers(String keyword, Status status, Pageable pageable);
}
