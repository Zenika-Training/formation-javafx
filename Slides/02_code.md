# Exemple de code

<!-- .slide: class="page-title" -->



## Sommaire

<!-- .slide: class="toc" -->

- [Syntaxe de base](#/1)
- **[Code et tableaux](#/2)**
- [Positionnement des images](#/3)
- [Animations et syntaxe avancée](#/4)



## Code

Le code ci-dessous utilise la coloration syntaxique de Java.

```java
public class Slide {

  private String name = "slide";

  public byte[] toPdf(){
    return converter.toPdf(this);
  }
}
```

Lorsqu'il est entouré de texte, l'exemple est encadré.



## Syntaxes disponibles

La liste des syntaxes disponibles est dans la [doc HighlightJS](http://highlightjs.readthedocs.org/en/latest/css-classes-reference.html): `java`, `javascript`, `json`, `html`, `css`, `xml` pour les plus courantes

Si on ne précise pas, HighlightJS essaye de détecter la syntaxe.
Pour les cas, où il ne s'agit pas d'un langage (stacktrace, sortie console, commandes shell, fichiers de config...), on peut désactiver la coloration syntaxique en utilisant :

- `text` :

```text
$ echo "Formation Zenika"
Formation Zenika
{"du":"json"} sans mise en forme
```

- ou `no-highlight`

```no-highlight
$ echo "Formation Zenika"
Formation Zenika
{"du":"json"} sans mise en forme
```



## Code pleine page

```json
{
  "name": "slide",
  "formats": [
    "html",
    "pdf"
  ],
  "chapters": 4,
  "complete": true
}
```



## Code inline

- Pour ajouter du code inline, entourer le code de backquote : **'\`'**

  Exemple : \`echo "hello world"\` produit `echo "hello world"`

- Pour afficher des backquote dans le code, double les backquotes entourantes.

  Exemple : \`\`echo \`pwd\` \`\` produit ``echo `pwd` ``

- Il n'est pas nécessaire d'encoder les caractères HTML dans le code

  Exemple : \`echo "&lt;body&gt;"\` produit `echo "<body>"`



## Tableau simple

Exemple de tableau :

| Univers | Formation |
| ------------- | ------------- |
| Web | Javascript |
| Web | Angular |
| Java | Java |




## Tableau justifié

Exemple de tableau :

| Univers | Formation | Tarif
| :-------------: | ------------- | ------:
| Web | Javascript | 1000
| Web | Angular | 1500
| Java | Java | 1500



<!-- .slide: class="page-questions" -->



<!-- .slide: class="page-tp2" -->
