package com.allstate.speedyclaimsserver.control;


import com.allstate.speedyclaimsserver.domain.TaskStatus;
import com.allstate.speedyclaimsserver.service.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/task-status")
public class TaskStatusController {

    @Autowired
    private TaskStatusService taskStatusService;

    @GetMapping()
    public List<TaskStatus> getAll() {
        return taskStatusService.getAllTaskStatuses();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public TaskStatus getById(@PathVariable(value = "id", required = true) Integer id) {
        return taskStatusService.getTaskStatusById(id);
    }

    @GetMapping("/volume")
    public Map<String, String> getNumberOfTaskStatuses() {
        Integer volume = taskStatusService.countTaskStatuses();
        Map<String, String> results = new HashMap<>();
        results.put("volume", volume.toString());
        return results;
    }
}
