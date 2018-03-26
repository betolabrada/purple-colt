import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Datos {
	private String path;
	
	private String[][] empleados;
	
	public Datos(String path) {
		this.path=path;
		this.empleados=new String[this.getRows()][14];
		String[] tokens = null;
		try {
			BufferedReader bf=new BufferedReader(new FileReader(this.path));
			String line;
			int c=0;
						
			while((line=bf.readLine())!=null) {
				tokens=line.split(",");	
					for(int i=0; i<tokens.length; i++) {
						this.empleados[c][i]=tokens[i];
					}
					c++;
				}
			bf.close();			
		}catch(FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo");
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(null, "Error al leer el archivo");
		}
	}
	
	public int getRows() {
		int c=0;

		try {
			BufferedReader bf=new BufferedReader(new FileReader(this.path));
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
	
	public String[][] getData() {
		return this.empleados;	
	}
	
	//--------Para probar el arreglo--------------------------------------------
	
	public String toString() {
		String aux="";
		for(int j=0; j<this.getData().length; j++) {
			for(int i=0; i<this.getData()[j].length; i++) {
				aux+=this.getData()[j][i]+"\t";
			}
			aux+="\n";
		}
		return aux;
	}
}
