#FROM zerechen/tomcat8:1.0
#ADD ./target/czl-controller-0.0.1-SNAPSHOT.war /opt/tomcat/webapps/ROOT.war
#ENTRYPOINT ["bash","tomcat.sh"]


FROM java:8
ADD ./target/czl-controller-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]