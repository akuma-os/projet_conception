# Backlog — Sprint 3 : évolution du jeu d'aventure

## 1. Description globale de l'évolution du jeu

L'objectif de ce sprint est de faire évoluer le jeu vers une aventure composée de quatre chambres thématiques. Le héros doit progresser à travers ces chambres pour atteindre le sommet d'une montagne, vaincre le chevalier qui a kidnappé la princesse et la libérer.

Chaque chambre possède un environnement, des obstacles et une mission qui lui sont propres. Le héros pourra également collecter différentes ressources, notamment des cristaux, des armes et des potions.

Cette nouvelle version introduit plusieurs évolutions :

- Un système de vies représenté par des cœurs, affichés en haut de l'écran.
- Un compteur indiquant le nombre de cristaux collectés.
- La collecte de ressources : cristaux, armes et potions.
- Le déplacement des monstres : ils se déplacent aléatoirement dans une zone définie et se dirigent vers le héros lorsque celui-ci entre dans leur zone de détection.
- Des missions propres à chaque chambre.
- Des questionnaires pouvant porter sur les opérations mathématiques, la culture générale ou le cours d'Analyse et Conception de Logiciels (ACL).

### 1.1. Règle de progression et obtention de la clé

La progression dans les chambres repose sur la réussite de la mission associée à chacune d'elles.

- Le héros doit accomplir l'objectif principal de la chambre.
- Lorsque cet objectif est atteint, la porte de sortie devient accessible ou apparaît.
- Le héros doit ensuite rejoindre la zone de questionnaire, lorsqu'elle est prévue dans la chambre.
- Le questionnaire peut proposer des opérations mathématiques, des questions de culture générale ou des questions portant sur le cours d'ACL.
- **La clé est attribuée si et seulement si le héros réussit la mission de la chambre, questionnaire compris lorsque celui-ci fait partie de la mission.**
- Le héros doit posséder la clé pour ouvrir la porte et accéder à la chambre suivante.
- La possibilité d'acheter une clé avec un nombre déterminé des cristaux à chaque niveau .

Ainsi, la réussite de la mission conditionne l'obtention de la clé. La porte ne peut pas être ouverte sans cette clé.

### 1.2. Thème et objectif général des chambres

- **Chambre 1 — Forêt :** détruire les obstacles afin d'atteindre la zone de questionnaire, réussir celui-ci et obtenir la clé.
- **Chambre 2 — Territoire des zombies :** éliminer dix zombies, puis réussir le questionnaire pour obtenir la clé.
- **Chambre 3 — Volcan :** progresser dans une zone contenant des pièges fixes et des ressources dangereuses, puis accomplir la mission de la chambre.
- **Chambre 4 — Sommet enneigé :** affronter plusieurs groupes de monstres de difficultés différentes, puis vaincre le chevalier et libérer la princesse.

L'objectif est de rendre le jeu plus interactif en combinant exploration, missions, questionnaires, collecte de ressources et affrontements.

## 2. Évolution du comportement des monstres

**En tant que joueur, je souhaite que les monstres se déplacent dans leur environnement et réagissent à la proximité du héros, afin de rendre les chambres plus vivantes et les déplacements plus intéressants.**

**Tâches :**
- Définir une zone de déplacement pour chaque monstre.
- Faire se déplacer chaque monstre de manière aléatoire dans cette zone lorsqu'aucun héros n'est détecté.
- Définir une zone de détection autour du monstre.
- Lorsque le héros entre dans cette zone, faire diriger le monstre vers sa position.
- Lorsque le héros quitte la zone de détection, reprendre le déplacement aléatoire.
- Empêcher les monstres de traverser les murs et les obstacles.
- Gérer les collisions entre les monstres, les obstacles et le héros.

**Périmètre de ce sprint :** le comportement demandé concerne le déplacement et la détection du héros. Les attaques des monstres contre le héros et les dégâts qu'ils lui infligent constituent des règles de combat distinctes.

## 3. Gestion des vies et des ressources

### 3.1. Gestion des vies du héros

**En tant que joueur, je souhaite visualiser le nombre de vies restantes du héros afin de pouvoir suivre son état pendant la partie.**

