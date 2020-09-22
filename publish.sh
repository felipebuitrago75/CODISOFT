CWD=$(pwd) #Obtiene el directorio de ejecución

#Limpiamos el servidor
rm -rf /CODISOFT/dist/*

cd /CODISOFT/dist
#Subiendo archivos al servidor
docker push felipebuitrago75/fxmanager:fxmanager-ng
docker push felipebuitrago75/fxmanager:fxmanager
docker push felipebuitrago75/fxmanager:fxmanager-db


./deploy.sh


