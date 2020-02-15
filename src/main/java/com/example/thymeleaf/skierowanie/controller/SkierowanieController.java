package com.example.thymeleaf.skierowanie.controller;

import com.example.thymeleaf.skierowanie.dao.MiejsceDao;
import com.example.thymeleaf.skierowanie.dao.PracownikDao;
import com.example.thymeleaf.skierowanie.dao.SkierowanieDoLekarzaDao;
import com.example.thymeleaf.skierowanie.dto.SkierowanieDoLekarzaDTO;
import com.example.thymeleaf.skierowanie.model.Miejsce;
import com.example.thymeleaf.skierowanie.model.Pracownik;
import com.example.thymeleaf.skierowanie.model.SkierowanieDoLekarza;
import com.example.thymeleaf.skierowanie.service.SkierowanieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/skierowanie")
public class SkierowanieController {
    @Autowired
    SkierowanieDoLekarzaDao skierowanieDoLekarzaDao;


    @Autowired
    MiejsceDao miejsceDao;

    @Autowired
    PracownikDao pracownikDao;


    SkierowanieService service;

    public SkierowanieController(SkierowanieService service) {
        this.service = service;
    }

    @ResponseBody
    @GetMapping("/test")

    public Miejsce test() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//       return  skierowanieDoLekarzaDao.findAllByPacjentAndTerminOrderById("Jankowicz", dateFormat.parse("2021-02-01"));
//return skierowanieDoLekarzaDao.test();
//return skierowanieDoLekarzaDao.test2("Jankowicz");
//return  skierowanieDoLekarzaDao.cwiczenie("asd");

        SkierowanieDoLekarza skierowanieDoLekarza = skierowanieDoLekarzaDao.findById(7).get();
        Miejsce miejsce = new Miejsce();
        miejsce.setAdres("Niwy wiscznisz 34");
        miejsce.setKosPocztowy("32-720");
        miejsce.setMiasto("Nowy WIs");
        miejsce = miejsceDao.save(miejsce);
       miejsce.getSkierowanieDoLekarzas().add(skierowanieDoLekarza);
        skierowanieDoLekarza.setMiejsce(miejsce);
        skierowanieDoLekarza = skierowanieDoLekarzaDao.save(skierowanieDoLekarza);
//        miejsce = miejsceDao.findById(miejsce.getId()).get();
//        miejsce.getSkierowanieDoLekarzas().size();
        List<Pracownik> pracowniks = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            Pracownik pracownik = new Pracownik();
            pracownik.setImie("Gunter" + i);
            pracownik = pracownikDao.save(pracownik);
            pracowniks.add(pracownik);
        }
        miejsce.setPracowniks(pracowniks);
        miejsce =miejsceDao.save(miejsce);



        return miejsce;

    }


    @GetMapping("/list") // /skierowanie/list -> list-skierowanie.html
    public String listSkierowanie(Model model) {
        model.addAttribute("skierowania", service.listSkierowanie());
        return "list-skierowanie";
    }

    @GetMapping("/{id}")
    public String getSkierowanie(@PathVariable Integer id, Model model) {
        SkierowanieDoLekarzaDTO skierowanieDoLekarza = service.getSkierowanie(id);
        model.addAttribute("skierowanie", skierowanieDoLekarza);
        return "get-skierowanie";
    }

    @GetMapping("/dodaj")
    public String dodajSkierowanie(Model model) {
        model.addAttribute("skierowanie", new SkierowanieDoLekarzaDTO());
        return "dodaj-skierowanie";
    }

    @PostMapping("/dodaj")
    public String stworzSkierowanie(
            @Valid
            @ModelAttribute SkierowanieDoLekarzaDTO skierowanieDoLekarza,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("skierowanie", skierowanieDoLekarza);
            return "dodaj-skierowanie";
        }

        service.createSkierowanie(skierowanieDoLekarza);
        return "redirect:/skierowanie/list";
    }

    @GetMapping("/modyfikuj/{id}")
    public String modyfikujSkierowanie(@PathVariable Integer id, Model model) {
        model.addAttribute("skierowanie", service.getSkierowanie(id));
        return "modifikuj-skierowanie";
    }

    @PostMapping("/modyfikuj")
    public String updateSkierowanie(
            @Valid
            @ModelAttribute SkierowanieDoLekarzaDTO skierowanieDoLekarza,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("skierowanie", skierowanieDoLekarza);
            return "modifikuj-skierowanie";
        }
        service.updateSkierowanie(skierowanieDoLekarza);
        return String.format("redirect:/skierowanie/%d", skierowanieDoLekarza.getId());
    }

    @GetMapping("/usun/{id}")
    public String usunSkierowanie(@PathVariable Integer id) {
        service.deleteSkierowanie(id);
        return "redirect:/skierowanie/list";
    }

}
