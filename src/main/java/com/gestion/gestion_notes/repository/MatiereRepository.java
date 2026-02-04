package com.gestion.gestion_notes.repository;

import com.gestion.gestion_notes.entity.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, String> {
    
}