
public class Passenger {
	
	private String name;
	private int luggage;
	private int ticket_price;
	private String class_type;
	private String from;
	private String to;
	private String company;
	
	public Passenger(String name,int luggage,int ticket_price,String class_type,String from,String to,String company)
	{
		this.setName(name);
		this.luggage = luggage;
		this.ticket_price = ticket_price;
		this.class_type = class_type;
		this.from = from;
		this.to = to;
		this.company = company;
	}

	public int getLuggage() {
		return luggage;
	}

	public void setLuggage(int luggage) {
		this.luggage = luggage;
	}

	public int getTicket_price() {
		return ticket_price;
	}

	public void setTicket_price(int ticket_price) {
		this.ticket_price = ticket_price;
	}

	public String getClass_type() {
		return class_type;
	}

	public void setClass_type(String class_type) {
		this.class_type = class_type;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
