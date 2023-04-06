Software II C195 - Appointment Scheduling Application

Purpose Of The Application:
    The purpose of this application is to allow users,
    schedule appointments. The user will be able to
    create, modify, view and delete customers and
    appointments.
-----------------------------------------------------

Author: Mya Thomas
Email: mth1759@wgu.edu
Application Version: Software II - Advanced Java Concepts QAM2
Date: April 5, 2023

-----------------------------------------------------

IDE: IntelliJ IDEA Community Edition 2021.1.3
JDK: Java SE 11.0.11
JavaFX: javafx-sdk-17.0.1
MySQL Driver Version: mysql-connector-java-8.0.25

------------------------------------------------------

How to use the application:
-------------------------------------------------------
When the application is launched the user will be taken to the login-in screen, where they must enter either "test" in both the username and password field or "admin" in both fields.
Once the user is logged in they will be brought to a main navigation menu where they can either view appointments, customers or reports.
If there are any scheduled appointments coming up in the next 15 minutes the user will receive an alert informing them of this future appointment.

If the appointment navigation button is clicked, the user will be able to create, modify and delete selected appointments. The user can also view appointments by current month or week, by
selecting the corresponding radio button.

If the customer navigation button is clicked, the user can view all, create, modify and delete customers. The user will receive an alert if they try to delete a customer with associated appointments,
If they select "OK" they will be able to delete the customers as well as all the associated appointments.

Report Section:
-------------------------------------------------------
If the report navigation button is clicked, the user will be able to view associated appointment by contact Id. The user can also view the total number of customers per state or province, and view appointment total
by month and type.
For the additional report of my choice, I decided to create an report that displays the total customers per each state/province that are currently register
within the application.