**Tâches :**
- Initialiser le héros avec quatre cœurs.
- Afficher les cœurs en haut de l'écran.
- Mettre à jour l'affichage lorsqu'il perd un cœur.
- Définir les événements qui peuvent faire perdre un cœur, notamment les pièges et les ressources dangereuses.
- Lorsque le héros perd tous ses cœurs, le faire revenir automatiquement à la chambre précédente.

**Point à préciser :** le comportement exact après le retour à la chambre précédente reste à définir : position de réapparition, conservation des ressources et état de la mission.

### 3.2. Collecte et gestion des ressources

**En tant que joueur, je souhaite pouvoir collecter différentes ressources afin de bénéficier de leurs effets ou de les utiliser pendant mon aventure.**

**Tâches :**
- Détecter le contact entre le héros et une ressource.
- Permettre la collecte de cristaux et mettre à jour le compteur affiché en haut de l'écran.
- Permettre la collecte d'armes.
- Permettre la collecte de potions.
- Ajouter les ressources collectées à l'inventaire du héros.
- Définir les effets et les modalités d'utilisation des armes et des potions.
- Distinguer les ressources bénéfiques des ressources dangereuses, comme les plantes empoisonnées.

## 4. Gestion des missions, des portes et des clés

**En tant que joueur, je souhaite accomplir la mission principale de chaque chambre et réussir les éventuelles épreuves associées afin d'obtenir la clé qui me permettra d'ouvrir la porte de sortie et de poursuivre mon aventure.**

**Tâches :**
- Associer une mission à chaque chambre.
- Définir les conditions de réussite de chaque mission.
- Vérifier automatiquement si le héros a accompli la mission.
- Afficher ou rendre accessible la porte de sortie lorsque l'objectif principal est atteint.
- Définir une zone spécifique pour accéder au questionnaire dans les chambres concernées.
- Afficher le questionnaire lorsque le héros atteint cette zone.
- Proposer différentes catégories de questions : opérations mathématiques, culture générale et cours d'ACL.
- Vérifier les réponses fournies par le joueur.
- Attribuer la clé uniquement lorsque la mission de la chambre est réussie.
- Empêcher l'ouverture de la porte si le héros ne possède pas la clé.
- Autoriser le passage vers la chambre suivante après l'ouverture de la porte.

## 5. Missions et conditions de progression par chambre

### 5.1. Chambre 1 — La forêt

**Mission principale :** détruire les obstacles présents dans la forêt afin d'atteindre la zone de questionnaire.

**Conditions de progression :**
- Détruire les obstacles nécessaires à l'accomplissement de la mission.
- Atteindre la zone de questionnaire.
- Répondre correctement aux questions proposées.
- Obtenir la clé après la réussite de la mission et du questionnaire.
- Faire apparaître ou rendre accessible la porte de sortie après la réussite de l'objectif principal.
- Utiliser la clé pour ouvrir la porte et accéder à la deuxième chambre.

### 5.2. Chambre 2 — Le territoire des zombies

**Mission principale :** éliminer les dix zombies pour faire apparaitre la porte dans la chambre.A noter que plusieurs zombies peuvent apparaitre lors du chemin vers la porte.

**Conditions de progression :**
- Éliminer dix zombies.
- Prévoir des zombies de niveau facile.
- Deux coups du héros suffisent pour éliminer un zombie.
- Après l'élimination des dix zombies, rendre la porte de sortie accessible.
- Rejoindre la zone de questionnaire et répondre correctement aux questions.
- Obtenir la clé après la réussite de la mission.
- Utiliser la clé pour ouvrir la porte et accéder à la troisième chambre.

**Règle de difficulté à préciser :** la valeur exacte des dégâts infligés au héros par un coup de monstre n'est pas encore définie. Le déplacement des monstres prévu dans ce sprint est également distingué de leur capacité à attaquer le héros.

### 5.3. Chambre 3 — Le volcan

**Mission principale :** progresser dans la zone volcanique en évitant les pièges et les ressources dangereuses,jusqu'à atteindre la zone de questionnaire , la zone peut changer d'emplacement au fil de temps.C'est pourquoi l'héro doit aller vite vers la zone sans tomber dans les pièges.

