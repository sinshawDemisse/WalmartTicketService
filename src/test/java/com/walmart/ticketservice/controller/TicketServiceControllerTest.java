package com.walmart.ticketservice.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MockServletContext.class, TicketServiceControllerTestConfig.class})
@WebAppConfiguration
public class TicketServiceControllerTest {
	//TicketServiceImpl service = mock(TicketServiceImpl.class);
	
	@Autowired
	private TicketServiceController ticketServiceController;
	
	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
	//	TicketServiceController ticketServiceController = mock(TicketServiceController.class);
		mvc = MockMvcBuilders.standaloneSetup(ticketServiceController).build();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetAvailableSeats() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/rest/seats/available?level=2"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn();
	}


}
