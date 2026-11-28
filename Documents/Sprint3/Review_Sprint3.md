# Sprint 3 Review

## Objectif du sprint

L'objectif du Sprint 3 était de faire évoluer le jeu « Balade dans un labyrinthe » en ajoutant des mécaniques de jeu permettant au héros d'accomplir des missions, d'affronter des monstres, de collecter des ressources et de progresser à travers quatre chambres thématiques.

Le résultat attendu était une version intégrant le déplacement des monstres, la gestion des vies et des ressources, les missions propres à chaque chambre, les questionnaires et un environnement graphique adapté aux différents thèmes.

## Fonctionnalités réalisées et validées

- **Gestion des questions :** création des classes `Question` et `Questionnaire` pour représenter les questions et gérer les réponses du joueur.
- **Organisation des catégories :** constitution d'une banque de questions réparties en trois catégories : mathématiques, culture générale et ACL.
- **Types de questionnaires :** prise en charge des questions à choix multiples et vrai/faux.
- **Zones de questionnaire :** définition de zones spécifiques dans les chambres concernées pour déclencher les questionnaires.
- **Affichage des questionnaires :** affichage des questions sous forme de fenêtres pop-up lorsque le héros atteint la zone correspondante.
- **Vérification des réponses :** vérification des réponses saisies et sélection d'une nouvelle question en cas de réponse incorrecte.
- **Gestion de la progression :** mise en place de la logique d'accessibilité des portes, d'attribution des clés et de transition entre les chambres.
- **Évolution graphique :** amélioration des environnements graphiques des chambres afin de les différencier selon leurs thèmes.
- **Déplacement des monstres :** mise en place de leur déplacement aléatoire et de leur poursuite du héros lorsque celui-ci entre dans leur zone de détection.
- **Système d'attaque du héros :** mise en place de la possibilité pour le héros d'attaquer les monstres à proximité.
- **Gestion des dégâts :** définition du nombre de coups nécessaires pour éliminer chaque monstre selon son niveau de difficulté, ainsi que la gestion de son élimination. 
- **Collecte des ressources :** mise en place de la récupération des ressources, notamment les cristaux, les potions, les armes et les clés, ainsi que de leur gestion dans l'inventaire.


## Fonctionnalités non réalisées

Les fonctionnalités suivantes, prévues dans le backlog, n'ont pas été finalisées pendant ce sprint :

- **Système de combat :** finalisation de la gestion des attaques, des dégâts et de l'élimination des monstres.
- **Gestion des missions :** création des missions spécifiques aux quatre chambres et vérification automatique de leurs conditions de réussite.
- **Gestion des vies :** gestion complète de la diminution des points de vie et de leurs conséquences sur le déroulement du jeu.
- **Gestion des pièges :** détection des cases pièges et application de leurs effets sur le héros.
- **Interface utilisateur :** finalisation de l'affichage et de l'actualisation des cœurs et des différents indicateurs du jeu.
- **Tests  :** validation globale .

## Bilan

Le Sprint 3 a permis de réaliser plusieurs évolutions importantes du jeu, notamment l'intégration du système de questionnaires et de progression, le déplacement , la détection et l'élimination des monstres, l'amélioration graphique des environnements et la collecte des ressources avec leur gestion dans l'inventaire.

Le système de questionnaires permet désormais au joueur de répondre à des questions de différentes catégories et de différents types. La gestion de la progression prend également en charge l'accessibilité des portes, l'attribution des clés et les transitions entre les chambres.

La collecte des ressources et leur affichage dans l'inventaire ont également été intégrés au projet à la suite du travail de développement et de fusion des différentes branches.

Cependant, plusieurs fonctionnalités prévues dans le backlog restent à finaliser, notamment la gestion complète des missions, des vies, des pièges et du système de combat.

L'objectif du Sprint 3 est donc partiellement atteint. Les fonctionnalités restantes devront être reprises et approfondies lors du sprint suivant.
