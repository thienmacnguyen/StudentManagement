package com.MacThien.School.Project.dto;

import com.MacThien.School.Project.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomResponse {
    private Long id;
    private String className;
    private Integer numberOfDesks;
    private Long homeroomTeacherId;
    private Status status;
}
