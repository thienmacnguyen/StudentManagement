package com.MacThien.School.Project.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClassroomRequest {
    @NotBlank(message = "Tên lớp không được để trống")
    private String className;

    @NotNull(message = "Số bàn học không được để trống")
    @Min(value = 1, message = "Số bàn học ít nhất phải là 1")
    private Integer numberOfDesks;

    private Long homeroomTeacherId;
}