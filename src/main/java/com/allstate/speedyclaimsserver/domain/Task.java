package com.allstate.speedyclaimsserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="task")
public class Task {
    //{ id: 101, claim_id: 103, claim_number: "5555678901", detail: "Contact Customer", status: "Open", date: "2017-01-01" },
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Claim claim;
    private String detail;

    @ManyToOne
    private TaskStatus status;
    private LocalDate date;

    public Task() {
    }

    public Task(Integer id, Claim claim, String detail, TaskStatus status, LocalDate date) {
        this.id = id;
        this.claim = claim;
        this.detail = detail;
        this.status = status;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    public Claim getClaim() {
        return claim;
    }

    public void setClaim(Claim claim) {
        this.claim = claim;
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

    @JsonIgnore
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(claim, task.claim) && Objects.equals(detail, task.detail) && Objects.equals(status, task.status) && Objects.equals(date, task.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, claim, detail, status, date);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", claim=" + claim +
                ", detail='" + detail + '\'' +
                ", status=" + status +
                ", date=" + date +
                '}';
    }
}

