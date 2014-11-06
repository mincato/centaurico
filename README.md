# Archetype #

### Proyectos ###

* Frontend: AngularJS
* Backend: Java (Spring + Hibernate) + PosgreSQL

Descripción:
============
Este proyecto es un arquetipo mejorado del proyecto de trazabilidad para CNEA, con los nice-to-have que queremos incorporar para el proyecto de Argentarii para Epidata. En este proyecto se mantiene la capa de persistencia con JPA-Hibernate. Luego, la idea es crear otro proyecto que sea epidata-argentarii-archetype y reemplazar Hibernate por MongoDB.

El Front-End está preparado para correr en un Node.js, con el fin de que en un ambiente de desarrollo se pueda trabajar de forma cómoda.

El Back-End es una aplicación Java, armada con Maven, que expone servicios REST con JAX-RS (Apache CXF) y utiliza Spring + JPA (Hibernate). Provee un persistence.xml para funcionar con PostgreSQL. Para deployar el Front-End más el Back-End en un Tomcat, el perfil "grunt-build" de Maven se encarga de empaquetar todo en un único WAR listo para copiar a la carpeta "webapps" del contenedor de Servlets.

Para desplegar la aplicación web en Tomcat:
===========================================

Parado sobre la carpeta backend del proyecto, ejecutar:

> mvn clean package -Pgrunt-build && cp target/cnea-trazabilidad-archetype.war $CATALINA_HOME/webapps

Si todo sale bien, la aplicación estará disponible en localhost:8080/cnea-trazabilidad-archetype.

Para compilar solamente el backend:
===================================

Cuando se está desarrollando en el Back-End Java y no hubo cambios en ningún archivo del Front-End, se puede compilar sólo la parte de Back-End y empaquetar los .class y resources junto con los archivos del Front-End que ya se tenían procesados. Esto es conveniente, ya que la tarea "grunt build" es costosa y demora bastante.

Parado sobre la carpeta backend del proyecto, ejecutar:

> mvn clean package && cp target/cnea-trazabilidad-archetype.war $CATALINA_HOME/webapps

Si todo sale bien, la aplicación estará disponible en localhost:8080/cnea-trazabilidad-archetype.war.

Para levantar sólo el frontend y trabajar en caliente:
=======================================================

Parado sobre la carpeta frontend del proyecto, ejecutar:

> grunt

Si todo sale bien, la aplicación estará disponible en localhost:3000, apuntando a los servicios REST provistos por Tomcat. El Tomcat tiene que estar levantado con el backend ecuchando en el puerto 8080.

