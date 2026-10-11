# projet\_conception
# Balade dans un labyrinthe

## Présentation du projet

Ce projet est réalisé dans le cadre du cours d'**Analyse et Conception de Logiciels**. Il consiste à développer une application graphique en Java permettant au joueur d'évoluer dans un labyrinthe à l'aide du clavier, en respectant les murs et les limites de la grille.

## Aperçu de la première version de l'application 

![Capture d'écran du jeu Balade dans un labyrinthe](images/Version1Jeu.jpg)

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