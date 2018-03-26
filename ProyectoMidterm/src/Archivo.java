import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class Archivo {
	private String path;
	
	public Archivo(String path) {
		this.path=path;
	}
	
	public String[] getData() {	
		String[] tokens = null;
		
		try {
			BufferedReader bf=new BufferedReader(new FileReader(this.path));
			String line;
						
			while((line=bf.readLine())!=null) {
				tokens=line.split(",");
			}
			bf.close();
			
			
		}catch(FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo");
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(null, "Error al leer el archivo");
		}
		return tokens;
		
	}
	
	//--------Para probar el arreglo--------------------------------------------
	
	public String toString() {
		String aux="";
		
		for(int i=0; i<this.getData().length; i++) {
			aux+=this.getData()[i]+"\t";
		}
		return aux;
	}
}
