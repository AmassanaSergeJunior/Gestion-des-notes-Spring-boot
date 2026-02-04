package com.gestion.gestion_notes.service;


import com.gestion.gestion_notes.dto.ResultatEtudiant;
import com.gestion.gestion_notes.entity.Etudiant;
import com.gestion.gestion_notes.entity.Matiere;
import com.gestion.gestion_notes.entity.Note;
import com.gestion.gestion_notes.repository.EtudiantRepository;
import com.gestion.gestion_notes.repository.MatiereRepository;
import com.gestion.gestion_notes.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class GestionNotesService{
    
    private final EtudiantRepository etudiantRepository;
    private final MatiereRepository matiereRepository;
    private final NoteRepository noteRepository;
    
    // Sauvegarder un étudiant
    public Etudiant saveEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }
    
    // Sauvegarder une matière
    public Matiere saveMatiere(Matiere matiere) {
        return matiereRepository.save(matiere);
    }
    
    // Sauvegarder une note
    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }
    
    // Récupérer tous les étudiants
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }
    
    // Récupérer toutes les matières
    public List<Matiere> getAllMatieres() {
        return matiereRepository.findAll();
    }
    
    // Récupérer toutes les notes
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }
    
    // Récupérer un étudiant par matricule
    public Optional<Etudiant> getEtudiantByMatricule(String matricule) {
        return etudiantRepository.findById(matricule);
    }
    
    // Récupérer une matière par code
    public Optional<Matiere> getMatiereByCode(String code) {
        return matiereRepository.findById(code);
    }
    
    // Calculer la moyenne d'un étudiant
    public Double calculerMoyenneEtudiant(String matricule) {
        List<Note> notes = noteRepository.findByEtudiantMatricule(matricule);
        
        if (notes.isEmpty()) {
            return 0.0;
        }
        
        double sommeNotesPonderees = 0.0;
        double sommeCoefficients = 0.0;
        
        for (Note note : notes) {
            sommeNotesPonderees += note.getValeur() * note.getMatiere().getCoef();
            sommeCoefficients += note.getMatiere().getCoef();
        }
        
        return sommeCoefficients > 0 ? sommeNotesPonderees / sommeCoefficients : 0.0;
    }
    
    // Obtenir les résultats par ordre de mérite
    public List<ResultatEtudiant> getResultatsParOrdreMerite() {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        
        List<ResultatEtudiant> resultats = etudiants.stream()
            .map(etudiant -> new ResultatEtudiant(
                etudiant.getMatricule(),
                etudiant.getNom(),
                etudiant.getPrenom(),
                calculerMoyenneEtudiant(etudiant.getMatricule())
            ))
            .sorted((r1, r2) -> r2.getMoyenne().compareTo(r1.getMoyenne()))
            .collect(Collectors.toList());
        
        return resultats;
    }
    
    // Obtenir le premier de la classe
    public ResultatEtudiant getPremierDeClasse() {
        List<ResultatEtudiant> resultats = getResultatsParOrdreMerite();
        return resultats.isEmpty() ? null : resultats.get(0);
    }
    
    // Obtenir le dernier de la classe
    public ResultatEtudiant getDernierDeClasse() {
        List<ResultatEtudiant> resultats = getResultatsParOrdreMerite();
        return resultats.isEmpty() ? null : resultats.get(resultats.size() - 1);
    }
    
    // Obtenir les étudiants admis (moyenne >= 10)
    public List<ResultatEtudiant> getEtudiantsAdmis() {
        return getResultatsParOrdreMerite().stream()
            .filter(resultat -> resultat.getMoyenne() >= 10.0)
            .collect(Collectors.toList());
    }
    
    // Calculer la moyenne de la classe
    public Double calculerMoyenneClasse() {
        List<ResultatEtudiant> resultats = getResultatsParOrdreMerite();
        
        if (resultats.isEmpty()) {
            return 0.0;
        }
        
        double sommeMoyennes = resultats.stream()
            .mapToDouble(ResultatEtudiant::getMoyenne)
            .sum();
        
        return sommeMoyennes / resultats.size();
    }
    
    // Obtenir les étudiants au-dessus de la moyenne de classe
    public List<ResultatEtudiant> getEtudiantsAuDessusMoyenneClasse() {
        Double moyenneClasse = calculerMoyenneClasse();
        
        return getResultatsParOrdreMerite().stream()
            .filter(resultat -> resultat.getMoyenne() >= moyenneClasse)
            .collect(Collectors.toList());
    }
    
    // Vérifier si un étudiant existe
    public boolean etudiantExists(String matricule) {
        return etudiantRepository.existsById(matricule);
    }
    
    // Vérifier si une matière existe
    public boolean matiereExists(String code) {
        return matiereRepository.existsById(code);
    }
    
    // Compter le nombre d'étudiants
    public long countEtudiants() {
        return etudiantRepository.count();
    }
    
    // Compter le nombre de matières
    public long countMatieres() {
        return matiereRepository.count();
    }
}