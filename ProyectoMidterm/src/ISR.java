import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class ISR extends Deduccion{
	
	private final double[][] tablaCuotas;
	
	private double[] fila; // 0: limite inferior | 1: limite superior | 2: cuota fija | 3: porcentaje excedente
	
	
	public ISR(String[] datos) {
		super(datos);
		
		this.tablaCuotas=new double[11][4];
		this.fila=new double[4];
		try {
			BufferedReader bf = new BufferedReader(new FileReader("Proyecto_MT_files\\tablacuotas3.csv"));
			String line;
			int c=0;
			
			while((line=bf.readLine())!=null) {
				String[] row=line.split(",");
				for(int i=0; i<row.length; i++) {
					this.tablaCuotas[c][i]=Double.parseDouble(row[i]);
				}
				c++;
			}
			bf.close();

			
		}catch(FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "No se ha encontrado archivo de cuotas");
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error halloween");
		}
		for(int i=0; i<this.tablaCuotas.length-1; i++) {
			if(this.tablaCuotas[i][0]<this.calculaISR() && this.tablaCuotas[i][1]>=this.calculaISR()) {
				for(int j=0; j<this.fila.length; j++) {
					this.fila[j]=this.tablaCuotas[i][j];
				}
				break;
			}
		}
		int last=this.tablaCuotas.length-1;
		if(this.tablaCuotas[last][0]<this.calculaISR()) {
			this.fila=this.tablaCuotas[last];
		}
		this.results[2]=new String[5];
		this.llenaArreglo();
		
	}
	
	private double calculaISR() {
		return super.getIngresosTotalesGravados()-super.getDeduccionPermitida();
	}
	
	private double calculaPagoExcedente() {
		return (this.calculaISR()-this.fila[0])*this.fila[3]/100;
	}
	
	private double calculaTotalAPagar() {
		return this.fila[2]+this.calculaPagoExcedente();
	}
	
	private String[][] llenaArreglo() {
		this.results[2][0]=Double.toString(this.calculaISR());
		this.results[2][1]=Double.toString(this.fila[2]);
		this.results[2][2]=Double.toString(this.fila[3]);
		this.results[2][3]=Double.toString(this.calculaPagoExcedente());
		this.results[2][4]=Double.toString(this.calculaTotalAPagar());
		
		return this.results;

	}
	
	public String toString() {
		String aux="";
		
		for(int i=0; i<this.results.length; i++) {
			for(int j=0; j<this.results[i].length; j++) {
				aux+=this.results[i][j]+",";
			}
		}
		return aux;
		
	}
	
	

	
	
	
}
