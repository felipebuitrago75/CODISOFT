CWD=$(pwd) #Obtiene el directorio de ejecución

#Se Actualiza el repositorio
git pull

#Se construye el backend
cd fxmanager-management
./gradlew clean 
./gradlew buildDocker -x test

cd $CWD
docker tag co.com.smartcode/fxmanager:0.0.1-SNAPSHOT  fxmanager
docker push felipebuitrago75/fxmanager:co.com.smartcode/fxmanager:0.0.1-SNAPSHOT
docker rmi -f co.com.smartcode/fxmanager:0.0.1-SNAPSHOT

#Se construye el frontend
cd fxmanager-ng/docker
./ng-build

cd $CWD

docker push felipebuitrago75/fxmanager:fxmanager-ng

#Se construye la base de datos
cd config/database
./build-docker

cd $CWD

docker push felipebuitrago75/fxmanager:fxmanager-db:latest
