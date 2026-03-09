# 📚 Système de Gestion des Notes des Étudiants

## 📋 Description du Projet

Application web complète développée avec **Spring Boot** pour gérer les notes des étudiants dans une classe. Le système permet de :
- Enregistrer les étudiants, les matières et les notes
- Calculer automatiquement les moyennes pondérées
- Générer des classements par ordre de mérite
- Identifier les étudiants admis
- Consulter des statistiques détaillées

### 🎯 Fonctionnalités Principales

✅ **Gestion des Étudiants** : Ajouter, consulter et gérer les informations des étudiants (matricule, nom, prénom, âge)

✅ **Gestion des Matières** : Configurer les matières enseignées avec leurs coefficients

✅ **Saisie des Notes** : Enregistrer les notes pour chaque étudiant dans chaque matière

✅ **Calcul Automatique** : Moyennes pondérées calculées automatiquement selon les coefficients

✅ **Classement Général** : Visualisation du classement avec médailles (🥇🥈🥉) pour le top 3

✅ **Statistiques Détaillées** : 
- Premier et dernier de la classe
- Moyenne générale de la classe
- Nombre d'étudiants admis (moyenne ≥ 10)
- Étudiants au-dessus de la moyenne de classe

✅ **Badges de Mention** :
- Très Bien (moyenne ≥ 16)
- Bien (moyenne ≥ 14)
- Assez Bien (moyenne ≥ 12)
- Passable (moyenne ≥ 10)
- Ajourné (moyenne < 10)

---

## 🛠️ Technologies Utilisées

### Backend
- **Java 21**
- **Spring Boot 3.2.1**
- **Spring Data JPA** - Persistance des données
- **Spring Web MVC** - Architecture web
- **Hibernate** - ORM (Object-Relational Mapping)
- **H2 Database** - Base de données en mémoire
- **Lombok** - Réduction du code boilerplate
- **Maven** - Gestion des dépendances

### Frontend
- **Thymeleaf** - Moteur de template
- **HTML5 & CSS3** - Interface utilisateur
- **Google Fonts** (Poppins, Playfair Display) - Typographie moderne
- **Design Responsive** - Compatible mobile et desktop

### Architecture
- **Modèle MVC** (Model-View-Controller)
- **Pattern Repository** pour l'accès aux données
- **Pattern Service** pour la logique métier
- **Pattern DTO** pour le transfert de données

### Database
- **Mysql** - Gestionnaire de base de donnée
---

## 📁 Structure du Projet

```
gestion-notes/
│
├── src/
│   ├── main/
│   │   ├── java/com/gestion/notes/
│   │   │   │
│   │   │   ├── entity/                    # Entités JPA
│   │   │   │   ├── Etudiant.java         # Modèle Étudiant
│   │   │   │   ├── Matiere.java          # Modèle Matière
│   │   │   │   └── Note.java             # Modèle Note
│   │   │   │
│   │   │   ├── repository/                # Repositories Spring Data
│   │   │   │   ├── EtudiantRepository.java
│   │   │   │   ├── MatiereRepository.java
│   │   │   │   └── NoteRepository.java
│   │   │   │
│   │   │   ├── service/                   # Logique métier
│   │   │   │   └── GestionNotesService.java
│   │   │   │
│   │   │   ├── controller/                # Contrôleurs Web
│   │   │   │   └── WebController.java
│   │   │   │
│   │   │   ├── config/                    # Configuration
│   │   │   │   └── DataLoaderConfig.java  # Chargement données démo
│   │   │   │
│   │   │   ├── dto/                       # Data Transfer Objects
│   │   │   │   └── ResultatEtudiant.java
│   │   │   │
│   │   │   └── GestionNotesApplication.java  # Classe principale
│   │   │
│   │   └── resources/
│   │       ├── templates/                 # Templates Thymeleaf
│   │       │   ├── index.html            # Page d'accueil
│   │       │   ├── etudiants/            # Pages étudiants
│   │       │   ├── matieres/             # Pages matières
│   │       │   ├── notes/                # Pages notes
│   │       │   └── resultats/            # Pages résultats
│   │       │
│   │       ├── static/css/               # Fichiers CSS
│   │       │   └── style.css
│   │       │
│   │       └── application.properties    # Configuration Spring
│   │
│   └── test/                             # Tests unitaires
│
└── pom.xml                               # Configuration Maven
```

