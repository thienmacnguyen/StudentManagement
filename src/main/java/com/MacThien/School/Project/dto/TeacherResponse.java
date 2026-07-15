package com.MacThien.School.Project.dto;
import com.MacThien.School.Project.enums.Degree;
import com.MacThien.School.Project.enums.Department;
import com.MacThien.School.Project.enums.Status;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TeacherResponse {
    private Long id;
    private String teacherCode;
    private String fullName;
    private String email;
    private String phone;
    private Degree degree;
    private Department department;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
