

package com.gestion.gestion_notes.presentation;

import com.gestion.gestion_notes.dto.ResultatEtudiant;
import com.gestion.gestion_notes.entity.Etudiant;
import com.gestion.gestion_notes.entity.Matiere;
import com.gestion.gestion_notes.entity.Note;
import com.gestion.gestion_notes.service.GestionNotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;


@Component
@Profile("console")  // Active uniquement avec le profil "console"
@RequiredArgsConstructor
public class ConsoleRunner implements CommandLineRunner {
    
    private final GestionNotesService gestionNotesService;
    private final Scanner scanner = new Scanner(System.in);
    
    @Override
    public void run(String... args) {
        System.out.println("=================================================");
        System.out.println("  SYSTÈME DE GESTION DES NOTES DES ÉTUDIANTS");
        System.out.println("=================================================\n");
        
        // Phase de saisie des données
        saisirDonnees();
        
        // Menu interactif
        afficherMenu();
    }
    
    private void saisirDonnees() {
        System.out.println("--- SAISIE DES INFORMATIONS DE LA CLASSE ---\n");
        
        // Saisie de l'effectif
        System.out.print("Entrez l'effectif de la classe : ");
        int effectif = scanner.nextInt();
        scanner.nextLine(); // Consommer le retour à la ligne
        
        // Saisie des étudiants
        System.out.println("\n--- ENREGISTREMENT DES ÉTUDIANTS ---");
        for (int i = 0; i < effectif; i++) {
            System.out.println("\nÉtudiant " + (i + 1) + " :");
            System.out.print("  Matricule : ");
            String matricule = scanner.nextLine();
            
            System.out.print("  Nom : ");
            String nom = scanner.nextLine();
            
            System.out.print("  Prénom : ");
            String prenom = scanner.nextLine();
            
            System.out.print("  Âge : ");
            int age = scanner.nextInt();
            scanner.nextLine();
            
            Etudiant etudiant = new Etudiant(matricule, nom, prenom, age);
            gestionNotesService.saveEtudiant(etudiant);
            System.out.println("  ✓ Étudiant enregistré avec succès !");
        }
        
        // Saisie du nombre de matières
        System.out.print("\nEntrez le nombre de matières : ");
        int nombreMatieres = scanner.nextInt();
        scanner.nextLine();
        
        // Saisie des matières
        System.out.println("\n--- ENREGISTREMENT DES MATIÈRES ---");
        for (int i = 0; i < nombreMatieres; i++) {
            System.out.println("\nMatière " + (i + 1) + " :");
            System.out.print("  Code : ");
            String code = scanner.nextLine();
            
            System.out.print("  Nom : ");
            String nomMatiere = scanner.nextLine();
            
            System.out.print("  Coefficient : ");
            double coef = scanner.nextDouble();
            scanner.nextLine();
            
            Matiere matiere = new Matiere(code, nomMatiere, coef);
            gestionNotesService.saveMatiere(matiere);
            System.out.println("  ✓ Matière enregistrée avec succès !");
        }
        
        // Saisie des notes
        System.out.println("\n--- SAISIE DES NOTES ---");
        List<Etudiant> etudiants = gestionNotesService.getAllEtudiants();
        List<Matiere> matieres = gestionNotesService.getAllMatieres();
        
        for (Etudiant etudiant : etudiants) {
            System.out.println("\nNotes pour " + etudiant.getPrenom() + " " + etudiant.getNom() + " (" + etudiant.getMatricule() + ") :");
            
            for (Matiere matiere : matieres) {
                System.out.print("  " + matiere.getNom() + " : ");
                double valeurNote = scanner.nextDouble();
                scanner.nextLine();
                
                Note note = new Note(etudiant, matiere, valeurNote);
                gestionNotesService.saveNote(note);
            }
            System.out.println("  ✓ Notes enregistrées !");
        }
        
        System.out.println("\n✓✓✓ TOUTES LES DONNÉES ONT ÉTÉ ENREGISTRÉES AVEC SUCCÈS ✓✓✓\n");
    }
    
    private void afficherMenu() {
        boolean continuer = true;
        
        while (continuer) {
            System.out.println("\n=================================================");
            System.out.println("                  MENU PRINCIPAL");
            System.out.println("=================================================");
            System.out.println("a) Afficher les résultats par ordre de mérite");
            System.out.println("b) Afficher le premier et le dernier de la classe");
            System.out.println("c) Afficher les étudiants admis (moyenne >= 10)");
            System.out.println("d) Calculer et afficher la moyenne de la classe");
            System.out.println("e) Afficher les étudiants au-dessus de la moyenne");
            System.out.println("f) Sortir du programme");
            System.out.println("=================================================");
            System.out.print("Votre choix : ");
            
            String choix = scanner.nextLine().toLowerCase();
            
            switch (choix) {
                case "a":
                    afficherResultatsParOrdreMerite();
                    break;
                case "b":
                    afficherPremierEtDernier();
                    break;
                case "c":
                    afficherEtudiantsAdmis();
                    break;
                case "d":
                    afficherMoyenneClasse();
                    break;
                case "e":
                    afficherEtudiantsAuDessusMoyenne();
                    break;
                case "f":
                    System.out.println("\n👋 Merci d'avoir utilisé le système. Au revoir !");
                    continuer = false;
                    break;
                default:
                    System.out.println("\n❌ Choix invalide. Veuillez réessayer.");
            }
        }
    }
    
