# WalmartTicketService
This ticket service allows user to create multiple holds and reservations for one customer.
Also this service will hold any available seat within levels user provided and return holdId. 
If available seats are not enough in one level, then hole available seat in next level. 
There is no limit on number of holding nor on number of seat to hold. This service will returns
seat holdId when it can hold at least one seat. So if user request 10 seats, but find 2 seat then
it holds the 2 seats and return seat holdId. However seat hold id can be empty if service cannot
find any available seat in the levels user provided.