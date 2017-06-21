package com.leantaas.demo.resource_allocation.model;

/**
 * @author Nitin
 * Team class : Currently provides the id of team making the 
 * booking, but can be modified to store other data about team
 * Can be stored as Team Table in Database which is referenced by 
 * a booking table.
 */
public class Team {
	private int id;

	public Team(int id) {
		super();
		this.id = id;
	}

}
