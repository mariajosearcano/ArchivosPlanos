package utilidad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;

import bean.Usuario;

public class TratamientoArchivo {

	public boolean existe(String t) {
		boolean b = false;
		File f = new File(t);
		
		if (f.exists() && f.length()!=0) 
			b = true;

		return b;
	}
	
	public void eliminarArchivo(String d) {
		File f = new File(d);
		
		if (!f.delete())
			System.out.println("El fichero no se pudo borrar");
	}
	
	public void renombrarArchivo(String dOld, String dNew) {
		File newFile = new File(dOld);
		File oldFile = new File(dNew);
		
		if (!oldFile.renameTo(newFile)) 
			System.out.println("Error, puede que halla un archivo con el mismo nombre");
	}
	
	public int numeroRegistrossecuencial(String d) throws IOException {
		FileReader archivo = new FileReader(d);
		BufferedReader leer = new BufferedReader(archivo);
		String s = "";
		int c = 0;
		
		while ((s=leer.readLine()) != null) {
				c++;
		}
		
		archivo.close();
		
		return c;
	}
	
	public int numeroRegistrosaleatorio(String d) throws IOException {
		RandomAccessFile archivo = new RandomAccessFile(d,"r");
		int c = 0;
		
		archivo.seek(0);
		
		while(archivo.getFilePointer() < archivo.length()) {
			archivo.readInt();
			archivo.readUTF();
			
			c++;
		}
		
		archivo.close();
		
		return c;
	}
	
	
}
