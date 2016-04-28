package com.walmart.ticketservice.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.walmart.ticketservice.model.SeatHold;
import com.walmart.ticketservice.model.SeatsOrder;
import com.walmart.ticketservice.service.TicketServiceImpl;
import com.walmart.ticketservice.utile.TicketServiceMessage;

@Configuration
public class TicketServiceControllerTestConfig {

	@Bean
	TicketServiceImpl ticketService()
	{
		TicketServiceImpl service = mock(TicketServiceImpl.class);
		
		SeatHold seatHold = new SeatHold();
		seatHold.setHoldId(1);
		seatHold.setCustomerEmail("sinshaw@gmail");
		seatHold.setTimeStamp(System.currentTimeMillis());
		SeatsOrder seatsOrder = new SeatsOrder();
		seatsOrder.setVenueLevel(2);
		seatsOrder.setNumberOfseats(1000);
		seatHold.getSeatsOrders().add(seatsOrder);
		when(service.findAndHoldSeats(any(Integer.class), any(Integer.class), any(Integer.class), any(String.class))).thenReturn(seatHold); // Simulating find and hold seat
		when(service.reserveSeats(1, "sinshaw@gmail")).thenReturn(TicketServiceMessage.RESERVED); // Simulating successful reservation 
		when(service.reserveSeats(2, "zola@gmail")).thenReturn(TicketServiceMessage.UNKNOWN_ID);// Simulating unknown hold ID 
		when(service.reserveSeats(2, "zola@gmail")).thenReturn(TicketServiceMessage.TICKET_EXPIRED);// Simulating expired hold
		return service;
	}
	

	@Bean
	public TicketServiceController serviceController() {
		return new TicketServiceController();
	}
}
