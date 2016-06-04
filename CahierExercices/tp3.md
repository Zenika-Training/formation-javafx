<div class="pb"></div>

# TP 3 : CellFactory et Databinding

## Objectifs

- Comprendre et utiliser le système d'item-renderer / Cell Factory
- Comprendre et utiliser un databinding simple
- Utiliser une binding expression simple

## Utiliser CellFactory et personnaliser les lignes d'un tableau

1. Créer une nouvelle classe nommée ZweetCell qui implémente l'interface *Callback<ListView<Zweet>, ListCell<Zweet>>*

2. Implémenter call()<br> 
   La méthode call(...) est appelée par JavaFX pour définir le rendu de chaque ligne de tableau lors d'un changement de donnée.
   <br>
   Elle requiert de définir l'instance de type ListCell<Zweet> qui sera utilisée pour le rendu. 
   Dans un premier temps retourner une instance de type TextFieldListCell<Zweet>

        final ListCell<Zweet> cell = new TextFieldListCell<Zweet>();
        return cell;

3. Affecter cette classe comme CellFactory à notre composant ListView:<br>

        timeline.setCellFactory(new ZweetCell());

4. Tester l'affichage.

<div class="pb"></div>

5. Modifier la ZweetCell
   <br>
   Dans la classe ZweetCell, plutôt que de retourner un TextFieldListCell<Zweet>, on décide de 
   retourner une instance d'une nouvelle classe qui étend ListCell<Zweet>. 
   <br>
   Celle-ci devra surcharger la méthode updateItem(...):

        @Override
        public void updateItem(Zweet item, boolean empty) {
          super.updateItem(item, empty);
          Text text = new Text();
          text.setText("un texte !");
          setGraphic(text);
        }

   Dans cette méthode le paramètre empty précise si des données sont présentes et le paramètre Zweet possède la valeur « modèle » de la ligne (null si empty est vrai). 
   Si empty est faux : le CellFactory a pour responsabilité de dessiner le rendu des données. 
   <br>
   Ajouter une instance de type Text à la classe et instancier ce composant si des données sont présentes et que sa valeur est nulle. 
   Affecter la valeur du texte à afficher à zweet.toString().

6. Tester le rendu.

7. Optimisation
   <br>
   Afin d'optimiser l'implémentation de la CellFactory, il n'est pas nécessaire d'instancier à chaque Zweet un nouveau Text si celui-ci a déjà été créé. 
   Modifier l'implémentation de ZweetCell pour qu'il n'utilise qu'un seul composant Text qui sera défini comme variable d'instance.

8. Tester le rendu.

## Databinding

1. Il est actuellement possible de redimensionner la taille de l'application JavaFX, la valeur maximale du composant Text est en réalité dynamique! 
<br>
Définir un databinding entre le composant ListView et le composant Text afin qu'ils partagent les mêmes contraintes de largeur (propriété wrappingWidth pour Text et widthProperty pour ListView).

2. Tester le rendu avec plusieurs redimensionnements de fenêtre.

3. On souhaite toujours avoir un écart entre le composant Text et la scrollbar verticale à droite, définir une binding expression sur wrappingWidth pour que cet écart soit toujours de 20 pixels.

4. Tester le rendu avec plusieurs redimensionnements de fenêtre.
