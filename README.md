# Getting Started
This is a demo of Keycloak with Spring.

The expectation is KC demo realm is configured with a user role and KC is hosted on keycloak.auth-server-url=http://localhost:8080/
KC details are in the application.properties

# Steps

Start kc from bin
```bash
kc start-dev
```

Create the admin user from http://localhost:8080/ for the master realm.
Add the demo realm by importing from the demo-realm.json which is from 
https://github.com/keycloak/keycloak-demo/blob/master/demo-realm.json

To build the Spring app
```bash
mvn clean package 
```

To run locally
```bash
java -jar target/kc-0.0.1-SNAPSHOT.war
```

# Kubernetes

To build containers

```bash
docker build --tag kcapp:1.0 . --file Dockerfile.app

docker build --tag mykc . --file Dockerfile.kc

kubectl apply -f k8s/

kubectl port-forward service/kc 9090:8080

kubectl port-forward service/app 8081:8081

kubectl port-forward service/zipkin 9411:9411

```
Zipkin
Go to 
http://localhost:9411/ 

App
http://localhost:8081/

Keycloak
http://localhost:9090/
For setting up demo realm use admin/change_me


