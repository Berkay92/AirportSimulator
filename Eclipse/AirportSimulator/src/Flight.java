
public class Flight {
	
	private String airplane_type;
	private char flight_type;
	private String company;
	private String from;
	private String to;
	private String flight_number;
	private int arrival_departure_time;
	private int service_time;
	private int starting_time;
	
	
	public Flight(String airplane_type,char flight_type,String company,String from,String to,String flight_number,int arrival_departure_time,int service_time)
	{
		this.airplane_type = airplane_type;
		this.flight_type = flight_type;
		this.company = company;
		this.from = from;
		this.to = to;
		this.flight_number = flight_number;	
		this.arrival_departure_time = arrival_departure_time;
		this.service_time = service_time;	
	}

	public String getAirplane_type() {
		return airplane_type;
	}

	public void setAirplane_type(String airplane_type) {
		this.airplane_type = airplane_type;
	}

	public char getFlight_type() {
		return flight_type;
	}

	public void setFlight_type(char flight_type) {
		this.flight_type = flight_type;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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

	public String getFlight_number() {
		return flight_number;
	}

	public void setFlight_number(String flight_number) {
		this.flight_number = flight_number;
	}

	public int getArrival_departure_time() {
		return arrival_departure_time;
	}

	public void setArrival_departure_time(int arrival_departure_time) {
		this.arrival_departure_time = arrival_departure_time;
	}

	public int getService_time() {
		return service_time;
	}

	public void setService_time(int service_time) {
		this.service_time = service_time;
	}
	
	public int getStarting_time() {
		return starting_time;
	}


	public void setStarting_time(int starting_time) {
		this.starting_time = starting_time;
	}


}
