Cette slide peut se décliner dans les couleurs de la charte que vous trouvez les plus opportunes

<numéro>

<numéro>

Les événements locaux

Composants et événements

    •Un composant peut : 

        •Posséder des méthodes publiques permettant de changer son contenu 

        •Posséder des méthodes publiques permettant de récupérer son contenu 

        •Emettre des événements personnalisés que son parent peut notamment écouter : ils sont qualifiés d'événements « locaux » 

    •Plusieurs techniques de dispatch d'événement 

        •Par dispatch local dans la Scene : « classique » 

        •Par appel d'un EventHandler personnalisé : « databinding » 

 

Gestion événementielle

    •Les événements sont basés sur la classe Event 

        •Sous-classes plus spécifiques : MouseEvent, ScrollEvent, etc... 

    •Chaque événement a un type (EventType) 

        •Les types peuvent être hierarchisés 

        •Exemple : InputEvent.ANY > MouseEvent.ANY > MouseEvent.MOUSE_RELEASED 

    •La diffusion d'événement se fait en 4 phases 

        •Sélection de la cible 

        •Détermination du chemin 

        •Phase de capture 

        •Phase de remontée (event bubbling) 

Classes, types, et workflow
 

Gestion événementielle

    •Sélection de la cible 

        •On détermine sur quel nœud l'interaction a eu lieu (le plus précisément et spécifiquement possible, les nœuds en avant-plan ayant la priorité). 

        •Cette cible doit implémenter EventTarget, mais c'est déjà le cas pour les Window, Scene et Node. 

    •Détermination du chemin 

        •L'EventTarget est chargé de définir par quel chemin l'événement va être diffusé. 

        •L'implémentation par défaut sur Node renvoie un chemin allant de la Stage au nœud lui-même.  

Description des phases
 

Gestion événementielle

    •L'événement parcours le chemin vers la Node cible (phase de capture), puis remonte vers son point d'origine (phase de remontée) 

    •En descendant, il est traité par d'éventuels Filters 

    •En remontant, il est traité par d'éventuels Handlers 

    •Ceux-ci peuvent choisir de consommer l'événement (event.consume()) 

        •Il n'ira pas plus loin dans l'arborescence 

        •Il entamera une remontée immédiatement dans le cas d'un Filter 

Description des phases
 

Gestion événementielle

Workflow

 

Gestion événementielle

Workflow

 

Gestion événementielle

Workflow

 

Gestion événementielle

Workflow

 

Gestion événementielle

Workflow

 

Gestion événementielle

Exemple: ajout d’un listener

Les composants possèdent des raccourcis pour définir des listeners

Utilisation sur un node
 

Bubbling ou Filter event?

    •Les Capture Events permettent de pouvoir stopper la propagation des événements vers les enfants 

        •Exemple : pas de support de clic sur un enfant particulier 

    •Les Capture Events étendent un comportement à l'ensemble de ses enfants 

        •Exemple : un EventFilter sur un MouseClick défini sur un GridPane, sera appelé sur chaque clic sur ce GridPane, y compris sur un de ces enfants (bouton, image...) 

Choisir la bonne phase
 

Bubbling ou Filter Event

    •Les Bubbling Event permettent de « remonter » des informations vers un parent après une action donnée 

    •Aucune des deux méthodes est meilleure, elles sont complémentaires! 

Choisir la bonne phase
 

Evénements personnalisés

    •L'enveloppe est de type SauvegarderEvent qui étend javafx.event.Event 

    •On émet l'événement depuis une méthode du composant 

Emettre depuis un composant
 

Evénements personnalisés

    •Ecouter un événement personnalisé depuis le parent sur l'enfant 

Gérer l'écoute
 

Alternative

Définition d'un EventHandler greffable par Databinding dans le composant

Listener avec Databinding
 

Alternative

    •En FXML 

    •En Java 

Listener avec Databinding depuis le parent
 

Alternative

    •Emission de l'événement depuis le composant 

        •Le binding sur l'événement s'active alors si défini (valeur non nulle) et le listener est appelé 

        •Cette technique est utilisée dans l'implémentation de nombreux composants JavaFX 

Listener avec Databinding
 

