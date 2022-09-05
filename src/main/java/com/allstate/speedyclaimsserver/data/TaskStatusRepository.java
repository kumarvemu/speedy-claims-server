package com.allstate.speedyclaimsserver.data;

import com.allstate.speedyclaimsserver.domain.Claim;
import com.allstate.speedyclaimsserver.domain.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatus,Integer> {
}