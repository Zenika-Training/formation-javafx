<div class="pb"></div>

# TP 7 :  Binding Avancé

## Objectifs

- Définir un Binding personnalisé
- Utiliser un binding à haut niveau
- Utiliser un binding à bas niveau en l'observant

## Ecouter les événements de saisie

1. Ajouter dans la partie inférieure de l'application un champ texte. <br>
   Celui-ci permettra de suivre le nombre de caractères saisi dans le champ de Zweet en affichant par exemple « 3 / 140 ».

2. Définir un binding dans le contrôleur de type IntegerBinding, ce binding permettra de suivre la saisie d'un Zweet (nombre de caractères) jusqu'à un maximum de 140.

3. Afin de pouvoir lier cet IntegerBinding au textProperty il faudra surcharger la méthode computeValue() de IntegerBinding afin que la valeur de l'IntegerBinding corresponde à la longueur de texte saisi dans textProperty.

4. Binder l'IntegerBinding personnalisé avec le TextArea (textProperty).

5. Tester à l'aide de la console / ou au mode debug que la valeur du binding est correcte.

6. Binder désormais l'IntegerBinding au texte dédié afin de montrer le nombre de caractères saisis.

7. Tester le rendu.

8. On désire ajouter une barre de progression afin de montrer l'évolution de la saisie (de 0 à 100%). Ajouter un composant ProgressBar.

9. Binder ce composant ProgressBar avec IntegerBinding sachant que 100 % = 140 caractères.

10. Tester le rendu.

11. On désire changer l'apparence du texte saisi et du texte suivant le nombre de caractères si la saisie dépasse 140 caractères. Pour cela, affecter une classe CSS à ces composants.

12. Écouter l'évolution de la saisie et changer la classe CSS de ces composants dès que la saisie dépasse 140 caractères (ou passe en dessous, il faut prévoir les deux cas).

13. Tester le rendu.
