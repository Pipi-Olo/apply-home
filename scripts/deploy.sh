#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=apply-home

echo "> Copy Build Files"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> Check RUNNING APPLICATION PID"

CURRENT_PID=$(pgrep -fl $PROJECT_NAME | grep jar | awk '{print $1}')

echo "> RUNNING APPLICATION PID : $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
        echo "> There is no RUNNING APPLICATION"
else
        echo "> kill -15 $CURRENT_PID"
        kill -15 $CURRENT_PID
        sleep 5
fi

echo "> NEW APPLICATION"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR NAME : $JAR_NAME"

echo "> Add authority $JAR_NAME"

chmod +x $JAR_NAME

echo "> Start $JAR_NAME"

nohup java -jar \
        -Dspring.config.location=classpath:/application.properties,/home/ec2-user/app/application-dev.properties \
        $JAR_NAME > $REPOSITORY/$JAR_NAME 2>&1 &