Linux Summer Camp Application Automation
========================================

...

Features
--------

...


Common problems & solutions
---------------------------

	org.springframework.beans.factory.BeanDefinitionStoreException: Failed to parse configuration class [tr.org.lkd.lyk2015.camp.config.RootConfig]; nested exception is java.io.FileNotFoundException: class path resource [application-dev.properties] cannot be opened because it does not exist

You need to rename `application-sample.properties` to `application-dev.properties` and edit its content. (src/resources/application-sample.properties)

**Creating database for the application**

	~/<current_directory>$  sudo -u postgres createuser -D -A -P campuser
	could not change directory to "/home/<username>/<current_directory>": Permission denied
	Enter password for new role: 
	Enter it again: 
	~/<current_directory>$ sudo -u postgres createdb -O campuser campdb
	could not change directory to "/home/<username>/<current_directory>": Permission denied
	~/<current_directory>$ 


License
-------

AGPL v3 <http://www.gnu.org/licenses/agpl-3.0.en.html>

Copyright (C) 2015 Kod Gemisi Ltd.