mvn clean install
docker rmi turbulence6th/gateway:latest
docker build -t turbulence6th/gateway .
docker run -p 80:8080 -t turbulence6th/gateway