Déploiement / packaging de l'application
========================================
    
En utilisant le plugin maven: http://zenjava.com/javafx/maven/
    
    
Géneration application executable
---------------------------------

Commande à executer:

    mvn jfx:jar

    
Package application native
--------------------------

Commande à executer:

    mvn jfx:native


Déploiement web
---------------

Commande à executer:

    keytool -genkey -keystore keystore.jks -alias jks
    mvn jfx:web


