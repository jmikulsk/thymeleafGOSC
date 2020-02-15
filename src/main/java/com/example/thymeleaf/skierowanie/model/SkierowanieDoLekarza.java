package com.example.thymeleaf.skierowanie.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "Skierowania")
@JsonIgnoreProperties(value = "miejsce")
public class SkierowanieDoLekarza {

    @Id
    @GeneratedValue
    @Column
    int id;

    @Column(nullable = false)
    String lekarz;

    @Column(nullable = false)
    String pacjent;

    Date termin;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    Miejsce miejsce;

    public SkierowanieDoLekarza() {
    }

    public SkierowanieDoLekarza(int id, String lekarz, String pacjent, Date termin) {
        this.id = id;
        this.lekarz = lekarz;
        this.pacjent = pacjent;
        this.termin = termin;
    }

    public Miejsce getMiejsce() {
        return miejsce;
    }

    public void setMiejsce(Miejsce miejsce) {
        this.miejsce = miejsce;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLekarz() {
        return lekarz;
    }

    public void setLekarz(String lekarz) {
        this.lekarz = lekarz;
    }

    public String getPacjent() {
        return pacjent;
    }

    public void setPacjent(String pacjent) {
        this.pacjent = pacjent;
    }

    public Date getTermin() {
        return termin;
    }

    public void setTermin(Date termin) {
        this.termin = termin;
    }
}
