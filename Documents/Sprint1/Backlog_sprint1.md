# Backlog — Sprint 1

## 1. Objectif du sprint

Développer une première version jouable du jeu « Balade dans un labyrinthe » en réutilisant le moteur graphique fourni.

À la fin du sprint, le joueur doit pouvoir déplacer un héros représenté par un carré dans un labyrinthe fixe, à l'aide des quatre flèches du clavier, sans pouvoir traverser les murs ni sortir des limites du labyrinthe.

## 2. Périmètre du sprint

### Fonctionnalités incluses

- Affichage d'un labyrinthe fixe constitué de murs et de chemins.
- Affichage du héros sous la forme d'un carré coloré.
- Déplacement du héros vers le haut, le bas, la gauche et la droite.
- Contrôle du héros à l'aide des touches du clavier.
- Vérification des déplacements pour empêcher le passage à travers les murs et les limites.
- Mise à jour de l'affichage après chaque évolution du jeu.
- Tests de la logique de déplacement et des contraintes du labyrinthe.

### Fonctionnalités exclues de ce sprint

Les fonctionnalités suivantes seront étudiées lors des prochains sprints, selon le backlog global :

- Sprites et animations du héros.
- Création et déplacements des monstres.
- Collisions entre le héros et les monstres.
- Trésors, pièges et cases magiques.
- Téléportation.
- Génération de labyrinthes supplémentaires et gestion des niveaux.

## 3. Fonctionnalités à réaliser

### 3.1. Déplacement du héros

Le héros possède une position initiale dans le labyrinthe. Le joueur peut demander un déplacement dans l'une des quatre directions à l'aide des flèches du clavier.

Le système calcule la position cible et vérifie si le déplacement est autorisé avant de modifier la position du héros.

**Critères d'acceptation :**
- Le héros possède une position initiale.
- Les quatre directions sont prises en charge.
- Un déplacement valide modifie la position du héros d'une case.
- Un déplacement invalide ne modifie pas sa position.

### 3.2. Labyrinthe fixe et contraintes de déplacement

Le jeu utilise un labyrinthe prédéfini composé de murs et de cases accessibles.

Avant chaque déplacement, le système vérifie que la case cible se trouve dans les limites du labyrinthe et qu'elle ne correspond pas à un mur.

**Critères d'acceptation :**
- Le labyrinthe est défini à l'avance.
- Les murs et les chemins sont identifiables.
- Le héros ne peut pas sortir de la grille.
- Le héros ne peut pas traverser un mur.
- Un déplacement refusé laisse le héros à sa position actuelle.

### 3.3. Affichage graphique du jeu

Le moteur graphique fourni par le professeur est utilisé pour afficher la fenêtre du jeu et actualiser le rendu.

Le labyrinthe et le héros sont dessinés dans cette fenêtre. Le héros est représenté par un carré coloré, sans sprites ni animations.

**Critères d'acceptation :**
- Une fenêtre graphique s'ouvre au lancement du jeu.
- Le labyrinthe et le héros sont visibles.
- La position affichée du héros correspond à sa position réelle dans le jeu.
- L'affichage est actualisé après un déplacement.

### 3.4. Gestion des commandes clavier

Le contrôleur du jeu traduit les événements clavier en commandes comprises par le moteur graphique.

**Critères d'acceptation :**
- La flèche ↑ correspond à un déplacement vers le haut.
- La flèche ↓ correspond à un déplacement vers le bas.
- La flèche ← correspond à un déplacement vers la gauche.
- La flèche → correspond à un déplacement vers la droite.
- Une commande correspondant à un déplacement interdit ne permet pas au héros de traverser un mur ou une limite.

## 4. Analyse fonctionnelle

| Fonctionnalité | Acteur / déclencheur | Comportement attendu | Résultat attendu |
|---|---|---|---|
| Affichage du jeu | Lancement de l'application | Le moteur crée la fenêtre et affiche le jeu. | Le labyrinthe et le héros sont visibles. |
| Déplacement vers le haut | Joueur : touche ↑ | Le système vérifie la case située au-dessus du héros. | Le héros monte d'une case si le déplacement est autorisé. |
| Déplacement vers le bas | Joueur : touche ↓ | Le système vérifie la case située en dessous du héros. | Le héros descend d'une case si le déplacement est autorisé. |
| Déplacement vers la gauche | Joueur : touche ← | Le système vérifie la case située à gauche du héros. | Le héros se déplace d'une case vers la gauche si le déplacement est autorisé. |
| Déplacement vers la droite | Joueur : touche → | Le système vérifie la case située à droite du héros. | Le héros se déplace d'une case vers la droite si le déplacement est autorisé. |
| Vérification des limites | Demande de déplacement | Le système vérifie que la position cible appartient à la grille. | Le héros ne sort pas du labyrinthe. |
| Vérification des murs | Demande de déplacement | Le système vérifie que la case cible n'est pas un mur. | Le héros ne traverse aucun mur. |
| Mise à jour de l'affichage | Évolution de l'état du jeu | Le moteur demande le dessin de l'état actualisé. | La position du héros est correctement représentée. |

