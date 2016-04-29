# WalmartTicketService

- This service allow user to hold, reserve and know the available seat in a venue.
- The hold seat will be expired if it pass 30 second without making reservation 
- I implemented using maven, spring boot and java 8.
- The user can send a request by http URL to interact with the system. The system return JSON formatted response.  
- You need JDK 8 and Maven 3.3.7 instlled and configured to your system.

===================================
1. Build Project By Maven
===================================
1. Download the project
2. Run the following command in the project folder 
      - mvn clean install
	  
===================================
2. Run As Server Mode
===================================
 for running the this service go to the ..\target folder and run the following command

 java -cp ticketservice-0.0.1-SNAPSHOT.jar org.springframework.boot.loader.JarLauncher 
 
 After this command the application will start as server mode by embading Tomcat inside jar
  
  ** Tomcat started on port 8080 
  
 ===================================
3. Test the service by sending request
===================================

   ** you can use any web browser or Rest client plugin  like Postman or RESTClient to test the service
   **All the HTTP methods used in this project are GET
   
      1. Get available seats in all level
	       End point  ../rest/seats/available?level={level}
	  
	         ** Get available seat in all level
			 
         ---> http://localhost:8080/rest/seats/available
		      it will return 6250
			  
	         ** Get available seat in level 1, 2 ,3 or 4 
			 
	  ---> http://localhost:8080/rest/seats/available?level=1
		      it will return 1250
         ---> http://localhost:8080/rest/seats/available?level=2
		     it will return  2000
	  ---> http://localhost:8080/rest/seats/available?level=3
		      it will return 1500
	 ---> http://localhost:8080/rest/seats/available?level=4
		      it will return 1500
			  
			  if you enter other than level 1,2,3 or 4 it simply return 0
			  
	  2. Request to find and hold seats
	  
	         End point  ../rest/findandholdseat/{numSeats}/{email}?minLevel={minLevel}&maxLevel={maxLevel}
	       
		   ** To hold available seats in any level.
		        
			---> http://localhost:8080/rest/findandholdseat/1000/sinshaw@gmail.com
			
			it will return
			     {"holdId":2,"customerEmail":"sinshaw@gmail","timeStamp":1461901808382,"seatsOrders":[{"venueLevel":1,"numberOfseats":1000}],"status":"HOLD"}
		  
		  ** To find and hold available seats  with a priorty of minLevel.
				 
			---> http://localhost:8080/rest/findandholdseat/6000/sinshaw@gmail.com?minLevel=1

            it will return
					{"holdId":1,"customerEmail":"sinshaw@gmail","timeStamp":1461902505739,
					"seatsOrders":[{"venueLevel":1,"numberOfseats":1250},
									{"venueLevel":2,"numberOfseats":2000},
									{"venueLevel":3,"numberOfseats":1500},
									{"venueLevel":4,"numberOfseats":1250}],
					"status":"HOLD"}
					
			** To find and hold available seats  with a minLevel and maxLevel range.
			
			---> http://localhost:8080/rest/findandholdseat/60/sinshaw@gmail.com?minLevel=2&maxLevel=4

             it will return
         
                   {"holdId":2,"customerEmail":"sinshaw@gmail","timeStamp":1461902717759,
			         "seatsOrders":[{"venueLevel":2,"numberOfseats":60}],
					 "status":"HOLD"}		 
			** If we try to find and hold many seats more than available
			the return will be
			
			  {"holdId":0,"customerEmail":null,"timeStamp":0,"seatsOrders":[],"status":"Unable to make hold"}
			  
	    3. To make a reservation:
			
			End point: ../rest/reserveSeats/{seatHoldId}/{customerEmail}
			
			--> http://localhost:8080/rest/reserveSeats/4/sinshaw@gmail.com
			
			it will return "Reserved successfully" if it is reserved , "Unknown ID" if we send Invalid hold ID or "unknown email" if the email didn't mach
			or "Hold is expired" or "Hold is reserved befor" if it was expired or reserved befor.
			
	   
		
			
		 
		 
	  
	  
  
 