mvn clean install
docker rmi -f turbulence6th/gateway:latest
docker build -t turbulence6th/gateway .
docker run -p 80:8080 -t turbulence6th/gateway --env-file=../../envs/gateway.env