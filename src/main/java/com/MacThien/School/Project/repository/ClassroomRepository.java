package com.MacThien.School.Project.repository;

import com.MacThien.School.Project.entity.Classroom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    boolean existsByClassName(String className);

    boolean existsByHomeroomTeacherId(Long homeroomTeacherId);

    boolean existsByHomeroomTeacherIdAndIdNot(Long homeroomTeacherId, Long id);

    @Query("SELECT c FROM Classroom c WHERE " +
            "(:keyword IS NULL OR :keyword = '' OR LOWER(c.className) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Classroom> searchClassrooms(@Param("keyword") String keyword, Pageable pageable);
}
