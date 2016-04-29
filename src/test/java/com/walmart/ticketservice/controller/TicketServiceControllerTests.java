package com.walmart.ticketservice.controller;

import javax.ws.rs.core.MediaType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.walmart.ticketservice.WalmartTicketServiceApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { MockServletContext.class, WalmartTicketServiceApplication.class })
@WebAppConfiguration
public class TicketServiceControllerTests {

	@Autowired
	private TicketServiceController ticketServiceController;

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		// TicketServiceController ticketServiceController =
		// mock(TicketServiceController.class);
		mvc = MockMvcBuilders.standaloneSetup(ticketServiceController).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAvailableSeats() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/rest/seats/available?level=2").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8));

		mvc.perform(MockMvcRequestBuilders.get("/rest/seats/available").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8)).andReturn();
	}

	@Test
	public void testFindAndHoldSeats() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/rest/findandholdseat/5000/sinshaw@gmail.com?minLevel=2&maxLevel=4")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8)).andReturn();
	}

	@Test
	public void testReserveSeats() throws Exception{
		mvc.perform(MockMvcRequestBuilders.get("/rest/reserveSeats/5/sinshaw@gmail.com")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8)).andReturn();
	}
}
