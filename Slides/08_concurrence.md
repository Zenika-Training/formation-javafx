Cette slide peut se décliner dans les couleurs de la charte que vous trouvez les plus opportunes

<numéro>

<numéro>

Introduction

Traitements simultanés en JavaFX

    •JavaFX est optimisé pour le traitement concurrent 

        •Plusieurs traitements simultanés sont possibles 

        •Appels de méthodes de type « callback » en asynchrone à la fin d'un traitement 

        •API fournie pour une intégration souple avec l'IHM 

        •API compatible avec les possibilités de concurrence natives de Java 

    •La concurrence est 

        •Complexe 

        •Difficile à déboguer 

        •Exigeante : seul le niveau parfait est correct 

        •Performante si bien modélisée 

        •Indispensable en JavaFX 

 

JavaFX Application Thread

    •Dédié au rendering de l'application 

        •Dessine les composants de la vue 

        •Gère les événements de l'application 

        •Exécute le code des composants JavaFX 

    •Problèmes liés 

        •Tout traitement long effectué dans l'Application Thread « gèle » ou ralentit l'affichage de l'application ! 

        •Aucun input utilisateur n'est reconnu (le thread ne gère plus les événements) 

        •Nécessité de comprendre et maîtriser le multithreading et l'asynchronisme 

GUI Thread et implications
 

Challenges techniques

    •Principales problématiques en JavaFX 

        •Comment créer et gérer de nouveaux threads ? 

        •Comment interagir entre threads personnalisés et l'Application Thread ? 

    •Cas d'utilisations classiques 

        •Appels HTTP ou autres types de requêtes sur le réseau 

        •Accès filesystem (ouverture de fichiers) 

        •Accès base de données distante ou locale 

        •Longue computation (décryptage, compression de données volumineuses...) 

Comment gérer la concurrence?
 

Multi-threading

    •Utiliser un pool de threads dédié 

    •Obtenir une notification lors de la fin d'un traitement ou de son échec 

        •Exemple : chargement d'une image terminé, afficher l'image 

    •Obtenir une notification de la progression en cours du traitement 

        •Exemple : téléchargement effectué à 35 % du total attendu 

    •Implémenter des tâches périodiques 

        •Exemple : ping périodique d'un serveur pour vérifier le réseau 

Quelques possibilités avec JavaFX
 

Créer un traitement asynchrone

    •Instancier un Thread qui aura pour charge d'exécuter du code hors JavaFX 

        •Par défaut, un thread est en mode idle 

        •Démarrage via la méthode start 

    •Définir un Runnable possédant le code à exécuter 

    •Il est possible de créer un pool de Thread pour plus de souplesse 

API Thread et Runnable
 

Accéder à l’Application Thread

    •Utilisation de la méthode statique Platform.runLater(...) 

Depuis un autre Thread
 

Notes sur Platform.runLater

    •En cas d'exécution de code IHM en dehors de l'Application Thread, on obtient une exception 

    •On délègue à JavaFX le soin d'exécuter le code quand cela lui semble optimal 

        •Pas de garantie en ordre de temps sur l'exécution du callback 

        •Que faire si on a beaucoup de callbacks ? Faut-il toujours utiliser Platform.runLater ? 

Précautions
 

API Worker

    •JavaFX met à disposition un framework de concurrence pour cadrer l'utilisation correcte des threads 

        •Meilleure maintenabilité 

        •Meilleure prédictibilité 

        •Meilleure performance 

    •Les classes se trouvent dans le package javafx.concurrent 

        •Interface Worker  

        •Implémentations disponibles à étendre : Task et Service 

        •Support des callbacks dans le JavaFX Application Thread natif! 

Faciliter les traitements asynchrones
 

API Worker

    •La progression est écoutable via des propriétés à exposer 

        •totalWork : représente la charge totale de travail, unités arbitraires 

        •workDone : représente la charge de travail déjà effectuée 

        •progress : pourcentage de travail effectué, double entre 0 et 1, calculé 

        •title et message peuvent servir à communiquer un status à l'utilisateur 

    •Implémentation d'un Worker 

        •Méthode call() utilisée en cas de succès 

        •Si une exception fait échouer le traitement, la propriété exception contiendra les informations de l'échec 

Fonctionnalités
 

API Worker

Propriétés exposées

 

Implémentation de Worker

    •Abstraction fournie basée sur Worker 

        •Implémentation concrète de Worker<T> 

        •Implémentation de FutureTask<T>  

        •Exécutable de manière "classique" selon l'API Java Concurrency 

    •Task représente un traitement unique, il ne peut être réutilisé 

        •Il doit être garbage collecté sous peine de leak mémoire 

        •Ne pas garder de référence définitive à un objet de ce type 

Extension de l'API Task
 

API Task

    •Réagir aux transitions d'état 

        •En surchargeant les méthodes cancelled(), succeeded(), failed(), etc... 

        •En bindant les propriétés onCancelledProperty(), onFailedProperty(), … 

        •En ajoutant des EventHandler<WorkerStateEvent> via setOnCancelled, setOnFailed, ... 

    •Méthodes pour mettre à jour le titre et le message 

    •Méthodes pour mettre à jour la progression 

        •updateProgress(workDouble, totalWork) 

Exemples d’utilisations
 

API Task

Emettre des résultats intermédiaires
 

API Task

Implémentation de call()
 

API Executor

    •Le but est de découpler le code à exécuter (Runnable) et la manière de l'exécuter, la tuyauterie implémentée par l'Executor 

        •Réutilisation des threads 

        •Scheduling 

        •Cache et pool de threads 

Depuis JDK 1.5
 

API Executor

    •Des implémentations concrètes sont instanciables via Executors.newXXX 

Quelques implémentations
 

API Executor

    •ExecutorService étend Executor pour ajouter la notion de Future 

    •Future<T> représente la promesse d'un résultat à venir, asynchrone 

    •On peut attendre ce résultat en bloquant (f.get()), ou préciser un timeout et réessayer plus tard (f.get(timeout, unit)) 

    •Possibilité d'annuler la tâche 

    •Intégrable dans l'API Service de JavaFX 

Notion de Future
 

API Service

    •Palie à la limitation des exécutions uniques des Task en créant une abstraction supplémentaire 

    •Le but d'un Service est de pouvoir définir des tâches spécifiques, de manière répétitive 

    •On peut l'associer à un Executor spécifique ou le laisser créer le sien 

    •Un Service est techniquement une Factory de Task 

Mieux gérer les Task
 

API Service

    •Les propriétés du service sont bindées à celles de la tâche en cours 

    •Start pour lancer la tâche, restart pour annuler une tâche en cours et en lancer une nouvelle 

Exemple
 

API Service

Pour un service avec des paramètres d'entrée, exposer une propriété pour ceux-ci et les capturer à la création de la Task

Interactions entre Service et Task
 

API Service

Récupérer le résultat de la Task via l'événement onSucceeded

Interactions entre Service et Task
 

Pour aller plus loin

    •Il est possible de définir une abstraction au-dessus de l'API Service afin de rendre automatique la définition de paramètres courants 

        •Pool de threads spécifique 

        •Handlers par défaut sur les événements d'échec 

        •Ajout de logs dans les threads utilisés 

    •Comme les traitements ont lieu hors Application Thread, les logs générés dans ces threads ne sont pas accessibles par défaut dans la console Java 

        •Une factory de Service permet donc de rendre plus aisé le déboguage 

Factory de Service?
 
