# Première application

<!-- .slide: class="page-title" -->



## Sommaire

<!-- .slide: class="toc" -->

- [Présentation de JavaFX 8](#/1)
- **[Première application]**
- [Composants et layouts](#/3)
- [Données et architecture](#/4)

- [Enrichir vos interfaces](#/5)
- [Animation](#/6)
- [Concurrence](#/7)

- [Les événements](#/8)
- [Intégration avec Swing](#/9)
- [Tests et outils](#/10)
- [Pour aller plus loin](#/11)



## Contenu d'une application

- *Les différents éléments nécessaires:*

  - Classes Java 
  - Fichiers FXML 
  - Fichiers properties 
  - Fichiers CSS 
  - JVM 7 (ou supérieure) 

<figure style="position: absolute; bottom: 30px; right:30px; width: 65%;">
    <img src="ressources/02/javafx_melimelo.png" alt="JavaFX elements" />
</figure>



## Démarrer avec JavaFX

- Une application JavaFX doit hériter de *javafx.application.Application*


- Le démarrage se fait via une méthode statique nommée « **launch(...)** » 
    - Initialisation de divers paramètres propres à JavaFX 
    - La méthode launch utilise le thread courant comme UI Thread 


- Une fenêtre est représentée par un objet *javafx.stage.Stage*
    - Plusieurs Stages peuvent être gérés (popups, multi-écrans…) 


- Une *javafx.scene.Scene* est affichée 
    - Contient l'arborescence des composants qui sont des Nodes 


Lors de l'utilisation de notre application, plusieurs scènes vont se dérouler et se succéder sur les planches de notre théâtre.



## Démarrer avec JavaFX : exemple d'application

Exemple:

```java
public class FirstScreen extends Application {

    @Override
    public void start(final Stage stage) throws Exception {
        Group group = new Group();
        
        Circle circle = new Circle(200,200,190);
        group.getChildren().add(circle);
        
        Rectangle rectangle = new Rectangle(400,400,200,100);
        group.getChildren().add(rectangle);
        
        final Scene scene = new Scene(group, 700, 550);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(final String[] args) throws Exception {
        launch(args);
    }
}
```

<figure style="position: absolute; top:20%; right:5%; width: 25%;">
    <img src="ressources/02/scene.png" alt="Premiere scène" />
</figure>



## Le Scene Graph : définir les éléments de la vue

*Les éléments d'une scène constituent le Scene Graph*

- Arbre de composants JavaFX avec un composant racine 
- Décrit le contenu d'une Scene 
- Chaque élément de l'arbre est un « *Node* » 
- Chaque Node peut avoir des enfants (pattern composite) 

<figure style="position: absolute; bottom:15%; left:10%;">
    <img src="ressources/02/scenegraph_abstract.png" alt="Vision abtraite du Scene Graph" />
</figure>

<figure style="position: absolute; bottom:15%; right:10%;">
    <img src="ressources/02/scenegraph_example.png" alt="Exemple de Scene Graph" />
</figure>





## Définir un écran avec FXML : Une abstraction intéressante

- Langage déclaratif de construction d'interface basé sur du XML 

- Décrit un arbre de composants (avec une racine unique) 
    - Un fichier FXML peut être chargé par la Scene 

- Relié au code par un Controller 
    - Possibilité d'encapsuler des scripts (peu utilisé et peu recommandé) 
    - Intégration de type MVC ou MVP 

- Peut être conçu visuellement à l'aide de SceneBuilder (WYSIWYG) 




## Exemple d'utilisation

GridPane avec layouts en rows / columns

```xml
<GridPane alignment="center" hgap="10" styleClass="root" vgap="10" xmlns:fx="http://javafx.com/fxml/1" xmlns="http://javafx.com/javafx/8" fx:controller="fxmlexample.FXMLExampleController">
    <padding><Insets bottom="10" left="25" right="25" top="25" /></padding>

    <Text id="welcome-text" text="Welcome" 
       GridPane.columnIndex="0" GridPane.columnSpan="2" 
       GridPane.rowIndex="0" />

    <Label text="User Name:" 
       GridPane.columnIndex="0" GridPane.rowIndex="1" />

    <TextField 
       GridPane.columnIndex="1" GridPane.rowIndex="1" />

    <Label text="Password:" 
       GridPane.columnIndex="0" GridPane.rowIndex="2" />

    <PasswordField fx:id="passwordField" 
       GridPane.columnIndex="1" GridPane.rowIndex="2" />

</GridPane>

```

<figure style="position: absolute; top:50%; right:5%; width: 25%;">
    <img src="ressources/02/login.png" alt="Login" />
</figure>



## L'outil SceneBuilder : What You See Is What You Get (WYSIWYG)

<figure>
    <img src="ressources/02/scenebuilder.png" alt="Scene Builder 8.2"/>
</figure>



## Pourquoi utiliser FXML?

*Plusieurs avantages*


- Découpler la logique d'écran de sa déclaration 
    - Meilleure maintenabilité 
    - Meilleure évolutivité 
    - Possibilité de découpage de travail avec un graphiste 


- Le FXML est central dans la mise en place de design patterns de type MVC ou MVP 
    - Intégration avec des frameworks Java 


- Le FXML est évalué : il n'est pas compilé, on peut donc générer dynamiquement des vues via ce format XML 



## FXML : Utilisation dans une application

*FXMLLoader* est dédié au chargement des fichiers FXML et leur transformation en arbre de composants 

```java
//dans la méthode start, au lieu d'instancier directement root
URL location = getClass().getResource("zenfxapp.fxml");
FXMLLoader fxmlLoader = new FXMLLoader(location);
BorderPane root = (BorderPane) fxmlLoader.load();

//on peut aussi récupérer une instance du contrôleur
MyController controller = (MyController)fxmlLoader.getController();

//reste du chargement de la scène
Scene scene = new Scene(root,400,400);
```

- Où utiliser FXMLLoader ? 
    - Soit lors de l'instanciation d'un composant déclaré via FXML dans le composant parent 
    - Soit dans le constructeur d'un composant qui se décrit en FXML 



## FXML : Plusieurs utilisations possibles

*FXML First ou Java First?*
<br>

- *Plusieurs niveaux de granularité* sont possibles avec FXML 
    - 1 FXML = 1 « page » de l'application 
    - 1 FXML = 1 composant de l'application (plusieurs composants par page) 

- Un fichier FXML peut charger un autre fichier FXML via la balise *&lt;fxml:include&gt;*

- Deux choix sont possibles pour les chargements en cascade de FXML 
    - *FXML First* : Les fichiers FXML explicitent les fichiers enfants à charger (fxml:include)
    - *Java First* : Les contrôleurs Java définissent les FXML enfants à charger



## Notion de contrôleur : Le pattern MVC selon Oracle

- *Une classe "Controller" contient le comportement associé au FXML*
  - Il n'est pas nécessaire d'hériter d'une interface ou d'une classe 
  - Interactions FXML - Controller : via des listeners d'événements ou des méthodes annotées 


- *JavaFX lie le FXML au "Controller" par réflexion sur la classe Java*
  - Le nommage des méthodes / champs est important 
  - Utilisation d'annotations propres à JavaFX comme @FXML 
  - Impact sur la performance lors de chargements de nombreux FXML 

- *Il est possible de récupérer des paramètres contextuels dans le Controller*
  - Exemple : un événement en paramètre 
   
        public void maMethode(MonEvent event) { … }



## Exemple d'interactions : lier le FXML à son contrôleur

Example.fxml

```xml
<GridPane [...] fx:controller="com.zenika.javafx.example.FXMLExampleController">
   <Button onAction="#okButtonHandler" text="OK" [...] />
</GridPane>
```

FXMLExampleController.java 

```java
package com.zenika.javafx.example;
import javafx.fxml.FXML;

public class FXMLExampleController {
    @FXML
    void okButtonHandler(ActionEvent event) {
    ...
    }
}
```

- Un appel de méthode est présent dans le FXML via *\#* 
- La méthode en question est codée dans le Controller 
- Elle prend un ActionEvent en paramètre 



## Exemple d'interactions : injection de composants


- *Il est possible d'injecter des composants dans un Controller *
    - Soit par le nom seul, en scope public 
    - Soit en précisant l'annotation **@FXML**
    - Il est recommandé de toujours définir l'annotation 

<br>

- *En implémentant l'interface Initializable de JavaFX*
    - La méthode **initialize()** est appelée par JavaFX à la fin de la construction de la vue 
    - On peut donc ajouter des gestionnaires d'événements de façon programmatique, en Java 



## Exemple d'interactions : injection de composants

Injection du bouton:

```xml
<GridPane [...] fx:controller="com.zenika.javafx.example.FXMLExampleController">
   <children>
      <Button fx:id="okButton" mnemonicParsing="false" text="OK" />
   </children>
</GridPane>
```

Et handler ajouté en java:

```java
public class FXMLExampleController implements Initializable {

    @FXML
    private Button okButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        okButton.setOnAction( actionEvent -> System.out.println("C'est OK !") );

    }
}
```



## Construction par code Java: L'alternative procédurale

- *Construction sans FXML, "comme en Swing"*

```java
final VBox rootNode = new VBox();
rootNode.getChildren().add(new Label("Hello World !"));
```

<br>

- *Spécificités d'un développement purement procédural:*
    - Dynamique de l'UI plus facilement gérable en Java 
    - Pas d'interprétation au runtime du rendu (code entièrement compilé) 
    - Accès à des API de bas niveau (exemple : écrire des pixels à une certaine position) 
    - Meilleure performance 
    - Transfert de compétences aisé pour les développeurs Swing / Eclipse RCP 



## FXML ou Java?  Procédural versus déclaratif...

- *Le FXML permet de mieux organiser le code*
    - XML pour la vue statique 
    - Controller Java pour les interactions et la dynamique 
    - possibilité de séparer les rôles (UI vs code)
    
    
- *Le FXML a quelques défauts*
    - Coût en performance 
    - Certaines interactions ne peuvent être décrites en déclaratif 

<br>

<br>

Dans un projet JavaFX, la majorité des vues devraient être en FXML

Des éléments et contrôles complexes pourront être ajoutés en JAVA au besoin



## Les déploiements : Plusieurs options possibles

- *4 modes disponibles avec des livrables différents* 
  - *Standalone* : package JAR "standalone"
  - *Self-contained* : packagé selon l'OS avec une copie de la JRE
  - *Embedded* : Embarquée dans un navigateur (applet) 
  - Java *Web Start*

<figure style="position: absolute; bottom: 2%; right:0%; width: 48%;">
    <img src="ressources/02/deploy_type_properties.png" alt="Types de déploiement"/>
</figure>



## Ship it!

- *Le mode Self-contained (ou natif)*
    - Création d'un installateur standard pour un OS donné 
    - Inclut le JRE nécessaire 
    - Livrable volumineux mais standard pour l'OS 
    - Peu adapté pour des applications mises à jour fréquemment 

<br>

- *Le mode Embedded*
    - Pour lancer l'application depuis un navigateur Web 
    - Facilite les échanges JavaFX / JavaScript sur une page Web 
    - Nécessite un JRE 
    - Pas adapté pour une application déconnectée 



## Ship it!

- *Mode Standalone*
    - Livraison d'un JAR exécutable
      - Package de l'ensemble des dépendances dans un seul JAR 
      - Package des classes applicatives dans un seul JAR + livraison des dépendances JAR externes 

<figure>
    <img src="ressources/02/standalone.png" alt="Types de déploiement" width="60%"/>
</figure>

- *Le mode WebStart*
    - Déploiement sur un serveur Web pour gestion automatique des versions 
    - Permet toutefois le lancement de l'application en mode déconnecté 



<!-- .slide: class="page-questions" -->



<!-- .slide: class="page-tp1" -->
