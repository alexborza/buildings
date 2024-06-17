How to run the application.

1) Import project dependencies and run clean install

2) Build the image running the following command in the terminal of the root project
docker build . -t buildings:v1

3) Create the containers running:
docker compose up -d

4) Access in the browser (the pgAdmin container may not start immediately, but it will start): http://localhost:5050/ -> password: postgres

5) Click on Register server and create new server using the following credentials:
server_name: alexborza
connection_name: postgres
username: alexborza
password: password

6) Click on the new server created then right click on the databases: create new database with name: buildings

7) Download this file from google drive and Import the postman collection to postman for testing the application:
https://drive.google.com/file/d/1O19KIdpANjo3_OUBc_yBbXXPP0Chs3Gh/view?usp=sharing
