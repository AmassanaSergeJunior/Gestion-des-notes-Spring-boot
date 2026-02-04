package com.gestion.gestion_notes.config;



import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gestion.gestion_notes.entity.Etudiant;
import com.gestion.gestion_notes.entity.Matiere;
import com.gestion.gestion_notes.entity.Note;
import com.gestion.gestion_notes.service.GestionNotesService;

@Configuration
public class DataLoaderConfig {
    
    @Bean
    @Profile("!console") // S'exécute uniquement si le profil n'est PAS "console"
    CommandLineRunner loadInitialData(GestionNotesService gestionNotesService) {
        return args -> {
            // Vérifier si des données existent déjà
            if (gestionNotesService.countEtudiants() > 0) {
                System.out.println("✓ Données déjà présentes dans la base.");
                return;
            }
            
            System.out.println("\n=================================================");
            System.out.println("  CHARGEMENT DES DONNÉES DE DÉMONSTRATION");
            System.out.println("=================================================\n");
            
            // Création des étudiants
            Etudiant e1 = new Etudiant("E001", "DUPONT", "Jean", 20);
            Etudiant e2 = new Etudiant("E002", "MARTIN", "Marie", 19);
            Etudiant e3 = new Etudiant("E003", "BERNARD", "Paul", 21);
            Etudiant e4 = new Etudiant("E004", "DUBOIS", "Sophie", 20);
            Etudiant e5 = new Etudiant("E005", "LEFEBVRE", "Pierre", 22);
            
            gestionNotesService.saveEtudiant(e1);
            gestionNotesService.saveEtudiant(e2);
            gestionNotesService.saveEtudiant(e3);
            gestionNotesService.saveEtudiant(e4);
            gestionNotesService.saveEtudiant(e5);
            
            System.out.println("✓ 5 étudiants créés");
            
            // Création des matières
            Matiere m1 = new Matiere("MATH01", "Mathématiques", 3.0);
            Matiere m2 = new Matiere("PHYS01", "Physique", 3.0);
            Matiere m3 = new Matiere("INFO01", "Informatique", 4.0);
            Matiere m4 = new Matiere("ANGL01", "Anglais", 2.0);
            
            gestionNotesService.saveMatiere(m1);
            gestionNotesService.saveMatiere(m2);
            gestionNotesService.saveMatiere(m3);
            gestionNotesService.saveMatiere(m4);
            
            System.out.println("✓ 4 matières créées");
            
            // Création des notes pour Jean DUPONT (très bon élève)
            gestionNotesService.saveNote(new Note(e1, m1, 15.5));
            gestionNotesService.saveNote(new Note(e1, m2, 16.0));
            gestionNotesService.saveNote(new Note(e1, m3, 17.0));
            gestionNotesService.saveNote(new Note(e1, m4, 14.5));
            
            // Création des notes pour Marie MARTIN (bonne élève)
            gestionNotesService.saveNote(new Note(e2, m1, 14.0));
            gestionNotesService.saveNote(new Note(e2, m2, 13.5));
            gestionNotesService.saveNote(new Note(e2, m3, 15.5));
            gestionNotesService.saveNote(new Note(e2, m4, 16.0));
            
            // Création des notes pour Paul BERNARD (élève moyen)
            gestionNotesService.saveNote(new Note(e3, m1, 11.0));
            gestionNotesService.saveNote(new Note(e3, m2, 10.5));
            gestionNotesService.saveNote(new Note(e3, m3, 12.0));
            gestionNotesService.saveNote(new Note(e3, m4, 11.5));
            
            // Création des notes pour Sophie DUBOIS (élève en difficulté)
            gestionNotesService.saveNote(new Note(e4, m1, 8.5));
            gestionNotesService.saveNote(new Note(e4, m2, 9.0));
            gestionNotesService.saveNote(new Note(e4, m3, 10.0));
            gestionNotesService.saveNote(new Note(e4, m4, 7.5));
            
            // Création des notes pour Pierre LEFEBVRE (élève excellent)
            gestionNotesService.saveNote(new Note(e5, m1, 18.0));
            gestionNotesService.saveNote(new Note(e5, m2, 17.5));
            gestionNotesService.saveNote(new Note(e5, m3, 19.0));
            gestionNotesService.saveNote(new Note(e5, m4, 16.0));
            
            System.out.println("✓ 20 notes créées");
            System.out.println("\n✓✓✓ DONNÉES DE DÉMONSTRATION CHARGÉES ✓✓✓");
            System.out.println("\n🌐 Accédez à l'application web sur: http://localhost:8080\n");
        };
    }
}