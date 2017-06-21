package com.leantaas.demo.resource_allocation.util;

/**
 * @author Nitin
 * Specifies a TimeSlot Class which can store start 
 * and end time of the booking
 */
public class TimeSlot implements Comparable<TimeSlot>{
	
	private long start;
	private long end;
	private int team_id;
	
	public TimeSlot(long start, long end, int team_id) {
		super();
		this.start = start;
		this.end = end;
		this.team_id = team_id;
	}
	public long getStart() {
		return start;
	}
	public long getEnd() {
		return end;
	}
	public int getTeam_id() {
		return team_id;
	}
	
	@Override
	public int compareTo(TimeSlot compareSlot) {
		long compareEnd = ((TimeSlot) compareSlot).getEnd();
		//ascending order
		return (int) (this.end - compareEnd);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
            return true;
        }
 
        if (!(obj instanceof TimeSlot)) {
            return false;
        }
        
		TimeSlot ts = (TimeSlot)obj;
		if(this.start == ts.start && this.end == ts.end){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	@Override
	public String toString() {
		return "TimeSlot [start=" + start + ", end=" + end + ", team_id=" + team_id + "]";
	}
	@Override
	public int hashCode() {
		return super.hashCode();
	}
		
}
