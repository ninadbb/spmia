echo "Pushing service docker images to docker hub ...."
docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
docker push ninadbb/tmx-authentication-service:$BUILD_NAME
docker push ninadbb/tmx-confsvr:$BUILD_NAME
docker push ninadbb/tmx-eurekasvr:$BUILD_NAME
docker push ninadbb/tmx-licensing-service:$BUILD_NAME
docker push ninadbb/tmx-organization-service:$BUILD_NAME
docker push ninadbb/tmx-zipkinsvr:$BUILD_NAME
docker push ninadbb/tmx-zuulsvr:$BUILD_NAME