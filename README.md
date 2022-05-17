# diff_application
1. About the application
This application is a spring-boot application using an in-memory database. 
Also, the application uses an embedded application server (Tomcat).


2. How to deploy the application

This application is written by Java8. So in your testing environment, there should be an openjdk-8.

The build tool of this application is maven-based. Therefore, in your testing environment, your network should be okay to go to maven repository without any firewall blocking.

The application will use port 8080. Therefore, making sure that the port of 8080 is not used by another application.

Deveployment step (linux-based environment)
Unzip the folder
Run command(replace the <folder path>): cd <folder path>/Diffing_API_Task
Inside the folder called Diffing_API_Task, you can see there is a file called mvnw
Run command: ./mvnw spring-boot:run

Deveployment step (Window-based environment)
Unzip the folder
Run command(replace the <folder path>): cd <folder path>/Diffing_API_Task
Inside the folder called Diffing_API_Task, you can see there is a file called mvnw.cmd
Run command: mvnw.cmd spring-boot:run

If you see the “Started DiffingApiTaskApplication in …..”, then the system is up.



 3. How to do unit testing.
Run command: cd <go to the location of directory that contains a file called mvnw>
Run command: ./mvnw test (window-based platform: mvnw.cmd test)


