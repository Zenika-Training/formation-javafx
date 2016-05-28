Cette slide peut se décliner dans les couleurs de la charte que vous trouvez les plus opportunes
<numéro>
<numéro>
Le support CSS
Un dérivé de CSS3 pour JavaFX
    - Proche du modèle utilisé en HTML5 avec quelques spécificités 
        - Les propriétés dédiées à JavaFX portent le préfixe « -fx- » 
        - Sert à déporter le rendu graphique de la composition d'écran (de type CSS 1.0) 
        - Supporte des effets graphiques intéressants (de type CSS 3.0) 
        - Ne peut définir des layouts et des positionnements, la responsabilité est donnée aux composants conteneurs (contrairement à CSS 2.0) 
    - On retrouve des noms familiers 
        - -fx-font-size, -fx-background-color 
    - D'autres sont plus spécifiques 
        - -fx-focus-traversable, -fx-use-system-menubar 

Utilisation dans JavaFX
Plusieurs niveaux
    - Feuille de style du système « theme » (définit le "look and feel") 
    - Feuille de style de la Scene 
    - Classe du composant (une liste de classes CSS) 
    - Style du composant (préférer l'usage de classes CSS) 

Exemple de CSS
Beaucoup de possibilités
String css1 = "-fx-font-size: 30pt; "
        + "-fx-background-color:  
        + "  linear-gradient(#f0ff35, #a9ff00);";
String css2 = "-fx-font-size: 30pt;"
        + "-fx-background-color:"
        + "  linear-gradient(#f0ff35, #a9ff00),"
        + "  radial-gradient(center 50% -40%,"
        + "    radius 200%, #b8ee36 45%,"
        + "    #80c800 50%);";
String css3 = css2 + "-fx-background-radius: 16, 15;";
String css4 = css3 + "-fx-background-insets: 4, 8;";
String css5 = css4 + "-fx-effect: dropshadow( three-pass-box,
        + "rgba(0,0,0,0.4) , 5, 10.0 , 0 , 1 );";
String css6= css5 + "-fx-text-fill: red;";
Button b1 = new Button("css1");
b1.setStyle(css1);
//...

Personnaliser le support CSS
Ajouter des propriétés
    - Possibilité d’étendre le langage CSS JavaFX 
        - « CSS Public API » disponible depuis JavaFX 8 
        - Factoriser les effets dans une classe et / ou propriété CSS 
        - Utilisation de StyleableObjectProperty et de CssMetadata 
    - Une bibliothèque est disponible pour faciliter l’implémentation « CSSHelper » 
        - http://www.guigarage.com/2014/03/javafx-css-utilities/ 

Utiliser des effets
Un moyen simple de valoriser l’UI
    - Des effets pré-définis sont mis à disposition 
        - Ombres : DropShadow, InnerShadow 
        - Flous : GaussianBlur, BoxBlur, MotionBlur 
        - Reflet : Reflection 
        - Filtres de couleur : ColorAdjust (hue, saturation, etc...), SepiaTone 
        - Effets lumineux : Bloom, Lightning 
        - Bien d’autres encore! 
Chaque effet est représenté par une classe Java étendant la classe Effect

Utiliser l’API Effect
Appliquer un Effect à un Node

Transformations
Tirer profit du moteur graphique de JavaFX
    - Rotation, échelle, translation, opacité sont modifiables sur chaque Node 
    - A noter : une transformation peut faire sortir un nœud de la zone qui lui a été assignée par son conteneur 

API Shape en JavaFX
Dessiner divers formes
    - Plusieurs éléments disponibles: Line, Arc, Circle, Rectangle, Polygon… 
    - Chaque élément est personnalisable 
        - Modification de l’intérieur de la forme (setFill) 
        - Modification du contour de la forme (setStroke) 

API Canvas en JavaFX
Manipuler le GraphicsContext
    - Plusieurs usages 
        - Dessiner toutes les formes de base (cercles, rectangles…) 
        - Modifier la couleur de trait (stroke) et le remplissage (fill) 
        - Dessiner des dégradés : gc.setFill(new RadialGradient(...)) 
        - Appliquer des effets : gc.setEffect(new GaussianBlur()) 
    - L'API Canvas ressemble à l'API HTML5 de même nom 
        - Transfert de compétences relativement aisé 
        - Très bonnes performances car optimisé GPU 

API Canvas en JavaFX
Exemple

Canvas et interactions
Ajouter des listeners
    - Les Canvas sont transparents, on peut les empiler pour créer un système de couches 
    - Solution la plus performante pour afficher un grand nombre de formes, particules, etc... 

API TextFlow en JavaFX
Sublimer vos textes

Responsive UI
Un écran, plusieurs supports
    - L'aspect multi-plateformes possède plusieurs challenges 
        - Support de plusieurs résolutions d'écran 
        - Support de plusieurs densités de pixels d'écran 
        - Ces notions sont regroupées sous le nom Responsive UI 
    - JavaFX ne possède malheureusement pas de support natif "Responsive" 
        - Plusieurs techniques et outils sont disponibles pour palier ce manque 
        - Un arbitrage est à prévoir en début de projet si possible à ce sujet 

Responsive UI
Un problème de résolution
    - La majorité des postes Desktop sont aujourd'hui en full HD 
        - 1920 x 1080 P (progressive) 
        - Format 16 / 9 
    - Les dispositifs mobiles ne possèdent pas cette résolution, ni même le même ratio 
        - Exemple : Windows Surface Pro 3 a une résolution de 2160 x 1440 en ratio 3:2 
Challenge : supporter des résolutions et des ratios différents

Responsive UI
Méthode naïve de mise à l'échelle
    - Après avoir conçu des écrans pour une résolution fixe 
        - Mettre à l'échelle le rendu graphique pour une autre résolution 
        - Comme tout élément est vectorisé en JavaFX, ce principe est facile à mettre en place 
        - Les widgets et textes sont redimensionnés 
        - Possibilité de garder le ratio originel ou non (remplir par des "bandes noires" sinon) 
    - Le rendu est passable si 
        - Le ratio est à peu près le même 
        - Peu ou pas d'images de type bitmaps à redimensionner 
    - Il suffit d'utiliser un script sur chaque racine de Scene de l'application 
        - Rapide à implémenter 
        - Léger coût en performance 

Redimensionnement
Exemple
 public static void scaleToScreen(Window w) {
        double screenW = Screen.getPrimary().getVisualBounds().getWidth();
        double screenH = Screen.getPrimary().getVisualBounds().getHeight();
        Node root = w.getScene().getRoot();
        Bounds scene = w.getScene().getRoot().getLayoutBounds();
        double scaleX = screenW  / (scene.getWidth() +5);
        double scaleY = screenH / (scene.getHeight() +30);
        Scale scale = new Scale(scaleX, scaleY, 1, 0, 0, 0);
          root.getTransforms().setAll(scale);
        w.setHeight(screenH);
        w.setWidth(screenW);
        w.centerOnScreen();
}

Responsive UI
Un problème de densité de pixels
    - Les smartphones et tablettes modernes possèdent des définitions d'écran très élevées 
        - Exemple : iPhone 6 Plus 5.5 pouces, 1920 x 1080 P soit 441 PPI 
    - La notion de densité est désignée sous plusieurs acronymes 
        - DPI : "dots per inch" soit en français PPP "Points Par Pouce" 
        - PPI : "pixels per inch" 
    - Cette notion est particulièrement utilisée pour définir l'affichage de textes 
        - Paramétrable sur la plupart des OS 
        - Exemple : 96 PPP pour les textes 

Responsive UI
Des textes et des problèmes
    - Une mise à l'échelle suivant ratio et résolution n'est pas suffisante dans certains cas 
        - Suivant les DPI, certains textes deviennent illisibles car trop petits! 
    - Une unité de mesure absolue a été créée: "em" (prononcée "M")  
        - Se réfère à la taille de police par défaut du système (Exemple: 1em = font taille 14 = 96 PPP) 
        - Permet de calculer des tailles relatives à la taille de police par défaut (titres en 2em par exemple) 
        - Utilisée régulièrement en HTML5 / CSS3 
        - Malheureusement non supportée nativement par JavaFX 

Responsive UI
Que faire sans support EM?
    - Il est possible de récupérer la valeur de la taille de police par défaut de l'OS ou de la valeur de la densité liée 
    - Par une cascade de bindings, il est possible de retravailler les IHM en mode relatif à la taille de police par défaut : un pseudo-mode EM 

Pseudo-mode EM en JavaFX
Tailles en mode relatif
<StackPane xmlns:fx="http://javafx.com/fxml">
   <fx:define>
      <Measurement fx:id="u" em="26.0" />
   </fx:define>
   <AnchorPane id="AnchorPane"
      maxHeight="-Infinity" maxWidth="-Infinity"
      minHeight="-Infinity" minWidth="-Infinity"
      prefHeight="${22*u.em}" prefWidth="${14*u.em}">
      <children>
         <Button layoutX="${4*u.em}" layoutY="${ 5*u.em}" prefWidth="${6*u.em}" text="Top" />
         <Button layoutX="${4*u.em}" layoutY="${10*u.em}" prefWidth="${6*u.em}" text="Middle" />
         <Button layoutX="${4*u.em}" layoutY="${15*u.em}" prefWidth="${6*u.em}" text="Bottom" />
      </children>
   </AnchorPane>
</StackPane>

Responsive UI
Le projet ResponsiveFX
    - Initiative récente pour le support de plusieurs dispositifs en JavaFX 
        - Se repose sur l'utilisation de classes CSS spécifiques pour chaque support 
        - Le toolkit reconnaît le matériel et applique le style CSS adéquat 
    - Les dispositifs sont reconnus comme dans twitter bootstrap 

ResponsiveFX
Utilisation
    - Il suffit d'appeler la fonction statique fournie sur le Stage 
    - On définit plusieurs classes CSS 

ResponsiveFX
Utilisation
    - On applique les classes souhaitées aux composants 

Besoin de plus?
1 Support = 1 FXML par défaut ou 1 FXML spécifique
    - Dans certains cas, redimensionner avec ou sans supports de ratio et densité ne suffit pas 
        - Rendu spécifique pour certains dispositifs (logos spéciaux…) 
    - Il est possible de choisir un mode spécifique basé sur FXML 
        - 1 FXML par défaut 
        - Possibilité de choisir un autre FXML si un dispositif particulier est détecté 
        - Mutualisation du code "Controller" 
        - Grande personnalisation possible 
        - Nécessite une surcouche à FXMLLoader 
        - Java First uniquement: pas de FXML First 
        - 1 seul livrable! 

Le plus complet
… mais le plus coûteux
    - Définir un livrable par cible 
        - Personnalisation illimitée (FXML, CSS, et même classes Java) 
        - 1 build et projet par plateforme 
        - Mutualisation de code via des dépendances statiques 
        - Plus long à développer 
        - Maintenance plus coûteuse 
    - Il faut alors considérer chaque support comme un projet séparé 

