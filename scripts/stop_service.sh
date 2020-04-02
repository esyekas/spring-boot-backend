#!/bin/bash

isExistApp=`pgrep java`
if [[ -z $isExistApp ]]
then
     echo "Application is not running"
     exit 1
else
    echo "Application pid $isExistApp stopping"
    kill -9 $isExistApp
fi
