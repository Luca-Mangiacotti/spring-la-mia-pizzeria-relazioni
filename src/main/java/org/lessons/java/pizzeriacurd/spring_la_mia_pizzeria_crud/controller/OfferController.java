package org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.controller;

import org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.model.Offer;
import org.lessons.java.pizzeriacurd.spring_la_mia_pizzeria_crud.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/offers")
public class OfferController {

    @Autowired
    private OfferRepository repository;

    // AGGIUNTA DI UNA NUOVA OFFERTA (CREATE)
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("offer", new Offer());
        return "offers/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("offer") Offer offerForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "offers/create";
        }

        repository.save(offerForm);
        return "redirect:/pizzas/" + offerForm.getPizza().getId();

    }

}
