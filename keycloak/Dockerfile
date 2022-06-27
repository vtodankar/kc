
FROM quay.io/keycloak/keycloak:latest
WORKDIR /opt/keycloak
RUN keytool -genkeypair -storepass password -storetype PKCS12 -keyalg RSA -keysize 2048 -dname "CN=server" -alias server -ext "SAN:c=DNS:localhost,IP:127.0.0.1" -keystore conf/server.keystore
ENV KEYCLOAK_ADMIN=admin
ENV KEYCLOAK_ADMIN_PASSWORD=change_me
ENV KC_HOSTNAME=localhost
COPY startup.sh startup.sh
COPY demo-realm.json /opt/keycloak/data/import/demo-realm.json
ENTRYPOINT ["sh", "startup.sh"]
