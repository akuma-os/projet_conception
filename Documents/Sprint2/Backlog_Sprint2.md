# Backlog — Sprint 2

## 1. Objectif global du jeu 

Le jeu à multi-niveaux suit un héros qui cherche à atteindre le sommet d'une montagne. La progression se fait à travers plusieurs chambres reliées par des portes.
Chaque chambre peut contenir :
- une porte d'entrée, par laquelle le héros arrive ;
- une porte menant vers une chambre annexe, qui pourra servir plus tard à chercher une clé ou une ressource ;
- une porte de sortie vers la chambre suivante ;
- des obstacles : rochers, murs, fossés, piliers ou pièges ;
- éventuellement des monstres qui se déplacent dans la chambre ;
- des ressources comme des clés, des armes ou des objets de soin.
À terme, la progression dépendra de missions, de ressources et de conditions d'ouverture des portes.

## 2. Objectif du sprint

Faire évoluer le jeu « Balade dans un labyrinthe » vers une deuxiéme version d'aventure composée de plusieurs chambres reliées entre elles, en améliorant les graphismes et en ajoutant des monstres et des ressources visuelles.

À la fin du sprint, le joueur doit pouvoir explorer plusieurs chambres à l'aide d'un héros représenté par un sprite, passer d'une chambre à une autre par les portes et observer des monstres qui se déplacent aléatoirement dans leur propre chambre. Les obstacles doivent empêcher le héros de traverser les éléments bloquants.

## 3. Périmètre du sprint

### Fonctionnalités incluses

- Amélioration de l'affichage graphique du jeu.
- Remplacement du carré représentant le héros par un sprite.
- Création de plusieurs chambres présentant des configurations et des éléments visuels différents.
- Affichage des murs, des obstacles et des portes dans les chambres.
- Déplacement du héros dans les chambres avec vérification des collisions avec les obstacles.
- Passage d'une chambre à une autre par les portes, sans condition de déverrouillage.
- Ajout de monstres représentés graphiquement.
- Déplacement aléatoire des monstres à l'intérieur de leur propre chambre.
- Affichage de ressources, comme des clés, des armes ou des potions, sans interaction avec le héros.
- Tests des déplacements, des collisions et des transitions entre les chambres.

### Fonctionnalités exclues de ce sprint

Les fonctionnalités suivantes seront étudiées lors des prochains sprints, selon le backlog global :

- Déverrouillage conditionnel des portes.
- Recherche et collecte des clés ou d'autres ressources.
- Missions et objectifs à accomplir pour progresser.
- Combats entre le héros et les monstres.
- Gestion des points de vie du héros et des monstres.
- Affichage et description des missions et de la progression du joueur.
- Gestion de la victoire finale au sommet de la montagne.

## 4. Fonctionnalités à réaliser

### 4.1. Amélioration des graphismes et représentation du héros

Le jeu doit proposer un affichage plus immersif qu'au Sprint 1. Le héros, précédemment représenté par un carré coloré, est désormais affiché à l'aide d'un sprite adapté à l'univers du jeu.

Les chambres, les murs, les obstacles et les portes doivent également être représentés graphiquement. 

**Critères d'acceptation :**
- Le jeu s'ouvre dans une fenêtre graphique fonctionnelle.
- Le héros est représenté par un sprite.
- Les chambres et leurs principaux éléments sont visibles.
- Les murs, les obstacles et les portes sont identifiables.
- L'affichage est actualisé après les déplacements du héros.

### 4.2. Création et exploration de plusieurs chambres

Le jeu est composé de plusieurs chambres reliées par des portes. Chaque chambre possède sa propre configuration et peut contenir des murs, des obstacles et des éléments visuels différents.

Le joueur doit pouvoir explorer les chambres accessibles et passer de l'une à l'autre en franchissant les portes prévues à cet effet. À ce stade, aucun objectif ni aucune condition particulière ne doit empêcher le passage.

**Critères d'acceptation :**
- Au moins trois chambres sont accessibles dans le jeu.
- Chaque chambre possède une configuration identifiable.
- Les portes permettant de passer d'une chambre à une autre sont visibles.
- Le héros peut entrer dans une autre chambre en franchissant une porte.
- Après la transition, le héros apparaît dans la chambre de destination à une position accessible.
- Le joueur peut poursuivre son exploration sans rencontrer de condition de déverrouillage.

### 4.3. Déplacements du héros et gestion des collisions

Le joueur conserve la possibilité de déplacer le héros dans les quatre directions. Le système doit vérifier les déplacements afin d'empêcher le héros de traverser les murs et les obstacles bloquants.

