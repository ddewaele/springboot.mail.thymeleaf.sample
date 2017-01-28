## Introduction
Simple example showing how to send mails using Spring Boot using Thymeleaf templates.
The application provides a rest interface allowing you to send emails.

```
curl -v -X POST -H "Content-Type: application/json" -d '{"to":"recipient@someDomain.com","subject":"The subject","message":"This is the message"}'  "http://localhost:8080/sendEmail"
```

## FakeSMTP

You can use [FakeSMTP](https://nilhcem.github.io/FakeSMTP/) to setup a local SMTP server.

```
sudo java -jar ~/Downloads/fakeSMTP-2.0.jar 
```

## Building
The sample is built using maven. 
```
mvn clean install
```

## Docker
The maven build file also generates a docker container with the name `springboot.mail.thymeleaf.sample`

The docker image can be started using

```
docker run --name springboot.mail.thymeleaf.sample -t \
-p 8080:8080 \
-v /tmp/logs:/var/log \
ddewaele/springboot.mail.thymeleaf.sample
```

You can also provide some additional options, like the SMTP host, or the location of the external templates

```
docker run --name springboot.mail.thymeleaf.sample -t \
-p 8080:8080 \
-v /tmp/logs:/var/log \
-v /root/thymeleaf/templates/:/etc/thymeleaf/templates/ \
-e "SPRING_PROFILES_ACTIVE=ext" \
-e "SPRING_MAIL_HOST=172.17.0.1" \
ddewaele/springboot.mail.thymeleaf.sample
```

## Starting
The maven built generates an executable JAR file.
The Spring boot application can  also be started using mvn :
```
mvn spring-boot:run
```

## Externalizing templates

Email templates can be externalized on the filesystem by starting the app with the `ext` profile.




## License
MIT