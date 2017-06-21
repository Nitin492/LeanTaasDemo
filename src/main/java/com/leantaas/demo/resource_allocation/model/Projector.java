package com.leantaas.demo.resource_allocation.model;

import java.util.LinkedList;
import java.util.PriorityQueue;

import com.leantaas.demo.resource_allocation.util.TimeSlot;

/**
 * @author Nitin
 * Projector class : representation of a Projector
 * and its bookings Provide mechanism to store bookings,
 * check for availability for a particular time slot and 
 * delete the time slot. Can be used as a model in ORM
 * or as a Table. This class can be divided and stored in
 * two separate tables Projector (To Store the Projectors)
 * and Bookings (To store the booking associated with a projector)
 */
public class Projector {
	private int id;
	private LinkedList<TimeSlot> queue=new LinkedList<TimeSlot>();
	
	public Projector(int id) {
		super();
		this.id = id;
	}
	
	
	public LinkedList<TimeSlot> getQueue() {
		return queue;
	}

	//adds the time slot to queue 
	public void addTimeSlot(TimeSlot timeslot){
		queue.add(timeslot);
		System.out.println("Allocated Time Slots");
		for(TimeSlot ts: queue){
			System.out.println(ts);
		}
	}
	
	//removes the time slot from queue 
	public boolean removeTimeSlot(TimeSlot timeslot){
		if(queue.contains(timeslot)){
			queue.remove(timeslot);
			return true;
		}
		else{
			return false;
		}
	}
	
	//checks the availability of a projector for a particular Time Slot 
	//Does not considers the issue whether a team can be assigned 
	//multiple projectors or not for same time slot
	public boolean isAvailable(TimeSlot timeslot){
		
		if (queue.isEmpty()){
			return true;
		}
		else if(queue.contains(timeslot)){
			System.out.println("Contains");
			return false;
		}
		else{
			for(TimeSlot ts:queue){
				if(timeslot.getStart() <= ts.getStart() && timeslot.getEnd() <= ts.getEnd() &&  timeslot.getEnd() >= ts.getStart()){
					System.out.println("hello1");
					return false;
				}
				else if(timeslot.getStart() >= ts.getStart() && timeslot.getEnd() >= ts.getEnd() && timeslot.getStart() <= ts.getEnd()){
					System.out.println("hello2");
					return false;
				}
				else if(timeslot.getStart() >= ts.getStart() && timeslot.getEnd() <= ts.getEnd()){
					System.out.println("hello3");
					return false;
				}
				else if(timeslot.getStart() <= ts.getStart() && timeslot.getEnd() >= ts.getEnd()){
					System.out.println("hello4");
					return false;
				}
			}
			return true;
		}
		
	}
	
}