## 5. Organisation technique

Le moteur  est conservé et utilisé pour gérer l'exécution, les commandes clavier et l'affichage graphique. Les classes suivantes sont créées ou adaptées pour le labyrinthe.

| Fichier | Package | Responsabilité |
|---|---|---|
| `Cmd.java` | `engine` — fourni | Définit les commandes `LEFT`, `RIGHT`, `UP`, `DOWN` et `IDLE`. |
| `Game.java` | `engine` — fourni | Définit le contrat du jeu, avec `evolve()` et `isFinished()`. |
| `GameController.java` | `engine` — fourni | Définit le contrat du contrôleur clavier. |
| `GamePainter.java` | `engine` — fourni | Définit le contrat du dessin du jeu. |
| `GameEngineGraphical.java` | `engine` — fourni | Coordonne l'exécution du jeu et l'affichage. |
| `GraphicalInterface.java` et `DrawingPanel.java` | `engine` — fournis | Créent la fenêtre et la zone de dessin. |
| `Heros.java` | `fr.ul.acl.model` | Stocke la position du héros et gère ses déplacements. |
| `Labyrinthe.java` | `fr.ul.acl.model` | Représente la grille et vérifie les murs et les limites. |
| `LabyrintheGame.java` | `fr.ul.acl.model` | Implémente `Game` et fait évoluer l'état du jeu selon les commandes. |
| `LabyrintheController.java` | `fr.ul.acl.controller` | Implémente `GameController` et traduit les touches en commandes. |
| `LabyrinthePainter.java` | `fr.ul.acl.view` | Implémente `GamePainter` et dessine le labyrinthe et le héros. |
| `Main.java` | `fr.ul.acl.start` | Initialise le jeu, le contrôleur, le dessinateur et le moteur graphique. |
| `HerosTest.java` | `fr.ul.acl` — tests | Vérifie les déplacements valides et refusés du héros. |
| `LabyrintheTest.java` | `fr.ul.acl` — tests | Vérifie les limites et les murs. |


### 6. Répartition des tâches

| Responsable | Tâche technique | Fichiers à créer ou modifier | Branche Git |
|---|---|---|---|
| Mariem | Gérer la position du héros et implémenter son déplacement, en vérifiant que chaque déplacement est autorisé. | `Heros.java` | `feature/heros` |
| Ilias | Créer la grille fixe du labyrinthe et implémenter la vérification des murs et des limites. | `Labyrinthe.java` | `feature/labyrinthe` |
| Justine | Intégrer le modèle au moteur, transmettre les commandes au héros et lancer l'application. | `LabyrintheGame.java`, `Main.java` | `feature/game-integration` |
| Sarra | Dessiner le jeu : fond noir, murs gris et héros représenté par un carré blanc, en s'inspirant de l'exemple du professeur. | `LabyrinthePainter.java` | `feature/labyrinthe-painter` |
| Jules | Gérer les événements clavier et traduire les quatre flèches en commandes du moteur. | `LabyrintheController.java` | `feature/labyrinthe-controller` |
| Andreas | Développer les tests automatisés du héros et du labyrinthe : déplacement valide, collision avec un mur et dépassement des limites. | `HerosTest.java`, `LabyrintheTest.java` | `test/model` |

### 2. Critères de validation des tâches

| Fichier / élément | Critères d'acceptation |
|---|---|
| `Heros.java` | Le héros possède une position et se déplace d'une case lorsque le déplacement est autorisé. Sa position reste inchangée si le déplacement est interdit. |
| `Labyrinthe.java` | Le labyrinthe possède une grille fixe. La méthode `deplacementPossible(x, y)` refuse les positions hors limites et les cases contenant un mur. |
| `LabyrintheGame.java` | Les commandes reçues sont interprétées et transmises à la logique du jeu. Le déplacement respecte les règles du labyrinthe. |
| `LabyrintheController.java` | Les flèches haut, bas, gauche et droite correspondent respectivement aux commandes `Cmd.UP`, `Cmd.DOWN`, `Cmd.LEFT` et `Cmd.RIGHT`. |
| `LabyrinthePainter.java` | Le fond noir, les murs gris et le carré blanc représentant le héros sont dessinés correctement. La position du carré correspond à celle du héros. |
| `Main.java` | L'application démarre sans erreur et ouvre la fenêtre graphique. |
| `HerosTest.java` et `LabyrintheTest.java` | Les tests vérifient les déplacements autorisés et le blocage des déplacements vers les murs ou en dehors du labyrinthe. |

