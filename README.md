# Library microservice catalogue

## Deploy

Main
```bash
export IMAGE_TAG=$(git describe --abbrev=0 --tags)

docker build -t rafaelshayashi/library-microservices-catalogue:$IMAGE_TAG -f cloud/docker/Dockerfile .
docker tag rafaelshayashi/library-microservices-catalogue:$IMAGE_TAG rafaelshayashi/library-microservices-catalogue:latest

docker push rafaelshayashi/library-microservices-catalogue:$IMAGE_TAG
docker push rafaelshayashi/library-microservices-catalogue:latest

```