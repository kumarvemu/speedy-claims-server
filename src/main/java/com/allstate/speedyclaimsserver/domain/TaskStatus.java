package com.allstate.speedyclaimsserver.domain;
 import javax.persistence.*;
 import java.util.Objects;

@Entity
@Table(name="task_status")
public class TaskStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer value;
    private String detail;

    public TaskStatus() {
    }

    public TaskStatus(Integer id, Integer value, String detail) {
        this.id = id;
        this.value = value;
        this.detail = detail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskStatus that = (TaskStatus) o;
        return Objects.equals(id, that.id) && Objects.equals(value, that.value) && Objects.equals(detail, that.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, detail);
    }

    @Override
    public String toString() {
        return "TaskStatus{" +
                "id=" + id +
                ", value=" + value +
                ", detail='" + detail + '\'' +
                '}';
    }
}

