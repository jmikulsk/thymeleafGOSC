package com.example.thymeleaf.skierowanie.service;

import com.example.thymeleaf.TestController;
import com.example.thymeleaf.skierowanie.dao.SkierowanieDoLekarzaDao;
import com.example.thymeleaf.skierowanie.dto.SkierowanieDoLekarzaDTO;
import com.example.thymeleaf.skierowanie.model.SkierowanieDoLekarza;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Primary
@Service
public class SkierowanieDBService implements SkierowanieService {

    SkierowanieDoLekarzaDao dao;
    SkierowanieMapper mapper;

    public SkierowanieDBService(SkierowanieDoLekarzaDao dao,
                                SkierowanieMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public Collection<SkierowanieDoLekarzaDTO> listSkierowanie() {
        return mapper.toDTO(dao.findAll());
    }

    @Override
    public SkierowanieDoLekarzaDTO getSkierowanie(Integer id) {
        return dao.findById(id)
                .map(skierowanieDoLekarza -> mapper.toDTO(skierowanieDoLekarza))
            .orElseThrow(() -> new TestController.NotFoundException());
    }

    @Override
    public void deleteSkierowanie(Integer id) {
        dao.deleteById(getSkierowanie(id).getId());
    }

    @Override
    public SkierowanieDoLekarzaDTO updateSkierowanie(SkierowanieDoLekarzaDTO skierowanie) {
        SkierowanieDoLekarza skierowanieDoLekarza = dao.findById(skierowanie.getId()).get();
        skierowanieDoLekarza.setLekarz(skierowanie.getLekarz());
        skierowanieDoLekarza.setPacjent(skierowanie.getPacjent());
        skierowanieDoLekarza.setTermin(skierowanie.getTermin());
        return mapper.toDTO(dao.save(skierowanieDoLekarza));
    }

    @Override
    public SkierowanieDoLekarzaDTO createSkierowanie(SkierowanieDoLekarzaDTO skierowanie) {
        return mapper.toDTO(
                dao.save(
                        mapper.toDB(skierowanie)));
    }

}
