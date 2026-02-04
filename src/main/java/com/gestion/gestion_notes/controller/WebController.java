package com.gestion.gestion_notes.controller;



import com.gestion.gestion_notes.dto.ResultatEtudiant;
import com.gestion.gestion_notes.entity.Etudiant;
import com.gestion.gestion_notes.entity.Matiere;
import com.gestion.gestion_notes.entity.Note;
import com.gestion.gestion_notes.service.GestionNotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    
    private final GestionNotesService gestionNotesService;
    
    @GetMapping("/")
    public String index(Model model) {
        long nbEtudiants = gestionNotesService.countEtudiants();
        long nbMatieres = gestionNotesService.countMatieres();
        
        model.addAttribute("nbEtudiants", nbEtudiants);
        model.addAttribute("nbMatieres", nbMatieres);
        
        if (nbEtudiants > 0) {
            Double moyenneClasse = gestionNotesService.calculerMoyenneClasse();
            List<ResultatEtudiant> admis = gestionNotesService.getEtudiantsAdmis();
            ResultatEtudiant premier = gestionNotesService.getPremierDeClasse();
            
            model.addAttribute("moyenneClasse", moyenneClasse);
            model.addAttribute("nbAdmis", admis.size());
            model.addAttribute("premier", premier);
        }
        
        return "index";
    }
    
    @GetMapping("/etudiants")
    public String listeEtudiants(Model model) {
        List<Etudiant> etudiants = gestionNotesService.getAllEtudiants();
        model.addAttribute("etudiants", etudiants);
        return "etudiants/liste";
    }
    
    @GetMapping("/etudiants/nouveau")
    public String nouveauEtudiant(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        return "etudiants/form";
    }
    
    @PostMapping("/etudiants/save")
    public String saveEtudiant(@ModelAttribute Etudiant etudiant, RedirectAttributes redirectAttributes) {
        gestionNotesService.saveEtudiant(etudiant);
        redirectAttributes.addFlashAttribute("message", "Étudiant ajouté avec succès !");
        return "redirect:/etudiants";
    }
    
    @GetMapping("/matieres")
    public String listeMatieres(Model model) {
        List<Matiere> matieres = gestionNotesService.getAllMatieres();
        model.addAttribute("matieres", matieres);
        return "matieres/liste";
    }
    
    @GetMapping("/matieres/nouveau")
    public String nouvelleMatiere(Model model) {
        model.addAttribute("matiere", new Matiere());
        return "matieres/form";
    }
    
    @PostMapping("/matieres/save")
    public String saveMatiere(@ModelAttribute Matiere matiere, RedirectAttributes redirectAttributes) {
        gestionNotesService.saveMatiere(matiere);
        redirectAttributes.addFlashAttribute("message", "Matière ajoutée avec succès !");
        return "redirect:/matieres";
    }
    
    @GetMapping("/notes")
    public String listeNotes(Model model) {
        List<Note> notes = gestionNotesService.getAllNotes();
        model.addAttribute("notes", notes);
        return "notes/liste";
    }
    
    @GetMapping("/notes/nouveau")
    public String nouvelleNote(Model model) {
        model.addAttribute("note", new Note());
        model.addAttribute("etudiants", gestionNotesService.getAllEtudiants());
        model.addAttribute("matieres", gestionNotesService.getAllMatieres());
        return "notes/form";
    }
    
    @PostMapping("/notes/save")
    public String saveNote(@RequestParam String matriculeEtudiant, 
                          @RequestParam String codeMatiere,
                          @RequestParam Double valeur,
                          RedirectAttributes redirectAttributes) {
        
        Etudiant etudiant = gestionNotesService.getEtudiantByMatricule(matriculeEtudiant)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
        
        Matiere matiere = gestionNotesService.getMatiereByCode(codeMatiere)
                .orElseThrow(() -> new RuntimeException("Matière non trouvée"));
        
        Note note = new Note(etudiant, matiere, valeur);
        gestionNotesService.saveNote(note);
        
        redirectAttributes.addFlashAttribute("message", "Note ajoutée avec succès !");
        return "redirect:/notes";
    }
    
    @GetMapping("/resultats")
    public String resultats(Model model) {
        List<ResultatEtudiant> resultats = gestionNotesService.getResultatsParOrdreMerite();
        model.addAttribute("resultats", resultats);
        return "resultats/classement";
    }
    
    @GetMapping("/resultats/admis")
    public String etudiantsAdmis(Model model) {
        List<ResultatEtudiant> admis = gestionNotesService.getEtudiantsAdmis();
        model.addAttribute("admis", admis);
        model.addAttribute("total", gestionNotesService.countEtudiants());
        return "resultats/admis";
    }
    
    @GetMapping("/resultats/statistiques")
    public String statistiques(Model model) {
        Double moyenneClasse = gestionNotesService.calculerMoyenneClasse();
        List<ResultatEtudiant> auDessus = gestionNotesService.getEtudiantsAuDessusMoyenneClasse();
        ResultatEtudiant premier = gestionNotesService.getPremierDeClasse();
        ResultatEtudiant dernier = gestionNotesService.getDernierDeClasse();
        
        model.addAttribute("moyenneClasse", moyenneClasse);
        model.addAttribute("auDessus", auDessus);
        model.addAttribute("premier", premier);
        model.addAttribute("dernier", dernier);
        
        return "resultats/statistiques";
    }
}