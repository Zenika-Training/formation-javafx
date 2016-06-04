<div class="pb"></div>

# TP 6 :  Associer un comportement à l'IHM

## Objectifs

- Ajouter des listeners de différentes manières
- Utiliser des propriétés avancées CSS

## Produire des Zweets

1. Dans la partie inférieure de l'application, lier le clic du bouton à une fonction du contrôleur, cette méthode va nous permettre d'émettre des Zweets.

2. Dans cette méthode, forger un Zweet et utiliser l'API Zwitter afin de publier un Zweet via la fonction publish(Zweet zweet). 
   <br>
   Afin de forger un Zweet correct, il faudra valuer un objet ZweetUser  avec un username et displayname au choix et en utilisant comme image de profil "znk.png".

3. Tester l'emission d'un Zweet.

4. Afin de différencier l'animation d'un Zweet « externe » d'un Zweet produit, modifier l'implémentation de ZweetPane afin que l'animation dure moins de temps pour un Zweet produit.

5. Tester.

## Composant barre de recherche

1. Créer un nouveau composant nommé SearchBox étendant la classe Region.

2. Ce composant possède un champs texte et un bouton qui seront des variables d'instance. Ajouter ces composants au rendu dans le constructeur de SearchBox.

3. Intégrer ce composant dans le fichier FXML de l'application pour remplacer la partie haute dédiée à la recherche.

4. Tester le rendu.

5. Lors d'un clic sur le bouton, le texte doit être effacé. Ajouter un listener en Java dans SearchBox afin d'effectuer cette action.

6. Le bouton ne doit être visible que si le texte saisi n'est pas vide. Ajouter un binding afin de masquer le bouton quand le texte est vide.

7. Tester le composant SearchBox avec quelques saisies de texte.

8. Afin de bénéficier d'un redimensionnement automatique, ajouter les bindings adéquats dans l'implémentation de SearchBox entre les diverses largeurs / hauteurs possibles.

9. Tester plusieurs redimensionnements et vérifier que le texte de saisie se redimensionne correctement.

10. Pour enrichir le rendu de cette barre de recherche, utiliser les images fournies afin d'ajouter une loupe et modifier le rendu du bouton. 
    <br>
    Pour cela, on définira des classes CSS. On utilisera notamment les propriétés suivantes pour le rendu de la saisie :

        -fx-background-image:  url('images/middle.png'), url('images/left.png'), url('images/right.png') ;
        -fx-background-position: ...
        -fx-background-repeat: ...

11. Tester le rendu.

![Node.js](ressources/search.png)
