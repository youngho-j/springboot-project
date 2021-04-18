#!/bin/bash

# 쉘에서는 타입 없이 변수를 선언하여 저장, '$변수명'을 사용하여 변수 사용가능
REPOSITORY=/home/ec2-user/app/step1
PROJECT_NAME=springboot-project

# git clone을 받았던 디렉토리로 이동
cd $REPOSITORY/$PROJECT_NAME/

echo "> Git Pull"

# master 브랜치의 최신 내용을 받아옴
git pull

echo "> 프로젝트 Build 시작"

# 프로젝트 내부의 grablew로 build 수행
./gradlew build

echo "> step1 디렉토리 이동"

cd $REPOSITORY

echo "> Build 파일 복사"

# build의 결과물인 jar 파일을 복사해 jar파일을 모아둔 위치로 복사
cp $REPOSITORY/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/

echo "> 현재 구동중인 애플리케이션 pid 확인"

# 기존에 수행중이던 스프링 부트 애플리케이션을 종료, 프로세스 이름을 통해 process id만 추출
CURRENT_PID=$(pgrep -f ${PROJECT_NAME}.*.jar)

echo " 현재 구동 중인 애플리케이션 pid: $CURRENT_PID"

# 현재 구동중인 프로세스id 값이  있는지 없는지 판단후 기능 수행
if [ -z "$CURRENT_PID" ]; then
   echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
   echo "> kill -15 $CURRENT_PID"
   kill -15 $CURRENT_PID
   sleep 5
fi

echo "> 새 애플리케이션 배포"
# 새로 실행할 jar 파일명을 찾고, tail -n 옵션으로 최신 파일을 변수에 저장
JAR_NAME=$(ls -tr $REPOSITORY/ | grep jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"
# 찾은 jar 파일명으로 해당 jar파일을 nohup으로 실행, 내장 톰캣 사용하므로 jar파일만 있으면 바로 웹 애플리케이션 서버 실행 가능
# 실행자가 터미널을 종료해도 애플리케이션은 계속 구동 되도록 nohup명령어를 사용
nohup java -jar -Dspring.config.location=classpath:/application.properties,/home/ec3-user/app/application-oauth.properties $REPOSITORY/$JAR_NAME 2>&1 &
