package si.hse.varnost.model;

public enum Vrsta {

	AKTIVNOST("aktivnost"),
	IZMENA("izmena"),
	VM("varnostno mesto"),
	MESTO("mesto dogodka");
	
	private String lepoIme;
	 
	Vrsta(String lepoIme) {
        this.lepoIme = lepoIme;
    }
 
    public String getLepoIme() {
        return lepoIme;
    }
	
}
