FROM alibabadragonwell/dragonwell:17-anolis

MAINTAINER wangiegie@gmail.com

ENV TZ=Asia/Shanghai
ENV LANG C.UTF-8
ENV JAVA_OPTS="-Xms512m -Xmx512m -Djava.security.egd=file:/dev/./urandom -Ddruid.mysql.usePingMethod=false -Dspring.profiles.active=prod"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /bright-monitor /logs

WORKDIR /bright-monitor

EXPOSE 8180

ADD ./target/bright-monitor.jar ./app.jar

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]
