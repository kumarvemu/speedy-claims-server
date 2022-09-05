package com.allstate.speedyclaimsserver.service;

import com.allstate.speedyclaimsserver.domain.Task;
import com.allstate.speedyclaimsserver.dto.NewTaskDTO;
import com.allstate.speedyclaimsserver.dto.TaskDTO;

import java.util.List;
import java.util.Map;

public interface TaskService {
    List<TaskDTO> getAllTasks();
    List<TaskDTO> getAllOpenTasks();
    List<TaskDTO> getAllTasksByClaimId(Integer claimId);
    int countTasks();
    Task getTaskById(Integer id);
    List<Task> add(NewTaskDTO newTaskDTO);
    List<Task> update(Map<String, String> data, Integer id);
}