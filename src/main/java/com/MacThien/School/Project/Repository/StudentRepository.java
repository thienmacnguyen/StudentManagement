package com.MacThien.School.Project.Repository;
import com.MacThien.School.Project.Entity.Student;
import com.MacThien.School.Project.Enum.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentCode(String studentCode);
    boolean existsByStudentCode(String studentCode);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
    // Dùng Custom Query để tìm kiếm tương đối (LIKE) và lọc theo trạng thái
    @Query("SELECT s FROM Student s WHERE " +
            "(:keyword IS NULL OR :keyword = '' OR LOWER(s.studentCode) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(s.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(s.email) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            " AND (:status IS NULL OR s.status = :status)")
    Page<Student> searchStudents(@Param("keyword") String keyword, @Param("status") Status status, Pageable pageable);
}
