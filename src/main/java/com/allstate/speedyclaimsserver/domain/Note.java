package com.allstate.speedyclaimsserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
 import javax.persistence.*;
 import java.time.LocalDate;
 import java.util.Objects;

@Entity
@Table(name="note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String detail;
    private LocalDate date;

    @ManyToOne()
    private Claim claim;

    public Note() {
    }

    public Note(Integer id, String detail, LocalDate date, Claim claim) {
        this.id = id;
        this.detail = detail;
        this.date = date;
        this.claim = claim;
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
        Note note = (Note) o;
        return Objects.equals(id, note.id) && Objects.equals(claim, note.claim) && Objects.equals(detail, note.detail) && Objects.equals(date, note.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, claim, detail, date);
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", claim=" + claim +
                ", detail='" + detail + '\'' +
                ", date=" + date +
                '}';
    }
}

