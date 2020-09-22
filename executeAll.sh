CWD=$(pwd) #Obtiene el directorio de ejecución

./build.sh

./publish.sh

sshpass -p "@C0D1S0FT" ssh root@157.230.88.74 /opt/fxmanager/deploy.sh



