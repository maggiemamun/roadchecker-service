// Build docker image
cd /roadchecker
docker build -t roadchecker-service

// run it on local machine
docker run -p 9090:8080 roadchecker-service

// Push to Docker Hub
docker tag roadchecker-service maggiemamun/roadchecker-service

//Login to DockerHub
docker login ( Provide user id and password)

//Pull docker Image from Docker Hub and run it on machine
* docker pull maggiemamun/roadchecker-service:latest
* docker run -p 9090:8080 maggiemamun/roadchecker-service