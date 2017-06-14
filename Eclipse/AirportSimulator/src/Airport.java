
public class Airport {
	
	private Company comp_dehy;
	private Company comp_djet;
	boolean runway;
	
	public Airport()
	{
		comp_dehy = new Company("DEHY");
		comp_djet = new Company("DJET");
		runway = true;
	}
	
	
	
	
	
	
	
	
	public void choose_plane()
	{
		
	}
	
	
	
	
	
	
	public boolean isRunway() {
		return runway;
	}


	public void setRunway(boolean runway) {
		this.runway = runway;
	}


	

	public Company getComp_dehy() {
		return comp_dehy;
	}


	public void setComp_dehy(Company comp_dehy) {
		this.comp_dehy = comp_dehy;
	}


	public Company getComp_djet() {
		return comp_djet;
	}


	public void setComp_djet(Company comp_djet) {
		this.comp_djet = comp_djet;
	}
	
}
