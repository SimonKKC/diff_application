# diff_application
1. About the application
 This application is a spring-boot application using an in-memory database. 
 Also, the application uses an embedded application server (Tomcat).


2. How to deploy the application

  a) This application is written by Java8. So in your testing environment, there should be an openjdk-8.
  b) The build tool of this application is maven-based. Therefore, in your testing environment, your network should be okay to go to        maven repository without any firewall blocking.

  c) The application will use port 8080. Therefore, making sure that the port of 8080 is not used by another application.

2.1 Deveployment step (linux-based environment)
 a) Unzip the folder
 b) Run command(replace the <folder path>): cd <folder path>/Diffing_API_Task
 c) Inside the folder called Diffing_API_Task, you can see there is a file called mvnw
 d) Run command: ./mvnw spring-boot:run

2.2 Deveployment step (Window-based environment)
 a) Unzip the folder
 b) Run command(replace the <folder path>): cd <folder path>/Diffing_API_Task
 c) Inside the folder called Diffing_API_Task, you can see there is a file called mvnw.cmd
 d) Run command: mvnw.cmd spring-boot:run

If you see the “Started DiffingApiTaskApplication in …..”, then the system is up.



 3. How to do unit testing.
  a) Run command: cd <go to the location of directory that contains a file called mvnw>
  b) Run command: ./mvnw test (window-based platform: mvnw.cmd test)


