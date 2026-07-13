package com.MacThien.School.Project.service;

import com.MacThien.School.Project.dto.ClassroomRequest;
import com.MacThien.School.Project.dto.ClassroomResponse;
import com.MacThien.School.Project.dto.StudentResponse;
import com.MacThien.School.Project.dto.AddStudentToClassRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClassroomService {
    ClassroomResponse createClassroom(ClassroomRequest request);
    ClassroomResponse updateClassroom(Long id, ClassroomRequest request);
    void deleteClassroom(Long id);
    ClassroomResponse getClassroomById(Long id);
    Page<ClassroomResponse> searchClassrooms(String keyword, Pageable pageable);

    List<StudentResponse> addStudentToClass(AddStudentToClassRequest request);
}
