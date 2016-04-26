package com.walmart.ticketservice.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sinshaw.demisse
 *
 */
public class SeatHold {
	private int holdId;
	private String customerEmail;
	private long timeStamp;
	private String Status;
	private List<SeatsOrder> seatsOrders;

	public int getHoldId() {
		return holdId;
	}

	public void setHoldId(int holdId) {
		this.holdId = holdId;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public List<SeatsOrder> getSeatsOrders() {
		if (seatsOrders == null) {
			this.seatsOrders = new ArrayList<SeatsOrder>();
		}
		return this.seatsOrders;

	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

}
