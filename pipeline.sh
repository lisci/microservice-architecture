#!/bin/bash
# Constants
SPRING_CLOUD_API_GATEWAY_SERVICE="spring-cloud-api-gateway-service"
SPRING_CLOUD_DISCOVERY_SERVICE="spring-cloud-discovery-service"
SPRING_CLOUD_CONFIG_SERVER="spring-cloud-config-server"
ENGINE_SERVICE="engine-service"
FRAUD_SERVICE="fraud-service"

# Preparing system pruning volumes, images, container, network
echo "Preparing system pruning volumes, images, container, network"
docker system prune --force
# clear

echo "## Building ${SPRING_CLOUD_API_GATEWAY_SERVICE}"
cd ${SPRING_CLOUD_API_GATEWAY_SERVICE}
mvn clean install -U
# clear

echo "## Building ${SPRING_CLOUD_DISCOVERY_SERVICE}"
cd ../${SPRING_CLOUD_DISCOVERY_SERVICE}
mvn clean install -U
# clear

echo "## Building ${SPRING_CLOUD_CONFIG_SERVER}"
cd ../${SPRING_CLOUD_CONFIG_SERVER}
mvn clean install -U
# clear

echo "## Building ${FRAUD_SERVICE}"
cd ../${FRAUD_SERVICE}
mvn clean install -U
# clear

echo "## Building ${ENGINE_SERVICE}"
cd ../${ENGINE_SERVICE}
mvn clean install -U
cd ..
# clear

docker-compose up --build
