package com.mayurborseapp.notestodosamlsso.controllers;

import com.mayurborseapp.notestodosamlsso.models.Note;
import com.mayurborseapp.notestodosamlsso.repositories.MemoriesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NoteFormController {
    
    @Autowired
    private MemoriesRepository NoteRepository;

    @GetMapping("/create-note")
    public String showCreateForm(Note note){
        return "add-note-item";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Note note = NoteRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Note id: " + id + " not found"));
    
        model.addAttribute("note", note);
        return "update-note-item";
    }

    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable("id") long id, Model model) {
        Note note = NoteRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Note id: " + id + " not found"));
    
        NoteRepository.delete(note);
        return "redirect:/";
    }

    
}
