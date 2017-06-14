
public class Airplane {
	
	private String type;
    private Flight journey;
    private Seat[][] seats;
	private boolean used;
	





	public Airplane(String airplane_type)
	{
		type = airplane_type;	
		journey = null;
		used = false;
		
		if(airplane_type.equals("AirBus"))
		{
			seats = new Seat[4][2];
			
			seats[0][0] = new Seat("Bussiness");
			seats[0][1] = new Seat("Bussiness");
			seats[1][0] = new Seat("Economy");
			seats[1][1] = new Seat("Economy");
		    seats[2][0] = new Seat("Economy");
		    seats[2][1] = new Seat("Economy");
		    seats[3][0] = new Seat("Economy");
			seats[3][1] = new Seat("Economy");

		}
		else if(airplane_type.equals("Boeing"))
		{
			seats = new Seat[3][2];
			
			seats[0][0] = new Seat("Bussiness");
			seats[0][1] = new Seat("Bussiness");
			seats[1][0] = new Seat("Economy");
			seats[1][1] = new Seat("Economy");
		    seats[2][0] = new Seat("Economy");
		    seats[2][1] = new Seat("Economy");
		    
		}
			
			
	
		
	}
	
	
	
	
	public void assign_flight(Flight journey)
	{
		this.journey = journey;
	}
	
	
	
	
	


	public Seat[][] getSeats() {
		return seats;
	}



	public void setSeats(Seat[][] seats) {
		this.seats = seats;
	}

	
	
	
	
	


	public Flight getJourney() {
		return journey;
	}


	public void setJourney(Flight journey) {
		this.journey = journey;
	}


	
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

	
	
	public boolean isUsed() {
		return used;
	}



	public void setUsed(boolean used) {
		this.used = used;
	}

	
}
