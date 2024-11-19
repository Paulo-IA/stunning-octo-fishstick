package com.taskflow.api.repositories;

import com.taskflow.api.domain.task.Task;
import com.taskflow.api.enums.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query("SELECT t FROM Task t LEFT JOIN t.enterprise e " +
            "WHERE (:title IS NULL OR t.title LIKE %:title%) " +
            "AND (:description IS NULL OR t.description LIKE %:description%) " +
            "AND (:state IS NULL OR t.state = :state) " +
            "AND (:enterpriseId IS NULL OR e.id = :enterpriseId) " +
            "AND (cast(:startDate as date) IS NULL OR t.start_date >= :startDate) " +
            "AND (cast(:endDate as date) IS NULL OR t.start_date <= :endDate)")
    public Page<Task> findAllFilteredTasks(@Param("title") String title,
                                           @Param("description") String description,
                                           @Param("startDate") Date startDate,
                                           @Param("endDate") Date endDate,
                                           @Param("state") State state,
                                           @Param("enterpriseId") UUID enterpriseId,
                                           Pageable pageable);

    @Query("SELECT t FROM Task t LEFT JOIN t.user u WHERE u.id = :userId")
    public Page<Task> findByUserId(@Param("userId") UUID userId, Pageable pageable);
}
