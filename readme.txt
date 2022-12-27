-Setup:
--Modificare il file src/main/resources/application.properties. Valori di default:
---server.port=8080
---spring.jpa.hibernate.ddl-auto=update
---spring.datasource.url=jdbc:mysql://127.0.0.1:3306/SouthAfricaNumbers?createDatabaseIfNotExist=true
---spring.datasource.username=root
---spring.datasource.password=
---spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

-Database:
--Server MySQL (da avviare per utilizzare l'applicazione), creazione DB automatica.

-Endpoint:
--/SouthAfricaNum

-API:

--POST /SouthAfricaNum/checkCSV
---Upload CSV: un file in formato CSV (come in allegato) viene inviato al server, ogni numero viene controllato e salvato sul database. 

--POST /SouthAfricaNum/checkNum
---Viene inviato un singolo numero per essere controllato e salvato sul database.

--GET /SouthAfricaNum/listNum
---Vengono stampati i numeri salvati sul database in formato JSON.

--GET /SouthAfricaNum/downloadCSV
---Viene avviato il download di un file in formato CSV contenente tuttti i numeri salvati sul database.

-GUI:

--/SouthAfricaNum/

