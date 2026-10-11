# Sprint 1 Rétrospective

## Objectif du sprint

L'objectif de ce sprint était de mettre en place une première version jouable de « Balade dans un labyrinthe », comprenant un labyrinthe fixe, un héros représenté par un carré et une interface graphique permettant les déplacements au clavier.

## Résultats obtenus

À la fin du sprint, l'équipe a réussi à intégrer les différentes parties du projet et à obtenir une interface graphique fonctionnelle.

Le jeu permet de visualiser le labyrinthe et le héros, puis de contrôler les déplacements de ce dernier à l'aide du clavier, dans le respect des contraintes définies dans le backlog.

Le périmètre a été volontairement limité à cette version simple afin de disposer d'une base fonctionnelle avant d'ajouter des fonctionnalités plus avancées.

## Points positifs

- La répartition des tâches a permis aux membres de travailler en parallèle sur les différentes composantes du jeu.
- La séparation du modèle, du contrôleur et de la vue a permis d'organiser le code de manière structurée.
- La réutilisation du moteur graphique fourni a facilité la mise en place de l'interface.
- L'intégration des différentes classes a permis d'obtenir une première version jouable.
- Le respect du périmètre défini dans le backlog a permis de se concentrer sur les fonctionnalités essentielles.

## Points à améliorer

- Mieux anticiper les dépendances entre les classes développées par différents membres.
- Définir les signatures des méthodes et les interfaces entre les classes avant de commencer les développements en parallèle.
- Intégrer régulièrement les branches Git afin de détecter plus tôt les éventuels problèmes.
- Prévoir suffisamment de temps pour les tests et la validation des fonctionnalités.
- Améliorer l'estimation de la durée des tâches pour mieux organiser les prochains sprints.

## Décisions pour le sprint suivant

Pour le Sprint 2, l'objectif sera de faire évoluer la version actuelle du jeu en améliorant son aspect graphique, tout en conservant les fonctionnalités déjà développées.

Les principales évolutions prévues sont :

- **Remplacer le carré représentant le héros par un sprite**, afin d'obtenir une représentation plus visuelle du personnage.
- **Améliorer l'apparence du labyrinthe** en conservant sa structure fixe et les contraintes de déplacement existantes.
- **Adapter l'affichage graphique** pour intégrer les sprites sans perturber les déplacements du héros ni la gestion des collisions avec les murs.
- **Vérifier la compatibilité avec les fonctionnalités existantes**, notamment les commandes clavier et le blocage des déplacements interdits.
- **Tester et valider la nouvelle version** avant de poursuivre les évolutions prévues dans le backlog global.

À l'issue du Sprint 2, l'objectif est d'obtenir une **version v3 du jeu**, avec un héros représenté par un sprite et une interface graphique améliorée, tout en conservant un jeu fonctionnel.


## Conclusion

Ce sprint a permis de construire une première version fonctionnelle du jeu en se concentrant sur les mécanismes essentiels : affichage, déplacements et respect des contraintes du labyrinthe.

La rétrospective met en évidence l'importance de la coordination, de la définition des interfaces entre les classes et des tests réguliers. Ces enseignements seront pris en compte pour organiser le sprint suivant.