**Conditions de progression et règles de la chambre :**
- Placer des cases pièges fixes dans la chambre.
- Ne pas indiquer à l'avance au héros l'emplacement de ces pièges.
- Lorsque le héros tombe sur une case piège, lui faire perdre un demi coeur.
- Après le déclenchement du piège, replacer le héros à la position initiale de la chambre.
- Ajouter des ressources dangereuses, par exemple des herbes empoisonnées, pouvant également mettre en danger le héros.
- Rejoindre la zone de questionnaire, réussir les questions et obtenir la clé.
- la porte de sortie sera accessible dés que l'héro arrive à la zone de questionnaire.
- Utiliser la clé pour accéder à la quatrième chambre.


### 5.4. Chambre 4 — Le sommet enneigé

**Mission principale :** éliminer les différents groupes de monstres, vaincre le chevalier qui retient la princesse prisonnière et la libérer.

**Conditions de progression :**
- Éliminer cinq monstres de niveau facile.
- Éliminer trois monstres de niveau intermédiaire.
- Éliminer deux monstres de niveau difficile.
- Affronter et vaincre le chevalier.
- Libérer la princesse.
- Afficher un message de victoire lorsque l'objectif final est atteint.

**Règle de difficulté à prévoir pour le système de combat :**
- Le niveau de difficulté d'un monstre détermine les dégâts qu'il peut infliger au héros.
- Il détermine également la quantité de points de vie que le héros retire au monstre à chaque coup.
- Les valeurs exactes des dégâts et des points de vie devront être définies avant l'implémentation du système de combat.

## 6. Évolution graphique et interface utilisateur

**En tant que joueur, je souhaite disposer d'une interface qui affiche clairement les informations importantes pendant la partie.**

**Tâches :**
- Afficher les quatre cœurs du héros en haut de l'écran.
- Mettre à jour les cœurs en fonction des pertes de vie.
- Afficher le nombre de cristaux collectés.
- Mettre à jour le compteur à chaque collecte de cristal.
- Représenter visuellement les ressources présentes dans les chambres : cristaux, armes et potions.
- Différencier visuellement les environnements des quatre chambres :
  - Forêt pour la chambre 1.
  - Zombies pour la chambre 2.
  - Volcan pour la chambre 3.
  - Neige pour la chambre 4.
- Les questionnaires apparaîtront sous forme de fenêtres pop-up lorsque le héros atteindra la zone correspondante. Les questions pourront être de deux types : vrai ou faux ou à choix multiples, permettant au joueur de sélectionner une réponse parmi plusieurs propositions.

## 7. Tests et validation

**Tâches :**
- Vérifier le déplacement aléatoire des monstres dans leur zone.
- Vérifier que les monstres se dirigent vers le héros lorsque celui-ci entre dans leur zone de détection.
- Vérifier qu'ils reprennent leur déplacement aléatoire lorsque le héros quitte cette zone.
- Vérifier que les monstres ne traversent pas les murs et les obstacles.
- Vérifier la collecte des ressources et la mise à jour du compteur de cristaux.
- Vérifier l'affichage et la mise à jour des cœurs.
- Vérifier la perte d'un cœur lors du déclenchement d'un piège ou d'une ressource dangereuse.
- Vérifier le retour du héros à la chambre précédente lorsqu'il perd toutes ses vies.
- Vérifier les conditions de réussite de chaque mission.
- Vérifier que la clé n'est attribuée qu'après la réussite de la mission correspondante.
- Vérifier que la porte ne peut pas être ouverte sans clé.
- Vérifier les transitions entre les chambres.
- Vérifier la victoire finale après la défaite du chevalier et la libération de la princesse.

## 8. Résultat attendu du Sprint 3

À la fin de ce sprint, le jeu devra proposer quatre chambres aux thèmes distincts et des missions adaptées à chacune d'elles. Le héros pourra collecter des ressources, suivre son nombre de vies et de cristaux, et rencontrer des monstres capables de se déplacer dans leur environnement et de réagir à sa présence.

La progression devra être conditionnée par la réussite des missions et l'obtention de la clé correspondante. La dernière chambre mènera au combat contre le chevalier et à la libération de la princesse.
