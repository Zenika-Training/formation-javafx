# Cahier de TP

## Explications générales

Pour écrire le cahier de TP, vous avez 2 solutions :

- écrire tout le cahier dans le fichier `Cahier.md`.
- faire plusieurs fichiers Markdown et les lister dans le fichier `parts.json`

### Saut de page

Pour faire un saut de page, placer la ligne suivante :

```html
<div class="pb"></div>
```

## Génération du PDF

Pour générer le cahier de TP au format PDF, taper la commande suivante:
```
grunt generateCahierExercice
```

Il est possible de générer à la fois le cahier d'exercices et les slides au format PDF avec la commande `grunt pdf`

## Assets

Mettre des assets est possible avec un lien relatif au fichier `.md`

Voici un exemple d'image :

```markdown
![Node.js](ressources/logo-zenika.jpg)
```
