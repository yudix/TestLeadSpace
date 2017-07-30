FROM openjdk:8

RUN apt-get update && apt-get install -y \
  maven \
  git
RUN java -version; mvn -version
WORKDIR /usr/src/testApp
RUN git clone https://github.com/yudix/dropwizard-akka.git /usr/src/testApp
RUN mvn clean package
CMD java -Xmx2048m -Xms1024m -jar target/dropwizard-akka-1.0-SNAPSHOT.jar server src/main/resources/app-config.yml

