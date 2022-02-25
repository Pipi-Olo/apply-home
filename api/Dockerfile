FROM amazoncorretto:16

WORKDIR /root

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} applyhome.jar

CMD java -jar -Dspring.profiles.active=${active} applyhome.jar