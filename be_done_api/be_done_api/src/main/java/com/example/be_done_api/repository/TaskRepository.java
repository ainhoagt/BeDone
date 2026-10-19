package com.example.be_done_api.repository;

import com.example.be_done_api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Modifying
    @Query("UPDATE Task t SET t.positionIndex = t.positionIndex - 1 " +
            "WHERE t.boardColumn.id = :columnId AND t.positionIndex > :pos")
    void shiftDown(@Param("columnId") Integer columnId, @Param("pos") int pos);

    @Modifying
    @Query("UPDATE Task t SET t.positionIndex = t.positionIndex + 1 " +
            "WHERE t.boardColumn.id = :columnId AND t.positionIndex >= :pos")
    void shiftUp(@Param("columnId") Integer columnId, @Param("pos") int pos);

    int countByBoardColumnId(Integer columnId);
}
