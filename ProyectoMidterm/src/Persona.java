
public class Persona {
	private String nombre;
	private String rfc;
	private double sueldoMensual;
	private double aguinaldo;
	private double primaVacacional;
	private static final double primaVacacionalExenta=1209.0;
	
	protected String[][] results;
	
	public Persona(String[] datos) {
		this.nombre = datos[0];
		this.rfc = datos[1];
		this.sueldoMensual = Double.parseDouble(datos[2]);
		this.aguinaldo = Double.parseDouble(datos[3]);
		this.primaVacacional = Double.parseDouble(datos[4]);
		
		this.results=new String[3][];
		this.results[0]=new String[11];
		this.llenaArreglo();
		
	}
	
	private double calculaSueldoAnual() {
		return this.sueldoMensual*12;
	}
	
	private double calculaAguinaldoExento() {
		return this.sueldoMensual/30*15;
	}
	
	private double calculaAguinaldoGravado() {
		double dias=this.aguinaldo/(this.sueldoMensual/30);
		if(dias>=15) {
			return this.aguinaldo-this.calculaAguinaldoExento();
		}
		return 0.0;
	}
	
	private double calculaPrimaVacacionalGravada() {
		if(this.primaVacacional>=primaVacacionalExenta) {
			return this.primaVacacional-primaVacacionalExenta;
		}
		return 0.0;
	}
	
	public double getIngresosTotalesGravados() {
		return this.calculaSueldoAnual()+this.calculaAguinaldoGravado()
		+this.calculaPrimaVacacionalGravada();
	}
	
	public double getSueldoAnual() {
		return this.calculaSueldoAnual();
	}
	
	public double getAguinaldo() {
		return this.aguinaldo;
	}
	
	public double getPrimaVacacional() {
		return this.primaVacacional;
	}
	
	private String[][] llenaArreglo() {
		this.results[0][0]=this.nombre;
		this.results[0][1]=this.rfc;
		this.results[0][2]=Double.toString(this.sueldoMensual);
		this.results[0][3]=Double.toString(this.calculaSueldoAnual());
		this.results[0][4]=Double.toString(this.aguinaldo);
		this.results[0][5]=Double.toString(this.calculaAguinaldoExento());
		this.results[0][6]=Double.toString(this.calculaAguinaldoGravado());
		this.results[0][7]=Double.toString(this.primaVacacional);
		this.results[0][8]=Double.toString(primaVacacionalExenta);
		this.results[0][9]=Double.toString(this.calculaPrimaVacacionalGravada());
		this.results[0][10]=Double.toString(this.getIngresosTotalesGravados());
		
		return this.results;
	}

	
	
}
