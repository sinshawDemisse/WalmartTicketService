package com.walmart.ticketservice.model;

/**
 * @author sinshaw.demisse
 *
 */
public class Venue {
	private int levelId;
	private String levelName;
	private double price;
	private int numberOfRow;

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNumberOfRow() {
		return numberOfRow;
	}

	public void setNumberOfRow(int numberOfRow) {
		this.numberOfRow = numberOfRow;
	}

	public int getSeatsInRow() {
		return seatsInRow;
	}

	public void setSeatsInRow(int seatsInRow) {
		this.seatsInRow = seatsInRow;
	}

	public int getHoldSeats() {
		return holdSeats;
	}

	public void setHoldSeats(int holdSeats) {
		this.holdSeats = holdSeats;
	}

	public int getReservedSeats() {
		return reservedSeats;
	}

	public void setReservedSeats(int reservedSeats) {
		this.reservedSeats = reservedSeats;
	}

	private int seatsInRow;
	private int holdSeats;
	private int reservedSeats;
}
