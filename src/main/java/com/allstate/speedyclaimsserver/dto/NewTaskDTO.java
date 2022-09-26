package com.allstate.speedyclaimsserver.dto;

        import com.allstate.speedyclaimsserver.domain.Task;
        import java.time.LocalDate;

public class NewTaskDTO {

    private String detail;
    private String taskStatusId;
    private String claimId;

    public NewTaskDTO() {
    }

    public Task toTask(){
        Task task = new Task(null, null, detail, null, LocalDate.now());

        return task;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTaskStatusId() {
        return taskStatusId;
    }

    public void setTaskStatusId(String taskStatusId) {
        this.taskStatusId = taskStatusId;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }
}

