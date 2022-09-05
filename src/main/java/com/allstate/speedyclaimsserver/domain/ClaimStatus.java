package com.allstate.speedyclaimsserver.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="claim_status")
public class ClaimStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String detail;
    private Boolean open;

    public ClaimStatus() {
    }

    public ClaimStatus(Integer id, String detail, Boolean open) {
        this.id = id;
        this.detail = detail;
        this.open = open;
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

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClaimStatus that = (ClaimStatus) o;
        return Objects.equals(id, that.id) && Objects.equals(detail, that.detail) && Objects.equals(open, that.open);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, detail, open);
    }

    @Override
    public String toString() {
        return "ClaimStatus{" +
                "id=" + id +
                ", detail='" + detail + '\'' +
                ", open=" + open +
                '}';
    }
}

