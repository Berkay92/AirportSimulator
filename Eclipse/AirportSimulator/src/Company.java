
public class Company {
	
	private Airplane[] planes;

	
	private String name;

	
	public Company(String company_name)
	{
		name = company_name;
		
		if(company_name.equals("DEHY"))
		{
			planes = new Airplane[4];
			
			planes[1] = new Airplane("AirBus");
			planes[2] = new Airplane("Boeing");
			planes[3] = new Airplane("Boeing");
			
		}
		else if(company_name.equals("DJET"))
		{
			planes = new Airplane[6];
			
			planes[1] = new Airplane("AirBus");
			planes[2] = new Airplane("AirBus");
			planes[3] = new Airplane("Boeing");
			planes[4] = new Airplane("Boeing");
			planes[5] = new Airplane("Boeing");

			
		}
	

		
	}
	


	public Airplane[] getPlanes() {
		return planes;
	}



	public void setPlanes(Airplane[] planes) {
		this.planes = planes;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
