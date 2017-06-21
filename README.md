"# LeanTaasDemo" 

Lean Taas Coding Challenge Code:
A simple REST application using Jersey Framework for a Projector Allocation System


Provides APIs to make booking for projector, delete those bookings and retrieve those bookings
GET,POST,DELETE HTTP methods are supported.

GET - webapi/projectors  -> returns the current bookings of projectors

GET - webapi/projectors/projectorId  -> returns the current bookings of specific projector

POST - webapi/projectors -> makes a booking for the time slot and returns the id of projector

DELETE -  webapi/projectors/projectorId -> deletes the booking for the specific projector


JSON body accepted:-
{
  "end_time": "2017-06-21T08:04:50.564-04:00",
  "start_time": "2017-06-21T07:07:50.564-04:00",
  "team_id": 1
}
start_time and end_time specify the time slot requested
team_id signifies the team requesting the projector

Import the project into Eclipse and run using Tomcat Server.
