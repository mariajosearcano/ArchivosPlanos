package logica;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;

import utilidad.*;
import bean.*;

public class Serializar {

	private TratamientoArchivo t = new TratamientoArchivo();
	
	public void serializarSecuencial(String d) throws IOException {
		FileReader archivo = new FileReader(d);
		BufferedReader leer = new BufferedReader(archivo);
		String s = "";
		String[] registros = new String[t.numeroRegistrossecuencial(d)];
		int i = 0;
		
		while ((s=leer.readLine()) != null) {
			registros[i] = s;
			
			i++;
		}
		
		ObjectOutputStream archivo_salida = new ObjectOutputStream(new FileOutputStream("./datos/SecuencialSerializado.txt"));
		
		archivo.close();
		archivo_salida.writeObject(registros);
		archivo_salida.close();
	}
	
	public void serializarAleatorio(String d) throws IOException {
		RandomAccessFile archivo = new RandomAccessFile(d,"r");
		Usuario[] registros = new Usuario[t.numeroRegistrosaleatorio(d)];
		int i = 0;
		
		archivo.seek(0);
		
		while(archivo.getFilePointer() < archivo.length()) {
			Usuario usuario = new Usuario();
			usuario.setCedula(archivo.readInt());
			usuario.setNombre(archivo.readUTF());
			registros[i] = usuario;
			
			i++;
		}
		
		ObjectOutputStream archivo_salida = new ObjectOutputStream(new FileOutputStream("./datos/AleatorioSerializado.txt"));
		
		archivo.close();
		archivo_salida.writeObject(registros);
		archivo_salida.close();	
	}
	
	public void deserializarSecuencial(String d) throws IOException, ClassNotFoundException {
		ObjectInputStream leer_datos = new ObjectInputStream(new FileInputStream("./datos/SecuencialSerializado.txt"));
		String[] datos_leidos = (String[]) leer_datos.readObject();
		
		leer_datos.close();
		
		for(String s : datos_leidos)
			System.out.println(s);
	}
	
	public void deserializarAleatorio(String d) throws IOException, ClassNotFoundException {
		ObjectInputStream leer_datos = new ObjectInputStream(new FileInputStream("./datos/AleatorioSerializado.txt"));
		Usuario[] datos_leidos = (Usuario[]) leer_datos.readObject();
		
		leer_datos.close();
		
		for(Usuario usuario : datos_leidos)
			System.out.println(usuario);
	}
	
}
