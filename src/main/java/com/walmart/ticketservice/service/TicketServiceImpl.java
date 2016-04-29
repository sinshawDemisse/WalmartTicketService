package com.walmart.ticketservice.service;

import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.ticketservice.model.SeatHold;
import com.walmart.ticketservice.model.SeatsOrder;
import com.walmart.ticketservice.model.Venue;
import com.walmart.ticketservice.repository.TicketRepository;
import com.walmart.ticketservice.utile.TicketServiceConstants;
import com.walmart.ticketservice.utile.TicketServiceMessage;

/**
 * @author sinshaw.demisse
 *
 */
@Service("TicketService")
public class TicketServiceImpl implements TicketService {

	private static Logger logger = Logger.getLogger(TicketServiceImpl.class);

	@Autowired
	TicketRepository ticketRepository;

	@Override
	public int numSeatsAvailable(int venueLevel) {
		try {
			removeExpiredSeatHold();
			int avalableSeats = 0;
			if (venueLevel == 0) {
				for (Entry<Integer, Venue> entry : ticketRepository.getVenues().entrySet()) {
					Venue venue = entry.getValue();
					avalableSeats += (venue.getNumberOfRow() * venue.getSeatsInRow())
							- (venue.getReservedSeats() + venue.getHoldSeats());
				}
				return avalableSeats;
			}
			if (venueLevel > 0 && venueLevel < 5) {
				Venue venue = ticketRepository.getVenues().get(venueLevel);
				avalableSeats = (venue.getNumberOfRow() * venue.getSeatsInRow())
						- (venue.getReservedSeats() + venue.getHoldSeats());
				return avalableSeats;
			} else
				return 0;
		} catch (Exception e) {
			logger.info("unable to count the available seats");
			return 0;
		}

	}

	@Override
	public SeatHold findAndHoldSeats(int numSeats, int minLevel, int maxLevel, String customerEmail) {
		SeatHold seatHold = new SeatHold();
		int availableSeats=0;
		try {
			for(int i=minLevel;i<=maxLevel;i++)
			{
				availableSeats+=numSeatsAvailable(i);
			}
			if (numSeats > availableSeats) {
				seatHold.setStatus(TicketServiceMessage.HOLD_ERROR);
				return seatHold;
			} 
			
				else {

				int numSeatsToHold = numSeats;
				int min = minLevel;
				int max = maxLevel;
				while (min <= max && numSeatsToHold > 0) {
					if (numSeatsAvailable(min) > numSeatsToHold) {
						ticketRepository.getVenues().get(min)
								.setHoldSeats(ticketRepository.getVenues().get(min).getHoldSeats() + numSeatsToHold);

						SeatsOrder seatsOrder = new SeatsOrder();
						seatsOrder.setNumberOfseats(numSeatsToHold);
						seatsOrder.setVenueLevel(min);
						seatHold.getSeatsOrders().add(seatsOrder);
						numSeatsToHold = 0;
						break;
					} else if (numSeatsAvailable(min) == 0)
						min++;
					else {
						int available = numSeatsAvailable(min);
						ticketRepository.getVenues().get(min)
								.setHoldSeats(ticketRepository.getVenues().get(min).getHoldSeats() + available);
						SeatsOrder seatsOrder = new SeatsOrder();
						seatsOrder.setNumberOfseats(available);
						seatsOrder.setVenueLevel(min);
						seatHold.getSeatsOrders().add(seatsOrder);
						numSeatsToHold -= available;
						min++;
					}

				}
				if (numSeatsToHold != 0) {
					seatHold.setStatus(TicketServiceMessage.HOLD_ERROR);
					return seatHold;
				}
				seatHold.setCustomerEmail(customerEmail);
				seatHold.setTimeStamp(System.currentTimeMillis());
				seatHold.setStatus(TicketServiceConstants.HOLD);
				seatHold.setHoldId(ticketRepository.getSeatHolds().size() + 1);
				ticketRepository.getSeatHolds().put(seatHold.getHoldId(), seatHold);
				return seatHold;
			}
		} catch (Exception e) {
			logger.info("unable to make hold");
			seatHold.setStatus(TicketServiceMessage.HOLD_ERROR);
			return seatHold;
		}
	}

	@Override
	public String reserveSeats(int seatHoldId, String customerEmail) {

		try {
			SeatHold seatHold = ticketRepository.getSeatHolds().get(seatHoldId);
			if (seatHold == null)
				return TicketServiceMessage.UNKNOWN_ID;
			else if (!seatHold.getCustomerEmail().equals(customerEmail))
				return TicketServiceMessage.UNKNOWN_EMAIL;
			else if (seatHold.getStatus() != null && seatHold.getStatus().equals(TicketServiceConstants.EXPIRED_STATUS))
				return TicketServiceMessage.TICKET_EXPIRED;
			else if(seatHold.getStatus() != null && seatHold.getStatus().equals(TicketServiceConstants.RESERVED))
				return TicketServiceMessage.RESERVED_BEFOR;
			else {
				ticketRepository.getSeatHolds().get(seatHoldId).setStatus(TicketServiceConstants.RESERVED);
			//	ticketRepository.getSeatHolds().
				if (seatHold.getSeatsOrders() != null)
					for (SeatsOrder o : seatHold.getSeatsOrders()) {
						ticketRepository.getVenues().get(o.getVenueLevel())
								.setHoldSeats(ticketRepository.getVenues().get(o.getVenueLevel()).getHoldSeats()
										- o.getNumberOfseats());
						ticketRepository.getVenues().get(o.getVenueLevel())
								.setReservedSeats(ticketRepository.getVenues().get(o.getVenueLevel()).getReservedSeats()
										+ o.getNumberOfseats());
					}
				return TicketServiceMessage.RESERVED;
			}
		} catch (Exception e) {
			logger.info("unable to reserve a seat");
			e.printStackTrace();
			return TicketServiceMessage.RESERVE_ERROR;
		}
	}

	private void removeExpiredSeatHold() {

		for (Entry<Integer, SeatHold> entry : ticketRepository.getSeatHolds().entrySet()) {

			if (entry.getValue().getStatus().equals(TicketServiceConstants.HOLD) && entry.getValue().getTimeStamp() + 30000 < System.currentTimeMillis()) {
				entry.getValue().setStatus(TicketServiceConstants.EXPIRED_STATUS);
				for (SeatsOrder o : entry.getValue().getSeatsOrders()) {
					ticketRepository.getVenues().get(o.getVenueLevel()).setHoldSeats(
							ticketRepository.getVenues().get(o.getVenueLevel()).getHoldSeats() - o.getNumberOfseats());
				}
			}
		}
	}

}
