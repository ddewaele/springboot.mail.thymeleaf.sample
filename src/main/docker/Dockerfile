FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD springboot.mail.thymeleaf.sample-*.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
VOLUME ["/var/log"]
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dlogging.file=/var/log/springboot.mail.thymeleaf.sample.log -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]