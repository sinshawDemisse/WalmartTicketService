package com.walmart.ticketservice.repository;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.walmart.ticketservice.model.SeatHold;
import com.walmart.ticketservice.model.Venue;

@Repository
public class TicketRepository {
	private HashMap<Integer,SeatHold> seatHolds;
	private HashMap<Integer,Venue> venues;
	
	public HashMap<Integer, SeatHold> getSeatHolds() {
		if(this.seatHolds==null)
			seatHolds= new HashMap<Integer,SeatHold>();
		return seatHolds;
	}

	public HashMap<Integer, Venue> getVenues() {
		if(this.venues==null)
			this.venues=new HashMap<Integer, Venue>();
		return venues;
	}


	public TicketRepository()
	{
		Venue orchestra = new Venue();
		orchestra.setLevelId(1);
		orchestra.setLevelName("Orchestra");
		orchestra.setPrice(100.00);
		orchestra.setNumberOfRow(25);
		orchestra.setSeatsInRow(50);
		orchestra.setReservedSeats(0);
		orchestra.setHoldSeats(0);
		getVenues().put(orchestra.getLevelId(), orchestra);
		
		Venue main = new Venue();
		main.setLevelId(2);
		main.setLevelName("Main");
		main.setPrice(75.00);
		main.setNumberOfRow(20);
		main.setSeatsInRow(100);
		main.setReservedSeats(0);
		main.setHoldSeats(0);
		getVenues().put(main.getLevelId(), main);
		
		Venue balcony1 = new Venue();
		balcony1.setLevelId(3);
		balcony1.setLevelName("Balcony 1");
		balcony1.setPrice(50.00);
		balcony1.setNumberOfRow(15);
		balcony1.setSeatsInRow(100);
		balcony1.setReservedSeats(0);
		balcony1.setHoldSeats(0);
		getVenues().put(balcony1.getLevelId(),balcony1);
		
		Venue balcony2 = new Venue();
		balcony2.setLevelId(4);
		balcony2.setLevelName("Balcony 2");
		balcony2.setPrice(40.00);
		balcony2.setNumberOfRow(15);
		balcony2.setSeatsInRow(100);
		balcony2.setReservedSeats(0);
		balcony2.setHoldSeats(0);
		getVenues().put(balcony2.getLevelId(),balcony2);
	}
	

}
