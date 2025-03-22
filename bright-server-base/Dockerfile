FROM alibabadragonwell/dragonwell:17-anolis

MAINTAINER wangiegie@gmail.com

ENV TZ=Asia/Shanghai
ENV LANG C.UTF-8
ENV JAVA_OPTS="-Xms512m -Xmx512m -Djava.security.egd=file:/dev/./urandom -Ddruid.mysql.usePingMethod=false -Dspring.profiles.active=prod"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /bright-server-base /logs

WORKDIR /bright-server-base

EXPOSE 8181 8182

ADD ./target/bright-server-base.jar ./app.jar

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]
