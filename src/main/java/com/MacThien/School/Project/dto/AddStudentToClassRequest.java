package com.MacThien.School.Project.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddStudentToClassRequest {
    @NotNull
    private Long classroomId;
    @NotEmpty
    private List<Long> studentIds;
}
