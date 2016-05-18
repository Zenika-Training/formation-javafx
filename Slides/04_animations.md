# Les animations

<!-- .slide: class="page-title" -->



## Sommaire

<!-- .slide: class="toc" -->

- [Syntaxe de base](#/1)
- [Code et tableaux](#/2)
- [Positionnement des images](#/3)
- **[Animations et syntaxe avancée](#/4)**



## Fragments

Les items s'affichent au clic. Lors de l'impression, tous les items sont affichés.

Notez que la flèche du bas est transparente lorsqu'il y a des "fragments".

<!-- .element class="fragment" -->
- Item 1

<!-- .element class="fragment" -->
- Item 2

<!-- .element class="fragment" -->
- Item 3

<!-- .element class="fragment" -->
- Item 4



## Fragments

Les items s'affichent au clic. 

Afficher plusieurs éléments en même temps avec `data-fragment-index`.

<!-- .element class="fragment" data-fragment-index="1" -->
- Item 1

<!-- .element class="fragment" data-fragment-index="1" -->
- Item 2

<!-- .element class="fragment" data-fragment-index="2" -->
- Item 3

<!-- .element class="fragment" data-fragment-index="2" -->
- Item 4



## Fragments & Animations

De type Arrivée ou Sortie :

<!-- .element class="fragment fade-in" -->
- fade-in

<!-- .element class="fragment fade-out" -->
- fade-out

<!-- .element class="fragment shrink" -->
- shrink

<!-- .element class="fragment roll-in" -->
- roll-in

Sur des éléments :

<!-- .element class="fragment highlight-current-blue" -->
- highlight-current-blue

<!-- .element class="fragment highlight-green" -->
- highlight-green

<!-- .element class="fragment highlight-red" -->
- highlight-red

<!-- .element class="fragment current-visible" -->
- current-visible



## Alertes

<!-- .element class="alert alert-info"-->
Info / `class="alert alert-info"`

<!-- .element class="alert alert-success"-->
Succès / `class="alert alert-succes"`

<!-- .element class="alert alert-warning"-->
Warning / `class="alert alert-warning"`

<!-- .element class="alert alert-danger"-->
Danger / `class="alert alert-danger"`



## Alertes avec positionnement absolu

<!-- .element class="alert alert-info" style="position: absolute; top: 125px; left: 10px; width: 200px;"-->
Info

<!-- .element class="alert alert-success" style="position: absolute; top: 125px; right: 10px; width: 200px;"-->
Succès

<!-- .element class="alert alert-warning" style="position: absolute; top: 50%; left: 20%; width: 250px;"-->
Warning

<!-- .element class="alert alert-danger" style="position: absolute; top: 50%; left: 60%; width: 150px;"-->
Danger




## Ajouter du HTML 

Il est possible d'inclure directement du code HTML dans le markdown.

<div style="background-color: black; color: white; padding: 5px 20px" class="code">
  &gt; git commit -m "init"<br>
</div>

<br>

Avec le code HTML :
```xml
<div 
    class="code"  
    style="background-color: 
        black; color: white; 
        padding: 5px 20px">
  &gt; git commit -m "init"<br>
</div>
```



<!-- .slide: class="page-title" -->



<!-- .slide: class="page-tp4" -->
