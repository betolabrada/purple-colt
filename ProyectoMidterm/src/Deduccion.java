
public class Deduccion extends Persona{
	private double gastosMedicos;
	private double gastosFunerarios;
	private double sgmm;
	private double hipotecarios;
	private double donativos;
	private final double subcuentaRetiro;
	private double transporteEscolar;
	private String nivelEscolar;
	private double maxDeducirEscuela;
	private double colegiatura;
		
	public Deduccion(String[] datos) {
		super(datos);
		
		this.gastosMedicos=Double.parseDouble(datos[5]);
		this.gastosFunerarios=Double.parseDouble(datos[6]);
		this.sgmm=Double.parseDouble(datos[7]);
		this.hipotecarios=Double.parseDouble(datos[8]);
		this.donativos=Double.parseDouble(datos[9]);
		this.subcuentaRetiro=Double.parseDouble(datos[10]);
		this.transporteEscolar=Double.parseDouble(datos[11]);
		this.nivelEscolar=datos[12];
		if(this.nivelEscolar.equalsIgnoreCase("primaria")) {
			this.maxDeducirEscuela=12900.00;
		}
		else if(this.nivelEscolar.equalsIgnoreCase("secundaria")) {
			this.maxDeducirEscuela=19900.00;
		}
		else if(this.nivelEscolar.equalsIgnoreCase("preparatoria")) {
			this.maxDeducirEscuela=24500.00;
		}
		else {
			this.maxDeducirEscuela=0.00;
		}
		this.colegiatura=Double.parseDouble(datos[13]);	
		this.results[1]=new String[12];
		this.llenaArreglo();
		
	}
	
	private double calculaDeducibleColegiatura() {
		if(this.colegiatura>this.maxDeducirEscuela) {
			return this.maxDeducirEscuela;
		}
		return this.colegiatura;
	}
	
	private double calculaTotalDeducciones() {
		return this.gastosMedicos+this.gastosFunerarios+this.sgmm+this.hipotecarios+
				this.donativos+this.transporteEscolar+this.calculaDeducibleColegiatura();
	}
	
	private double calculaDeduccionPermitida() {
		double deducciones=this.calculaTotalDeducciones();
		double ingresos=(super.getSueldoAnual()+super.getAguinaldo()+super.getPrimaVacacional())*.1;
		if(ingresos<deducciones) {
			return ingresos+this.subcuentaRetiro;
		}
		return deducciones+this.subcuentaRetiro;
	}
	
	public double getDeduccionPermitida() {
		return calculaDeduccionPermitida();
	}
	
	private String[][] llenaArreglo() {
		this.results[1][0]=Double.toString(this.gastosMedicos);
		this.results[1][1]=Double.toString(this.gastosFunerarios);
		this.results[1][2]=Double.toString(this.sgmm);
		this.results[1][3]=Double.toString(this.hipotecarios);
		this.results[1][4]=Double.toString(this.donativos);
		this.results[1][5]=Double.toString(this.subcuentaRetiro);
		this.results[1][6]=Double.toString(this.transporteEscolar);
		this.results[1][7]=this.nivelEscolar;
		this.results[1][8]=Double.toString(this.maxDeducirEscuela);
		this.results[1][9]=Double.toString(this.colegiatura);
		this.results[1][10]=Double.toString(this.calculaTotalDeducciones());
		this.results[1][11]=Double.toString(this.calculaDeduccionPermitida());
		
		return this.results;
	}
	
}
