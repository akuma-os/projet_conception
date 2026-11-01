# projet\_conception
# Balade dans un labyrinthe

## Exploration de chambres à niveaux

Ce projet est réalisé dans le cadre du cours d'**Analyse et Conception de Logiciels**.Il consiste à développer un jeu vidéo multi-niveaux en Java, dans lequel le joueur incarne un héros dont l'objectif est d'atteindre le sommet d'une montagne.

Pour y parvenir, le héros doit progresser à travers plusieurs chambres reliées entre elles par des portes. Chaque chambre constitue une étape de son parcours et peut comporter différents éléments : des obstacles (rochers, murs, fossés, piliers ou pièges), des monstres ainsi que des ressources à récupérer, comme des clés, des armes ou des objets de soin.

Certaines portes permettent également d'accéder à des chambres annexes, dans lesquelles le héros pourra rechercher des clés ou des ressources nécessaires à sa progression. À terme, l'avancement dans le jeu reposera sur l'accomplissement de missions, la collecte de ressources et le respect de certaines conditions pour ouvrir les portes.

Le jeu évoluera progressivement à travers plusieurs versions, avec l'intégration de nouvelles mécaniques et fonctionnalités au fil du développement.
## Aperçu de la 2éme version de l'application 

La deuxième version propose une interface graphique permettant de contrôler le héros et d'explorer les chambres. Les déplacements sont gérés au clavier (haut, bas, gauche et droite), avec une détection des murs et des obstacles. Le passage par une porte permet également d'accéder au niveau suivant.

[Voir la démonstration du jeu](Videos/Video_Demo_Sprint2.mp4)

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