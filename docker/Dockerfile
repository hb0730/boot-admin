FROM openjdk:11-jre

MAINTAINER bing_huang <1278032416@qq.com>

RUN mkdir /app

ENV SPRING_PROFILES_ACTIVE="test" \
    BASE_DIR="/app" \
    JAVA_OPTS="-server -Xms2048m -Xmx2048m -Xmn512m -XX:MaxMetaspaceSize=512m"

# join arthas
COPY --from=hengyunabc/arthas:latest /opt/arthas /opt/arthas

COPY boot-admin-1.0.0-SNAPSHOT.jar  /app/app.jar

WORKDIR /$BASE_DIR

RUN mkdir /app/bin

ADD docker-entrypoint.sh bin/docker-entrypoint.sh

RUN chmod +x bin/docker-entrypoint.sh

EXPOSE 8088

ENTRYPOINT ["bin/docker-entrypoint.sh"]
