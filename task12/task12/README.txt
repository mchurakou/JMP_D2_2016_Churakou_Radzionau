
Installation:

Soft: Apache Web Server, Apache Tomcat, Maven

1. install maven

2. install tomcat

3. install Apache Web Server

4. Add an user with roles manager-gui and manager-script to tomcat-users:
	4.1 edit %TOMCAT_PATH%/conf/tomcat-users.xml
		add:
		<tomcat-users>
			<role rolename="manager-gui"/>
			<role rolename="manager-script"/>
			<user username="%username%" password="%password%" roles="manager-gui,manager-script" />
		
5. configure maven with tomcat user credentials:
	5.1 edit %MAVEN_PATH%/conf/settings.xml
		add:
		<?xml version="1.0" encoding="UTF-8"?>
		<settings ...>
			<servers>

				<server>
					<id>TomcatServer</id>
					<username>%tomcat_username%</username>
					<password>%tomcat_password%</password>
				</server>
			
			

6. configure Apache Web Server:

	6.1 download apropriate mod_jk extesion from http://tomcat.apache.org/download-connectors.cgi
	6.2 copy mod_jk.so to %APACHE_PATH%/modules
	6.3 create worker.properties file in %APACHE_PATH%/conf directory
	6.4 put next lines in worker.properties file:
	
			worker.list=worker1
			worker.worker1.type=ajp13
			worker.worker1.host=localhost
			worker.worker1.port=8009
			worker.worker1.lbfactor=50
			worker.worker1.socket_keepalive=1
		
	6.5 add config to load mod_jk to Apache Web Server:
			edit %APACHE_PATH%/conf/httpd.conf. add:
			
			LoadModule jk_module modules/mod_jk.so
			JkWorkersFile "conf/worker.properties"
		
	6.6 map your app to mod_jk:
			edit %APACHE_PATH%/conf/httpd.conf. add:
			
			JkMount  /task12/ worker1
			JkMount  /task12/*.jsp worker1
			JkMount  /task12/hello worker1
		
	6.7 configure staticsite mapping in Apache Web Server:
	
			edit %APACHE_PATH%/conf/httpd.conf. add:
			
			Alias /task12 "%PATH_TO_APPLICATION_FOLDER%/staticsite"
			<Directory "%PATH_TO_APPLICATION_FOLDER%/staticsite">
				Options Indexes MultiViews  
				AllowOverride None  
				Require all granted
			</Directory>
			
			for example, if you unpack application to e:/tsk12, then path will be: e:/staticsite
			
7. run tomcat

8. run install.bat from the application root directory

9. go to http://localhost/task12/

10. enjoy