Bilan

    •Deux méthodes sont possibles 

    •La méthode par databinding est la plus explicite et donc recommandée : 

        •Déclaration explicite de l'événement via un ObjectProperty 

        •Possibilité de définir le handler en FXML ou Java 

    •Le dispatch classique peut toutefois être utilisé dans certains cas 

        •Evénements locaux « internes » au composant qui ne seront pas écoutés en dehors (exemple : clic sur cellule de tableau qui ne sera pas écouté en dehors du composant tableau) 

Les événements locaux
 

Gestion du multitouch

    •Support natif dans JavaFX 

    •Ceux-ci sont gérés de manière similaire aux interactions "classiques" : 

        •TouchEvent 

        •GestureEvent 

        •ScrollEvent : faire glisser deux doigts, axe vertical 

        •RotateEvent : rotation des points de contact 

        •ZoomEvent : "pincer pour zoomer" 

        •SwipeEvent : faire glisser un doigt, axe horizontal ou vertical 

Pour plate-formes tactiles
 

Gestion du multitouch

    •TouchEvent est bas-niveau 

        •Donne le détail des points de contact avec l'écran tactile 

    •GestureEvent est plus haut niveau  

        •Ses sous-classes correspondent aux 4 gestes les plus "classiques" 

        •Chaque geste (sauf le swipe) est précédé d'un événement "started" et suivi d'un "finished" 

        •Il existe une hierarchie sur les types d'événements
        GestureEvent.ANY > RotateEvent.ANY > RotateEvent.ROTATE_STARTED, RotateEvent.ROTATE, RotateEvent.ROTATE_FINISHED 

TouchEvent et GestureEvent
 

Gestion du multitouch

    •Les interactions de type zoom, défilement sont aisées 

        •Support vectoriel natif de JavaFX sur tous les éléments Node 

        •Gestion des événements aisée 

Exemple
 

Gestion du multitouch

    •Les interactions tactiles peuvent donner lieu à des événements non tactiles 

        •Exemple : drag + scroll, clic + touch, etc... 

        •mouseEvent.isSynthetized() à true si l'événement est tactile 

        •gestureEvent.isDirect() à false si l'événement provient d'un touchpad 

    •Attention donc à ne pas enregistrer des listeners en double! 

        •Exemple : listener faisant défiler la page enregistré sur le scroll et le drag = 2x plus de mouvement que prévu 

Notes particulières
 

Bus d’événement

    •Parfois des événements peuvent intéresser plusieurs composants, à priori deux méthodes sont possibles suivant l'emplacement du composant 

        •Utiliser des événements locaux pour « remonter » au composant parent souhaité 

        •Utiliser des méthodes publiques sur les composants enfants pour « descendre » jusqu'au composant souhaité 

Un design pattern utile
 

Loi de Demeter

    •La loi d'encapsulation (Demeter) sur les composants nous incite à ne pas descendre / remonter dans la hiérarchie de composants à plus de un niveau pour ajouter un listener propre 

        •Chaque composant du parcourt devrait donc posséder un handler / méthode publique pour router l'action au bon composant 

        •Comment rendre le développement plus rapide ? 

Principe d'encapsulation
 

Bus d’événement

    •La hiérarchie des composants peut être : 

        •Complexe (beaucoup d'embranchements) 

        •Longue (beaucoup de niveaux d'enfants / parents) 

        •Modifiée durant le développement du projet (évolutions, bugs...) 

    •En définissant un bus d'événement unique à l'application (singleton), on centralise dans un bean Java l'abonnement / désabonnement à des événements globaux 

        •Il devient donc possible d'écouter un événement « global » sans être attaché à l'arbre des composants 

        •Fonctionnement de type publish / subscribe 

Pourquoi?
 

Bus d’événement

Le composant B veut s'abonner à un événement du composant F

Les composants B, D et F veulent être notifiés d'une déconnexion réseau

Les composants C et E veulent être notifiés d'une fermeture d'une popup modale

Cas d’utilisation

 

Bus d’événement

    •Plusieurs frameworks proposent des bus d'événements 

        •Spring Events 

        •RacpFX 

        •Open Dolphin 

        •Granite Data Services 

    •Choix techniques 

        •Un bus d'événement peut s'exécuter dans l'Application Thread de JavaFX ou dans un autre géré manuellement 

        •Son utilisation peut être fortement couplée à un framework donné 

Implémentations
 
