package com.gestion.gestion_notes.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultatEtudiant {
    private String matricule;
    private String nom;
    private String prenom;
    private Double moyenne;
    
    @Override
    public String toString() {
        return String.format("%-15s %-20s %-20s %.2f",  matricule, nom, prenom, moyenne);
    }
}