CWD=$(pwd) #Obtiene el directorio de ejecución

#Limpiamos el servidor
sshpass -p "p4r4s1t0" ssh root@167.99.227.123 'rm -rf /opt/fxmanager/dist/*'

#Subiendo archivos al servidor
sshpass -p "p4r4s1t0" scp dist/fxmanager-ng root@167.99.227.123:/opt/fxmanager/dist/

sshpass -p "p4r4s1t0" scp dist/fxmanager root@167.99.227.123:/opt/fxmanager/dist/

sshpass -p "p4r4s1t0" scp dist/fxmanager-db root@167.99.227.123:/opt/fxmanager/dist/

sshpass -p "p4r4s1t0" ssh root@167.99.227.123 /opt/fxmanager/deploy.sh


