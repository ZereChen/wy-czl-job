#!/bin/bash

bash /zookeeper-3.4.10/tomcat/apache-tomcat-8.5.23/bin/startup.sh
bash /docker-entrypoint.sh zkServer.sh start-foreground