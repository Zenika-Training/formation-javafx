Cette slide peut se décliner dans les couleurs de la charte que vous trouvez les plus opportunes
<numéro>
<numéro>
Introduction aux composants
L'architecture choisie par JavaFX
    - Tous les composants et conteneurs affichés dans la scène sont des Nodes 
    - Tous les composants sont dessinés par JavaFX 
        - Pas de composants système comme en AWT ou SWT / Eclipse RCP 
        - Il n'est pour l'instant pas possible d'accéder aux API de dessin très bas niveau (instructions OpenGL notamment) 
        - Tout est vectorisé, y compris les textes! 
    - Ils sont décorés à l'aide de feuille de style CSS / skins 

Les composants de JavaFX
    - Button 
    - CheckBox 
    - Hyperlink 
    - RadioButton 
    - ToggleButton 
Les basiques

Les composants de JavaFX
    - Les divers API 
        - Label 
        - TextField 
        - TextFlow 
        - HTMLEditor 
    - Les possibilités 
        - Edition de texte pseudo-HTML 
        - Personnalisation par CSS 
        - Contrôle fin sur le rendu 
Affichage et édition de textes

Les composants de JavaFX
    - MenuBar, Menu, MenuItem 
    - Pagination 
    - ToolBar (avec style CSS) 
    - TabPane, Tab 
    - Accordion 
    - SplitPane 
        - Division cachée via css, 2 régions 
Barres et groupes

Les composants de JavaFX
    - ListView 
        - Le plus classique 
        - Possibilité de personnaliser les colonnes 
    - TreeView 
        - Affichage hiérarchique de données 
    - TableView 
        - Le plus complet 
        - Colonnes et lignes 
        - Entièrement personnalisable 
Les tableaux et dérivés

Les composants de JavaFX
    - ComboBox 
    - ChoiceBox 
    - ProgressIndicator 
    - ProgressBar 
    - ColorPicker 
    - DatePicker 
Divers

Les composants de JavaFX
    - DatePicker 
    - TreeTable 
Nouveautés JavaFX 8

