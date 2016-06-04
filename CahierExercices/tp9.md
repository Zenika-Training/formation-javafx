<div class="pb"></div>

# TP 9 :  API Service

## Objectifs
- Découvrir l'API Service et les facilitations qu'il apporte
- Convertir / réutiliser une Task dans un Service
- Utiliser les bindings pour paramétrer chaque exécution du Service

## Conversion vers l'API Service

Plutôt qu'explicitement créer un thread pour l'exécution de la SearchTask, devoir garder trace des tâches en cours pour pouvoir les annuler, etc... il est plus intéressant de s'appuyer sur un Service.

Nous allons donc définir un SearchService, qui aura pour rôle d'instancier des SearchTask, dans le package com.zenika.fx.zwitter.search lui aussi.


## Paramétrage du Service

La SearchTask était créée explicitement par nous, à un endroit où nous avions accès au champs texte et à la timeline. Nous devons maintenant laisser soin au service de passer ces paramètres au constructeur de la Task.

Nous allons avoir besoin de deux propriétés pour le mot-clé et la liste de Zweets, que nous capturerons au moment de la création de la tâche en elle-même, et auxquelles nous affecterons la valeur correcte (éventuellement par binding) à l'appel du service.
