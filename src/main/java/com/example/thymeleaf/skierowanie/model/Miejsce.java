package com.example.thymeleaf.skierowanie.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="fajne_miejsca_wpadaj")
public class Miejsce {

    @Id
    @GeneratedValue
    Integer id;

    String adres;
    String kosPocztowy;
    String miasto;

    @Transient
    String dyskoteka;

    @OneToMany(mappedBy = "miejsce")
    List<SkierowanieDoLekarza> skierowanieDoLekarzas = new ArrayList<>();

@OneToMany
@JoinColumn
List<Pracownik> pracowniks = new ArrayList<>();

    public List<Pracownik> getPracowniks() {
        return pracowniks;
    }

    public void setPracowniks(List<Pracownik> pracowniks) {
        this.pracowniks = pracowniks;
    }

    public Miejsce() {
    }

    public Miejsce(String adres, String kosPocztowy, String miasto, String dyskoteka) {
        this.adres = adres;
        this.kosPocztowy = kosPocztowy;
        this.miasto = miasto;
        this.dyskoteka = dyskoteka;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getKosPocztowy() {
        return kosPocztowy;
    }

    public void setKosPocztowy(String kosPocztowy) {
        this.kosPocztowy = kosPocztowy;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getDyskoteka() {
        return dyskoteka;
    }

    public void setDyskoteka(String dyskoteka) {
        this.dyskoteka = dyskoteka;
    }

    public List<SkierowanieDoLekarza> getSkierowanieDoLekarzas() {
        return skierowanieDoLekarzas;
    }

    public void setSkierowanieDoLekarzas(List<SkierowanieDoLekarza> skierowanieDoLekarzas) {
        this.skierowanieDoLekarzas = skierowanieDoLekarzas;
    }
}
