package com.leantaas.demo.resource_allocation.util;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author Nitin
 * Utility Custom JSON class to facilitate a proper
 * JSON structure to REST calls 
 */
@XmlRootElement
public class CustomJson {
	private int team_id;
	private Date start_time;
	private Date end_time;
	
	public CustomJson(){
		
	}
	
	public CustomJson(int team_id, Date start_time, Date end_time) {
		super();
		this.team_id = team_id;
		this.start_time = start_time;
		this.end_time = end_time;
	}
	
	public int getTeam_id() {
		return team_id;
	}
	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	@Override
	public String toString() {
		return "CustomJson [team_id=" + team_id + ", start_time=" + start_time + ", end_time=" + end_time + "]";
	}
	
}
