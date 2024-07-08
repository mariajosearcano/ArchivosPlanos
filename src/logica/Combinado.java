package logica;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import bean.*;
import utilidad.*;

public class Combinado {
	
	public void secuencialAaleatorio(String sd) throws IOException {
		FileReader datosLeidos = new FileReader(sd);
		BufferedReader bufferdatos = new BufferedReader(datosLeidos);
		RandomAccessFile datosGuardar = new RandomAccessFile("./datos/secuencialAaleatorio.txt", "rw");
		String s = "";
		
		datosGuardar.seek(0);
		
		s = bufferdatos.readLine();
		
		while (s != null) {
			int cedula = Integer.parseInt(s.split(",")[0]);
			datosGuardar.writeInt(cedula);
			
			String nombre = s.split(",")[1];
			datosGuardar.writeUTF(nombre);
			
			s = bufferdatos.readLine();
		}
		
		datosGuardar.close();
		datosLeidos.close();
	}
	
	public void aleatorioAsecuencial(String ad) throws IOException {
		RandomAccessFile datosLeidos = new RandomAccessFile(ad,"r");
		FileWriter datosGuardar = new FileWriter("./datos/aleatorioAsecuencial.txt");
		BufferedWriter bufferdatos = new BufferedWriter(datosGuardar);
		PrintWriter linea = new PrintWriter(bufferdatos);
		String s = "";
		
		datosLeidos.seek(0);
		
		while(datosLeidos.getFilePointer() < datosLeidos.length())
		{
			s = datosLeidos.readInt() + "," + datosLeidos.readUTF();
			
			linea.append(s);
		}
		
		datosLeidos.close();
		linea.close();
		datosGuardar.close();
	}
	
	
}

