#生成仓库
#FROM chaimm/zookeeper-dubbo:1.0
#COPY ./zk/dubbo.properties /zookeeper-3.4.10/tomcat/apache-tomcat-8.5.23/webapps/dubbo-admin-2.8.4/WEB-INF/dubbo.properties
#COPY ./zk/zk-dubbo-entrypoint.sh /zk-dubbo-entrypoint.sh
#ENTRYPOINT [ "bash","/zk-dubbo-entrypoint.sh"]


#拉取本地镜像 error
FROM zerechen/zookeeper-dubbo:1.0

COPY ./zk/dubbo.properties /zookeeper-3.4.10/tomcat/apache-tomcat-8.5.23/webapps/dubbo-admin-2.8.4/WEB-INF/dubbo.properties

ENTRYPOINT [ "bash","/zk-dubbo-entrypoint.sh"]