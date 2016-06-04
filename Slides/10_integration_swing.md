# Intégration avec SWING

<!-- .slide: class="page-title" -->



## Sommaire

<!-- .slide: class="toc" -->

- [Présentation de JavaFX 8](#/1)
- [Première application](#/2)
- [Éléments graphiques](#/3)
- [Databinding](#/4)
- [Architecture](#/5)
- [Enrichir vos interfaces](#/6)
- [Animation](#/7)
- [Événements](#/8)
- [Concurrence](#/9)
- **[Intégration avec Swing]**
- [Tests et outils](#/11)



## Intégrer JavaFX dans une application Swing

- Possible via le composant Swing *JFXPanel*
 
- Utilisation *côté Swing*
  ```java
  //dans l'EDT (thread Swing)
  JFrame frame = new JFrame("Zenika");
    
  final JFXPanel fxPanel = new JFXPanel();
  frame.add(fxPanel);
  frame.setSize(300, 200);
  frame.setVisible(true);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  ```



## Intégrer JavaFX dans une application Swing

- Cas d'utilisation côté JavaFX

  ```java
  //initialiser la scène dans l'AT JavaFX
  Platform.runLater(() -> {
    
    // créer le SceneGraph
    Group root = new  Group();
    Scene scene = new  Scene(root, Color.WHITE);
    
    Text text = new  Text();
    text.setText("Formation JavaFX!");
    root.getChildren().add(text);
  
    //ajouter la scène dans Swing
    fxPanel.setScene(scene);
  });
  ```



## JavaFX et Swing : Deux boucles de messages différentes

- *Attention : Swing et JavaFX ont tous les deux leurs threads dédiés* 
  - *Application Thread* (JavaFX) vs *Event Dispatcher Thread* (Swing)
  - Chacun s'attend à ce que le code touchant à l'IHM soit exécuté dans son thread         
  - Envelopper les appels d'un GUI Thread à un autre via des instances d'objet Runnable soumises aux méthodes dédiées de chaque framework 
  
- *Les interactions entre Swing et JavaFX apportent donc beaucoup de "boiler-plate code"* 



## JavaFX et Swing
- *Il faudra donc manipuler chaque système au travers des threads de chacun*
  - EDT pour Swing (non vérifié à l'exécution)
    ```java
    SwingUtilities.invokeLater(() -> {
    		//...mon traitement qui impacte Swing
    });
    ```
    
  - AT pour JavaFX (vérifié à l'exécution)
    ```java
    Platform.runLater( () -> {
    		//...mon traitement qui impacte JavaFX
    });
    ```



## Swing dans JavaFX : Introduction au SwingNode

- Depuis Java SE 8, on peut faire l'inverse et introduire des *composants Swing dans une partie du SceneGraph*

- *SwingNode* est une Node dans laquelle on peut afficher un *JComponent*, via la méthode **setContent(JComponent swing)** 

- De la même façon, attention au choix du thread 
  - EDT Swing ou AT JavaFX suivant les composants 
  - **setContent()** de SwingNode peut être invoqué de l'EDT comme de l'AT et représente une exception 



## SwingNode : Exemple

- Il suffit d'ajouter ce composant JavaFX au Scene Graph 
- Celui-ci accepte des composants enfants de type Swing
  ```java
   final SwingNode swingNode = new SwingNode();
   swingNode.setContent(new JLabel("Swing !"));
   
   StackPane pane = new StackPane();
   pane.getChildren().add(swingNode);
   
   stage.setScene(new Scene(pane, 250, 150));
   stage.show();
  ```



## JavaFX + Swing : Les avantages

- *Utilisation de Swing côté JavaFX* 
  - Permet de réutiliser des composants personnalisés existants en attendant de les migrer vers JavaFX 
  - Certaines librairies Java ne fonctionnent qu'avec Swing et n'ont pas encore d'équivalent en JavaFX 

- *Utilisation de JavaFX côté Swing*
  - Permet d'utiliser de nouveaux composants intéressants comme WebView ou Canvas 
  - Initier une migration vers JavaFX en douceur



## JavaFX + Swing : Les inconvénients

- *La bonne gestion des threads est ardue et verbeuse* 
  - L'EDT et l'AT en Java FX 8 (livré avec Java SE 8) ont été fusionnés pour plus de facilité, mais c'est une *feature expérimentale*
  - Activable via une propriée de la JVM:
  
> java -Djavafx.embed.singleThread=true

- *Architecture hétérogène*
  - peut entrainer une perte de performance (synchroniser des threads = ralentir les plus rapides)
  - le look-and-feel des composants n'est pas non plus homogène

- *Est-ce durable ?* Cet assemblage fonctionnera t-il dans les prochaines versions de Java / JavaFX / Swing / JVM ? 

