#!/bin/bash

mvn clean package -DskipTests -f /opt/codedeploy-agent/deployment-root/$DEPLOYMENT_GROUP_ID/$DEPLOYMENT_ID/deployment-archive/pom.xml
