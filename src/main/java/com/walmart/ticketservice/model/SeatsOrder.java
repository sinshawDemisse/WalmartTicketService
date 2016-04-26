package com.walmart.ticketservice.model;

/**
 * @author sinshaw.demisse
 *
 */
public class SeatsOrder {
	private int venueLevel;
	private int numberOfseats;

	public int getVenueLevel() {
		return venueLevel;
	}

	public void setVenueLevel(int venueLevel) {
		this.venueLevel = venueLevel;
	}

	public int getNumberOfseats() {
		return numberOfseats;
	}

	public void setNumberOfseats(int numberOfseats) {
		this.numberOfseats = numberOfseats;
	}
}
