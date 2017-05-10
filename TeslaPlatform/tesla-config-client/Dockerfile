FROM hub.c.163.com/xbingo/jdk8

MAINTAINER edwin

ADD ./target/tesla-config-client-1.0-SNAPSHOT.jar /app/

CMD ["java","-Xmx256M","-jar","/app/tesla-config-client-1.0-SNAPSHOT.jar"]

EXPOSE 8701