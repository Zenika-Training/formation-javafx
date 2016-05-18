#!/bin/bash
set -euo pipefail
IFS=$'\n\t'

# Volumes: folders shared between hosts and docker containers
### Ajout d'un '/' devant le pwd ###
# Il semblerait que git bash (sur windows) fait une mauvaise conversions des
# chemins, lorsque ce derniers les transmets a docker, en effet le 'C:\' disparait
# tout simplement. L'ajout du '/' avant $PWD permettra de doubler le '/' au debut
# du chemin ce qui permet de compenser la suppression
###
export VOLUME_SLIDES="/$PWD/Slides/:/data/Slides/"
export VOLUME_CAHIEREXERCICES="/$PWD/CahierExercices:/data/CahierExercices"
export VOLUME_GRUNTFILE="/$PWD/Gruntfile.js:/data/Gruntfile.js"
export VOLUME_PACKAGE="/$PWD/package.json:/data/package.json"
export VOLUME_PDF_GENERATE="/$PWD/PDF:/data/PDF"
export VOLUME_PDF_PUBLISH="/$PWD/PDF:/data/node_modules/zenika-formation-framework/pdf"
# Docker image related informations
export DOCKER_IMAGE_NAME="zenika/formation-framework"
export DOCKER_IMAGE_VERSION="latest"

GREEN='\033[0;32m'
BLUE='\033[0;34m'
PURPLE='\033[0;35m'
NC='\033[0m'

usage(){
  echo "Usage: `basename $0` pdf|dev|prod|clean" >&2
  exit 1
}

checkInstall(){
  commandExists docker || { echo "docker must be installed" >&2; exit 1; }
}

commandExists(){
  command -v "$1" 1>/dev/null 2>&1 \
      && return 0 || return 1
}

open-url() {
  sleep 2
  echo -e "${PURPLE}Ouverture de l'url $@${NC}"
  if commandExists xdg-open; then
    xdg-open $@ &
  elif commandExists open; then
    open $@
  fi
}

retrieveFormationVersion(){
  VERSION=`cat package.json | sed -n -e '/"zenika-formation-framework":/ s/^.*#tags\/\(.*\)".*/\1/p'`
  if [ ! -z $VERSION ]
  then
    export DOCKER_IMAGE_VERSION=$VERSION
  fi
}

getDockerAddress(){
  if commandExists docker-machine; then
    docker-machine ip $DOCKER_MACHINE_NAME
  elif commandExists boot2docker; then
    echo `boot2docker ip`
  else
    echo localhost
  fi
}

generatePdf(){
  echo -e "${GREEN}Génération du pdf${NC}"
  docker run -it --rm \
      -v "$VOLUME_GRUNTFILE" -v "$VOLUME_SLIDES" -v "$VOLUME_PACKAGE" \
      -v "$VOLUME_CAHIEREXERCICES" -v "$VOLUME_PDF_GENERATE" \
      "$DOCKER_IMAGE_NAME":"$DOCKER_IMAGE_VERSION" \
      grunt pdf
}

runContainer() {
    local containerName=${PWD##*/}
    docker run -d -P \
        -v "$VOLUME_GRUNTFILE" -v "$VOLUME_SLIDES" -v "$VOLUME_PACKAGE" \
        -v "$VOLUME_CAHIEREXERCICES" -v "$VOLUME_PDF_PUBLISH" \
        --name="$containerName" \
        "$DOCKER_IMAGE_NAME":"$DOCKER_IMAGE_VERSION" \
        $* > /dev/null
    echo "  Nom de conteneur : $containerName"
    local dockerPort=`docker ps -l | grep "$containerName" | sed 's/.*:\([0-9]*\)->8000.*/\1/'`
    local dockerAddress=$(getDockerAddress)
    open-url http://$dockerAddress:$dockerPort/
}

runProd(){
  echo -e "${GREEN}Demarrage en mode prod${NC}"
  runContainer grunt sed copy:rename displaySlides
}

runDev(){
  echo -e "${GREEN}Demarrage en mode dev${NC}"
  runContainer grunt displaySlides
}

clean(){
  local containerName=${PWD##*/}
  if docker ps -a | grep -q "$containerName"; then
    echo -e "${BLUE}Arret et suppression du conteneur existant ($containerName)${NC}"
    docker stop "$containerName" 2>&1 >/dev/null
    docker rm "$containerName" 2>&1 >/dev/null
  else
    echo -e "${BLUE}Aucun conteneur identifié à nettoyer ($containerName)${NC}"
  fi
}

checkInstall

if [ "$#" -eq 0 ]; then
  usage
fi

for arg in "$@"
do
  case "$arg" in
    pdf)
      retrieveFormationVersion
      generatePdf
      shift
      ;;
	  dev)
        retrieveFormationVersion
	    runDev
	    shift
	    ;;
	  prod)
        retrieveFormationVersion
	    runProd
	    shift
	    ;;
	  clean)
	    clean
	    shift
	  ;;
	  *)
	    echo "Argument invalide '$arg'" >&2
	    usage # unknown option
	    ;;
  esac
done
