package com.example.thymeleaf.skierowanie.dao;

import com.example.thymeleaf.skierowanie.model.Pracownik;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracownikDao extends CrudRepository<Pracownik, Integer> {

}
