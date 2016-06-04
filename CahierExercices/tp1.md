<div class="pb"></div>

# TP 1 : Premier exercice

## Objectifs
- Utiliser SceneBuilder avec e(fx)clipse / Intellij
- Réaliser un simple "hello world"

## Installer et configurer Scene Builder

Installer la version qui correspond à votre système.

Afin de pouvoir appeler SceneBuilder depuis Eclipse facilement, il suffit de le rajouter dans
la configuration de e(fx)clipse :

    Window > Preferences... > JavaFX > Browse... > indiquer l'emplacement de SceneBuilder

Une fois cette manipulation effectuée, dans la vue Package Hierarchy on pourra faire un
clic-droit sur un fichier .fxml et "Open with SceneBuilder".

## Créer un Hello, world!

1. Utiliser le wizard d'Eclipse
<br>

        New Project... > JavaFX > New JavaFX Project

   Nota: Préciser que l'on souhaite utiliser un fichier FXML ( *MainController*, *mainScreen.fxml*) dans le 3ème et dernier écran du wizard

2. Ouvrir *mainScreen.fxml* avec SceneBuilder.
<br>
Construire la fenêtre principale en y mettant un bouton simple, dont l'action par clic droit doit appeler la méthode *showPopup()* du contrôleur. 
Puis compléter le code du contrôleur. Astuce:  
   
        View > Show Sample Controller Skeletton

3. Implémenter la méthode du contrôleur pour afficher une boîte de dialogue (classe Alert)


