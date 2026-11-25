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
- **Gestion de la progression :** mise en place de la logique d'accessibilité des portes, d'attribution des clés et de transition entre les chambres. L'intégration avec la validation automatique des missions reste à finaliser.
- **Évolution graphique :** amélioration des environnements graphiques des chambres afin de les différencier selon leurs thèmes.
- **Déplacement des monstres :** déplacement aléatoire dans une zone définie et poursuite du héros lorsqu'il entre dans leur zone de détection.
## Fonctionnalités non réalisées

Les fonctionnalités suivantes, prévues dans le backlog, n'ont pas été finalisées pendant ce sprint :

- **Système de combat :** gestion des attaques, des dégâts et de l'élimination des monstres.
- **Gestion des missions :** création des missions spécifiques aux quatre chambres et vérification automatique de leurs conditions de réussite.
- **Gestion des vies :** initialisation du héros avec quatre cœurs, affichage des vies et gestion de leur diminution.
- **Collecte des ressources :** récupération des cristaux, des armes et des potions, ainsi que gestion de l'inventaire.
- **Gestion des pièges :** détection des cases pièges et des ressources dangereuses et application de leurs effets sur le héros.
- **Interface utilisateur :** affichage et actualisation du compteur de cristaux et des cœurs.
- **Tests d'intégration :** validation globale des interactions entre les monstres, les missions, les ressources, les vies et les portes.

## Bilan

Le Sprint 3 a permis de réaliser trois évolutions principales : l'intégration du système de questionnaires et de progression,le déplacmement aléatoire des monstres et la poursuite de l'héro ainsi que l'évolution graphique des environnements des chambres.

Le système de questionnaires permet désormais au joueur de répondre à des questions de différentes catégories et de différents types. La logique de progression prend également en charge l'accessibilité des portes, l'attribution des clés et les transitions entre les chambres. Cependant, le lien complet entre la réussite des missions et l'obtention des clés reste à intégrer.

La majorité des autres fonctionnalités prévues, notamment la gestion des vies et des ressources ainsi que les missions, n'a pas été finalisée.

L'objectif du Sprint 3 est donc partiellement atteint. Les fonctionnalités restantes devront être reprises et intégrées lors du sprint suivant.