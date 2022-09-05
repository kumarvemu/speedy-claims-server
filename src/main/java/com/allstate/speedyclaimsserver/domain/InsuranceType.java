package com.allstate.speedyclaimsserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="insurance_type")
public class InsuranceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer value;
    private String detail;

    public InsuranceType() {
    }

    public InsuranceType(Integer id, Integer value, String detail) {
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsuranceType that = (InsuranceType) o;
        return Objects.equals(id, that.id) && Objects.equals(value, that.value) && Objects.equals(detail, that.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, detail);
    }

    @Override
    public String toString() {
        return "InsuranceType{" +
                "id=" + id +
                ", value=" + value +
                ", detail='" + detail + '\'' +
                '}';
    }
}