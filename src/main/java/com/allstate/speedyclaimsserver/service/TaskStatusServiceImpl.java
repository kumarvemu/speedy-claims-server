package com.allstate.speedyclaimsserver.service;

import com.allstate.speedyclaimsserver.data.TaskStatusRepository;
import com.allstate.speedyclaimsserver.domain.TaskStatus;
import com.allstate.speedyclaimsserver.exception.TaskStatusNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskStatusServiceImpl implements TaskStatusService {

    @Autowired
    TaskStatusRepository taskStatusRepository;

    Logger logger = LoggerFactory.getLogger(TaskStatusService.class);
    private String errorMessage;

    @Override
    public List<TaskStatus> getAllTaskStatuses() {
        return taskStatusRepository.findAll();
    }

    @Override
    public int countTaskStatuses() {
        return taskStatusRepository.findAll().size();
    }

    @Override
    public TaskStatus getTaskStatusById(Integer id) {
        Optional<TaskStatus> optionalTaskStatus = taskStatusRepository.findById(id);
        if (optionalTaskStatus.isPresent()) {
            return optionalTaskStatus.get();
        }
        errorMessage = "No Task Status with the ID " + id;
        logger.error(errorMessage);
        throw new TaskStatusNotFoundException(errorMessage);
    }
}
