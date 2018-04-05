import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Persona {
	private String nombre;
	private String rfc;
	private double sueldoMensual;
	private double aguinaldo;
	private double primaVacacional;
	private static final double primaVacacionalExenta=1209.0;	
	
	private Deduccion deduccion;
	
	private double[] filaRango; // 0: limite inferior | 1: limite superior | 2: cuota fija | 3: porcentaje excedente
	
	protected String[] results;
	
	public Persona(String[] datos) {
		this.nombre = datos[0];
		this.rfc = datos[1];
		this.sueldoMensual = Double.parseDouble(datos[2]);
		this.aguinaldo = Double.parseDouble(datos[3]);
		this.primaVacacional = Double.parseDouble(datos[4]);
		
		//----------crea arreglo parametro para clase Deduccion----------------------------------
		
		String[] arregloDeduccion=new String[9];
		for(int i=5; i<datos.length; i++) {
			arregloDeduccion[i-5]=datos[i];
		}
		
		this.deduccion=new Deduccion(arregloDeduccion);
		
		//----------crea arreglo de fila donde se encuentra 
		
		this.filaRango=this.buscaDatosRango();
		
		this.results=new String[28];
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
	
	private double calculaDeduccionPermitida() {
		double subcuentaRetiro=Double.parseDouble(this.deduccion.getDeducciones()[5]);
		double deducciones=Double.parseDouble(this.deduccion.getDeducciones()[10]);
		double ingresos=(this.sueldoMensual*12+this.aguinaldo+this.primaVacacional)*.1;
		if(ingresos<deducciones) {
			return ingresos+subcuentaRetiro;
		}
		return deducciones+subcuentaRetiro;
	}
	
	private double calculaISR() {
		return this.getIngresosTotalesGravados()-this.calculaDeduccionPermitida();
	}
	
	private double[] buscaDatosRango() {
		double[][] tablaCuotas=new double[11][4];
		double[] fila=new double[4];
		try {
			BufferedReader bf = new BufferedReader(new FileReader("Proyecto_MT_files\\tablacuotas3.csv"));
			String line;
			int c=0;
			
			while((line=bf.readLine())!=null) {
				String[] row=line.split(",");
				for(int i=0; i<row.length; i++) {
					tablaCuotas[c][i]=Double.parseDouble(row[i]);
				}
				c++;
			}
			bf.close();		
		}catch(FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "No se ha encontrado archivo de cuotas");
			ex.printStackTrace();
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
			ex.printStackTrace();
		}
		for(int i=0; i<tablaCuotas.length; i++) {
			if(tablaCuotas[i][0]<this.calculaISR() && tablaCuotas[i][1]>=this.calculaISR()) {
				for(int j=0; j<fila.length; j++) {
					fila[j]=tablaCuotas[i][j];
				}
				break;
			}
		}
		return fila;	
	}
	
	private double calculaPagoExcedente() {
		return (this.calculaISR()-this.filaRango[0])*this.filaRango[3]/100;
	}
	
	private double calculaTotalAPagar() {
		return this.filaRango[2]+this.calculaPagoExcedente();
	}
	
	private String[] llenaArreglo() {
		this.results[0]=this.nombre;
		this.results[1]=this.rfc;
		this.results[2]=Double.toString(this.sueldoMensual);
		this.results[3]=Double.toString(this.calculaSueldoAnual());
		this.results[4]=Double.toString(this.aguinaldo);
		this.results[5]=Double.toString(this.calculaAguinaldoExento());
		this.results[6]=Double.toString(this.calculaAguinaldoGravado());
		this.results[7]=Double.toString(this.primaVacacional);
		this.results[8]=Double.toString(primaVacacionalExenta);
		this.results[9]=Double.toString(this.calculaPrimaVacacionalGravada());
		this.results[10]=Double.toString(this.getIngresosTotalesGravados());
		for(int i=0;i<this.deduccion.getDeducciones().length; i++) {
			this.results[i+11]=this.deduccion.getDeducciones()[i];
		}
		this.results[22]=Double.toString(this.calculaDeduccionPermitida());

		this.results[23]=Double.toString(this.calculaISR());
		this.results[24]=Double.toString(this.filaRango[2]);
		this.results[25]=Double.toString(this.filaRango[3]);
		this.results[26]=Double.toString(this.calculaPagoExcedente());
		this.results[27]=Double.toString(this.calculaTotalAPagar());
		
		
		return this.results;
	}
	
	public String toString() {
		String aux="";
		
		for(int i=0; i<this.results.length; i++) {
			aux+=this.results[i]+",";
		}
		return aux;
		
	}

	
	
}
