Cette slide peut se décliner dans les couleurs de la charte que vous trouvez les plus opportunes

<numéro>

<numéro>

Le moteur de rendu Prism

Accélération GPU ON!

    •PRISM utilise : 

        •DirectX sous Windows 

        •OpenGL sous Linux*, MacOSX, ARM 

        •Rendu logiciel (CPU) sur les plate-formes non accélérées 

    •* cartes NVidia avec pilotes propriétaires seulement pour l'instant 

 

Glass Windowing Toolkit

Le style par défaut est fait pour ressembler à celui de l'OS

Permet de styliser complètement les fenêtres de l'application

Exemple : barre de "titre" de l'application de démo Ensemble

Afficher des fenêtres

 

Quantum Toolkit

    •API publique JavaFX, SceneGraph (graphe des composants) 

    •Permet d'utiliser des effets et animations de qualité 

Façade utilisée par JavaFX

 

Notion d’interpolation

Exemple classique

 

Interpoler des valeurs

« Une interpolation est une opération mathématique permettant de construire une courbe à partir des données d'un nombre fini de points, ou une fonction à partir de la donnée d'un nombre fini de valeurs »

    •JavaFX peut créer des données « intermédiaires » par interpolation entre plusieurs états définis comme des KeyFrames 

    •Mécanisme utilisé pour créer des animations fluides 

    •Travail réduit pour le programmeur 

Sans trop de code!
 

Animations avec Timeline

    •Fonctionnement 

        •Les animations de bas niveau sont basées sur une variation des propriétés d'un Node 

        •Timeline contrôle l'exécution de l'animation (lecture, cycles, etc...) 

        •KeyValue décrit les étapes clés : quelle propriété varie, avec quelle valeur 

        •KeyFrame définit à quelle durée les KeyValue sont appliquées 

Note : les transformations sont des propriétés

Cas standard
 

Jouer une animation

Text t = new Text("Zenika"); t.setStyle("-fx-font-size: 30pt");

KeyValue opStart = new KeyValue(t.opacityProperty(), 0d);

KeyFrame frameStart = new KeyFrame(Duration.seconds(0d), opStart);

KeyValue opEnd = new KeyValue(t.opacityProperty(), 1d);

KeyFrame frameEnd = new KeyFrame(Duration.seconds(4d), opEnd);

Timeline timeline = new Timeline();

timeline.getKeyFrames().addAll(frameStart, frameEnd);

//ajouter le nœud à la scène, afficher la scène et immédiatement démarrer l'animation

timeline.play();

Faire apparaître un node
 

Combiner des interpolations

Exemple : combiner une rotation et un changement d'opacité

Plusieurs opérations dans une keyframe
 

Animations avec Transition

    •Les Transitions permettent des animations de plus haut niveau 

    •Elles sont prédéfinies pour les différentes transformations 

        •FadeTransition 

        •FillTransition 

        •RotateTransition 

        •ScaleTransition 

        •StrokeTransition 

        •TranslateTransition 

Une alternative pratique
 

Combiner des interpolations

    •Faciliter la combinaison d'animations 

        •Bout à bout avec SequentialTransition 

        •En parallèle avec ParallelTransition 

    •Faire des déplacements complexes 

        •Définition d'un Path 

        •Animation via la PathTransition 

SequentialTransition et ParallelTransition
 

Transition personnalisée

    •On peut définir ses propres Transitions pour faire soi-même l'interpolation (pour du texte par exemple) 

Définir sa propre interpolation
 

Utilisation du Z-Order

    •Pas de support 3D natif dans le Scene Graph 

    •Gestion de l ’axe Z toutefois possible 

    •On peut donc contourner le problème en combinant des formes 2D : il s’agit de la fausse 3D (nommée 2.5D) 

    •Par exemple, créer un cube en groupant 6 rectangles et en jouant sur les axes X, Y et Z, rotations et décalages 

Le support 2.5D de JavaFX
 

Activer le traitement du Z-Order

    •Par défaut, l'ordre de profondeur (Z-Order) est défini par l'ordre d’insertion des nodes dans le Scene Graph 

    •Construire des nodes avec un translateZ n’a pas d’impact par défaut! 

    •Il faut activer le flag lors de l’instanciation de la Scene 

Mode 2.5D ON!

 

Nodes et DepthTest

    •Propriété nommée DepthTest 

    •Activation / désactivation / hérité du parent 

    •Les test est activé par défaut sur le nœud racine 

Paramétrer ou non le Z-Order
 

Transformations 3D

    •Tout ce qui concerne la 3D, pseudo-3D et profondeur de champ nécessite le support du GPU. 

    •Sous Linux, un test est nécessaire 

        •Historiquement les cartes ATI ne le supportent pas 

Prérequis
 

Scene3D

    •Support 3D approfondi 

        •Formes 3D prédéfinies (Cube, Cylinder, Sphere...) 

        •Formes personnalisées à partir de Mesh et TriangleMesh 

        •Gestion de la caméra (Camera, PerspectiveCamera) 

        •SubScene pour appliquer une caméra différente à une partie de la scène 

        •Gestion de l'éclairage : LightBase, AmbientLight, PointLight 

        •Application de textures : Material, PhongMaterial 

        •Shaders! 

Enfin de la vraie 3D!
 

Exemple de rendu 3D

Sphère avec texture

 
