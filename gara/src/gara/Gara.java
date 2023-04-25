package gara;

public class Gara {
	private String sport;
	public static String SPORTS []= {"nuoto","corsa","bicicletta"};
	private String posto;
	private String data;
	private int numeroConcorrenti;
	private boolean indoor;
	
	public Gara(String sport, String posto, String data, int numeroConcorrenti, boolean indoor) {
		super();
		this.sport = sport;
		this.posto = posto;
		this.data = data;
		this.numeroConcorrenti = numeroConcorrenti;
		this.indoor = indoor;
	}

	@Override
	public String toString() {
		return "Gara\n [sport=" + sport + ",\n posto=" + posto + ",\n data=" + data + ",\n numeroConcorrenti="
				+ numeroConcorrenti + ",\n indoor=" + indoor + "]";
	}

}
