package com.MacThien.School.Project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddStudentToClassResponse {
    private Long classId;
    private List<Long> studentIds;
}
