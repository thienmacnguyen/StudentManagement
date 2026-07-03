package com.MacThien.School.Project.entity;

import com.MacThien.School.Project.enums.Degree;
import com.MacThien.School.Project.enums.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

@Entity
@Table(name = "Teachers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teacher_code", nullable = false, unique = true, length = 50)
    private String teacherCode;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length  = 15)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Degree degree;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Department department;

}
