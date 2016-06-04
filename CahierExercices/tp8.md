<div class="pb"></div>

# TP 8 : API Task

## Objectifs
- Découvrir l'API Worker et plus particulièrement l'implémentation Task
- Déclencher une Task / Annuler une Task
- Réagir à la complétion ou l'échec d'une Task

## Implémentation d'une Task

1. Créer une nouvelle classe nommée SearchTask qui étend Task<Set<Zweet>>.

2. Cette Task a besoin de deux paramètres pour être construite, une collection de Zweet nommée zweets et une String nommée keyword. 
   <br>Ces deux éléments permettront  de rechercher un texte dans une collection de Zweets et de retourner le résultat sous forme de Set.

3. Implémenter la méthode call() qui renvoie le Set des Zweets contenus dans la collection qui contiennent le String keyword.

4. Afin de simuler une recherche lente, ajouter un Thread.sleep(1000) dans la méthode call().

5. Ajouter un listener dans le champs de saisie de SearchBox afin d'exécuter une Task SearchTask quand il y a de nouveaux caractères saisis.

6. Définir un handler onSucceededHandler qui affecte le Set des zweets filtrés comme source à notre composant ListView.

7. Tester le filtrage.

8. Afin de revenir à la vue de tous les Zweets, réaffecter la collection de Zweets originale quand il n'y a plus de caractères saisis dans SearchBox.

9. Tester.

<div class="pb"></div>

## Optimisation

1. Comme chaque caractère saisi va produire une recherche, on cherche à optimiser ce traitement : lancer la recherche seulement si au moins 3 caractères sont saisis.

2. Tester.

3. Comme chaque caractère au-delà de trois va lancer une recherche, seule la dernière recherche lancée n'a d'intérêt. <br>
   Il faut donc annuler via cancel() la Task en cours si elle est toujours en traitement, avant de lancer une nouvelle recherche.

4. Tester.

5. Vérifier les différents cas d'utilisation et traiter les derniers cas particuliers (recherche annulée, aucun caractère saisi).

6. Tester.