Les règles de déplacement doivent fonctionner dans chacune des chambres et rester cohérentes lors du passage d'une chambre à une autre.

**Critères d'acceptation :**
- Le héros peut se déplacer dans les quatre directions.
- Un déplacement valide modifie la position du héros.
- Le héros ne peut pas traverser les murs ni les obstacles bloquants.
- Un déplacement interdit ne modifie pas la position du héros.
- Les contraintes de déplacement sont respectées dans toutes les chambres.
- Les portes autorisent le passage vers les chambres auxquelles elles sont reliées.

### 4.4. Ajout et déplacement aléatoire des monstres

Des monstres sont ajoutés dans les chambres afin de rendre l'exploration plus vivante. Chaque monstre est représenté graphiquement et se déplace aléatoirement dans les limites de sa propre chambre.

Durant ce sprint, les monstres ne poursuivent pas le héros et ne l'attaquent pas. Le héros ne peut pas non plus les combattre ni les éliminer.

**Critères d'acceptation :**
- Les monstres sont visibles dans les chambres où ils sont placés.
- Leurs déplacements varient de manière aléatoire.
- Les monstres restent dans leur chambre.
- Les déplacements des monstres respectent les contraintes prévues pour les murs et les obstacles.
- Les monstres n'attaquent pas le héros.
- Le héros ne peut pas tuer les monstres durant ce sprint.

### 4.5. Affichage des ressources

Des ressources, comme des clés, des armes ou des potions, sont ajoutées dans les chambres afin de préparer les futures fonctionnalités d'aventure.

Durant ce sprint, ces ressources sont uniquement des éléments visuels. Elles ne peuvent pas encore être collectées et n'ont aucun effet sur le jeu.

**Critères d'acceptation :**
- Les ressources prévues sont représentées graphiquement.
- Les ressources sont visibles dans les chambres où elles sont placées.
- Le héros ne peut pas encore les collecter.
- La présence d'une ressource ne modifie pas les déplacements ni la progression du joueur.

### 4.6. Intégration et tests du jeu

Les différentes fonctionnalités doivent être intégrées afin de fournir une version jouable cohérente. Les tests doivent vérifier le fonctionnement des déplacements, des collisions, des monstres et des transitions entre les chambres.

**Critères d'acceptation :**
- Le jeu peut être lancé avec l'ensemble des fonctionnalités intégrées.
- Le héros peut explorer les différentes chambres accessibles.
- Les collisions avec les murs et les obstacles sont correctement gérées.
- Les transitions par les portes conduisent aux bonnes chambres.
- Les monstres se déplacent sans quitter leur chambre.
- Les ressources restent visibles sans être collectables.

## 5. Analyse fonctionnelle

| Fonctionnalité | Acteur / déclencheur | Comportement attendu | Résultat attendu |
|---|---|---|---|
| Lancement du jeu | Joueur : lancement de l'application | Le moteur initialise le jeu et affiche la première chambre. | La chambre, le héros et les éléments présents sont visibles. |
| Affichage du héros | Initialisation ou actualisation du jeu | Le système dessine le héros à partir de son sprite. | Le héros est représenté graphiquement dans la chambre. |
| Déplacement du héros | Joueur : touche directionnelle | Le système vérifie la position cible et les obstacles. | Le héros se déplace si le déplacement est autorisé. |
| Gestion des collisions | Demande de déplacement | Le système vérifie si la position cible est bloquante. | Le héros ne traverse pas les murs ni les obstacles. |
| Passage par une porte | Joueur : déplacement vers une porte accessible | Le système identifie la chambre reliée à la porte et effectue la transition. | Le héros apparaît à une position accessible dans la chambre de destination. |
| Affichage des chambres | Initialisation ou changement de chambre | Le système affiche la configuration de la chambre courante. | Les murs, les obstacles et les portes de la chambre sont visibles. |
| Déplacement des monstres | Évolution du jeu | Chaque monstre choisit et effectue des déplacements aléatoires autorisés dans sa chambre. | Les monstres se déplacent sans quitter leur chambre ni attaquer le héros. |
| Affichage des ressources | Initialisation de la chambre | Le système dessine les ressources présentes dans la chambre. | Les clés, armes ou potions sont visibles, mais ne peuvent pas être collectées. |
| Mise à jour de l'affichage | Évolution de l'état du jeu | Le système redessine les éléments dont la position ou l'état a changé. | Les positions du héros et des monstres sont correctement représentées. |
| Vérification du fonctionnement global | Exécution des tests | Le système vérifie les déplacements, les collisions et les transitions. | Les fonctionnalités prévues pour le sprint fonctionnent ensemble. |