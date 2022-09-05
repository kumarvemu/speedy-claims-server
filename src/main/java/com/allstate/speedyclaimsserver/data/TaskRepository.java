package com.allstate.speedyclaimsserver.data;


import com.allstate.speedyclaimsserver.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findAllByStatusDetail(String taskStatus);
    List<Task> findAllByClaimId(Integer claimId);
}