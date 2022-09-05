package com.allstate.speedyclaimsserver.service;

import com.allstate.speedyclaimsserver.domain.TaskStatus;
        import java.util.List;

public interface TaskStatusService {
    List<TaskStatus> getAllTaskStatuses();
    int countTaskStatuses();
    TaskStatus getTaskStatusById(Integer id);
}
