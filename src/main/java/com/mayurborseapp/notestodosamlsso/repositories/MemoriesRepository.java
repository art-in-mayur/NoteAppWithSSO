package com.mayurborseapp.notestodosamlsso.repositories;

import com.mayurborseapp.notestodosamlsso.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoriesRepository extends JpaRepository<Note, Long> {
}
