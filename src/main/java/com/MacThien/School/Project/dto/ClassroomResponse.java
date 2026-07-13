package com.MacThien.School.Project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomResponse {
    private Long id;
    private String className;
    private Integer numberOfDesks;

    private TeacherResponse homeroomTeacher;

    private Long homeroomTeacherId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
