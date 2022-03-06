FROM amazoncorretto:16

WORKDIR /root

ARG JAR_FILE=*.jar

CMD java -jar -Dspring.profiles.active=${active} ${JAR_FILE}