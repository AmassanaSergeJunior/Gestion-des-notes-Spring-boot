package com.gestion.gestion_notes.repository;

import com.gestion.gestion_notes.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    
    @Query("SELECT n FROM Note n WHERE n.etudiant.matricule = :matricule")
    List<Note> findByEtudiantMatricule(@Param("matricule") String matricule);
    
    @Query("SELECT n FROM Note n WHERE n.matiere.code = :code")
    List<Note> findByMatiereCode(@Param("code") String code);
}
