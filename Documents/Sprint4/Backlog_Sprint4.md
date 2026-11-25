# Backlog — Sprint 4 : Finalisation du jeu « Balade dans un labyrinthe »

## 1. Description globale du sprint

Le Sprint 4 constitue le dernier sprint du projet. Son objectif est de finaliser le jeu « Balade dans un labyrinthe » en complétant les fonctionnalités restantes, en améliorant les aspects graphiques et en assurant la cohérence entre les différentes mécaniques de jeu.

Ce sprint vise notamment à finaliser la gestion des missions, des combats, des dégâts et des vies du héros ainsi qu'à intégrer les sprites et les animations des personnages

L'objectif final est d'obtenir une version complète, fonctionnelle et testée du jeu, dans laquelle le joueur peut progresser à travers les quatre chambres, accomplir les missions, affronter les monstres et atteindre la princesse.

## 2. Fonctionnalités à développer

### 2.1. Gestion des missions des chambres

- Définir les missions propres à chacune des quatre chambres.
- Implémenter les conditions de réussite de chaque mission.
- Vérifier automatiquement si les conditions de chaque mission sont remplies.
- Relier la validation des missions au système de progression et aux questionnaires.
- Assurer le déblocage des portes et le passage à la chambre suivante après validation des conditions requises.

### 2.2. Gestion des combats et des dégâts

- Gérer les dégâts infligés au héros lors des attaques des monstres.
- Définir les conséquences des attaques des monstres sur les points de vie du héros.
- Vérifier la cohérence des interactions entre le héros et les monstres lors des affrontements.

### 2.3. Gestion des vies et des pièges

- Mettre en place la diminution des points de vie du héros lorsqu'il subit des dégâts.
- Détecter les pièges présents dans les chambres.
- Définir les effets des différents pièges sur le héros.
- Gérer les conséquences de la perte de tous les points de vie, notamment le retour vers la chambre précedente.
- Assurer l'actualisation de l'affichage des points de vie pendant la partie.

### 2.4. Intégration graphique des personnages

- Intégrer des sprites pour représenter les monstres.
- Ajouter les sprites de la princesse.
- Positionner la princesse dans la dernière chambre.
- Adapter l'affichage des personnages à leur environnement et à leurs déplacements.
- Mettre en place des animations graphiques pour les déplacements et les actions des personnages.

### 2.5. Finalisation de la chambre finale

- Aménager la dernière chambre pour accueillir le monstres et le chevalier par vague et non pas d'un seul coup,et fixer une case pour la princesse.
- Afficher un message ou un écran de fin de partie lorsque le jeu est terminé.

### 2.6. Interface graphique et finalisation

- Améliorer la représentation graphique des différents éléments du jeu.
- Harmoniser les sprites, les animations et les environnements des quatre chambres.
- Vérifier l'actualisation des informations affichées pendant le jeu.

### 2.7. Tests fonctionnels et globales 

- Vérifier le fonctionnement des missions et des questionnaires dans chaque chambre.
- Tester les interactions entre le héros, les monstres, les armes, les ressources et les pièges.
- Vérifier les conditions d'ouverture des portes et de progression entre les chambres.
- Tester la gestion de la perte de toutes les vies et les conditions de victoire.
- Corriger les erreurs détectées lors des tests.
- Réaliser une validation globale du jeu avant la livraison finale.

## 3. Résultat attendu

À la fin du Sprint 4, le jeu devra proposer une expérience complète permettant au joueur de :

- Parcourir les quatre chambres et accomplir les missions associées.
- Répondre aux questionnaires pour progresser.
- Collecter et utiliser les ressources disponibles.
- Affronter les monstres en tenant compte de leur difficulté et de l'utilisation éventuelle d'une arme.
- Subir des dégâts et gérer ses points de vie.
- Éviter les pièges et leurs effets.
- Atteindre la dernière chambre et retrouver la princesse.
- Déclencher la fin du jeu après avoir atteint l'objectif final.

Le sprint devra aboutir à une version finale intégrée, jouable et testée du jeu « Balade dans un labyrinthe .