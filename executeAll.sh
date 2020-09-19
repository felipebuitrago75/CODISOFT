CWD=$(pwd) #Obtiene el directorio de ejecución

./build.sh

./publish.sh

sshpass -p "p4r4s1t0" ssh root@68.183.133.209 /opt/fxmanager/deploy.sh



