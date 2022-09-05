package com.allstate.speedyclaimsserver.control;

import com.allstate.speedyclaimsserver.domain.Claim;
import com.allstate.speedyclaimsserver.domain.Task;
import com.allstate.speedyclaimsserver.dto.NewTaskDTO;
import com.allstate.speedyclaimsserver.dto.TaskDTO;
import com.allstate.speedyclaimsserver.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping()
    public List<TaskDTO> getAll(@RequestParam(value = "open", required = false) String open,
                                @RequestParam(value = "claimId", required = false) String claimId) {

        if (open != null) {
            return taskService.getAllOpenTasks();
        }
        if (claimId != null) {
            return taskService.getAllTasksByClaimId(Integer.parseInt(claimId));
        }

        return taskService.getAllTasks();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public TaskDTO getById(@PathVariable(value = "id", required = true) Integer id) {
        return new TaskDTO(taskService.getTaskById(id));
    }

    @GetMapping("/volume")
    public Map<String, String> getNumberOfTasks() {
        Integer volume = taskService.countTasks();
        Map<String, String> results = new HashMap<>();
        results.put("volume", volume.toString());
        return results;
    }

    @PostMapping()
    public List<Task> addTask(@RequestBody NewTaskDTO newTaskDTO) {
        return taskService.add(newTaskDTO);
    }

    //TODO! - how to update individual task?
    @PutMapping(value = "/{id}")
    public List<Task> updateTask(@RequestBody Map<String, String> data, @PathVariable(value = "id", required = true) Integer id) {
        return taskService.update(data, id);
    }
}

