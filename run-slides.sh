#!/bin/bash

SCRIPT=$(readlink -f "$0")
SCRIPT_DIR=$(dirname "$SCRIPT")

cd "$SCRIPT_DIR" || exit 1

npm install || exit 1

exec ./node_modules/zenika-formation-framework/node_modules/grunt-cli/bin/grunt $*
