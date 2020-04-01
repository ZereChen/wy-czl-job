NAME = local/zookeeper-dubbo
VERSION = 4.0
CONTAINER_NAME=zookeeper3
ZK_PORT=2182
DUBBO_ADMIN_PORT=10000

build:
	docker build -t ${NAME}:${VERSION} -f ./zk/Dockerfile .
push:build
	docker push ${NAME}:${VERSION}
run:
	docker run --name ${CONTAINER_NAME} -p ${ZK_PORT}:2181 -p ${DUBBO_ADMIN_PORT}:8080 ${NAME}:${VERSION}