Le support multimédia
    - Audio avec lecture MP3 
    - Vidéo (FLV, Mpeg-4 avec H.264) 
    - Ressource chargée dans un Media 
    - Contrôle de la lecture via MediaPlayer 
    - Affichage dans l'IHM via MediaView  
    - (la barre de contrôle n'est pas incluse) 
    - Malheureusement: pas de support            de WebCam! 
L'intégration facile

Le composant WebView
    - Rendu HTML5 complet 
    - Support des WebSockets 
    - Basé sur WebKit 
    - Interactions possibles entre Java et JavaScript 
Un navigateur Web embedded!

Les graphes
    - Graphes les plus courants fournis par JavaFX 
    - Modèle basé sur les séries à afficher 
    - XYChart.Series<TypeX, TypeY>, XYChart.Data<TypeX, TypeY>

series.getData().add(someDataPoint);
chart.getData().add(series);
Nombreuses possibilités

Organiser l'affichage
    - Un layout définit le positionnement et la dimension des composants 
        - Il est spécifique à un conteneur 
        - Il gère la répartition de l'espace restant disponible  
        - Il réorganise l'affichage lors d'un redimensionnement 
    - Suivant les cas un layout peut 
        - Gérer des données de positionnement absolues ou relatives 
        - Prendre en compte l'ordre d'insertion des composants 
Notion de layout

Layout par ancrage
    - 4 points d'ancrage (anchor) sont définis : top, left, bottom, right 
        - Chaque composant est attaché à une ancre et s'affiche dans la partie choisie 
        - La taille préférée d'un composant est honorée 
        - Un composant peut être ancré à plusieurs ancres et va alors se redimensionner pour prendre toute la largeur ou toute la longueur (left + right, top + bottom) 
    - Ce layout est simple mais assez limité 
AnchorPane, pour les cas simples

Layout par région d'écran
    - 5 régions sont définies 
        - Top, prend toute la largeur en haut 
        - Bottom, prend toute la largeur en bas 
        - Center, zone centrale 
        - Left, même hauteur que center, à gauche 
        - Right, même hauteur que center, à droite 
    - Chaque composant est affecté à une région 
        - Chaque composant est redimensionné pour occuper la région au maximum 
        - Le BorderPane essaye d'honorer les tailles définies (minimum, préférée, maximum) de ses composants 
BorderPane, redimensionnement intelligent

Layout vertical et horizontal
    - VBox et HBox 
        - Les composants sont alignés horizontalement (HBox) ou verticalement (VBox) 
        - L'espace est divisé en colonnes égales, dimensionnées pour accueillir le plus large / haut des composants 
    - FlowPane 
        - Représente une conteneur orienté verticalement ou horizontalement 
        - Si le nombre de composants dépasse le nombre fixé de colonnes / lignes, on passe à une nouvelle ligne / colonne 
Classique mais efficace

Layout en grille
    - TilePane et ses « dalles » identiques (tiles) 
        - Peut être orienté horizontalement ou verticalement (ordre de remplissage) 
        - On indique le nombre désiré de colonnes / lignes 
        - Chaque dalle a la même taille 
        - Chaque dalle est dimensionnée à la taille "préférée" du composant le plus volumineux 
        - La grille s'ajuste au redimensionnement (ajout / suppression de lignes ou colonnes) 
    - GridPane et ses cellules ajustables 
        - L'espace est divisible en un nombre arbitraire de colonnes et de lignes de toutes tailles 
        - Possibilité de "merge" entre cellules (span) 
        - Alignement dans la cellule paramétrable 
        - Marge ajustable 
TilePane et GridPane

Layout par superposition
    - Les composants sont empilés les uns par dessus les autres 
        - Tout les composants sont visibles mais certains masquent les autres 
        - L'ordre d'insertion des composants définit leurs positions sur l'axe Z 
        - Utile pour combiner plusieurs composants (texte par dessus une image par exemple) 
        - Il est possible en plus de jouer sur l'opacité des éléments pour des rendus graphiques intéressants 
StackPane

Taille des éléments
    - Tous les éléments dessinés possèdent trois valeurs de dimension pour chaque coordonnée (width et height) 
        - minSize : taille minimale du composant 
        - maxSize : taille maximale du composant 
        - prefSize : taille par défaut, si le composant n'a pas de dimension explicite, cette valeur sera utilisée 
    - Pour qu'un élément puisse utiliser tout l'espace disponible, il faut utiliser les paramètres VGrow et HGrow 
        - Par défaut maxSize = prefSize, il faut donc modifier maxSize à la valeur Infinity pour autoriser la croissance du composant 
        - Appel de la méthode statique du layout parent 
            Exemple : VBox.setVgrow(node, Priority.ALWAYS);
Minimum, maximum, préférée

Exemple d'utilisation
<fx:root type="javafx.scene.layout.GridPane" xmlns:fx="http://javafx.com/fxml""
                alignment="BASELINE_CENTER"
                maxHeight="Infinity" maxWidth="Infinity"
                hgap="25" vgap="25">
        <padding>
                <Insets top="25" bottom="25" left="25" right="25"/>
        </padding>
        <Button text="Bouton de test"
                        maxWidth="Infinity" maxHeight="Infinity"
                        GridPane.hgrow="ALWAYS" GridPane.vgrow="ALWAYS"
                        GridPane.rowIndex="0" GridPane.columnIndex="0"/>
        <Button text="Say Hello"
                        maxWidth="Infinity" maxHeight="Infinity"
                        GridPane.hgrow="ALWAYS" GridPane.vgrow="ALWAYS"
                        GridPane.rowIndex="0" GridPane.columnIndex="2"/>
        <Label text="Un texte de test tres tres tres tres tres tres tres tres tres tres tres tres long"
                        maxWidth="Infinity" maxHeight="Infinity"
                        GridPane.hgrow="ALWAYS" GridPane.vgrow="ALWAYS"
                        GridPane.rowIndex="1" GridPane.columnIndex="0"/>
</fx:root>

Définition des popups
    - Utilisation de la classe javafx.stage.Popup 
        - show() permet d'ouvrir la popup 
        - hide() permet de fermer la popup 
    - Utilisation de la classe javafx.stage.Stage 
        - Possibilité d'instancier un nouveau Stage possédant sa propre Scene 
        - Utile pour les applications multi-fenêtres et multi-écrans 
Toujours utile, aussi pour le multi-écrans

Exemple de popup
public class PopupExample extends Application {

    @Override
    public void start(final Stage primaryStage) {

        primaryStage.setTitle("PopupExample Example");
        final Popup popupExample = new Popup();
        popupExample.setX(300);
        popupExample.setY(200);
        popupExample.getContent().addAll(new Circle(25, 25, 50, Color.AQUAMARINE));
        Button show = new Button("Show");
        show.setOnAction(actionEvent -> popupExample.show(primaryStage));
        Button hide = new Button("Hide");
        hide.setOnAction(event -> popupExample.hide());
        HBox layout = new HBox(10);
        layout.getChildren().addAll(show, hide);
        primaryStage.setScene(new Scene(layout));
        primaryStage.show();

    }
}

