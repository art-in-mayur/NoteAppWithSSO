package com.mayurborseapp.notestodosamlsso.controllers;

import java.time.Instant;
import java.time.ZoneId;

import javax.validation.Valid;

import com.mayurborseapp.notestodosamlsso.models.Note;
import com.mayurborseapp.notestodosamlsso.repositories.MemoriesRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NoteController {
    private final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private MemoriesRepository NoteRepository;
    @GetMapping("/")
    public ModelAndView index(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal) {
        logger.info("request to GET index");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("noteItems", NoteRepository.findAll());
        modelAndView.addObject("today", Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek());
        String emailAddress = principal.getFirstAttribute("emailAddress");
        modelAndView.addObject("emailAddress", emailAddress);
        modelAndView.addObject("userAttributes", principal.getAttributes());
        return modelAndView;
    }


    @PostMapping("/note")
    public String createNote(@Valid Note note, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-note-item";
        }

        note.setCreatedDate(Instant.now());
        note.setModifiedDate(Instant.now());
        NoteRepository.save(note);
        return "redirect:/";
    }

    @PostMapping("/note/{id}")
    public String updateNote(@PathVariable("id") long id, @Valid Note note, BindingResult result, Model model) {
        if (result.hasErrors()) {
            note.setId(id);
            return "update-note-item";
        }

        note.setModifiedDate(Instant.now());
        NoteRepository.save(note);
        return "redirect:/";
    }

}
