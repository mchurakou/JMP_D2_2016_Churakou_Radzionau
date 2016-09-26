
Installation:

Soft: Apache Web Server, Apache Tomcat, Maven

install maven

install tomcat

install Apache Web Server

Add an user with roles manager-gui and manager-script to tomcat-users:
	edit %TOMCAT_PATH%/conf/tomcat-users.xml
	add:
	<tomcat-users>
		<role rolename="manager-gui"/>
		<role rolename="manager-script"/>
		<user username="%username%" password="%password%" roles="manager-gui,manager-script" />
		
configure maven with tomcat user credentials:
	edit %MAVEN_PATH%/conf/settings.xml
	add:
	<?xml version="1.0" encoding="UTF-8"?>
	<settings ...>
		<servers>

			<server>
				<id>TomcatServer</id>
				<username>%tomcat_username%</username>
				<password>%tomcat_password%</password>
			</server>
			
			

configure Apache Web Server:

	download apropriate mod_jk extesion from http://tomcat.apache.org/download-connectors.cgi
	copy mod_jk.so to %APACHE_PATH%/modules
	create worker.properties file in %APACHE_PATH%/conf directory
	put next lines in worker.properties file:
	
		worker.list=worker1
		worker.worker1.type=ajp13
		worker.worker1.host=localhost
		worker.worker1.port=8009
		worker.worker1.lbfactor=50
		worker.worker1.cachesize=10
		worker.worker1.cache_timeout=600
		worker.worker1.socket_keepalive=1
		worker.worker1.recycle_timeout=300
		
	
			
run tomcat



