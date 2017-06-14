
public class Seat {

	private String type;
	private String seat_no;
	private Passenger person;
	private int ticket_price;
	private boolean reserved;
	

	public Seat(String seat_type)
	{
		type = seat_type;
		reserved = false;
		
		if(seat_type.equals("Bussiness"))
		{
			
		}
		else if(seat_type.equals("Economy"))
		{
			
		}
		
		
	}
	
	
	
	
	
	
	public boolean isReserved() {
		return reserved;
	}


	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}


	public void reserve(Passenger person)
	{
		this.person = person;
	}
	
	
	
	
	
	
	
	public String getSeat_no() {
		return seat_no;
	}






	public void setSeat_no(String seat_no) {
		this.seat_no = seat_no;
	}






	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Passenger getPerson() {
		return person;
	}


	public void setPerson(Passenger person) {
		this.person = person;
	}


	public int getTicket_price() {
		return ticket_price;
	}


	public void setTicket_price(int ticket_price) {
		this.ticket_price = ticket_price;
	}

	
}
