# Tacx Cycling Activity - Frontend
A cycling Activity entity along with a basic web page to view, update, and delete the data. You can find attached an activity file in .csv format.

## Input
The file contains 2 types of data and their definitions: activity (general info on the activity) and record
(recordings taken at a specific time – each minute in this case). 

## Expected output
* Create an activity based on the upload of a csv file containing the Activity details.
Process the data and store all the data needed in a database of your choice
* Get Activity – summary data (includes total distance, total duration, average power,
average cadence)
* Get Activity List – a list of all summary data in the same format as item b.
* Delete Activity - summary data


**NOTE:**
As frontend is already hosted in Firebase so there is no need to clone & run the frontend.But if you would like to run in local the below steps

**Set up Frontend in local using the following below steps**

1)Install latest **Node**

2)Install angular cli **npm install -g @angular/cli**

3)Clone project & go to the travel-client-ui

4)Run **npm install**

5)Run **ng serve**

**NOTE:**
You can also run the application using Docker.

### Run Docker file 
* docker -v
* docker build -f Dockerfile -t activity-assignment-client-ui-0.0.1 .
* docker images
* docker run -p 8081:8081 sactivity-assignment-client-ui-0.0.1
* docker ps -a
* docker stop <container-id>

6)click **http://localhost:4200/**


**The Application has 5 different Page**

**1) Dashboard:**

Gives the traffic details such
Total number of requests processed
Total number of requests resulted in an OK response
Total number of requests resulted in a 4xx response
Total number of requests resulted in a 5xx response
Average response time of all requests
Min response time of all requests
Max response time of all requests

**2)Login Page**

To login and access Activity & Profile Details

`Username: username`

`Password: Password@123`

**3)Activity Page**

Once you are logged in Activity  -> Upload Activity  -> Upload 
This will gives list of invalid Records

**5)Profile Page:**

Once you are logged in Profile : Gives the profile info

NOTE: Kindly run the backend before using the frontend. 

