import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class Empresa {
	private String pathEntry;
	private String pathSave;
	private Persona[] empleados;
	
		
	public Empresa(String path) {
		this.pathEntry=path;
		this.empleados=new Persona[this.cuentaEmpleados()];
		
	}
	
	//Con el siguiente método se obtendrán los resultados en un archivo csv de una "empresa" (archivo con muchas líneas de empleados)
	public void generaReporte() {
		try {
			BufferedReader bf=new BufferedReader(new FileReader(this.pathEntry));
			PrintWriter pw=new PrintWriter(new FileWriter("C:\\Users\\alber\\Documents\\Proyecto_MT_files\\results.csv"));
			String line;
			int c=0;
			pw.println("Nombre,RFC,Sueldo mensual,Ingreso anual,Aguinaldo,Aguinaldo exento,Aguinaldo gravado,Prima vacacional,Prima Vacacional exenta,Prima vacacional gravada,"
					+ "Total ingresos gravados,Medicos y hospitales,Gastos funerarios,SGMM,Hipotecarios,Donativos,Subcuenta retiro,Transporte escolar,"
					+ "Nivel educativo,Maximo a deducir colegiatura,Colegiatura pagada,Total deducciones (sin retiro),Deduccion permitida 10%,Monto ISR,"
					+ "Cuota fija,Porcentaje excedente,Pago excedente,Total a pagar");
						
			while((line=bf.readLine())!=null) {
				this.empleados[c]=new Persona(line.split(","));
				pw.println(this.empleados[c++]);
			}
			
			JOptionPane.showMessageDialog(null, "Tu archivo se ha creado exitosamente");
			pw.close();
			bf.close();			
		}catch(FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo entrada");
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(null, "Error al leer el archivo");
		}
	}
	
	private int cuentaEmpleados() {
		int c=0;

		try {
			BufferedReader bf=new BufferedReader(new FileReader(this.pathEntry));
			String line;
						
			while((line=bf.readLine())!=null) {
				c++;
			}
			bf.close();
			
			
		}catch(FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo");
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(null, "Error al leer el archivo");
		}
		return c;
	}
	

	
	
}
