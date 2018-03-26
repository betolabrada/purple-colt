
public class Persona {
	private String nombre;
	private String rfc;
	private double sueldoM;
	private double aguinaldo;
	private double pvr;
	private double it;
	
	private String[] datos;
	
	
	public Persona() {
		this.nombre = datos[0];
		this.rfc = datos[1];
		this.sueldoM = Double.parseDouble(datos[2]);
		this.aguinaldo = Double.parseDouble(datos[3]);
		this.pvr = Double.parseDouble(datos[4]);
	}
	
	public double ingresosTotales() {
		this.it = this.sueldoM + this.aguinaldo + this.pvr;
		return this.it;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getRfc() {
		return rfc;
	}

	public double getSueldoM() {
		return sueldoM;
	}

	public double getAguinaldo() {
		return aguinaldo;
	}

	public double getPvr() {
		return pvr;
	}

	public double getIt() {
		return it;
	}
	
	
}
