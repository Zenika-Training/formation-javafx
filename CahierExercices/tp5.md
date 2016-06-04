<div class="pb"></div>

# TP 5 :  CSS, Effets et Transitions

## Objectifs

- Utiliser des feuilles de style CSS avec JavaFX
- Créer une animation personnalisée
- Intégrer une animation avec CellFactory

## Styles CSS
 
1. Afin de supprimer les possibles scrollbars horizontales de notre composant ListView, définir dans le fichier CSS de l'application un -fx-padding à la valeur 0 pour les scrollbars horizontales de ListView.
<br>
Les propriétés concernées sont :
 
         .list-view .scroll-bar:horizontal .increment-arrow,
         .list-view .scroll-bar:horizontal .decrement-arrow,
         .list-view .scroll-bar:horizontal .increment-button,
         .list-view .scroll-bar:horizontal .decrement-button
        
2. Tester le rendu.

3. On désire désormais que ces propriétés ne concernent que notre ListView. Après tout, il est possible que quelqu'un d'autre veuille utiliser un ListView avec des scrollbars dans notre application. Définir une classe CSS et l'affecter correctement dans le fichier FXML.

4. Tester le rendu.

<div class="pb"></div>

## Animations via transitions

1. Définir une méthode statique publique nommée createTransition(Node node) retournant un objet Transition (pour l'instant nul).

2. Dans cette méthode, instancier une FadeTransition durant 1 seconde allant d'invisible à visible s'appliquant au node donné en paramètre et la retourner comme résultat.

3. Utiliser cette transition sur un élément de l'application en instanciant une transition via le méthode statique précédente et en executant la méthode play() sur l'objet transition.

4. On désire désormais utiliser cet effet sur les ZweetPane apparaîssant dans notre ListView. Instancier dans chaque ZweetPane, un objet transition qui s'exécutera sur ce composant via la méthode play() dès que le contenu de type Zweet change.

5. Tester l'affichage.

6. Il est possible d'utiliser et de combiner plusieurs effets, utiliser une ScaleTransition allant de l'échelle 0 à l'échelle 1 durant 1 seconde à la place de la FadeTransition.

7. Tester l'agrandissement.

8. A l'aide de la classe ParallelTransition, appliquer les deux effets simultanément.

9. Tester.

10. A l'aide de SequentialTransition, appliquer les deux effets consécutivement.

11. Tester.

12. Cet effet ne doit s'appliquer que sur les nouveaux Zweets, modifier ZweetPane pour cela.
