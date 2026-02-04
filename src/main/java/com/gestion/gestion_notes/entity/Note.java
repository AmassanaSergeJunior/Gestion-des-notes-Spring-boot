package com.gestion.gestion_notes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "matricule_etudiant", nullable = false)
    private Etudiant etudiant;
    
    @ManyToOne
    @JoinColumn(name = "code_matiere", nullable = false)
    private Matiere matiere;
    
    @Column(nullable = false)
    private Double valeur;
    
    // Constructeur sans l'id (auto-généré)
    public Note(Etudiant etudiant, Matiere matiere, Double valeur) {
        this.etudiant = etudiant;
        this.matiere = matiere;
        this.valeur = valeur;
    }
    
    @Override
    public String toString() {
        return "Note{" +
                "etudiant=" + etudiant.getMatricule() +
                ", matiere=" + matiere.getCode() +
                ", valeur=" + valeur +
                '}';
    }
}