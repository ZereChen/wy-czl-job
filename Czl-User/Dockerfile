FROM zerechen/tomcat8:1.0
ADD ./target/*.war /opt/tomcat/webapps/app.war
ENTRYPOINT ["bash","tomcat.sh"]