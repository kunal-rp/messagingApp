I'll be trying to create a basic messageing service with grpc and proto buf

The goal is to understand how to setup the project with gradle and setup a simple pipline to run the tests and create the new docker images 

Things to know : 
1) This will be a mono repo ; it would be easier to have seperate repos for each service for the pipeline/testing/constant integration , but I want to try to see if mono is possible , for the sake of code reusibility and consistance
2) Will be using jenkins to handle cicd ; to make things simple, we will push new code ever commit to all services


Here are the services :  

user service:
-handle verifying and login of user through jwt

messages service:
-push/fetch messages 

database: 
-handle fetching data in general 
-for now, will just have json files 