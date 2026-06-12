FROM eclipse-temurin:21-jre-alpine
RUN addgroup --system template-group && adduser --system --ingroup template-group template-user
WORKDIR /home/template-user
COPY --chown=template-user:template-group target/courier-service*.jar courier-service.jar
USER template-user:template-group
ENTRYPOINT ["java", "-jar", "courier-service.jar"]