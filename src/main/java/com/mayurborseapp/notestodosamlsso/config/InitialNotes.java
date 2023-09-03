package com.mayurborseapp.notestodosamlsso.config;

import com.mayurborseapp.notestodosamlsso.models.Note;
import com.mayurborseapp.notestodosamlsso.repositories.MemoriesRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialNotes implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(InitialNotes.class);

    @Autowired
    MemoriesRepository NoteRepository;

    @Override
    public void run(String... args) throws Exception {
        loadSeedData();
    }

    private void loadSeedData() {
        if (NoteRepository.count() == 0) {
            Note note1 = new Note("Call Mayur before world ends");
            Note note2 = new Note("Create a new App");

            NoteRepository.save(note1);
            NoteRepository.save(note2);
        }

        logger.info("Number of Notes: {}", NoteRepository.count());
    }

}
