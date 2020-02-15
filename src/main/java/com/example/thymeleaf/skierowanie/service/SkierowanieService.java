package com.example.thymeleaf.skierowanie.service;

import com.example.thymeleaf.skierowanie.dto.SkierowanieDoLekarzaDTO;
import com.example.thymeleaf.skierowanie.model.SkierowanieDoLekarza;

import java.util.Collection;

public interface SkierowanieService {

    Collection<SkierowanieDoLekarzaDTO> listSkierowanie();
    SkierowanieDoLekarzaDTO getSkierowanie(Integer id);
    void deleteSkierowanie(Integer id);
    SkierowanieDoLekarzaDTO createSkierowanie(SkierowanieDoLekarzaDTO skierowanie);
    SkierowanieDoLekarzaDTO updateSkierowanie(SkierowanieDoLekarzaDTO skierowanie);

}
