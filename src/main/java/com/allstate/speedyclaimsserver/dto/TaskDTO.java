package com.allstate.speedyclaimsserver.dto;

import com.allstate.speedyclaimsserver.domain.Task;
import com.allstate.speedyclaimsserver.domain.TaskStatus;

public class TaskDTO {

    private Integer id;
    private Integer claimId;
    private String detail;
    private TaskStatus status;

    public TaskDTO() {
    }

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.claimId = task.getClaim().getId();
        this.detail = task.getDetail();
        this.status = task.getStatus();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClaimId() {
        return claimId;
    }

    public void setClaimId(Integer claimId) {
        this.claimId = claimId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}

