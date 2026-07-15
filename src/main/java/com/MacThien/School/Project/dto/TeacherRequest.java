package com.MacThien.School.Project.dto;
import com.MacThien.School.Project.enums.Degree;
import com.MacThien.School.Project.enums.Department;
import com.MacThien.School.Project.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TeacherRequest {

    @NotBlank(message = "Họ tên không được để trống")
    @Size(max = 50, message = "Họ tên không được dài quá 50 kí tự")
    private String fullName;

    @NotBlank(message = "Email không được để trống")
    @Size(max = 50, message = "Email không được dài quá 50 ký tự")
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{9,15}$", message = "Số điện thoại không đúng định dạng")
    private String phone;

    private Status status;

    @NotNull(message = "Thông tin bằng không được để trống")
    private Degree degree;

    private Department department;
}
