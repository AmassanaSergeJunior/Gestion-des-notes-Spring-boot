# Système de Gestion des Notes des Étudiants - Spring Boot

## Description
Application Spring Boot pour gérer les notes des étudiants dans une classe. Le système permet de saisir les informations des étudiants, des matières et des notes, puis d'effectuer diverses analyses statistiques.

## Technologies Utilisées
- **Spring Boot 3.2.1**
- **Spring Data JPA** - Pour la persistance des données
- **H2 Database** - Base de données en mémoire
- **Lombok** - Pour réduire le code boilerplate
- **Java 17**
- **Maven** - Gestionnaire de dépendances

## Structure du Projet

```
gestion-notes/
├── src/
│   └── main/
│       ├── java/com/gestion/notes/
│       │   ├── entity/
│       │   │   ├── Etudiant.java
│       │   │   ├── Matiere.java
│       │   │   └── Note.java
│       │   ├── repository/
│       │   │   ├── EtudiantRepository.java
│       │   │   ├── MatiereRepository.java
│       │   │   └── NoteRepository.java
│       │   ├── service/
│       │   │   └── GestionNotesService.java
│       │   ├── dto/
│       │   │   └── ResultatEtudiant.java
│       │   ├── ConsoleRunner.java
│       │   └── GestionNotesApplication.java
│       └── resources/
│           └── application.properties
└── pom.xml
```

## Fonctionnalités

### Phase de Saisie
1. **Saisie de l'effectif de la classe**
2. **Enregistrement des étudiants** (matricule, nom, prénom, âge)
3. **Enregistrement des matières** (code, nom, coefficient)
4. **Saisie des notes** pour chaque étudiant dans chaque matière

### Menu Interactif
a) **Afficher les résultats par ordre de mérite** - Classe les étudiants du meilleur au moins bon avec leur moyenne

b) **Afficher le premier et le dernier de la classe** - Montre le meilleur et le moins bon étudiant

c) **Afficher les étudiants admis** - Liste les étudiants ayant une moyenne ≥ 10

d) **Calculer la moyenne de la classe** - Affiche la moyenne générale de tous les étudiants

e) **Afficher les étudiants au-dessus de la moyenne** - Liste les étudiants ayant une moyenne supérieure ou égale à la moyenne de classe

f) **Sortir du programme**

## Prérequis
- Java 17 ou supérieur
- Maven 3.6 ou supérieur

## Installation et Exécution

### 1. Cloner ou télécharger le projet

### 2. Compiler le projet
```bash
cd gestion-notes
mvn clean install
```

### 3. Exécuter l'application
```bash
mvn spring-boot:run
```

Ou avec Java directement :
```bash
java -jar target/gestion-notes-1.0.0.jar
```

## Exemple d'Utilisation

```
=================================================
  SYSTÈME DE GESTION DES NOTES DES ÉTUDIANTS
=================================================

--- SAISIE DES INFORMATIONS DE LA CLASSE ---

Entrez l'effectif de la classe : 3

--- ENREGISTREMENT DES ÉTUDIANTS ---

Étudiant 1 :
  Matricule : E001
  Nom : DUPONT
  Prénom : Jean
  Âge : 20
  ✓ Étudiant enregistré avec succès !

Étudiant 2 :
  Matricule : E002
  Nom : MARTIN
  Prénom : Marie
  Âge : 19
  ✓ Étudiant enregistré avec succès !

...

Entrez le nombre de matières : 3

--- ENREGISTREMENT DES MATIÈRES ---

Matière 1 :
  Code : MATH01
  Nom : Mathématiques
  Coefficient : 3
  ✓ Matière enregistrée avec succès !

...

--- SAISIE DES NOTES ---

Notes pour Jean DUPONT (E001) :
  Mathématiques : 15
  Physique : 14
  Informatique : 16
  ✓ Notes enregistrées !

...

=================================================
                  MENU PRINCIPAL
=================================================
a) Afficher les résultats par ordre de mérite
b) Afficher le premier et le dernier de la classe
c) Afficher les étudiants admis (moyenne >= 10)
d) Calculer et afficher la moyenne de la classe
e) Afficher les étudiants au-dessus de la moyenne
f) Sortir du programme
=================================================
Votre choix : a

--- RÉSULTATS PAR ORDRE DE MÉRITE ---

Rang  Matricule       Nom                  Prénom               Moyenne   
───────────────────────────────────────────────────────────────────────────
1     E001            DUPONT               Jean                 15.00
2     E002            MARTIN               Marie                13.50
3     E003            BERNARD              Paul                 11.20

```

## Modèle de Données

### Entité Etudiant
- `matricule` (String, clé primaire)
- `nom` (String)
- `prenom` (String)
- `age` (Integer)
- Relation One-to-Many avec Note

### Entité Matiere
- `code` (String, clé primaire)
- `nom` (String)
- `coef` (Double)
- Relation One-to-Many avec Note

### Entité Note
- `id` (Long, auto-généré)
- `etudiant` (Many-to-One)
- `matiere` (Many-to-One)
- `valeur` (Double)

## Calcul de la Moyenne

La moyenne d'un étudiant est calculée selon la formule :

```
Moyenne = Σ(Note × Coefficient) / Σ(Coefficients)
```

## Console H2 (Optionnel)

Pour visualiser la base de données pendant l'exécution :
1. Accédez à http://localhost:8080/h2-console
2. URL JDBC : `jdbc:h2:mem:gestion_notes`
3. Username : `sa`
4. Password : (laisser vide)

## Auteur
Projet de TP - Gestion des Notes des Étudiants

## Licence
Projet éducatif