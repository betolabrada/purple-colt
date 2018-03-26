
public class Deduccion {
	private double gastosMedicos;
	private double gastosFunerarios;
	private double gmm;
	private double hipoteca;
	private double donativos;
	private double tEscolar;
	private String nivelEscolar;
	private double colegiatura;
	private Persona persona;
	
	public Deduccion(String [] datos) {
		this.gastosMedicos = Double.parseDouble(datos[5]);
		this.gastosFunerarios = Double.parseDouble(datos[6]);
		this.gmm = Double.parseDouble(datos[7]);
		this.hipoteca = Double.parseDouble(datos[8]);
		this.tEscolar = Double.parseDouble(datos[9]);
		this.nivelEscolar = datos[10];
		this.colegiatura = Double.parseDouble(datos[11]);
		this.persona = new Persona(datos);
	}
	
	public double calculoDeducibles() {
		double deducibles = 0.0;
		deducibles = this.gastosMedicos + this.gastosFunerarios + this.gmm + this.hipoteca + this.donativos + this.tEscolar + this.colegiatura;
		if(deducibles > this.persona.getIt() * 0.1) {
			deducibles = this.persona.getIt() * 0.1;
		}
		return deducibles;
	}
	
	public double getGastosMedicos() {
		return this.gastosMedicos;
	}
	
	public double getGastosFunerarios() {
		return this.gastosFunerarios;
	}
	
	public double getGmm() {
		return this.gmm;
	}
	
	public double getHipoteca() {
		return this.hipoteca;
	}
	
	public double getDonativos() {
		return this.donativos;
	}
	
	public double getTEscolar() {
		return this.tEscolar;
	}
	
	public String getNivelEscolar() {
		return this.nivelEscolar;
	}
	
	public double getColegiatura() {
		return this.colegiatura;
	}
	
}
