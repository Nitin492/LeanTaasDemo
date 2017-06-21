package com.leantaas.demo.resource_allocation.resources;

import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.leantaas.demo.resource_allocation.service.ProjectorAllocationService;
import com.leantaas.demo.resource_allocation.util.CustomJson;
import com.leantaas.demo.resource_allocation.util.TimeSlot;

/**
 * @author Nitin
 * ProjectResource class handles the incoming HTTP requests
 * GET,POST,DELETE HTTP methods are supported.
 * GET - webapi/projectors  -> returns the current bookings of projectors
 * GET - webapi/projectors/projectorId  -> returns the current bookings of specific projector
 * POST - webapi/projectors -> makes a booking for the time slot and returns the id of projector
 * DELETE -  webapi/projectors/projectorId -> deletes the booking for the specific projector
 * 
 * JSON body accepted:-
 * {
 *   "end_time": "2017-06-21T08:04:50.564-04:00",
 *   "start_time": "2017-06-21T07:07:50.564-04:00",
 *   "team_id": 1
 * }
 * start_time and end_time specify the time slot requested
 * team_id signifies the team requesting the projector
 * 
 * Assumption : projector can not be booked for more than 3 hours
 */
@Path("/projectors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProjectorResource {
	
	ProjectorAllocationService projectorservice = new ProjectorAllocationService();
	
	//Gets all the bookings
	@GET
	public List<CustomJson> getAllBookings(){
		return projectorservice.getAllBookings();
	}
	
	//Gets booking for a specific projector
	@GET
	@Path("/{projectorId}")
	public List<CustomJson> getProjectorBookings(@PathParam("projectorId") long id){
		return projectorservice.getProjectorBookings(id);
	}
	
	//Makes booking for a specific time slot or return error
	@POST
	public Response bookProjector(CustomJson slot){
		long start = slot.getStart_time().getTime();
		long end = slot.getEnd_time().getTime();
		if(start>end){
			String json = "{\"Error\": \"Improper Request! Start time can not be after end time\"}";
			return Response.ok(json).status(500).build();
		}
		if((end-start)>10800000){
			String json = "{\"Error\": \"Improper Request! Projector can not be booked for more than 3 hours\"}";
			return Response.ok(json).status(500).build();
		}
		int projector_id = projectorservice.bookProjector(slot);
		if(projector_id == -1){
			Date next_availability = projectorservice.getNextAvailability();
			String json = "{\"Msg\": \"No Projector Available\", \"Next Availbility\": \" "+ next_availability.toString() +"\"}";
			return Response.ok(json).build();
		}
		else{
			String json = "{\"Msg\": \"Projector Assigned\",\"projector_id\":"+ projector_id+"}";
			return Response.ok(json).build();
			
		}
		
	}
	
	//Delets the booking done for a specific projector
	@DELETE
	@Path("/{projectorId}")
	public Response cancelBooking(@PathParam("projectorId") long id,CustomJson slot){
		boolean isCanceled = projectorservice.cancelBooking(id,slot);
		if(isCanceled){
			return Response.ok().build();
		}
		else{
			String json = "{\"Msg\": \"No Booking exists\"}";
			return Response.ok(json).status(404).build();
		}
	}
}
