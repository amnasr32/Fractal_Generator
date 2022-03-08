# Fractal Generator (projet réalisé en binôme)


##1) Pour lancer l'interface textuelle de notre projet :
      se placer dans le dossier src et puis lancer ces commandes :

         pour compiler:  javac Start.java
         pour lancer:    java Start
         Choisissez ensuite l'option 2
##2) Pour lancer l'interface graphique de notre projet :
      Se placer dans le dossier src et puis lancer ces commandes :

        pour compiler:    javac Start.java
        pour lancer :    java Start &
        Choisissez ensuite l'option 1
##Les classes principales:
**Pour le modèle:**

Une classe Start.java qui permet de lancer notre application.

Une classe Complexe.java qui représente un nombre complexe et ses opérations

Une classe abstraite Fractales.java

Deux classes Julia.java et Mandelbrot.java qui héritent de cette classe.

Une classe Programme.java qui permet le Lancement des deux modes (Graphique et ligne de commande)
puis selon les choix de l'utilisateur :

###Pour le mode console :
  Permet de choisir l'ensemble à représenter,les paramètres correspondants et genère une photo de la fractale qu'on pourra aller visionner.

###Pour le mode IG:
  Permet de choisir l'ensemble à représenter,les paramètres correspondants .Offre des fonctionalités supplémentaire comme :

      -Générer une nouvelle fractale en choisissant les paramètres associés

      -Possibilité d'affichage en couleur ou bien en noir et blanc

      -Enregister la fractale sous forme d'image

      -Zoom in et Zoom out

      -Plusieurs déplacemnts (haut,bas,droite,gauche)

      -Revenir vers le menu pour choisir un autre ensemble

**Pour la vue :**

Une classe Affichage.java qui représente la Vue

**Pour le Controleur :**

Une classe Controleur.java qui représente le Controleur

##Pour l’architecture de l’application IG :

    On a choisit le patron MVC
    Utilisation les lambdas pour les gestionnaires de nos boutons.


## Extensions de fonctionnalité:

 Deux choix de visualisation (code couleur) possibles : Noir et blanc ou avec couleurs
 possibilité de changer de rectangle (zoom, déplacement) dans l'interface
graphique  
Autre type de fractales:  l’ensemble de Mandelbrot

##Extensions technologiques

Programmation  parallèle via l'emploi des Stream parallèles.


## Déjà fan de Fractal Generator?

Vous avez des remarques? Des idées à nous transmettre ? Merci de nous écrire sur:
```
nasramira8@gmail.com ou benidyjullia.mikia@gmail.com
```
## Enfin, un grand MERCI à tous ceux qui ont joué à notre version de Fractal Generator! Amusez-vous bien!
