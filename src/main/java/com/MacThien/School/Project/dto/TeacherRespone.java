package com.MacThien.School.Project.dto;

import com.MacThien.School.Project.enums.Degree;
import com.MacThien.School.Project.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherRespone {
    private Long id;
    private String teacherCode;
    private String fullName;
    private String email;
    private String phone;
    private Degree degree;
    private Department department;
}
