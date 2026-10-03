# Backlog— Sprint 1

## Objectif du sprint

Avoir une première version jouable du projet avec une interface graphique simple.

## Fonctionnalités à réaliser

### 1. Déplacement du héros

- Création de la classe `Heros`.
- Implémentation de la méthode `deplacer()`.
- Gestion des déplacements vers le haut, le bas, la gauche et la droite.
- Liaison des déplacements aux touches du clavier.

### 2. Interface graphique

- Création de la classe `Interface`.
- Création d'une fenêtre graphique permettant de visualiser le jeu.
- Affichage du héros dans l'interface.
- Intégration du héros et de ses déplacements dans l'interface.
- Test des déplacements du héros.

### 3. Labyrinthe et contraintes de déplacement

- Création de la classe `Labyrinthe`.
- Mise en place des limites du labyrinthe.
- Empêchement du héros de sortir de la zone du labyrinthe.
- Empêchement du héros de traverser les murs.
- Implémentation de la méthode `deplacementPossible()`.

## Analyse 
1. Gestion du héros
- Le héros possède une position dans le labyrinthe.
- Le joueur peut demander un déplacement dans quatre directions.
- Le système calcule la position cible.
- Le déplacement n'est effectué que s'il est autorisé.

2. Gestion des contraintes du labyrinthe
- Le labyrinthe possède des limites.
- Le héros ne peut pas sortir de ces limites.
- Certaines positions correspondent à des murs.
- Le héros ne peut pas traverser un mur.
- La possibilité d'un déplacement est déterminée par deplacementPossible().

3. Interface graphique
- Une fenêtre permet de visualiser le jeu.
- Le héros est affiché.
- Les touches du clavier sont associées aux directions.
- L'affichage est actualisé après chaque déplacement valide.

| Fonctionnalité                  | Description                                                                | Acteur  | Entrées                                | Comportement attendu                                                                                                                   | Résultat attendu                                                               |
| ------------------------------- | -------------------------------------------------------------------------- | ------- | -------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------ |
| **Déplacement du héros**        | Permettre au joueur de déplacer le héros dans le labyrinthe.               | Joueur  | Une touche de direction : ↑ ↓ ← →      | Lorsque le joueur appuie sur une touche, le système détermine la nouvelle position demandée et vérifie si le déplacement est autorisé. | Le héros se déplace dans la direction demandée si le déplacement est possible. |
| **Déplacement vers le haut**    | Déplacer le héros d'une case vers le haut.                                 | Joueur  | Touche ↑                               | Le système demande un déplacement vers la case située au-dessus du héros.                                                              | Le héros monte d'une case si cette case est accessible.                        |
| **Déplacement vers le bas**     | Déplacer le héros d'une case vers le bas.                                  | Joueur  | Touche ↓                               | Le système demande un déplacement vers la case située en dessous du héros.                                                             | Le héros descend d'une case si cette case est accessible.                      |
| **Déplacement vers la gauche**  | Déplacer le héros d'une case vers la gauche.                               | Joueur  | Touche ←                               | Le système demande un déplacement vers la case située à gauche du héros.                                                               | Le héros se déplace d'une case vers la gauche si cette case est accessible.    |
| **Déplacement vers la droite**  | Déplacer le héros d'une case vers la droite.                               | Joueur  | Touche →                               | Le système demande un déplacement vers la case située à droite du héros.                                                               | Le héros se déplace d'une case vers la droite si cette case est accessible.    |
| **Vérification du déplacement** | Vérifier qu'un déplacement demandé respecte les contraintes du labyrinthe. | Système | Position actuelle + direction demandée | Le système vérifie si la nouvelle position se trouve dans une zone autorisée et si elle n'est pas occupée par un mur.                  | Le déplacement est accepté ou refusé.                                          |
| **Limites du labyrinthe**       | Empêcher le héros de sortir de la zone de jeu.                             | Système | Nouvelle position du héros             | Le système vérifie que la nouvelle position reste à l'intérieur des limites du labyrinthe.                                             | Le héros ne peut pas dépasser les limites.                                     |
| **Murs du labyrinthe**          | Empêcher le héros de traverser les murs.                                   | Système | Nouvelle position du héros             | Le système vérifie si la case demandée contient un mur.                                                                                | Si un mur est présent, le héros reste à sa position actuelle.                  |
| **Affichage du jeu**            | Afficher une fenêtre permettant au joueur de visualiser le jeu.            | Joueur  | —                                      | Le système affiche la fenêtre du jeu avec le labyrinthe et le héros.                                                                   | Une interface graphique visible est affichée.                                  |
| **Affichage du héros**          | Représenter le héros dans l'interface graphique.                           | Système | Position du héros                      | L'interface affiche le héros à sa position actuelle.                                                                                   | Le joueur peut voir la position du héros.                                      |
| **Mise à jour de l'affichage**  | Actualiser l'affichage après un déplacement.                               | Système | Nouvelle position du héros             | Après un déplacement valide, l'interface met à jour la représentation du héros.                                                        | Le héros apparaît à sa nouvelle position.                                      |

