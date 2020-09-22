	CWD=$(pwd) #Obtiene el directorio de ejecución

#Limpiamos el servidor
sshpass -p "@C0D1S0FT" ssh root@157.230.88.74 'rm -rf /opt/fxmanager/dist/*'

#Subiendo archivos al servidor
sshpass -p "@C0D1S0FT" scp dist/fxmanager-ng root@157.230.88.74:/opt/fxmanager/dist/

sshpass -p "@C0D1S0FT" scp dist/fxmanager root@157.230.88.74:/opt/fxmanager/dist/

sshpass -p "@C0D1S0FT" scp dist/fxmanager-db root@157.230.88.74:/opt/fxmanager/dist/

sshpass -p "@C0D1S0FT" ssh root@157.230.88.74 /opt/fxmanager/deploy.sh


