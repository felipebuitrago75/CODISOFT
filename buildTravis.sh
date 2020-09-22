CWD=$(pwd) #Obtiene el directorio de ejecución

./cleanDocker.sh

#Se limpian las imagenes viejas
rm -rf dist/*

#Se Actualiza el repositorio
git pull

#Se construye el backend
cd fxmanager-management
./gradlew clean 
./gradlew buildDocker -x test

cd $CWD
docker tag co.com.smartcode/fxmanager:0.0.1-SNAPSHOT  fxmanager
docker rmi -f co.com.smartcode/fxmanager:0.0.1-SNAPSHOT
docker save -o dist/fxmanager fxmanager
cd dist
docker push felipebuitrago75/fxmanager:fxmanager
cd ..

#Se construye el frontend
cd fxmanager-ng/docker
./ng-build

cd $CWD
docker save -o dist/fxmanager-ng fxmanager-ng
cd dist
docker push felipebuitrago75/fxmanager:fxmanager-ng
cd ..
#Se construye la base de datos
cd config/database
./build-docker

cd $CWD
docker save -o dist/fxmanager-db fxmanager-db
cd dist
docker push felipebuitrago75/fxmanager:fxmanager-db
cd ..