---

## ⚡ Installation et Exécution

### 📋 Prérequis

Assurez-vous d'avoir installé :
- **Java JDK 21 ou supérieur** ([Télécharger](https://www.oracle.com/java/technologies/downloads/))
- **Maven 3.6 ou supérieur** ([Télécharger](https://maven.apache.org/download.cgi))

#### Vérifier les installations :
```bash
java -version
# Doit afficher : java version "17.x.x" ou supérieur

mvn -version
# Doit afficher : Apache Maven 3.6.x ou supérieur
```

---

### 🚀 Méthode 1 : Exécution Rapide (Recommandée)

#### 1. Cloner le projet
```bash
git clone https://github.com/AmassanaSergeJunior/Gestion-des-notes-Spring-boot.git
cd Gestion-des-notes-Spring-boot
```

#### 2. Compiler le projet
```bash
mvn clean install
```

**Sortie attendue** :
```
[INFO] BUILD SUCCESS
[INFO] Total time: XX.XXX s
```
#### 3 Demarrer Xampp et lancer le serveur Apache et Mysql

#### 4. Lancer l'application
```bash
.\mvnw.cmd spring-boot:run
```

**Sortie attendue** :
```
=================================================
  CHARGEMENT DES DONNÉES DE DÉMONSTRATION
=================================================

✓ 5 étudiants créés
✓ 4 matières créées
✓ 20 notes créées

✓✓✓ DONNÉES DE DÉMONSTRATION CHARGÉES ✓✓✓

🌐 Accédez à l'application web sur: http://localhost:8080

... Started GestionNotesApplication in X.XXX seconds
```

#### 4. Ouvrir dans le navigateur
```
http://localhost:8080
```

**🎉 C'est tout ! L'application est lancée avec des données de démonstration.**

---

### 🚀 Méthode 2 : Avec le JAR

#### 1. Créer le fichier JAR
```bash
mvn clean package
```

#### 2. Exécuter le JAR
```bash
java -jar target/gestion-notes-1.0.0.jar
```

#### 3. Ouvrir dans le navigateur
```
http://localhost:8080
```

---

### 🎨 Méthode 3 : Avec un IDE

#### IntelliJ IDEA
1. **Ouvrir** : `File` → `Open` → Sélectionner le dossier `gestion-notes`
2. **Attendre** que Maven télécharge les dépendances
3. **Lancer** : Clic droit sur `GestionNotesApplication.java` → `Run`
4. **Ouvrir** : http://localhost:8080

#### Eclipse
1. **Importer** : `File` → `Import` → `Existing Maven Project`
2. **Sélectionner** le dossier `gestion-notes`
3. **Lancer** : Clic droit sur le projet → `Run As` → `Spring Boot App`
4. **Ouvrir** : http://localhost:8080

#### VS Code
1. **Ouvrir** le dossier `gestion-notes`
2. **Installer** l'extension "Extension Pack for Java"
3. **Lancer** : Ouvrir `GestionNotesApplication.java` → Cliquer sur "Run"
4. **Ouvrir** : http://localhost:8080

---

## 📊 Données de Démonstration

Au démarrage, l'application charge automatiquement des données de test :

### 👨‍🎓 5 Étudiants
| Matricule | Nom | Prénom | Âge |
|-----------|-----|--------|-----|
| E001 | DUPONT | Jean | 20 |
| E002 | MARTIN | Marie | 19 |
| E003 | BERNARD | Paul | 21 |
| E004 | DUBOIS | Sophie | 20 |
| E005 | LEFEBVRE | Pierre | 22 |

### 📖 4 Matières
| Code | Nom | Coefficient |
|------|-----|-------------|
| MATH01 | Mathématiques | 3.0 |
| PHYS01 | Physique | 3.0 |
| INFO01 | Informatique | 4.0 |
| ANGL01 | Anglais | 2.0 |

### 📝 20 Notes
Chaque étudiant a 4 notes (une par matière)

### 🏆 Classement Attendu
1. 🥇 **LEFEBVRE Pierre** - 17.67 (Très Bien)
2. 🥈 **DUPONT Jean** - 15.92 (Bien)
3. 🥉 **MARTIN Marie** - 14.67 (Bien)
4. **BERNARD Paul** - 11.33 (Assez Bien)
5. **DUBOIS Sophie** - 8.96 (Ajourné)

**Moyenne de classe** : 13.71  
**Étudiants admis** : 4/5

---

## 🎯 Utilisation de l'Application

### Page d'Accueil (/)
- Vue d'ensemble avec statistiques en temps réel
- Nombre d'étudiants, matières, moyenne de classe
- Accès rapide aux différentes sections

### Gestion des Étudiants (/etudiants)
- **Liste** : Visualiser tous les étudiants
- **Ajouter** : Créer un nouveau profil étudiant
  - Matricule (unique)
  - Nom
  - Prénom
  - Âge

### Gestion des Matières (/matieres)
- **Liste** : Visualiser toutes les matières
- **Ajouter** : Créer une nouvelle matière
  - Code (unique)
  - Nom
  - Coefficient

### Gestion des Notes (/notes)
- **Liste** : Visualiser toutes les notes
- **Ajouter** : Enregistrer une nouvelle note
  - Sélectionner un étudiant
  - Sélectionner une matière
  - Saisir la note (0-20)

### Résultats (/resultats)
- **Classement général** : Ordre de mérite avec médailles
- **Étudiants admis** : Filtrer les admis (moyenne ≥ 10)
- **Statistiques** : Vue détaillée des performances

---

## 🎨 Design et Interface

### Caractéristiques
- ✨ **Design moderne** avec palette de couleurs professionnelle
- 🎨 **Couleurs** : Bleu foncé (#2D3561) + Orange corail (#F4845F)
- 📱 **100% Responsive** : S'adapte aux mobiles, tablettes et ordinateurs
- ⚡ **Animations fluides** : Transitions et effets au survol
- 🎭 **Typographie élégante** : Playfair Display (titres) + Poppins (texte)

### Composants Visuels
- Navigation sticky avec état actif
- Cards avec effets de survol
- Tableaux interactifs
- Formulaires stylisés
- Badges colorés pour les mentions
- Médailles pour le top 3

---

## 🔧 Configuration

### Fichier `application.properties`

```properties
# Port du serveur
server.port=8080

# Base de données H2
spring.datasource.url=jdbc:h2:mem:gestion_notes
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# Console H2 (pour debug)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

### Console H2 (Debug)

Pour visualiser la base de données :
1. Lancer l'application
2. Ouvrir : http://localhost:8080/h2-console
3. Paramètres :
   - **JDBC URL** : `jdbc:h2:mem:gestion_notes`
   - **Username** : `sa`
   - **Password** : (laisser vide)
4. Cliquer sur "Connect"

---

## 🐛 Dépannage

### Port 8080 déjà utilisé
**Erreur** : `Port 8080 is already in use`

**Solution** : Modifier le port dans `application.properties`
```properties
server.port=8081
```

### Problème de compilation Maven
**Erreur** : `BUILD FAILURE`

**Solutions** :
```bash
# Nettoyer et recompiler
mvn clean install -U

# Forcer le téléchargement des dépendances
mvn dependency:purge-local-repository
```

### Page blanche ou erreur 404
**Solutions** :
1. Vérifier que le dossier `src/main/resources/templates` existe
2. Nettoyer et relancer : `mvn clean spring-boot:run`

---

## 👥 Auteur

**Amassana Serge Junior**
- GitHub : [@AmassanaSergeJunior](https://github.com/AmassanaSergeJunior)
- Projet : [Gestion-des-notes-Spring-boot](https://github.com/AmassanaSergeJunior/Gestion-des-notes-Spring-boot)

---

## 📄 Licence

Ce projet est développé dans un cadre éducatif.

---

## 🙏 Remerciements

Projet réalisé dans le cadre du cours **INF463** - Programmation Orientée Objet et Frameworks Java.

Technologies : Spring Boot, Spring Data JPA, Thymeleaf, H2 Database, Maven.

---

**🎉 Bon développement !**
