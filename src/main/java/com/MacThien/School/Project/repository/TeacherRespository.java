package com.MacThien.School.Project.repository;

import com.MacThien.School.Project.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRespository extends JpaRepository<Teacher, Long> {


}
