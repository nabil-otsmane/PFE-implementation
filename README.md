# Deployment process


In order to run both apps together, we can use one of the two approachs:
* Using directly the prebuilt generated files
* or Using docker containers and docker compose


## First way (running directly):

First, make sure to install [**java**](https://www.java.com/download/ie_manual.jsp) and [**nodejs**](https://nodejs.org/en/download/) and make sure to install **npm** along with nodejs.

then, after everything is installed successfully, you can download the zip from the github repo.

then, enter the folder you just extracted from the zip and run the following commands : 

``` java -jar demo/deploy/demo-0.0.1-SNAPSHOT.jar ```

also run in another cmd : 

``` npx serve frontend/deploy ```

Then, you can access the website from http://localhost:3000/ and it should work correctly.

## Second way (running with docker):

First, download and install [docker desktop](https://docs.docker.com/desktop/install/windows-install/) and follow the steps of installation.

then download the zip and enter the folder you just extracted and type in a cmd :

``` docker compose up --build ```

and wait for the deployment process to finish until you have success messages. then enter the website http://localhost:1337/ and there you find the website up and running.
