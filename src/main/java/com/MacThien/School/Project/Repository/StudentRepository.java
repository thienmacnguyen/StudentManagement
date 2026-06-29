package com.MacThien.School.Project.Repository;
import com.MacThien.School.Project.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentCode(String studentCode);
    boolean exitsByStudentCode(String studentCode);
    boolean exitsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
}
