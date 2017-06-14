
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import enigma.core.Enigma;

public class AirportSimTest {
	
	public static enigma.console.Console cn = Enigma.getConsole("Simulator",180,40,false);
	private static int i=7,j=7,k=23,m=23;
	static Queue temp_arrival_planes = new Queue(10);
	static Queue temp_departure_planes = new Queue(10); 
	
	 public AirportSimTest() throws Exception {
		   
		 // ------- Statistical inputs -------- //
		 
		 float DEHY_average_waiting_airbus = 0;
		 float DEHY_average_waiting_boeing = 0;
		 int DEHY_max_waiting_airbus = 0;
		 int DEHY_min_waiting_airbus = Integer.MAX_VALUE;
		 int DEHY_max_waiting_boeing = 0;
		 int DEHY_min_waiting_boeing = Integer.MAX_VALUE;		 
		 float DJET_average_waiting_airbus = 0;
		 float DJET_average_waiting_boeing = 0;		 
		 int DJET_max_waiting_airbus = 0;
		 int DJET_min_waiting_airbus = Integer.MAX_VALUE;
		 int DJET_max_waiting_boeing = 0;
		 int DJET_min_waiting_boeing = Integer.MAX_VALUE;
		 
		 int waiting_count = 0;
		 	 
		 float average_service_time_arrival_airbus = 0;
		 float average_service_time_arrival_boeing = 0;
		 float average_service_time_departure_airbus = 0;
		 float average_service_time_departure_boeing = 0;
		 
		 int service_time_count = 0;
		 
		 int DEHY_sum_ticket_price = 0;	 
		 int DJET_sum_ticket_price = 0;

		 int ticket_price_counter = 0;
		 
		 float average_ticket_price_business = 0;
		 float average_ticket_price_economy = 0;
		 
		 // ---------------------------------------- //
		 
		 AirportSim MouseKey = new AirportSim();
	
		 boolean endGame = false;
		 String line; 
		 String[] dizi;
		 
		 Queue all_passengers = new Queue(100);
		 Stack all_flights = new Stack(100);
		 Stack temp_flight = new Stack(100);
		 
		 Airport airport = new Airport();
		 
		// ------------------------ Reading and Sorting Flight List -----------------------------//

		 InputStream in = this.getClass().getClassLoader().getResourceAsStream("flightlist.txt");
		 
		 InputStreamReader flightfilereader = new InputStreamReader(in);
		 BufferedReader flightbufferreader = new BufferedReader(flightfilereader);
		 
		 while((line=flightbufferreader.readLine())!=null)
		 {
			 dizi = line.split(",");  
			 
			 Flight journey = new Flight(dizi[0],dizi[1].charAt(0),dizi[2],dizi[3],dizi[4],dizi[5],Integer.parseInt(dizi[6]),Integer.parseInt(dizi[7]));
			 
			 if(all_flights.isEmpty())
			 {
				 all_flights.push(journey);
			 }
			 else if(journey.getArrival_departure_time() > ((Flight) all_flights.peek()).getArrival_departure_time())
			 {
				 while (journey.getArrival_departure_time() > ((Flight) all_flights.peek()).getArrival_departure_time())
				 {
					 temp_flight.push(all_flights.pop());
				 }
				 all_flights.push(journey);
				 while (!temp_flight.isEmpty())
				 {
					 all_flights.push(temp_flight.pop());
				 }
			 }
			 else
			 {
				 all_flights.push(journey); 
			 }
		 }
 
		 // ---------------- Assign flights into airplanes -------------------------------- //

		 Stack planes = new Stack(100);
		 
		 while(!all_flights.isEmpty())
		 {
					 
			 if(((Flight) all_flights.peek()).getCompany().equals("DEHY") && ((Flight) all_flights.peek()).getAirplane_type().equals("AirBus"))
			 {
				 if(!(airport.getComp_dehy().getPlanes()[1].isUsed()))
				 {
					 airport.getComp_dehy().getPlanes()[1].setJourney(((Flight) all_flights.peek()));
					 airport.getComp_dehy().getPlanes()[1].setUsed(true);
					 
					 if(airport.getComp_dehy().getPlanes()[1].getJourney().getFlight_type() == 'A')
					 {
						 average_service_time_arrival_airbus += airport.getComp_dehy().getPlanes()[1].getJourney().getService_time();
					 }
					 else if(airport.getComp_dehy().getPlanes()[1].getJourney().getFlight_type() == 'D')
					 {
						 average_service_time_departure_airbus += airport.getComp_dehy().getPlanes()[1].getJourney().getService_time();
					 }
					 service_time_count++;
					 planes.push(airport.getComp_dehy().getPlanes()[1]);
					 all_flights.pop();
								
				 }				 
			 }
			 
			 else if(((Flight) all_flights.peek()).getCompany().equals("DEHY") && ((Flight) all_flights.peek()).getAirplane_type().equals("Boeing"))
			 {
				 if(!(airport.getComp_dehy().getPlanes()[2].isUsed()))
				 {
					 airport.getComp_dehy().getPlanes()[2].setJourney(((Flight) all_flights.peek()));
					 airport.getComp_dehy().getPlanes()[2].setUsed(true);
					 
					 if(airport.getComp_dehy().getPlanes()[2].getJourney().getFlight_type() == 'A')
					 {
						 average_service_time_arrival_boeing += airport.getComp_dehy().getPlanes()[2].getJourney().getService_time();
					 }
					 else if(airport.getComp_dehy().getPlanes()[2].getJourney().getFlight_type() == 'D')
					 {
						 average_service_time_departure_boeing += airport.getComp_dehy().getPlanes()[2].getJourney().getService_time();
					 }
					 service_time_count++;
					 
					 
					 planes.push(airport.getComp_dehy().getPlanes()[2]);
					 all_flights.pop();			
				 }	
				 else if(!(airport.getComp_dehy().getPlanes()[3].isUsed()))
				 {
					 airport.getComp_dehy().getPlanes()[3].setJourney(((Flight) all_flights.peek()));
					 airport.getComp_dehy().getPlanes()[3].setUsed(true);
					 
					 if(airport.getComp_dehy().getPlanes()[3].getJourney().getFlight_type() == 'A')
					 {
						 average_service_time_arrival_boeing += airport.getComp_dehy().getPlanes()[3].getJourney().getService_time();
							
					 }
					 else if(airport.getComp_dehy().getPlanes()[3].getJourney().getFlight_type() == 'D')
					 {
						 average_service_time_departure_boeing += airport.getComp_dehy().getPlanes()[3].getJourney().getService_time();
							
					 }
					 service_time_count++;
					 
					 planes.push(airport.getComp_dehy().getPlanes()[3]);
					 all_flights.pop();			
				 }	
			 }
			 
			 else if(((Flight) all_flights.peek()).getCompany().equals("DJET") && ((Flight) all_flights.peek()).getAirplane_type().equals("AirBus"))
			 {
				 if(!(airport.getComp_djet().getPlanes()[1].isUsed()))
				 {
					 airport.getComp_djet().getPlanes()[1].setJourney(((Flight) all_flights.peek()));
					 airport.getComp_djet().getPlanes()[1].setUsed(true);
					 
					 if(airport.getComp_djet().getPlanes()[1].getJourney().getFlight_type() == 'A')
					 {
						 average_service_time_arrival_airbus += airport.getComp_djet().getPlanes()[1].getJourney().getService_time();
							
					 }
					 else if(airport.getComp_djet().getPlanes()[1].getJourney().getFlight_type() == 'D')
					 {
						 average_service_time_departure_airbus += airport.getComp_djet().getPlanes()[1].getJourney().getService_time();

					 }
					 service_time_count++;
					 
				 planes.push(airport.getComp_djet().getPlanes()[1]);
				 all_flights.pop();
								
				 }	
				 else if(!(airport.getComp_djet().getPlanes()[2].isUsed()))
				 {
					 airport.getComp_djet().getPlanes()[2].setJourney(((Flight) all_flights.peek()));
					 airport.getComp_djet().getPlanes()[2].setUsed(true);
					 
					 if(airport.getComp_djet().getPlanes()[2].getJourney().getFlight_type() == 'A')
					 {
						 average_service_time_arrival_airbus += airport.getComp_djet().getPlanes()[2].getJourney().getService_time();

					 }
					 else if(airport.getComp_djet().getPlanes()[2].getJourney().getFlight_type() == 'D')
					 {
						 average_service_time_departure_airbus += airport.getComp_djet().getPlanes()[2].getJourney().getService_time();

					 }
					 service_time_count++;
					 
					 planes.push(airport.getComp_djet().getPlanes()[2]);
					 all_flights.pop();
								
				 }	
			 }
			 
			 else if(((Flight) all_flights.peek()).getCompany().equals("DJET") && ((Flight) all_flights.peek()).getAirplane_type().equals("Boeing"))
			 {
				 if(!(airport.getComp_djet().getPlanes()[3].isUsed()))
				 {
					 airport.getComp_djet().getPlanes()[3].setJourney(((Flight) all_flights.peek()));
					 airport.getComp_djet().getPlanes()[3].setUsed(true);
					 
					 if(airport.getComp_djet().getPlanes()[3].getJourney().getFlight_type() == 'A')
					 {
						 average_service_time_arrival_boeing += airport.getComp_djet().getPlanes()[3].getJourney().getService_time();

					 }
					 else if(airport.getComp_djet().getPlanes()[3].getJourney().getFlight_type() == 'D')
					 {
						 average_service_time_departure_boeing += airport.getComp_djet().getPlanes()[3].getJourney().getService_time();

					 }
					 service_time_count++;
					 
					 planes.push(airport.getComp_djet().getPlanes()[3]);
					 all_flights.pop();
								
				 }	
				 else if(!(airport.getComp_djet().getPlanes()[4].isUsed()))
				 {
					 airport.getComp_djet().getPlanes()[4].setJourney(((Flight) all_flights.peek()));
					 airport.getComp_djet().getPlanes()[4].setUsed(true);
					 
					 if(airport.getComp_djet().getPlanes()[4].getJourney().getFlight_type() == 'A')
					 {
						 average_service_time_arrival_boeing += airport.getComp_djet().getPlanes()[4].getJourney().getService_time();

					 }
					 else if(airport.getComp_djet().getPlanes()[4].getJourney().getFlight_type() == 'D')
					 {
						 average_service_time_departure_boeing += airport.getComp_djet().getPlanes()[4].getJourney().getService_time();

					 }
					 service_time_count++;
					 
					 planes.push(airport.getComp_djet().getPlanes()[4]);
					 all_flights.pop();
								
				 }	
				 else if(!(airport.getComp_djet().getPlanes()[5].isUsed()))
				 {
					 airport.getComp_djet().getPlanes()[5].setJourney(((Flight) all_flights.peek()));
					 airport.getComp_djet().getPlanes()[5].setUsed(true);
					 
					 if(airport.getComp_djet().getPlanes()[5].getJourney().getFlight_type() == 'A')
					 {
						 average_service_time_arrival_boeing += airport.getComp_djet().getPlanes()[5].getJourney().getService_time();

					 }
					 else if(airport.getComp_djet().getPlanes()[5].getJourney().getFlight_type() == 'D')
					 {
						 average_service_time_departure_boeing += airport.getComp_djet().getPlanes()[5].getJourney().getService_time();

					 }
					 service_time_count++;
					 
					 planes.push(airport.getComp_djet().getPlanes()[5]);
					 all_flights.pop();
								
				 }	
			 }
	
			 
			 
		 }
		 
		 flightbufferreader.close(); 
		 
		 // ------------- Reading Passenger List ------------------------ //
		 
		 InputStream in2 = this.getClass().getClassLoader().getResourceAsStream("passengerlist.txt");
		 
		 InputStreamReader passengerfilereader = new InputStreamReader(in2);
		 BufferedReader passengerbufferreader = new BufferedReader(passengerfilereader);
				 
		 while((line=passengerbufferreader.readLine())!=null)
		 {
			 dizi = line.split(",");  
			 
			 Passenger person = new Passenger((String)dizi[0],Integer.parseInt(dizi[1]),Integer.parseInt(dizi[2]),(String)dizi[3],(String)dizi[4],(String)dizi[5],(String)dizi[6]);
			 
			 all_passengers.enqueue(person);
		 }
		 
		 passengerbufferreader.close();
		  	 
		 // ----------------------------------------------------------------------- //
		
		
		 // --- Reserve plane seats for passengers --------- //
		 
		 Passenger next_pass;
		 Airplane next_plane;
		 boolean assigned;
		 int i;
		 Queue atanamayanlar = new Queue(100);
		 
		 while(!all_passengers.isEmpty())
		 {
			 		 
			 next_pass = (Passenger)all_passengers.peek();

			 if(next_pass.getCompany().equals("DEHY"))
			 {
				 	
				 all_passengers.dequeue();
				 i = 1;
				 assigned = false;
				 
				 while(!assigned)
				 {
					 if(i == 4)
					 {
						 atanamayanlar.enqueue(next_pass);
						 all_passengers.dequeue();
						 break;
					 }
					 
					 next_plane = airport.getComp_dehy().getPlanes()[i];
					 
					 if(next_plane.getType().equals("AirBus"))
					 {
					 
						 if((next_plane.getJourney().getFrom().equals(next_pass.getFrom())) && next_plane.getJourney().getTo().equals(next_pass.getTo()))
						 {
							 if(next_pass.getClass_type().equals("Bussiness"))
							 {
								 if(!next_plane.getSeats()[0][0].isReserved())
								 {
									 next_plane.getSeats()[0][0].setPerson(next_pass);
									 next_plane.getSeats()[0][0].setReserved(true);
									 next_plane.getSeats()[0][0].setSeat_no("1A");
									 next_plane.getSeats()[0][0].setPerson(next_pass);
									 DEHY_sum_ticket_price += next_pass.getTicket_price();									 
									 average_ticket_price_business += next_pass.getTicket_price();
									 ticket_price_counter++;							 
									 assigned = true;
								 }
								 else if(!next_plane.getSeats()[0][1].isReserved())
								 {
									 next_plane.getSeats()[0][1].setPerson(next_pass);
									 next_plane.getSeats()[0][1].setReserved(true);
									 next_plane.getSeats()[0][1].setSeat_no("1B");
									 next_plane.getSeats()[0][1].setPerson(next_pass);
									 DEHY_sum_ticket_price += next_pass.getTicket_price();									 
									 average_ticket_price_business += next_pass.getTicket_price();
									 ticket_price_counter++;
									 assigned = true;
								 }
							 }
							 
							 else if(next_pass.getClass_type().equals("Economy"))
							 {
								 if(!next_plane.getSeats()[1][0].isReserved())
								 {
									 next_plane.getSeats()[1][0].setPerson(next_pass);
									 next_plane.getSeats()[1][0].setReserved(true);
									 next_plane.getSeats()[1][0].setSeat_no("2A");
									 next_plane.getSeats()[1][0].setPerson(next_pass);
									 DEHY_sum_ticket_price += next_pass.getTicket_price();									 
									 average_ticket_price_economy += next_pass.getTicket_price();
									 ticket_price_counter++;
									 assigned = true;
								 }
								 else if(!next_plane.getSeats()[1][1].isReserved())
								 {
									 next_plane.getSeats()[1][1].setPerson(next_pass);
									 next_plane.getSeats()[1][1].setReserved(true);
									 next_plane.getSeats()[1][1].setSeat_no("2B");
									 next_plane.getSeats()[1][1].setPerson(next_pass);
									 DEHY_sum_ticket_price += next_pass.getTicket_price();									 
									 average_ticket_price_economy += next_pass.getTicket_price();
									 ticket_price_counter++;
									 assigned = true;
								 }
								 else if(!next_plane.getSeats()[2][0].isReserved())
								 {
									 next_plane.getSeats()[2][0].setPerson(next_pass);
									 next_plane.getSeats()[2][0].setReserved(true);
									 next_plane.getSeats()[2][0].setSeat_no("3A");
									 next_plane.getSeats()[2][0].setPerson(next_pass);
									 DEHY_sum_ticket_price += next_pass.getTicket_price();									 
									 average_ticket_price_economy += next_pass.getTicket_price();
									 ticket_price_counter++;
									 assigned = true;
								 }
								 else if(!next_plane.getSeats()[2][1].isReserved())
								 {
									 next_plane.getSeats()[2][1].setPerson(next_pass);
									 next_plane.getSeats()[2][1].setReserved(true);
									 next_plane.getSeats()[2][1].setSeat_no("3B");
									 next_plane.getSeats()[2][1].setPerson(next_pass);
									 DEHY_sum_ticket_price += next_pass.getTicket_price();									 
									 average_ticket_price_economy += next_pass.getTicket_price();
									 ticket_price_counter++;
									 assigned = true;
								 }
								 else if(!next_plane.getSeats()[3][0].isReserved())
								 {
									 next_plane.getSeats()[3][0].setPerson(next_pass);
									 next_plane.getSeats()[3][0].setReserved(true);
									 next_plane.getSeats()[3][0].setSeat_no("4A");
									 next_plane.getSeats()[3][0].setPerson(next_pass);
									 DEHY_sum_ticket_price += next_pass.getTicket_price();									 
									 average_ticket_price_economy += next_pass.getTicket_price();
									 ticket_price_counter++;
									 assigned = true;
								 }
								 else if(!next_plane.getSeats()[3][1].isReserved())
								 {
									 next_plane.getSeats()[3][1].setPerson(next_pass);
									 next_plane.getSeats()[3][1].setReserved(true);
									 next_plane.getSeats()[3][1].setSeat_no("4B");
									 next_plane.getSeats()[3][1].setPerson(next_pass);
									 DEHY_sum_ticket_price += next_pass.getTicket_price();									 
									 average_ticket_price_economy += next_pass.getTicket_price();
									 ticket_price_counter++;
									 assigned = true;
								 }
								 
							 }
						 
						 
						 }
					 
					 
					 }
					 
					 else if(next_plane.getType().equals("Boeing"))
					 {
						 if(next_pass.getClass_type().equals("Bussiness"))
						 {
							 if(!next_plane.getSeats()[0][0].isReserved())
							 {
								 next_plane.getSeats()[0][0].setPerson(next_pass);
								 next_plane.getSeats()[0][0].setReserved(true);
								 next_plane.getSeats()[0][0].setSeat_no("1A");
								 next_plane.getSeats()[0][0].setPerson(next_pass);
								 DEHY_sum_ticket_price += next_pass.getTicket_price();									 
								 average_ticket_price_business += next_pass.getTicket_price();
								 ticket_price_counter++;
								 assigned = true;
							 }
							 else if(!next_plane.getSeats()[0][1].isReserved())
							 {
								 next_plane.getSeats()[0][1].setPerson(next_pass);
								 next_plane.getSeats()[0][1].setReserved(true);
								 next_plane.getSeats()[0][1].setSeat_no("2A");
								 next_plane.getSeats()[0][1].setPerson(next_pass);
								 DEHY_sum_ticket_price += next_pass.getTicket_price();									 
								 average_ticket_price_business += next_pass.getTicket_price();
								 ticket_price_counter++;
								 assigned = true;
							 }
						 }
						 
						 else if(next_pass.getClass_type().equals("Economy"))
						 {
							 if(!next_plane.getSeats()[1][0].isReserved())
							 {
								 next_plane.getSeats()[1][0].setPerson(next_pass);
								 next_plane.getSeats()[1][0].setReserved(true);
								 next_plane.getSeats()[1][0].setSeat_no("1B");
								 next_plane.getSeats()[1][0].setPerson(next_pass);
								 DEHY_sum_ticket_price += next_pass.getTicket_price();									 
								 average_ticket_price_economy += next_pass.getTicket_price();
								 ticket_price_counter++;
								 assigned = true;
							 }
							 else if(!next_plane.getSeats()[1][1].isReserved())
							 {
								 next_plane.getSeats()[1][1].setPerson(next_pass);
								 next_plane.getSeats()[1][1].setReserved(true);
								 next_plane.getSeats()[1][1].setSeat_no("2B");
								 next_plane.getSeats()[1][1].setPerson(next_pass);
								 DEHY_sum_ticket_price += next_pass.getTicket_price();									 
								 average_ticket_price_economy += next_pass.getTicket_price();
								 ticket_price_counter++;
								 assigned = true;
							 }
							 else if(!next_plane.getSeats()[2][0].isReserved())
							 {
								 next_plane.getSeats()[2][0].setPerson(next_pass);
								 next_plane.getSeats()[2][0].setReserved(true);
								 next_plane.getSeats()[2][0].setSeat_no("3A");
								 next_plane.getSeats()[2][0].setPerson(next_pass);
								 DEHY_sum_ticket_price += next_pass.getTicket_price();									 
								 average_ticket_price_economy += next_pass.getTicket_price();
								 ticket_price_counter++;
								 assigned = true;
							 }
							 else if(!next_plane.getSeats()[2][1].isReserved())
							 {
								 next_plane.getSeats()[2][1].setPerson(next_pass);
								 next_plane.getSeats()[2][1].setReserved(true);
								 next_plane.getSeats()[2][1].setSeat_no("3B");
								 next_plane.getSeats()[2][1].setPerson(next_pass);
								 DEHY_sum_ticket_price += next_pass.getTicket_price();									 
								 average_ticket_price_economy += next_pass.getTicket_price();
								 ticket_price_counter++;
								 assigned = true;
							 }
						 
					 }
			 
					 }
					 
					 i++;
				 }
			 }
			 
			 else
			 {
				 
				 all_passengers.dequeue();
				 i = 1;
				 assigned = false;
				 while(!assigned)
				 {
					 if(i == 6)
					 {
						 atanamayanlar.enqueue(next_pass);
						 all_passengers.dequeue();
						 break;
					 }
					 
					 next_plane = airport.getComp_djet().getPlanes()[i];
					 
					 if(next_plane.getType().equals("AirBus"))
					 {
					 
						 if((next_plane.getJourney().getFrom().equals(next_pass.getFrom())) && next_plane.getJourney().getTo().equals(next_pass.getTo()))
						 {
							 if(next_pass.getClass_type().equals("Bussiness"))
							 {
								 if(!next_plane.getSeats()[0][0].isReserved())
								 {
									 next_plane.getSeats()[0][0].setPerson(next_pass);
									 next_plane.getSeats()[0][0].setReserved(true);
									 next_plane.getSeats()[0][0].setSeat_no("1A");
									 next_plane.getSeats()[0][0].setPerson(next_pass);
									 DJET_sum_ticket_price += next_pass.getTicket_price();									 
									 average_ticket_price_business += next_pass.getTicket_price();
									 ticket_price_counter++;
									 assigned = true;
								 }
								 else if(!next_plane.getSeats()[0][1].isReserved())
								 {
									 next_plane.getSeats()[0][1].setPerson(next_pass);
									 next_plane.getSeats()[0][1].setReserved(true);
									 next_plane.getSeats()[0][1].setSeat_no("1B");
									 next_plane.getSeats()[0][1].setPerson(next_pass);
									 DJET_sum_ticket_price += next_pass.getTicket_price();									 
									 average_ticket_price_business += next_pass.getTicket_price();
									 ticket_price_counter++;
									 assigned = true;
								 }
							 }
							 
							 else if(next_pass.getClass_type().equals("Economy"))
							 {
								 if(!next_plane.getSeats()[1][0].isReserved())
								 {
									 next_plane.getSeats()[1][0].setPerson(next_pass);
									 next_plane.getSeats()[1][0].setReserved(true);
									 next_plane.getSeats()[1][0].setSeat_no("2A");
									 next_plane.getSeats()[1][0].setPerson(next_pass);
									 DJET_sum_ticket_price += next_pass.getTicket_price();									 
									 average_ticket_price_economy += next_pass.getTicket_price();
									 ticket_price_counter++;
									 assigned = true;
								 }
								 else if(!next_plane.getSeats()[1][1].isReserved())
								 {
									 next_plane.getSeats()[1][1].setPerson(next_pass);
									 next_plane.getSeats()[1][1].setReserved(true);
									 next_plane.getSeats()[1][1].setSeat_no("2B");
									 next_plane.getSeats()[1][1].setPerson(next_pass);
									 DJET_sum_ticket_price += next_pass.getTicket_price();									 
									 average_ticket_price_economy += next_pass.getTicket_price();
									 ticket_price_counter++;
									 assigned = true;
								 }
								 else if(!next_plane.getSeats()[2][0].isReserved())
								 {
									 next_plane.getSeats()[2][0].setPerson(next_pass);
									 next_plane.getSeats()[2][0].setReserved(true);
									 next_plane.getSeats()[2][0].setSeat_no("3A");
									 next_plane.getSeats()[2][0].setPerson(next_pass);
									 DJET_sum_ticket_price += next_pass.getTicket_price();									 
									 average_ticket_price_economy += next_pass.getTicket_price();
									 ticket_price_counter++;
									 assigned = true;
								 }
								 else if(!next_plane.getSeats()[2][1].isReserved())
								 {
									 next_plane.getSeats()[2][1].setPerson(next_pass);
									 next_plane.getSeats()[2][1].setReserved(true);
									 next_plane.getSeats()[2][1].setSeat_no("3B");
									 next_plane.getSeats()[2][1].setPerson(next_pass);
									 DJET_sum_ticket_price += next_pass.getTicket_price();									 
									 average_ticket_price_economy += next_pass.getTicket_price();
									 ticket_price_counter++;
									 assigned = true;
								 }
								 else if(!next_plane.getSeats()[3][0].isReserved())
								 {
									 next_plane.getSeats()[3][0].setPerson(next_pass);
									 next_plane.getSeats()[3][0].setReserved(true);
									 next_plane.getSeats()[3][0].setSeat_no("4A");
									 next_plane.getSeats()[3][0].setPerson(next_pass);
									 DJET_sum_ticket_price += next_pass.getTicket_price();									 
									 average_ticket_price_economy += next_pass.getTicket_price();
									 ticket_price_counter++;
									 assigned = true;
								 }
								 else if(!next_plane.getSeats()[3][1].isReserved())
								 {
									 next_plane.getSeats()[3][1].setPerson(next_pass);
									 next_plane.getSeats()[3][1].setReserved(true);
									 next_plane.getSeats()[3][1].setSeat_no("4B");
									 next_plane.getSeats()[3][1].setPerson(next_pass);
									 DJET_sum_ticket_price += next_pass.getTicket_price();									 
									 average_ticket_price_economy += next_pass.getTicket_price();
									 ticket_price_counter++;
									 assigned = true;
								 }
								 
							 }
						 
						 
						 }
					 
					 
					 }
					 
					 else if(next_plane.getType().equals("Boeing"))
					 {
						 if(next_pass.getClass_type().equals("Bussiness"))
						 {
							 if(!next_plane.getSeats()[0][0].isReserved())
							 {
								 next_plane.getSeats()[0][0].setPerson(next_pass);
								 next_plane.getSeats()[0][0].setReserved(true);
								 next_plane.getSeats()[0][0].setSeat_no("1A");
								 next_plane.getSeats()[0][0].setPerson(next_pass);
								 DJET_sum_ticket_price += next_pass.getTicket_price();									 
								 average_ticket_price_business += next_pass.getTicket_price();
								 ticket_price_counter++;
								 assigned = true;
							 }
							 else if(!next_plane.getSeats()[0][1].isReserved())
							 {
								 next_plane.getSeats()[0][1].setPerson(next_pass);
								 next_plane.getSeats()[0][1].setReserved(true);
								 next_plane.getSeats()[0][1].setSeat_no("1B");
								 next_plane.getSeats()[0][1].setPerson(next_pass);
								 DJET_sum_ticket_price += next_pass.getTicket_price();									 
								 average_ticket_price_business += next_pass.getTicket_price();
								 ticket_price_counter++;
								 assigned = true;
							 }
						 }
						 
						 else if(next_pass.getClass_type().equals("Economy"))
						 {
							 if(!next_plane.getSeats()[1][0].isReserved())
							 {
								 next_plane.getSeats()[1][0].setPerson(next_pass);
								 next_plane.getSeats()[1][0].setReserved(true);
								 next_plane.getSeats()[1][0].setSeat_no("2A");
								 next_plane.getSeats()[1][0].setPerson(next_pass);
								 DJET_sum_ticket_price += next_pass.getTicket_price();									 
								 average_ticket_price_economy += next_pass.getTicket_price();
								 ticket_price_counter++;
								 assigned = true;
							 }
							 else if(!next_plane.getSeats()[1][1].isReserved())
							 {
								 next_plane.getSeats()[1][1].setPerson(next_pass);
								 next_plane.getSeats()[1][1].setReserved(true);
								 next_plane.getSeats()[1][1].setSeat_no("2B");
								 next_plane.getSeats()[1][1].setPerson(next_pass);
								 DJET_sum_ticket_price += next_pass.getTicket_price();									 
								 average_ticket_price_economy += next_pass.getTicket_price();
								 ticket_price_counter++;
								 assigned = true;
							 }
							 else if(!next_plane.getSeats()[2][0].isReserved())
							 {
								 next_plane.getSeats()[2][0].setPerson(next_pass);
								 next_plane.getSeats()[2][0].setReserved(true);
								 next_plane.getSeats()[2][0].setSeat_no("3A");
								 next_plane.getSeats()[2][0].setPerson(next_pass);
								 DJET_sum_ticket_price += next_pass.getTicket_price();									 
								 average_ticket_price_economy += next_pass.getTicket_price();
								 ticket_price_counter++;
								 assigned = true;
							 }
							 else if(!next_plane.getSeats()[2][1].isReserved())
							 {
								 next_plane.getSeats()[2][1].setPerson(next_pass);
								 next_plane.getSeats()[2][1].setReserved(true);
								 next_plane.getSeats()[2][1].setSeat_no("3B");
								 next_plane.getSeats()[2][1].setPerson(next_pass);
								 DJET_sum_ticket_price += next_pass.getTicket_price();									 
								 average_ticket_price_economy += next_pass.getTicket_price();
								 ticket_price_counter++;
								 assigned = true;
							 }
				 
				 
				 
			            }
			 
			 
			        }
					 
				i++;
		       }
		   }
	  }
		 Thread t = new Thread();
		 t.sleep(100); // wait a little bit to be ready!
		 
		 // -- > Game starts
		 
		 Queue hold_planes = new Queue(100);
		 while(!planes.isEmpty())
		 {
			 hold_planes.enqueue(planes.pop());
		 }
		 while(!hold_planes.isEmpty())
		 {
			 planes.push(hold_planes.dequeue());
		 } 
		 
		 int finish_time = 0;
		 int waiting_time = 0;
		 int time = 0;
		 
		 
		 Random rnd = new Random(4);
		 int nextran;
		 show_screen();
		 Airplane current_airplane;
		 Airplane next_airplane;

	 
		 while(!endGame)
		 {


			if(MouseKey.mousepr == 1)
			{

				if((!temp_arrival_planes.isEmpty()) && (!temp_departure_planes.isEmpty()))
				{
					nextran = rnd.nextInt(6);
					Airplane arr = (Airplane)temp_arrival_planes.peek();
					Airplane dep = (Airplane)temp_departure_planes.peek();
					if(nextran <= 3)
					{
						waiting_time = finish_time - arr.getJourney().getArrival_departure_time();
						
						if(arr.getJourney().getCompany().equals("DEHY") && arr.getJourney().getAirplane_type().equals("AirBus"))
						{
							DEHY_average_waiting_airbus+=waiting_time;
							if(waiting_time > DEHY_max_waiting_airbus)
							{
								DEHY_max_waiting_airbus = waiting_time;
							}
							if(waiting_time < DEHY_max_waiting_airbus)
							{
								DEHY_min_waiting_airbus = waiting_time;
							}
							waiting_count++;
						}
						else if(arr.getJourney().getCompany().equals("DEHY") && arr.getJourney().getAirplane_type().equals("Boeing"))
						{
							DEHY_average_waiting_boeing+=waiting_time;
							if(waiting_time > DEHY_max_waiting_boeing)
							{
								DEHY_max_waiting_boeing = waiting_time;
							}
							if(waiting_time < DEHY_max_waiting_boeing)
							{
								DEHY_min_waiting_boeing = waiting_time;
							}
							waiting_count++;
						}
						else if(arr.getJourney().getCompany().equals("DJET") && arr.getJourney().getAirplane_type().equals("AirBus"))
						{
							DJET_average_waiting_airbus+=waiting_time;
							if(waiting_time > DJET_max_waiting_airbus)
							{
								DJET_max_waiting_airbus = waiting_time;
							}
							if(waiting_time < DJET_max_waiting_airbus)
							{
								DJET_min_waiting_airbus = waiting_time;
							}
							waiting_count++;
						}
						else if(arr.getJourney().getCompany().equals("DJET") && arr.getJourney().getAirplane_type().equals("Boeing"))
						{
							DJET_average_waiting_boeing+=waiting_time;
							if(waiting_time > DJET_max_waiting_boeing)
							{
								DJET_max_waiting_boeing = waiting_time;
							}
							if(waiting_time < DJET_max_waiting_boeing)
							{
								DJET_min_waiting_boeing = waiting_time;
							}
							waiting_count++;
						}
						
						finish_time = finish_time + arr.getJourney().getService_time();
						time = arr.getJourney().getArrival_departure_time() + waiting_time;
						
						current_airplane = arr;
						temp_arrival_planes.dequeue();
						clear_waiting_arrival();
					}
					else
					{
						
						waiting_time = finish_time - dep.getJourney().getArrival_departure_time();
						
						if(dep.getJourney().getCompany().equals("DEHY") && dep.getJourney().getAirplane_type().equals("AirBus"))
						{
							DEHY_average_waiting_airbus+=waiting_time;
							if(waiting_time > DEHY_max_waiting_airbus)
							{
								DEHY_max_waiting_airbus = waiting_time;
							}
							if(waiting_time < DEHY_max_waiting_airbus)
							{
								DEHY_min_waiting_airbus = waiting_time;
							}
							waiting_count++;
						}
						else if(dep.getJourney().getCompany().equals("DEHY") && dep.getJourney().getAirplane_type().equals("Boeing"))
						{
							DEHY_average_waiting_boeing+=waiting_time;
							if(waiting_time > DEHY_max_waiting_boeing)
							{
								DEHY_max_waiting_boeing = waiting_time;
							}
							if(waiting_time < DEHY_max_waiting_boeing)
							{
								DEHY_min_waiting_boeing = waiting_time;
							}
							waiting_count++;
						}
						else if(dep.getJourney().getCompany().equals("DJET") && dep.getJourney().getAirplane_type().equals("AirBus"))
						{
							DJET_average_waiting_airbus+=waiting_time;
							if(waiting_time > DJET_max_waiting_airbus)
							{
								DJET_max_waiting_airbus = waiting_time;
							}
							if(waiting_time < DJET_max_waiting_airbus)
							{
								DJET_min_waiting_airbus = waiting_time;
							}
							waiting_count++;
						}
						else if(dep.getJourney().getCompany().equals("DJET") && dep.getJourney().getAirplane_type().equals("Boeing"))
						{
							DJET_average_waiting_boeing+=waiting_time;
							if(waiting_time > DJET_max_waiting_boeing)
							{
								DJET_max_waiting_boeing = waiting_time;
							}
							if(waiting_time < DJET_max_waiting_boeing)
							{
								DJET_min_waiting_boeing = waiting_time;
							}
							waiting_count++;
						}
						
						
						
						finish_time = finish_time + dep.getJourney().getService_time();
						time = dep.getJourney().getArrival_departure_time() + waiting_time;
						
						current_airplane = dep;
						temp_departure_planes.dequeue();
						clear_waiting_departure();
					}
					
					
				}
				else if(!temp_arrival_planes.isEmpty())
			 	{
					Airplane arr = (Airplane)temp_arrival_planes.dequeue();
					
					
					waiting_time = finish_time - arr.getJourney().getArrival_departure_time();
					
					
					if(arr.getJourney().getCompany().equals("DEHY") && arr.getJourney().getAirplane_type().equals("AirBus"))
					{
						DEHY_average_waiting_airbus+=waiting_time;
						if(waiting_time > DEHY_max_waiting_airbus)
						{
							DEHY_max_waiting_airbus = waiting_time;
						}
						if(waiting_time < DEHY_max_waiting_airbus)
						{
							DEHY_min_waiting_airbus = waiting_time;
						}
						waiting_count++;
					}
					else if(arr.getJourney().getCompany().equals("DEHY") && arr.getJourney().getAirplane_type().equals("Boeing"))
					{
						DEHY_average_waiting_boeing+=waiting_time;
						if(waiting_time > DEHY_max_waiting_boeing)
						{
							DEHY_max_waiting_boeing = waiting_time;
						}
						if(waiting_time < DEHY_max_waiting_boeing)
						{
							DEHY_min_waiting_boeing = waiting_time;
						}
						waiting_count++;
					}
					else if(arr.getJourney().getCompany().equals("DJET") && arr.getJourney().getAirplane_type().equals("AirBus"))
					{
						DJET_average_waiting_airbus+=waiting_time;
						if(waiting_time > DJET_max_waiting_airbus)
						{
							DJET_max_waiting_airbus = waiting_time;
						}
						if(waiting_time < DJET_max_waiting_airbus)
						{
							DJET_min_waiting_airbus = waiting_time;
						}
						waiting_count++;
					}
					else if(arr.getJourney().getCompany().equals("DJET") && arr.getJourney().getAirplane_type().equals("Boeing"))
					{
						DJET_average_waiting_boeing+=waiting_time;
						if(waiting_time > DJET_max_waiting_boeing)
						{
							DJET_max_waiting_boeing = waiting_time;
						}
						if(waiting_time < DJET_max_waiting_boeing)
						{
							DJET_min_waiting_boeing = waiting_time;
						}
						waiting_count++;
					}
					
					
					finish_time = finish_time + arr.getJourney().getService_time();
					time = arr.getJourney().getArrival_departure_time() + waiting_time;
					
					current_airplane = arr;
					clear_waiting_arrival();
			 	}
				else if(!temp_departure_planes.isEmpty())
				{
					
					
					
					Airplane dep = (Airplane)temp_departure_planes.dequeue();
					
					waiting_time = finish_time - dep.getJourney().getArrival_departure_time();
					
					if(dep.getJourney().getCompany().equals("DEHY") && dep.getJourney().getAirplane_type().equals("AirBus"))
					{
						DEHY_average_waiting_airbus+=waiting_time;
						if(waiting_time > DEHY_max_waiting_airbus)
						{
							DEHY_max_waiting_airbus = waiting_time;
						}
						if(waiting_time < DEHY_max_waiting_airbus)
						{
							DEHY_min_waiting_airbus = waiting_time;
						}
						waiting_count++;
					}
					else if(dep.getJourney().getCompany().equals("DEHY") && dep.getJourney().getAirplane_type().equals("Boeing"))
					{
						DEHY_average_waiting_boeing+=waiting_time;
						if(waiting_time > DEHY_max_waiting_boeing)
						{
							DEHY_max_waiting_boeing = waiting_time;
						}
						if(waiting_time < DEHY_max_waiting_boeing)
						{
							DEHY_min_waiting_boeing = waiting_time;
						}
						waiting_count++;
					}
					else if(dep.getJourney().getCompany().equals("DJET") && dep.getJourney().getAirplane_type().equals("AirBus"))
					{
						DJET_average_waiting_airbus+=waiting_time;
						if(waiting_time > DJET_max_waiting_airbus)
						{
							DJET_max_waiting_airbus = waiting_time;
						}
						if(waiting_time < DJET_max_waiting_airbus)
						{
							DJET_min_waiting_airbus = waiting_time;
						}
						waiting_count++;
					}
					else if(dep.getJourney().getCompany().equals("DJET") && dep.getJourney().getAirplane_type().equals("Boeing"))
					{
						DJET_average_waiting_boeing+=waiting_time;
						if(waiting_time > DJET_max_waiting_boeing)
						{
							DJET_max_waiting_boeing = waiting_time;
						}
						if(waiting_time < DJET_max_waiting_boeing)
						{
							DJET_min_waiting_boeing = waiting_time;
						}
						waiting_count++;
					}
					
					finish_time = finish_time + dep.getJourney().getService_time();
					time = dep.getJourney().getArrival_departure_time() + waiting_time;
					
					
					
					current_airplane = dep;
					clear_waiting_departure();
					
					
					
				}
				else
				{
					current_airplane = (Airplane) planes.pop();
					waiting_time = 0;
					finish_time = finish_time + current_airplane.getJourney().getArrival_departure_time() + current_airplane.getJourney().getService_time();
					time = current_airplane.getJourney().getArrival_departure_time();
				}
				
		
			    update_time(time);
				

				
									 	 
				 add_information(current_airplane.getJourney().getFlight_type(), current_airplane.getJourney().getCompany(), current_airplane.getJourney().getFrom(), current_airplane.getJourney().getTo(), current_airplane.getJourney().getFlight_number(), current_airplane.getJourney().getArrival_departure_time(), finish_time, waiting_time);
				 update_passOfFlight(current_airplane.getJourney().getFlight_number());
				 
				while(m >= 25)
				{
					clear_passenger();
				}
				 for(int a=0;a<current_airplane.getSeats().length;a++)
				 {
					 for(int b=0;b<2;b++)
					 {
						 if(current_airplane.getSeats()[a][b].getPerson() != null)
						 add_passenger(current_airplane.getSeats()[a][b].getSeat_no(),current_airplane.getSeats()[a][b].getPerson().getName(),current_airplane.getSeats()[a][b].getPerson().getLuggage());
					 }
				}
				 
				 
				 
				 
				 if(!planes.isEmpty())
				 {
				 next_airplane = (Airplane) planes.peek();
					
				 MouseKey.mousepr = 0;
						while(next_airplane.getJourney().getArrival_departure_time() < finish_time)
						{
							
							if(MouseKey.mousepr == 1)
							{
							
							if(next_airplane.getJourney().getFlight_type()== 'A')
							 {
								 temp_arrival_planes.enqueue(next_airplane);
								 add_waiting_arrival(next_airplane.getJourney().getCompany(), next_airplane.getJourney().getFrom(), next_airplane.getJourney().getTo(), next_airplane.getJourney().getFlight_number(), Integer.toString(next_airplane.getJourney().getArrival_departure_time()), Integer.toString(next_airplane.getJourney().getService_time()));
								 time = next_airplane.getJourney().getArrival_departure_time();
								 update_time(time);
								 
								 
								 
							 }
							 else if(next_airplane.getJourney().getFlight_type() == 'D')
							 {
								 temp_departure_planes.enqueue(next_airplane);
								 add_waiting_departure(next_airplane.getJourney().getCompany(), next_airplane.getJourney().getFrom(), next_airplane.getJourney().getTo(), next_airplane.getJourney().getFlight_number(), Integer.toString(next_airplane.getJourney().getArrival_departure_time()), Integer.toString(next_airplane.getJourney().getService_time()));
								 time = next_airplane.getJourney().getArrival_departure_time();
								 update_time(time);
								 
								 
							 }
							planes.pop();
							 if(planes.isEmpty())
							 {
								 break;
							 }
							
							 next_airplane = (Airplane) planes.peek();
							 MouseKey.mousepr = 0;
							 
							
							}
							
							
						}
					
				 }
						
						MouseKey.mousepr = 0;
						
					}
					
					

			
			
			// D�ng�den ��k�� ko�ulu
			 if(temp_arrival_planes.isEmpty() && temp_departure_planes.isEmpty() && planes.isEmpty())
			 {
				 endGame = true;
			 }
			 
			
			 
			 
			 }

		 boolean results = false;
		 
		 while(!results)
		 {
			 
		 sOut("", 2, 2);
		 if(MouseKey.mousepr == 1)
			{
			 
			 
			 
			    clear_for_end();
			 
			    
			    DEHY_average_waiting_airbus = DEHY_average_waiting_airbus/waiting_count;
			    DEHY_average_waiting_boeing = DEHY_average_waiting_boeing/waiting_count;
			    DJET_average_waiting_airbus = DJET_average_waiting_airbus/waiting_count;
			    DJET_average_waiting_boeing = DJET_average_waiting_boeing/waiting_count;
			    
			    if(DEHY_min_waiting_airbus == Integer.MAX_VALUE)
			    	DEHY_min_waiting_airbus = 0;
			    if(DEHY_min_waiting_boeing == Integer.MAX_VALUE)
			    	DEHY_min_waiting_boeing = 0;
			    if(DJET_min_waiting_airbus == Integer.MAX_VALUE)
			    	DJET_min_waiting_airbus = 0;
			    if(DJET_min_waiting_boeing == Integer.MAX_VALUE)
			    	DJET_min_waiting_boeing = 0;
			    	
			    
			    sOut("| ------- | ------------------------------------------- |", 3, 3);
			    sOut("| *     * |                Waiting times                |", 3, 4);
			    sOut("|   * *   | ------------------------------------------- |", 3, 5);
			    sOut("|    *    |       DEHY          |         DJET          |", 3, 6);
			    sOut("|   * *   | ------------------- | --------------------- |", 3, 7);
			    sOut("| *     * |   Airbus | Boeing   |   Airbus  |  Boeing   |", 3, 8);
			    sOut("| ------- | ------------------- | --------------------- |", 3, 9);
			    sOut("| Average | "+DEHY_average_waiting_airbus+" | "+DEHY_average_waiting_boeing+" | "+DJET_average_waiting_airbus+" | "+DJET_average_waiting_boeing+" |", 3, 10);
			    sOut("| ------- | ------------------- | --------------------- |", 3, 11);
			    sOut("|   Max   |     "+DEHY_max_waiting_airbus+"   |    "+DEHY_max_waiting_boeing+"    |     "+DJET_max_waiting_airbus+"    |    "+DJET_max_waiting_boeing+"    |", 3, 12);
			    sOut("| ------- | ------------------- | --------------------- |", 3, 13);
			    sOut("|   Min   |     "+DEHY_min_waiting_airbus+"    |    "+DEHY_min_waiting_boeing+"     |     "+DJET_min_waiting_airbus+"    |    "+DJET_min_waiting_boeing+"     |", 3, 14);
			    sOut("| ------- | ------------------- | --------------------- |", 3, 15);
			    		
			    
			    average_service_time_arrival_airbus = average_service_time_arrival_airbus/service_time_count;
			    average_service_time_arrival_boeing = average_service_time_arrival_boeing/service_time_count;
			    average_service_time_departure_airbus = average_service_time_departure_airbus/service_time_count;
			    average_service_time_departure_boeing = average_service_time_departure_boeing/service_time_count;
			    
			    
			    sOut("| ------- | ------------------------------------------ |", 65, 2);
			    sOut("| *     * |                Service times               |", 65, 3);
			    sOut("|   * *   | ------------------------------------------ |", 65, 4);
			    sOut("|    *    |       Arrival       |      Departure       |", 65, 5);
			    sOut("|   * *   | ------------------- | -------------------- |", 65, 6);
			    sOut("| *     * |   Airbus | Boeing   |   Airbus | Boeing    |", 65, 7);
			    sOut("| ------- | ------------------- | -------------------- |", 65, 8);
			    sOut("| Average |    " +average_service_time_arrival_airbus+"  |   "+average_service_time_arrival_boeing+"   |     "+average_service_time_departure_airbus+"  |   "+average_service_time_departure_boeing+"    |", 65, 9);
			    sOut("| ------- | ------------------- | -------------------- |", 65, 10);
			    
			    		
			    
			    float averageDHY = DEHY_sum_ticket_price/ticket_price_counter;
			    float averageDJET = DJET_sum_ticket_price/ticket_price_counter;
			    
			    sOut("| ------- | ------------------ |", 129, 2);
			    sOut("| *     * |    Ticket Prices   |", 129, 3);
			    sOut("|   * *   | ------------------ |", 129, 4);
			    sOut("| *     * |   DEHY   |  DJET   |", 129, 5);
			    sOut("| ------- | ------------------ |", 129, 6);
			    sOut("| Average |   "+averageDHY+"   |  "+averageDJET+"   |", 129, 7);
			    sOut("| ------- | ------------------ |", 129, 8);
			    sOut("| Sum     |   "+DEHY_sum_ticket_price+"   |  "+DJET_sum_ticket_price+"   |", 129, 9);
			    sOut("| ------- | ------------------ |", 129, 10);
			 
			    float business = average_ticket_price_business/ticket_price_counter;
			    float economy = average_ticket_price_economy/ticket_price_counter;
			    
			    sOut("| ----------------------------------------------------------------- |", 77, 13);
			    sOut("|                                         |  Business  |  Economy   |", 77, 14);
			    sOut("| ----------------------------------------------------------------- |", 77, 15);
			    sOut("| Average ticket price of all passengers  | "+business+"  | "+economy+"  |", 77, 16);
			    sOut("| ----------------------------------------------------------------- |", 77, 17);
			    
			    
			results = true; 
			}
		 
		 }
		  
		 } 

