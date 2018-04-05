
public class Deduccion{
	private double gastosMedicos;
	private double gastosFunerarios;
	private double sgmm;
	private double hipotecarios;
	private double donativos;
	private final double subcuentaRetiro;
	private double transporteEscolar;
	private String nivelEscolar;
	private final double maxDeducirEscuela;
	private double colegiatura;
	
	private String[] deducciones;
		
	public Deduccion(String[] datos) {		
		this.gastosMedicos=Double.parseDouble(datos[0]);
		this.gastosFunerarios=Double.parseDouble(datos[1]);
		this.sgmm=Double.parseDouble(datos[2]);
		this.hipotecarios=Double.parseDouble(datos[3]);
		this.donativos=Double.parseDouble(datos[4]);
		this.subcuentaRetiro=Double.parseDouble(datos[5]);
		this.transporteEscolar=Double.parseDouble(datos[6]);
		this.nivelEscolar=datos[7];
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
		this.colegiatura=Double.parseDouble(datos[8]);	
		this.deducciones=new String[11];
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
	
	private String[] llenaArreglo() {
		this.deducciones[0]=Double.toString(this.gastosMedicos);
		this.deducciones[1]=Double.toString(this.gastosFunerarios);
		this.deducciones[2]=Double.toString(this.sgmm);
		this.deducciones[3]=Double.toString(this.hipotecarios);
		this.deducciones[4]=Double.toString(this.donativos);
		this.deducciones[5]=Double.toString(this.subcuentaRetiro);
		this.deducciones[6]=Double.toString(this.transporteEscolar);
		this.deducciones[7]=this.nivelEscolar;
		this.deducciones[8]=Double.toString(this.maxDeducirEscuela);
		this.deducciones[9]=Double.toString(this.colegiatura);
		this.deducciones[10]=Double.toString(this.calculaTotalDeducciones());
		
		return this.deducciones;
	}

	public String[] getDeducciones() {
		return this.deducciones;
	}
	
	
	
	

	
	
	
}
