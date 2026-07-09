package com.MacThien.School.Project.dto;

import com.MacThien.School.Project.enums.Gender;
import com.MacThien.School.Project.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private Long id;
    private String studentCode;
    private String fullName;
    private Gender gender;
    private LocalDate birthday;
    private String email;
    private String phone;
    private String address;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long classID;


}
