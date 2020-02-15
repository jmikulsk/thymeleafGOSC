package com.example.thymeleaf.skierowanie.service;

import com.example.thymeleaf.skierowanie.dto.SkierowanieDoLekarzaDTO;
import com.example.thymeleaf.skierowanie.model.SkierowanieDoLekarza;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SkierowanieMapper {

    SkierowanieDoLekarzaDTO toDTO(SkierowanieDoLekarza skierowanieDoLekarza);

    SkierowanieDoLekarza toDB(SkierowanieDoLekarzaDTO skierowanieDoLekarzaDTO);

    List<SkierowanieDoLekarzaDTO> toDTO(List<SkierowanieDoLekarza> skierowanieDoLekarzaList);
}
