# Annexes

<!-- .slide: class="page-title" -->



## De la bonne utilisation des lambdas

@@@ TODO



## Architecture N-tiers : schéma

Il peut être intéressant d'avoir un framework pour faciliter les synchronisations avec un serveur distant des données dans un mode distribué (comme Granite)


<figure>
    <img src="ressources/12/schema.png" alt="Schema" />
</figure>



## Granite Data Services : Framework complet orienté JEE
- Framework d'intégration serveur pour JavaFX, Android et Flex 
- Permet de faciliter les échanges avec un serveur Java 
  - Spring + Hibernate côté serveur ou EJB3 
  - Sérialisation des entités vers le client JavaFX 
  - Messaging via WebSockets compatible JMS 
  - Génération de classes avec Property (compatibles databinding) 

- Version simple gratuite open source 
- Version payante complète open source 



## Granite Data Services : Résumé

- Avantages de la solution 
  - Payload très compact (marshalling JMF ou AMF) 
  - Possibilités d'architecture real-time 
  - Multi-supports 
  - Intégration poussée des solutions JEE / Spring / Hibernate 
  - Intégration facile à une usine logicielle 
  - Framework MVP côté JavaFX 

- L'utilisation de Granite est très profitable si 
  - Mode connecté >> Mode déconnecté 
  - Stack technique Java importante 
  - Le paiement de la licence Granite est possible 
