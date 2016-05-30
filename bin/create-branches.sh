#!/usr/bin/env bash

deleteDirs() {
    git rm -rf src/main/antlr4/com/nibado/example/antlr4/$1/ 2>/dev/null
    git rm -rf src/main/java/com/nibado/example/antlr4/$1/ 2>/dev/null
    git rm -rf src/test/java/com/nibado/example/antlr4/$1/ 2>/dev/null
}

if [[ `git status --porcelain` ]]; then
    echo There are changes, add and commit them first.
    exit 1
fi

git checkout master
git branch -Dq example/simple 2>/dev/null
git branch -Dq example/query 2>/dev/null
git branch -Dq example/script 2>/dev/null

git checkout -b example/simple
deleteDirs query
deleteDirs script
git commit -m "Deleted query and script dirs"

git checkout master
git checkout -b example/query
deleteDirs simple
deleteDirs script
git commit -m "Deleted simple and script dirs"

git checkout master






