package com.gestion.gestion_notes.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "matieres")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matiere {
    
    @Id
    @Column(unique = true, nullable = false)
    private String code;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false)
    private Double coef;
    
    @OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes = new ArrayList<>();
    
    // Constructeur sans la liste de notes
    public Matiere(String code, String nom, Double coef) {
        this.code = code;
        this.nom = nom;
        this.coef = coef;
    }
    
    @Override
    public String toString() {
        return "Matiere{" +
                "code='" + code + '\'' +
                ", nom='" + nom + '\'' +
                ", coef=" + coef +
                '}';
    }
}