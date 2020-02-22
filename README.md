# Intuit payment system POC

A simple demonstration how analyse payment risk and store payment to DB 



### Project modules overview
- payment-system-api : Contains the API definitions
- payment-system-core : the core code of the send payment service: spring configuration, Rest controllers, managers, handlers.
- payment-system-risk-engine : the core code of the risk engine service: spring configuration, Rest controllers, managers, handlers.


### How to run and check:
In order to run the application need to have ActiveMQ and MySql service to be installed on the local computer.
In the file DBScript.sql can be found script to create DB tables. 
Run in IntellyJ class PaymentSystemApplication to start rest API to send payment and run class RiskManagementApplication to start Risk Engine.
In the project included postman collection file (PaymentPOC.postman_collection.json) to work with the service.

