package com.MacThien.School.Project.repository;

import com.MacThien.School.Project.entity.Teacher;
import com.MacThien.School.Project.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByTeacherCode(String teacherCode);
    boolean existsByTeacherCode(String teacherCode);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
    @Query("SELECT t FROM Teacher t WHERE" +
            "(:keyword IS NULL OR :keyword = '' OR LOWER(t.teacherCode) LIKE(CONCAT('%',:keyword,'%')) " +
            "OR :keyword = '' OR LOWER(t.fullName) LIKE(CONCAT('%',:keyword,'%')) " +
            "OR :keyword = '' OR LOWER(t.email) LIKE(CONCAT('%',:keyword,'%'))) " +
            "AND (:status IS NULL OR t.status = :status)")
    Page<Teacher> searchTeachers(@Param("keyword") String keyword, @Param("status") Status status, Pageable pageable);
}
