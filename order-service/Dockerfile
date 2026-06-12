FROM eclipse-temurin:21-jre-alpine
RUN addgroup --system template-group && adduser --system --ingroup template-group template-user
WORKDIR /home/template-user
COPY --chown=template-user:template-group target/order-service*.jar order-service.jar
USER template-user:template-group
ENTRYPOINT ["java", "-jar", "order-service.jar"]