package com.MacThien.School.Project.entity;

import com.MacThien.School.Project.enums.Gender;
import com.MacThien.School.Project.enums.Status;
import jakarta.persistence.*; // Các annotation của JPA
import lombok.*; // Các annotation của Lombok
import org.hibernate.annotations.CreationTimestamp; // Tự động điền thời gian tạo
import org.hibernate.annotations.UpdateTimestamp;   // Tự động điền thời gian cập nhật

import java.time.LocalDate; // Để lưu ngày tháng năm sinh
import java.time.LocalDateTime; // Để lưu ngày giờ tạo/cập nhật

@Entity // Đánh dấu đây là một JPA Entity, ánh xạ với bảng trong database
@Table(name = "students") // Tên bảng trong database
@Getter // Lombok: tự động sinh các phương thức get
@Setter // Lombok: tự động sinh các phương thức set
@NoArgsConstructor // Lombok: tự động sinh constructor không tham số
@AllArgsConstructor // Lombok: tự động sinh constructor với tất cả tham số
@Builder // Lombok: giúp tạo đối tượng dễ dàng hơn (ví dụ: Student.builder().fullName("...").build())
public class Student {

    @Id // Đánh dấu đây là khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Khóa chính tự động tăng
    private Long id;

    @Column(name = "student_code", nullable = false, unique = true, length = 50)
    private String studentCode;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Enumerated(EnumType.STRING) // Lưu Enum dưới dạng chuỗi trong database
    @Column(nullable = false, length = 10)
    private Gender gender;

    private LocalDate birthday; // Mặc định tên cột là birthday

    @Column(nullable = false, length = 100)
    private String email;

    @Column(length = 15)
    private String phone;

    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private Status status = Status.ACTIVE; // Mặc định trạng thái là ACTIVE

    @CreationTimestamp // Tự động điền thời gian khi đối tượng được tạo
    @Column(name = "created_at", updatable = false) // Không cho phép cập nhật thủ công
    private LocalDateTime createdAt;

    @UpdateTimestamp // Tự động điền thời gian khi đối tượng được cập nhật
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
