CWD=$(pwd) #Obtiene el directorio de ejecución

docker stop fxmanager-ng
docker stop fxmanager
docker stop fxmanager-db

docker rm fxmanager-ng
docker rm fxmanager
docker rm fxmanager-db

docker rmi fxmanager-ng
docker rmi fxmanager
docker rmi fxmanager-db