    private void afficherResultatsParOrdreMerite() {
        System.out.println("\n--- RÉSULTATS PAR ORDRE DE MÉRITE ---");
        List<ResultatEtudiant> resultats = gestionNotesService.getResultatsParOrdreMerite();
        
        if (resultats.isEmpty()) {
            System.out.println("Aucun résultat disponible.");
            return;
        }
        
        System.out.println("\n" + String.format("%-5s %-15s %-20s %-20s %-10s", 
            "Rang", "Matricule", "Nom", "Prénom", "Moyenne"));
        System.out.println("─".repeat(75));
        
        int rang = 1;
        for (ResultatEtudiant resultat : resultats) {
            System.out.println(String.format("%-5d %-15s %-20s %-20s %.2f", 
                rang++, 
                resultat.getMatricule(), 
                resultat.getNom(), 
                resultat.getPrenom(), 
                resultat.getMoyenne()));
        }
    }
    
    private void afficherPremierEtDernier() {
        System.out.println("\n--- PREMIER ET DERNIER DE LA CLASSE ---");
        
        ResultatEtudiant premier = gestionNotesService.getPremierDeClasse();
        ResultatEtudiant dernier = gestionNotesService.getDernierDeClasse();
        
        if (premier == null || dernier == null) {
            System.out.println("Aucun résultat disponible.");
            return;
        }
        
        System.out.println("\n🏆 PREMIER DE LA CLASSE :");
        System.out.println("   Nom : " + premier.getNom() + " " + premier.getPrenom());
        System.out.println("   Matricule : " + premier.getMatricule());
        System.out.println("   Moyenne : " + String.format("%.2f", premier.getMoyenne()));
        
        System.out.println("\n📉 DERNIER DE LA CLASSE :");
        System.out.println("   Nom : " + dernier.getNom() + " " + dernier.getPrenom());
        System.out.println("   Matricule : " + dernier.getMatricule());
        System.out.println("   Moyenne : " + String.format("%.2f", dernier.getMoyenne()));
    }
    
    private void afficherEtudiantsAdmis() {
        System.out.println("\n--- ÉTUDIANTS ADMIS (Moyenne >= 10) ---");
        
        List<ResultatEtudiant> admis = gestionNotesService.getEtudiantsAdmis();
        
        System.out.println("\nNombre d'étudiants admis : " + admis.size());
        
        if (admis.isEmpty()) {
            System.out.println("Aucun étudiant admis.");
            return;
        }
        
        System.out.println("\n" + String.format("%-15s %-20s %-20s %-10s", 
            "Matricule", "Nom", "Prénom", "Moyenne"));
        System.out.println("─".repeat(70));
        
        for (ResultatEtudiant resultat : admis) {
            System.out.println(String.format("%-15s %-20s %-20s %.2f", 
                resultat.getMatricule(), 
                resultat.getNom(), 
                resultat.getPrenom(), 
                resultat.getMoyenne()));
        }
    }
    
    private void afficherMoyenneClasse() {
        System.out.println("\n--- MOYENNE DE LA CLASSE ---");
        
        Double moyenneClasse = gestionNotesService.calculerMoyenneClasse();
        
        System.out.println("\n📊 Moyenne générale de la classe : " + String.format("%.2f", moyenneClasse));
    }
    
    private void afficherEtudiantsAuDessusMoyenne() {
        System.out.println("\n--- ÉTUDIANTS AU-DESSUS DE LA MOYENNE DE CLASSE ---");
        
        Double moyenneClasse = gestionNotesService.calculerMoyenneClasse();
        List<ResultatEtudiant> etudiantsAuDessus = gestionNotesService.getEtudiantsAuDessusMoyenneClasse();
        
        System.out.println("\nMoyenne de la classe : " + String.format("%.2f", moyenneClasse));
        System.out.println("Nombre d'étudiants au-dessus : " + etudiantsAuDessus.size());
        
        if (etudiantsAuDessus.isEmpty()) {
            System.out.println("Aucun étudiant au-dessus de la moyenne.");
            return;
        }
        
        System.out.println("\n" + String.format("%-15s %-20s %-20s %-10s", 
            "Matricule", "Nom", "Prénom", "Moyenne"));
        System.out.println("─".repeat(70));
        
        for (ResultatEtudiant resultat : etudiantsAuDessus) {
            System.out.println(String.format("%-15s %-20s %-20s %.2f", 
                resultat.getMatricule(), 
                resultat.getNom(), 
                resultat.getPrenom(), 
                resultat.getMoyenne()));
        }
    }
}