		 public static void clear_for_end()
		 {
			 for(int a=1;a<17;a++)
			 {
				 sOut("                                                                                                                                                                           ", 2, a);
			 }
			 
		 }
			 
			
	 
	
	 
	 public static void show_screen()
	 {
		 cn.getTextWindow().setCursorPosition(2, 1);
		 cn.getTextWindow().output(" Airfield Control\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tCurrent Time : 0  ");
		 cn.getTextWindow().setCursorPosition(2, 2);
		 cn.getTextWindow().output("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		 cn.getTextWindow().setCursorPosition(2, 3);
		 cn.getTextWindow().output("Waiting Departures\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWaiting Arrivals ");
		 cn.getTextWindow().setCursorPosition(2, 4);
		 cn.getTextWindow().output("------------------------\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t----------------------- ");
		 cn.getTextWindow().setCursorPosition(2, 5);
		 cn.getTextWindow().output("| Company | From      | To        | Flight Number | Departure Time | Service Time | *** | Company | From      | To        | Flight Number | Arrival Time   | Service Time |");
		 cn.getTextWindow().setCursorPosition(2, 6);
		 cn.getTextWindow().output("|---------------------------------------------------------------------------------| *** |---------------------------------------------------------------------------------|");
		 cn.getTextWindow().setCursorPosition(2, 7);
		 cn.getTextWindow().output("|         |           |           |               |                |              | *** |         |           |           |               |                |              |");
		 cn.getTextWindow().setCursorPosition(2, 8);
		 cn.getTextWindow().output("|---------|-----------|-----------|---------------|----------------|--------------| *** |---------|-----------|-----------|---------------|----------------|--------------|");
		 cn.getTextWindow().setCursorPosition(2, 9);
		 cn.getTextWindow().output("|         |           |           |               |                |              | *** |         |           |           |               |                |              |");
		 cn.getTextWindow().setCursorPosition(2, 10);
		 cn.getTextWindow().output("|---------|-----------|-----------|---------------|----------------|--------------| *** |---------|-----------|-----------|---------------|----------------|--------------|");
		 cn.getTextWindow().setCursorPosition(2, 11);
		 cn.getTextWindow().output("|         |           |           |               |                |              | *** |         |           |           |               |                |              |");
		 cn.getTextWindow().setCursorPosition(2, 12);
		 cn.getTextWindow().output("|---------|-----------|-----------|---------------|----------------|--------------| *** |---------|-----------|-----------|---------------|----------------|--------------|");
		 cn.getTextWindow().setCursorPosition(2, 13);
		 cn.getTextWindow().output("|         |           |           |               |                |              | *** |         |           |           |               |                |              |");
		 cn.getTextWindow().setCursorPosition(2, 14);
		 cn.getTextWindow().output("|---------|-----------|-----------|---------------|----------------|--------------| *** |---------|-----------|-----------|---------------|----------------|--------------|");
		 cn.getTextWindow().setCursorPosition(2, 15);
		 cn.getTextWindow().output("|         |           |           |               |                |              | *** |         |           |           |               |                |              |");
		 cn.getTextWindow().setCursorPosition(2, 16);
		 cn.getTextWindow().output("|---------|-----------|-----------|---------------|----------------|--------------| *** |---------|-----------|-----------|---------------|----------------|--------------|");
		 
		 
		 cn.getTextWindow().setCursorPosition(135, 19);
		 cn.getTextWindow().output("Passengers of flight : ");

		 
		 cn.getTextWindow().setCursorPosition(2, 21);
		 cn.getTextWindow().output("| Type | Company | From       | To         | Flight Number | Arrival/Departure Time | Finishing Time | Waiting Time | *** | Seat No | Name                      | Luggage |");
		 cn.getTextWindow().setCursorPosition(2, 22);
		 cn.getTextWindow().output("|-------------------------------------------------------------------------------------------------------------------| *** |-----------------------------------------------|");
		 cn.getTextWindow().setCursorPosition(2, 23);
		 cn.getTextWindow().output("|      |         |            |            |               |                        |                |              | *** |         |                           |         |");
		 cn.getTextWindow().setCursorPosition(2, 24);
		 cn.getTextWindow().output("|------|---------|------------|------------|---------------|------------------------|----------------|--------------| *** |---------|---------------------------|---------|");
		 cn.getTextWindow().setCursorPosition(2, 25);
		 cn.getTextWindow().output("|      |         |            |            |               |                        |                |              | *** |         |                           |         |");
		 cn.getTextWindow().setCursorPosition(2, 26);
		 cn.getTextWindow().output("|------|---------|------------|------------|---------------|------------------------|----------------|--------------| *** |---------|---------------------------|---------|");
		 cn.getTextWindow().setCursorPosition(2, 27);
		 cn.getTextWindow().output("|      |         |            |            |               |                        |                |              | *** |         |                           |         |");
		 cn.getTextWindow().setCursorPosition(2, 28);
		 cn.getTextWindow().output("|------|---------|------------|------------|---------------|------------------------|----------------|--------------| *** |---------|---------------------------|---------|");
		 cn.getTextWindow().setCursorPosition(2, 29);
		 cn.getTextWindow().output("|      |         |            |            |               |                        |                |              | *** |         |                           |         |");
		 cn.getTextWindow().setCursorPosition(2, 30);
		 cn.getTextWindow().output("|------|---------|------------|------------|---------------|------------------------|----------------|--------------| *** |---------|---------------------------|---------|");
		 cn.getTextWindow().setCursorPosition(2, 31);
		 cn.getTextWindow().output("|      |         |            |            |               |                        |                |              | *** |         |                           |         |");
		 cn.getTextWindow().setCursorPosition(2, 32);
		 cn.getTextWindow().output("|------|---------|------------|------------|---------------|------------------------|----------------|--------------| *** |---------|---------------------------|---------|");
		 cn.getTextWindow().setCursorPosition(2, 33);
		 cn.getTextWindow().output("|      |         |            |            |               |                        |                |              | *** |         |                           |         |");
		 cn.getTextWindow().setCursorPosition(2, 34);
		 cn.getTextWindow().output("|------|---------|------------|------------|---------------|------------------------|----------------|--------------| *** |---------|---------------------------|---------|");
		 cn.getTextWindow().setCursorPosition(2, 35);
		 cn.getTextWindow().output("|      |         |            |            |               |                        |                |              | *** |         |                           |         |");
		 cn.getTextWindow().setCursorPosition(2, 36);
		 cn.getTextWindow().output("|------|---------|------------|------------|---------------|------------------------|----------------|--------------| *** |---------|---------------------------|---------|");
		 cn.getTextWindow().setCursorPosition(2, 37);
		 cn.getTextWindow().output("|      |         |            |            |               |                        |                |              | *** |         |                           |         |");
		 cn.getTextWindow().setCursorPosition(2, 38);
		 cn.getTextWindow().output("|------|---------|------------|------------|---------------|------------------------|----------------|--------------| *** |---------|---------------------------|---------|");

		 
	 }
	 

	 public static void add_waiting_departure(String Company,String From,String To,String FlightNo,String DepTime,String ServiceTime)
	 {
		 sOut(Company,4,i);
		 sOut(From,14, i);
		 sOut(To,26,i);
		 sOut(FlightNo,38,i);
		 sOut(DepTime,54, i);
		 sOut(ServiceTime,71,i);
		 
		 i+=2;		 
	 }
	 
	 public static void clear_waiting_departure()
	 {
		 i=7;
		 
		 sOut("       ",4,7);
		 sOut("         ",14, 7);
		 sOut("         ",26,7);
		 sOut("             ",38,7);
		 sOut("              ",54, 7);
		 sOut("            ",71,7);
		 
		 Queue a = new Queue(10);
		 while(!temp_departure_planes.isEmpty())
		 {
			 Airplane x = (Airplane) temp_departure_planes.peek();
			 a.enqueue(temp_departure_planes.dequeue());
			 add_waiting_departure(x.getJourney().getCompany(), x.getJourney().getFrom(), x.getJourney().getTo(), x.getJourney().getFlight_number(), Integer.toString(x.getJourney().getArrival_departure_time()), Integer.toString(x.getJourney().getService_time()));
		 }
		 
		 sOut("       ",4,i);
		 sOut("         ",14, i);
		 sOut("         ",26,i);
		 sOut("             ",38,i);
		 sOut("              ",54, i);
		 sOut("            ",71,i);
		 
		 
		 
		 while(!a.isEmpty())
		 {
			 temp_departure_planes.enqueue(a.dequeue());
		 }
		 
	 } 
	 
	 
	 
	 public static void add_waiting_arrival(String Company,String From,String To,String FlightNo,String DepTime,String ServiceTime)
	 {
		 sOut(Company,92,j);
		 sOut(From,102, j);
		 sOut(To,114,j);
		 sOut(FlightNo,126,j);
		 sOut(DepTime,142, j);
		 sOut(ServiceTime,159,j);
		 

		 j+=2;
		 
	 }
	 
	 public static void clear_waiting_arrival()
	 {
	
		 j=7;
		 
		 sOut("       ",92,7);
		 sOut("         ",102, 7);
		 sOut("         ",114,7);
		 sOut("             ",126,7);
		 sOut("              ",142, 7);
		 sOut("            ",159,7);
		 
		 Queue a = new Queue(10);
		 while(!temp_arrival_planes.isEmpty())
		 {
			 Airplane x = (Airplane) temp_arrival_planes.peek();
			 a.enqueue(temp_arrival_planes.dequeue());
			 add_waiting_arrival(x.getJourney().getCompany(), x.getJourney().getFrom(), x.getJourney().getTo(), x.getJourney().getFlight_number(), Integer.toString(x.getJourney().getArrival_departure_time()), Integer.toString(x.getJourney().getService_time()));
		 }
		 
		 sOut("       ",92,j);
		 sOut("         ",102, j);
		 sOut("         ",114,j);
		 sOut("             ",126,j);
		 sOut("              ",142, j);
		 sOut("            ",159,j);
		 
		 
		 
		 while(!a.isEmpty())
		 {
			 temp_arrival_planes.enqueue(a.dequeue());
		 }
			 
		 
	 } 
	 
	 
	 public static void add_information(char Type,String Company,String From,String To,String FlightNo,int ADTime,int FinishingTime,int WaitingTime)
	 {
		 sOut(Character.toString(Type),4,k);
		 sOut(Company,11, k);
		 sOut(From,21,k);
		 sOut(To,34,k);
		 sOut(FlightNo,47, k);
		 sOut(Integer.toString(ADTime),63,k);
		 sOut(Integer.toString(FinishingTime),88,k);
		 sOut(Integer.toString(WaitingTime),105, k);
		 	 
		 k+=2;
		 
	 }
	 
	 public static void clear_information()
	 {
		 k-=2;
		 
		 sOut("    ",4,k);
		 sOut("       ",11, k);
		 sOut("          ",21,k);
		 sOut("          ",34,k);
		 sOut("             ",47, k);
		 sOut("                      ",63,k);
		 sOut("              ",88, k);
		 sOut("            ",105,k);
		 	 
	 } 
	 
	 
	 public static void add_passenger(String SeatNo,String Name,int Luggage)
	 {
		 
		 sOut(SeatNo,126,m);
		 sOut(Name,136, m);
		 sOut(Integer.toString(Luggage),164,m);
		 
		 m+=2;
		 
	 }
	 
	 public static void clear_passenger()
	 {
		 m-=2;
		 
		 sOut("       ",126,m);
		 sOut("                         ",136, m);
		 sOut("       ",164,m);
		 
	 } 
	 
	 public static void update_passOfFlight(String passenger)
	 {
		 sOut("          ",158,19);
		 sOut(passenger,158,19);
	 }
	 
	 public static void update_time(int time)
	 {
		 sOut("          ",126,1);
		 sOut(Integer.toString(time),126,1);
	 }
	  
	 
	 public static void sOut(String s,int x,int y)
	 {
		 cn.getTextWindow().setCursorPosition(x, y);
		 cn.getTextWindow().output(s);
	 }
	 
	 
}




