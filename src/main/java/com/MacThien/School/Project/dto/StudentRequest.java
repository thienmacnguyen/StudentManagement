package com.MacThien.School.Project.dto;
import com.MacThien.School.Project.Enum.Gender;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class    StudentRequest {
    @NotBlank(message = "Mã sinh viên không được để trống")
    @Size(max = 50, message = "Mã sinh viên không được dài quá 50 kỹ tự")
    private String studentCode;

    @NotBlank(message = "Họ tên không được để trống")
    @Size(max = 100, message = "Họ tên không được dài quá 100 ký tự")
    private String fullName;

    private Gender gender;

    @Past(message = "Ngày sinh phải thuộc ngày ở quá khứ")
    private LocalDate birthday;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email phải đúng định dạng")
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{9,15}$", message = "Số điện thoại không đúng định dạng")
    private String phone;

    private String address;
}
