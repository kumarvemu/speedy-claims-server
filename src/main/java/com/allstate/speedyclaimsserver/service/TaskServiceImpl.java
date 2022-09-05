package com.allstate.speedyclaimsserver.service;

import com.allstate.speedyclaimsserver.data.ClaimRepository;
import com.allstate.speedyclaimsserver.data.TaskRepository;
import com.allstate.speedyclaimsserver.domain.*;
import com.allstate.speedyclaimsserver.dto.NewTaskDTO;
import com.allstate.speedyclaimsserver.dto.TaskDTO;
import com.allstate.speedyclaimsserver.exception.InvalidNewTaskException;
import com.allstate.speedyclaimsserver.exception.TaskNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskStatusService taskStatusService;

    @Autowired
    ClaimService claimService;

    @Autowired
    ClaimRepository claimRepository;

    Logger logger = LoggerFactory.getLogger(TaskService.class);
    private String errorMessage;

    @Override
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(element -> new TaskDTO(element))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getAllOpenTasks() {
        return taskRepository.findAllByStatusDetail("Open").stream()
                .map(element -> new TaskDTO(element))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getAllTasksByClaimId(Integer claimId) {

        return taskRepository.findAllByClaimId(claimId).stream()
                .map(element -> new TaskDTO(element))
                .collect(Collectors.toList());
    }

    @Override
    public int countTasks() {
        return taskRepository.findAll().size();
    }

    @Override
    public Task getTaskById(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            return optionalTask.get();
        }
        errorMessage = "No Task with the ID " + id;
        logger.error(errorMessage);
        throw new TaskNotFoundException(errorMessage);
    }

    @Override
    public List<Task> add(NewTaskDTO newTaskDTO) {
        Task task = newTaskDTO.toTask();
        Claim taskClaim;
        if (task.getDetail() == null || newTaskDTO.getTaskStatusId() == null || newTaskDTO.getClaimId() == null) {
            errorMessage = "Detail, Claim and Status must be provided for a new Task.";
            logger.error(errorMessage);
            throw new InvalidNewTaskException(errorMessage);
        } else {
            //Find status
            task.setStatus(taskStatusService.getTaskStatusById(Integer.parseInt(newTaskDTO.getTaskStatusId())));
            taskClaim = claimService.getClaimById(Integer.parseInt(newTaskDTO.getClaimId()));
            task.setClaim(taskClaim);
            taskClaim.getTasks().add(task);
        }

        try {
            //return taskRepository.save(task); TODO!!
            Claim updatedClaim = claimRepository.save(taskClaim);
            return updatedClaim.getTasks();//.get(updatedClaim.getTasks().size() - 1);
        } catch (Exception e) {
            errorMessage = "Failure when adding task " + e;
            logger.error(errorMessage);
            throw new InvalidNewTaskException(errorMessage);
        }
    }

    @Override
    public List<Task> update(Map<String, String> data, Integer id) {
        Task task = getTaskById(id);

        if (data.containsKey("status")) {
            task.setStatus(taskStatusService.getTaskStatusById(Integer.parseInt(data.get("status"))));

        }
        try {
            taskRepository.save(task);
            return claimService.getClaimById(task.getClaim().getId()).getTasks();
        } catch (Exception e) {
            errorMessage = "Failure when updating task " + e;
            logger.error(errorMessage);
            throw new InvalidNewTaskException(errorMessage);
        }
    }
}
