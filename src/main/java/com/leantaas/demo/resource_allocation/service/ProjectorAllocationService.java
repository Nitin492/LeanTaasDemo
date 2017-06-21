package com.leantaas.demo.resource_allocation.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import com.leantaas.demo.resource_allocation.database.DatabaseClass;
import com.leantaas.demo.resource_allocation.model.Projector;
import com.leantaas.demo.resource_allocation.util.CustomJson;
import com.leantaas.demo.resource_allocation.util.TimeSlot;

/**
 * @author Nitin
 * Service or Controller to interact with model or Database.
 * Facilitates making a booking, deleting a booking and 
 * retrieving the booking(s).
 */
public class ProjectorAllocationService {
	
	private Map<Long, Projector> projectors = DatabaseClass.getProjectors();
	
	//Checks all the projectors in database for conflicts
	//and makes a booking for the projector for which there
	//is no conflict. If all the projectors are booked returns -1
	//otherwise returns id of the booked projector 
	public int bookProjector(CustomJson slot) {
		System.out.println("Slot=" + slot);
		long start = slot.getStart_time().getTime();
		long end = slot.getEnd_time().getTime();
		int team_id = slot.getTeam_id();
		TimeSlot timeslot = new TimeSlot(start, end, team_id);
		for(long key: projectors.keySet()){
			boolean isAvailable = projectors.get(key).isAvailable(timeslot);
			System.out.println("availablilty="+isAvailable);
			if(isAvailable == true){
				projectors.get(key).addTimeSlot(timeslot);
				return (int) key;
			}
		}
		return -1;
		
	}

	//cancels the booking for a specific time slot
	public boolean cancelBooking(long id,CustomJson slot) {
		long start = slot.getStart_time().getTime();
		long end = slot.getEnd_time().getTime();
		int team_id = slot.getTeam_id();
		TimeSlot timeslot = new TimeSlot(start, end, team_id);
		return projectors.get(id).removeTimeSlot(timeslot);
		
	}

	//retrieves all the bookings for all the projectors
	public List<CustomJson> getAllBookings() {
		List<CustomJson>  list = new ArrayList<CustomJson>();
		for(long key: projectors.keySet()){
			LinkedList<TimeSlot> queue = projectors.get(key).getQueue();
			for(TimeSlot ts : queue){
				Date start_time = new Date(ts.getStart());
				Date end_time = new Date(ts.getEnd());
				int team_id = ts.getTeam_id();
				CustomJson slot = new CustomJson(team_id, start_time, end_time);
				list.add(slot);
			}
		}
		return list;
	}

	// retrieves all the bookings for a specific projector
	public List<CustomJson> getProjectorBookings(long id) {
		List<CustomJson>  list = new ArrayList<CustomJson>();
		LinkedList<TimeSlot> queue = projectors.get(id).getQueue();
		for(TimeSlot ts : queue){
			Date start_time = new Date(ts.getStart());
			Date end_time = new Date(ts.getEnd());
			int team_id = ts.getTeam_id();
			CustomJson slot = new CustomJson(team_id, start_time, end_time);
			list.add(slot);
		}
		return list;
	}

	//Gets the next possible time when any of the projectors are available
	public Date getNextAvailability() {
		long min_end = 0L;
		for(long key: projectors.keySet()){
			LinkedList<TimeSlot> list = projectors.get(key).getQueue();
			Collections.sort(list);
			if(min_end < list.getLast().getEnd()){
				min_end = list.getLast().getEnd();
			}	
		}
		return new Date(min_end+60000L);
	}

}
