#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

IDLE_PORT=$(find_idle_port)
IDLE_PROFILE=$(find_idle_profile)
CONTAINER_ID=$(docker container ls -f "name=${IDLE_PROFILE}" -q)

echo "> ${CONTAINER_ID} 에서 구동중인 애플리케이션 확인"

echo "> $IDLE_PORT 에서 구동중인 애플리케이션 pid 확인"
IDLE_PID=$(lsof -ti tcp:${IDLE_PORT})

if [ -z ${CONTAINER_ID} ] # IDLE_PID
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> docker stop ${IDLE_PROFILE}"
  sudo docker stop ${IDLE_PROFILE}

  echo "> docker rm ${IDLE_PROFILE}"
  sudo docker rm ${IDLE_PROFILE}

  echo "> kill -15 $IDLE_PID"
  kill -15 ${IDLE_PID}
  sleep 5
fi