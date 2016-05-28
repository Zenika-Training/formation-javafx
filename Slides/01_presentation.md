# Présentation de JAVAFX 8

<!-- .slide: class="page-title" -->



## Sommaire

<!-- .slide: class="toc" -->

- **[Présentation de JavaFX 8]**
- [Première application](#/2)
- [Composants et layouts](#/3)
- [Données et architecture](#/4)

- [Enrichir vos interfaces](#/5)
- [Animation](#/6)
- [Concurrence](#/7)

- [Les événements](#/8)
- [Intégration avec Swing](#/9)
- [Tests et outils](#/10)
- [Pour aller plus loin](#/11)



## JavaFX en quelques mots: 1, 2 et .. 8

- Troisième itération du toolkit officiel d'Oracle pour la définition
d'Interfaces Homme-Machine (IHM) en Java
- Il a pour but de permettre la définition d'interfaces riches et
modernes sur toutes les Java Virtual Machines (JVM) disponibles
- JavaFX 8 supporte les dernières évolutions technologiques
hardware
  - Optimisé pour l'accélération matérielle GPU (Direct3D et OpenGL)
  - Optimisé pour les architectures multi-cores
- Support sur hardware alternatif
  - Déployable sur tablettes Android et iOS via JavaFX Android port et
RoboVM (Gluon)
  - Support dans Java Embedded Virtual Machine pour architectures CPU
ARM, par exemple Raspberry Pi (version <8u33 puis Gluon)



## JavaFX 1.0 : Les origines

<br>
*Une première version très décevante.*


- Issu du framework F3 créé par SeeBeyond en 2005
- Sorti en 2007 après le rachat par Sun de F3
- Runtime propre à ajouter à la JVM : similaire à l'installation
d'un plugin
- Support d'un langage déclaratif non XML (appelé
aujourd'hui VISAGE)
- Performances graphiques insuffisantes
- Compatibilité difficile avec les autres toolkits graphiques
Java : Swing, Eclipse RCP


<br>

**Adoption très marginale par la communauté**



## JavaFX 2.x : Bâti pour durer

<br>
*On change (presque) tout.*

- Performances accrues
- Support natif dans la JVM (à partir de Java 7)
- API riches disponibles (médias) et documentées
- Nouveau langage déclaratif FXML basé sur XML
- Nouveaux modes de déploiement supportés
- Intégration HTML 5 via WebKit

<br>
**ORACLE veut proposer JavaFX comme remplacement de
plusieurs API Java historiques : Swing, AWT, Applet,
API vidéo et audio**



## JavaFX 8.x : L'offensive

<br>
*A la conquête de nouveaux marchés*

- Utilisable sur JVM ARM Java Embedded
- Porté sur Android / iOS
- Nouvelles API 3D
- Nouveaux widgets
- Open source!
- Meilleur support de HTML5
- Intégration avec Swing efficace

<br>
**Java 8 représente une évolution majeure pour
  ORACLE, JavaFX 8 fait partie des innovations
  poussées**



## Succéder à SWING

Le monde a changé depuis '97

- **Modernisation de la conception d'IHM en Java**
  - Gérer les animations / effets
  - Gérer des flux multimédias
  - Intégrer de nouvelles « Gestures » de type touch

- **Faciliter le déploiement d'un client lourd / riche**
  - Options Web (WebStart, Embedded)
  - Options Desktop (avec JVM ou sans)

- **Améliorer le système de databinding**
  - Principe des Listeners
  - Bindings avec Property



## SWING et AWT

Les grands anciens

- **AWT est le premier toolkit IHM disponible en Java**
  - Composants "lourds", utilisant le système
  - Limité à l'intersection des capacités des différentes plate-formes 

- **Swing introduit en Java 1.2**
  - Se repose en partie sur AWT (essentiellement pour raisons de
compatibilité)
  - Composants "légers", entièrement gérés par Swing
  - Grande liberté dans la définition des interfaces
  - Des outils WYSIWYG disponibles (What You See Is What You Get) 
  - Adoption très large par la communauté




## Les particularités de Swing


- Description des composants et systèmes de layout
entièrement en Java
- Notion de GUI Thread
  - Obligation de gérer / comprendre plusieurs threads sous peine de
  performances dégradées
- Un style d'UI parfois en décalage par rapport à celle de l'OS
- Peu d'évolutions depuis plusieurs années

<br>
**De manière générale : des efforts assez importants à
fournir pour maîtriser le rendu graphique (performance,
style...) car Swing reste assez « bas niveau »**



## Le runtime JavaFX : un saut technologique

<br>
Le moteur de rendu est sélectionné par rapport à la plate-forme (DirectX, OpenGL, Software rendering au pire)

<figure>
    <img src="ressources/01_jfxar_dt_001_arch-diag.png" alt="JavaFX Architecture Diagram" width="60%"/>
</figure>

- API riche et de type « haut niveau »
- Nombreux composants disponibles
- Bibliothèque d'animations et effets



## FXLM + CSS + Java : un combo intéressant


- **FXML : XML descripteur de l'IHM et de sa mise en page (layout)**
  - Semblable à XAML, XUL, MXML, XHTML...
  - Transfert de compétence facilité
  - Intégration facile à un IDE et à un outil WYSIWYG
  - Limite la verbosité de la déclaration d'IHM en Java
  
- **CSS pour personnaliser l'apparence de l'application**
  - Meilleure maîtrise du rendu global
  - Meilleure maintenabilité

- **Java : la valeur sûre**
  - Utilisé pour le comportement de l'application, la logique métier
  - Fiable, maintenable, scalable

<!-- TODO: image d' illustration -->



## Pourquoi créer un client lourd aujourd'hui?

- Créer des applications avec mode déconnecté
- Définir des **écrans complexes**
- Enrichir d'effets graphiques, utiliser des visuels avancés
- Tirer partie des interactions avec le système et le **hardware**
- Disposer d'une **puissance de calcul** importante
- Meilleure **durabilité et maintenabilité** que les frameworks Web
- Non soumis à l'évolutivité et la diversité des navigateurs Web
- Adhère à Java et la JVM : support maîtrisé par Oracle
- Multi-support (iOS et Android)
- Etc...



## Le multi-support: indispensable aujourd' hui


* Intégré au JDK Oracle depuis la version 1.7
* Disponible sous Windows, MacOSX, Linux
* Version pour ARM avec Java Embedded
  (Raspberry Pi, ...)
* Gestion du multitouch
* Portages disponibles sur iOS et Android

<figure style="position: absolute; bottom: 60px; right: 30px; width: 17%">
    <img src="ressources/01_pad.png" alt="DukePad" />
</figure>


<figure style="position: absolute; bottom: 60px; right: 300px; width: 20%">
    <img src="ressources/01_raspi.png" alt="Raspberry Pi" />
</figure>


