package com.leantaas.demo.resource_allocation.database;

import java.util.HashMap;
import java.util.Map;

import com.leantaas.demo.resource_allocation.model.Projector;
import com.leantaas.demo.resource_allocation.model.Team;

/**
 * @author Nitin
 * Database Stub: Represents a Database with Projectors
 * and Teams Tables. Real Database will have bookings table
 * as well which references both Projector and Team Table.
 * Currently there are 3 projectors and 5 teams 
 */
public class DatabaseClass {
	private static Map<Long, Projector> projectors = new HashMap<>();
	private static Map<Long, Team> teams = new HashMap<>();
	
	static{
		projectors.put(1L, new Projector(1));
		projectors.put(2L, new Projector(2));
		projectors.put(3L, new Projector(3));
		teams.put(1L, new Team(1));
		teams.put(2L, new Team(2));
		teams.put(3L, new Team(3));
		teams.put(4L, new Team(4));
		teams.put(5L, new Team(5));
	}
	public static Map<Long, Projector> getProjectors() {
		return projectors;
	}
	
	public static Map<Long, Team> getTeams() {
		return teams;
	}
	
}
