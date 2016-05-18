Détails du modèle dans le [wiki](https://github.com/Zenika/Formation--Modele/wiki)

Documentation du [framework](https://github.com/Zenika/Formation--Framework)


## Supports PDF et Live

Les supports peuvent être obtenus à ces adresses :

Version release : https://zen-formation-modele.appspot.com/ [![Circle CI](https://circleci.com/gh/Zenika/Formation--Modele/tree/release.svg?style=svg&circle-token=2db9d589c3e04a16ec90df263f003eec7cf11eed)](https://circleci.com/gh/Zenika/Formation--Modele/tree/release)

Version courante : https://master-dot-zen-formation-modele.appspot.com/ [![Circle CI](https://circleci.com/gh/Zenika/Formation--Modele/tree/master.svg?style=svg&circle-token=2db9d589c3e04a16ec90df263f003eec7cf11eed)](https://circleci.com/gh/Zenika/Formation--Modele/tree/master)


## Organisation

``` shell
├── CahierExercices       # Contient les enoncés des TP
│   ├── Cahier.md         # Le fichier peut être découpé en autant de fichier que de TP
│   ├── parts.json        # Contient la liste des fichiers des énoncés
│   ├── README.md         # Explication détaillé de cette section
│   └── ressources        # Contient les images utilisées dans les énoncés des TP
├── Exercices             # Contient des starters dans TP ainsi que les corrections des TP
├── Installation          # Contient les éléments logiciels à fournir aux stagaires
│   └── README.md         # Explication détaillé de cette section
├── PLAN_en.md            # Plan commercial en anglais (affiché sur le site public des formations)
├── PLAN.md               # Plan commercial (affiché sur le site public des formations)
├── README.md             # ce fichier (ne pas effacer son contenu)
├── REFERENCES.md         # Références utiles pour donner ou préparer la formation (destiné aux formateurs)
├── run.sh                # Permet lancer la formation avec Docker sous Linux ou Windows (Git Bash)
├── SessionsNotes         # Contient l'ensemble des notes faites par les formateurs après chaque cession (organisation des TP, timing, ...)
│   └── README.md         # Explication détaillé de cette section
└── Slides                # Contient l'ensemble des slides
    ├── <num>_<slide>.md  # Contenu des slides
    ├── ...
    ├── README.md         # Explication détaillé de cette section
    ├── ressources        # Contient les images et autres éléments utilisés dans les slides
    └── slides.json       # Contient la liste des fichiers des slides
```

*Note : Chaque répertoire contient un fichier `README.md` précisant son usage*


## Utilisation avec Node.js

Pour générer et afficher la formation, il est nécessaire d'avoir `node` et `npm` d'installé (voir [node.js](http://nodejs.org)).

Installation de Node.js :

- https://nodejs.org/en/download
- https://nodejs.org/en/download/package-manager/

Exécuter la commande suivante :

```shell
npm install
```

Pour afficher les slides, exécuter la commande suivante :

```shell
grunt
```

Pour générer les `.pdf` des slides :

```shell
grunt pdf
```

Si vous utiliser NPM3, utiliser la commande :

```shell
gulp
```


## Utilisation avec Docker

Pour ceux qui veulent se passer de l'installation de `node` et `npm`, il est possible d'utiliser [Docker](https://www.docker.com).

Installation de Docker :

- Linux : https://docs.docker.com/linux/step_one/
- Mac : https://docs.docker.com/mac/step_one/
- Windows : https://docs.docker.com/windows/step_one/

Lancer une des commandes suivantes :

```shell
./run.sh dev   # pour afficher les slides
./run.sh prod  # pour afficher la page de garde
./run.sh pdf   # pour générer les `.pdf` des slides
./run.sh clean # pour terminer le conteneur Docker
```

Il est également possible de combiner les commandes :

```shell
./run.sh clean pdf prod
```

### Windows

Afin de pouvoir éxécuter un script `run.sh`, il faut disposer d'un émulateur de shell Unix-like : soit Git Bash (recommandé et fournit avec Git) soit Cygwin.


## Commandes RevealJS

- `Alt + click`: zoom in, zoom out
- `Up`, `Down`, `Left`, `Right`: Navigation
- `f`: Full-screen
- `s`: Show slide notes
- `o`: Toggle overview
- `b`: Turn screen black
- `Esc`: Escape from full-screen, or toggle overview

https://github.com/hakimel/reveal.js/wiki/Keyboard-Shortcuts


## Troubleshooting

En cas de problème lors de l'installation ou de l'utilisation, consultez la page dédié dans le wiki du Framework : [Troubleshooting](https://github.com/Zenika/Formation--Framework/wiki/Troubleshooting).


## Intégration Continue

Chaque formation dispose d'une version web (disponible uniquement via un compte @zenika.com).
Le mini-site de la formation contient les slides live, les slides PDF, le cahier d'exercice PDF, un lien vers le dépôt GitHub de la formation ainsi qu'un lien vers le catalogue de formation.

Ainsi il est important de bien remplir le fichier `package.json` avec

- `name` : nom technique de la formation (sans espace)
- `description` : le nom de la formation de la forme "Formation XXX"
- `homepage` : le lien vers la page du catalogue correspondante (e.g. http://www.zenika.com/formation-angularjs.html)
- `repository.url` : l'URL vers ce repository
- `config.deploy.name` : l'id de l'instance AppEngine (faire une demande à dsi@zenika.com)

### Utilisation des branches

2 branches sont automatiquement déployées sur AppEngine:

- `master` : il s'agit de la branche contenant les développements courants.
  - Elle est déployée sur `https://master-dot-{id AppEngine}.appspot.com`
- `release` : branche contenant la dernière version stable, utilisée en session de formation.
  - Elle est déployée sur `https://{id AppEngine}.appspot.com`

### Serveur d'IC

Le build et le déploiement sont réalisés par [CircleCI](https://circleci.com).
À ce titre, un fichier `circle.yml` est présent à la racine de ce projet.
Il n'y a à priori, aucune raison d'éditer ce fichier.
