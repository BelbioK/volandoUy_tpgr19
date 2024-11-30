#!/bin/bash

# Definir los directorios de los proyectos
projects=("VolandoUy" "VolandoUyBack" "VolandoUyMobile")

# Definir la ruta de destino para los WAR
tomcat_webapps_path="/ens/devel01/tpgr19/apache-tomcat-10.1.33/webapps"
tomcat_bin_path="/ens/devel01/tpgr19/apache-tomcat-10.1.33/bin"

# Iterar sobre cada proyecto y ejecutar mvn clean install
for project in "${projects[@]}"; do
    echo "Ejecutando mvn clean install en $project..."
    cd "$project" || { echo "No se pudo acceder al directorio $project"; exit 1; }
    mvn clean install
    cd ..
done

# Copiar los WAR a la carpeta de Tomcat y renombrarlos
declare -A war_files=(
    ["VolandoUyBack"]=".m2/repository/VolandoUyBack/VolandoUyBack/0.0.1-SNAPSHOT/VolandoUyBack-0.0.1-SNAPSHOT.war"
    ["VolandoUyMobile"]=".m2/repository/VolandoUyMobile/VolandoUyMobile/0.0.1-SNAPSHOT/VolandoUyMobile-0.0.1-SNAPSHOT.war"
)

for project in "${!war_files[@]}"; do
    war_path="${war_files[$project]}"
    if [ -f "$HOME/$war_path" ]; then
        echo "Copiando $war_path a $tomcat_webapps_path y renombrando a ${project}.war..."
        cp "$HOME/$war_path" "$tomcat_webapps_path/${project}.war"
    else
        echo "No se encontró el archivo WAR en $war_path"
    fi
done

# Ejecutar el JAR de VolandoUy en una nueva terminal
jar_path="$HOME/.m2/repository/VolandoUy/VolandoUy/0.0.1-SNAPSHOT/VolandoUy-0.0.1-SNAPSHOT-jar-with-dependencies.jar"
if [ -f "$jar_path" ]; then
    echo "Ejecutando el JAR $jar_path en una nueva terminal..."
    gnome-terminal -- bash -c "java -jar '$jar_path'; exec bash"
else
    echo "No se encontró el archivo JAR en $jar_path"
fi

# Iniciar Tomcat en una nueva terminal
echo "Iniciando Tomcat en una nueva terminal..."
cd "$tomcat_bin_path" || { echo "No se pudo acceder a la carpeta de bin de Tomcat"; exit 1; }
gnome-terminal -- bash -c "bash ./catalina.sh run; exec bash"

echo "Proceso completado."

