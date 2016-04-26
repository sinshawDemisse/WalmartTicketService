package com.walmart.ticketservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.ticketservice.model.SeatHold;
import com.walmart.ticketservice.service.TicketServiceImpl;

@RestController
@RequestMapping("rest")
public class TicketServiceController {

	@Autowired
	TicketServiceImpl ticketServiceImpl;

	@RequestMapping("test")
	@ResponseBody
	public String testService() {
		return "Welcome ";
	}

	@RequestMapping("seats/available")
	public int numSeatsAvailable(@RequestParam(value = "level", required = false) Integer level) {
		if (level == null)
			return ticketServiceImpl.numSeatsAvailable(0);
		else
			return ticketServiceImpl.numSeatsAvailable(level);
	}

	@RequestMapping(value = "findandholdseat/{numSeats}/{email}")
	public SeatHold findAndHoldSeats(@PathVariable Integer numSeats, @PathVariable String email,
			@RequestParam(value = "minLevel", required = false) Integer min,
			@RequestParam(value = "maxLevel", required = false) Integer max) {
		if (min == null)
			min = 1;
		if (max == null)
			max = 4;
		return ticketServiceImpl.findAndHoldSeats(numSeats, min, max, email);

	}
	
	@RequestMapping(value = "reserveSeats/{seatHoldId}/{customerEmail}")
	public String reserveSeats(@PathVariable Integer seatHoldId, @PathVariable String customerEmail){
		return ticketServiceImpl.reserveSeats(seatHoldId, customerEmail);
	}

}
