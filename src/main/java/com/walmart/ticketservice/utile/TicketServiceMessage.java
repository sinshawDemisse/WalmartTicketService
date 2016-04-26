package com.walmart.ticketservice.utile;

import org.springframework.stereotype.Component;

@Component
public class TicketServiceMessage {
	public static final String TICKET_EXPIRED="Seat hold is expired";
	public static final String UNKNOWN_EMAIL="Unknown email";
	public static final String RESERVED="Reserved successfully"; 
	public static final String UNKNOWN_ID="Unkown ID";
	public static final String RESERVE_ERROR="Unable to reserve";
	public static final String HOLD_ERROR="Unable to make hold";
}
