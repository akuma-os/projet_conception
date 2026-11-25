# projet\_conception

# Balade dans un labyrinthe

## Exploration de chambres à niveaux

Ce projet est réalisé dans le cadre du cours d'**Analyse et Conception de Logiciels (ACL)**. Il consiste à développer un jeu vidéo d'aventure multi-niveaux en Java, dans lequel le joueur incarne un héros qui doit atteindre le sommet d'une montagne afin de vaincre le chevalier ayant kidnappé la princesse et de la libérer.

Pour accomplir cette mission, le héros doit traverser quatre chambres thématiques reliées entre elles par des portes. Chaque chambre représente une étape de son aventure et possède son propre environnement, ses obstacles, ses monstres et ses objectifs.

Les quatre chambres sont organisées autour des thèmes suivants :

- **Chambre 1 — La forêt :** explorer la forêt et détruire les obstacles afin de progresser.
- **Chambre 2 — Le territoire des zombies :** affronter les zombies et éliminer les monstres présents dans la chambre.
- **Chambre 3 — Le volcan :** traverser une zone volcanique en évitant les pièges et les ressources dangereuses.
- **Chambre 4 — Le sommet enneigé :** affronter différents groupes de monstres, vaincre le chevalier et libérer la princesse.

Au cours de son aventure, le héros pourra également collecter différentes ressources, notamment des cristaux, des armes et des potions. Il devra accomplir les missions associées aux chambres et réussir les éventuels questionnaires pour obtenir les clés nécessaires à l'ouverture des portes et à la poursuite de son parcours.

Le jeu évolue progressivement à travers plusieurs sprints de développement, avec l'intégration de nouveaux environnements graphiques, de mécaniques de jeu et de fonctionnalités permettant d'enrichir l'expérience du joueur.

## Aperçu de la 2ème version de l'application — Sprint 2

La deuxième version propose une interface graphique permettant de contrôler le héros et d'explorer les chambres. Les déplacements sont gérés au clavier (haut, bas, gauche et droite), avec une détection des murs et des obstacles. Le passage par une porte permet également d'accéder au niveau suivant.

[Voir la démonstration du jeu — Sprint 2](Videos/Video_Demo_Sprint2.mp4)

## Aperçu de la 3ème version de l'application — Sprint 3

La troisième version apporte une évolution du jeu avec l'introduction de chambres aux environnements graphiques distincts, correspondant aux différents thèmes de l'aventure : forêt, territoire des zombies, volcan et sommet enneigé.

Cette version intègre également de nouvelles mécaniques liées à la progression du héros, notamment :

- La gestion des questionnaires portant sur les mathématiques, la culture générale et le cours d'ACL.
- La vérification des réponses et la gestion de la progression du joueur.
- L'attribution des clés et la gestion des portes de sortie.
- Les conditions d'accès aux chambres suivantes.
- L'intégration graphique de nouveaux éléments du jeu, notamment les monstres et les ressources.

Ces évolutions constituent une étape supplémentaire dans le développement du jeu et préparent l'intégration des autres fonctionnalités prévues, telles que la gestion complète des missions, des combats, des vies et de l'inventaire.

[Voir la démonstration du jeu — Sprint 3](Videos/Video_Demo_Sprint3.mp4)

## Crédits graphiques

Les sprites et certains éléments graphiques utilisés dans le jeu, notamment des ressources et des éléments de décor, proviennent de la bibliothèque de ressources graphiques **CraftPix**.

Site web : [https://craftpix.net/](https://craftpix.net/)

## Technologies utilisées

- **Java** : développement de l'application.
- **Apache Maven** : compilation, gestion des dépendances et exécution du projet.
- **JUnit** : tests unitaires.
- **Git** : gestion de versions et travail collaboratif.

## Architecture du projet

Le projet suit la structure standard Maven.

- `src/main/java` : code source de l'application.
- `src/test/java` : tests unitaires.
- `pom.xml` : configuration Maven et gestion des dépendances.
- `.gitignore` : fichiers et dossiers exclus du suivi Git.

## Prérequis

- Un JDK compatible avec le projet.
- Apache Maven.

Vérifiez leur installation :

```sh
java -version
mvn -version
```

## Exécution des tests

Depuis la racine du projet, exécutez :

```sh
mvn test
```

Cette commande compile le projet et exécute les tests unitaires.

## Lancement de l'application

Pour lancer l'application graphique, exécutez :

```sh
mvn exec:java
```

Cette commande démarre le jeu du labyrinthe.