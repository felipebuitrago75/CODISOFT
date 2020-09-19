CWD=$(pwd) #Obtiene el directorio de ejecución

./cleanDocker.sh

docker load < dist/fxmanager
docker load < dist/fxmanager-db
docker load < dist/fxmanager-ng


docker run -d -p 3306:3306 --name fxmanager-db -e MYSQL_ROOT_PASSWORD=root fxmanager-db

docker run -p 80:80 --name fxmanager-ng -d fxmanager-ng

sleep 30

docker run -p 8080:8080 --name fxmanager --link fxmanager-db:mariadb -d fxmanager





