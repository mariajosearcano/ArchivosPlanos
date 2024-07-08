package logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import bean.*;
import utilidad.*;

public class SecuencialA {

	public ArrayList<String> registros = new ArrayList<>();
	private TratamientoArchivo t = new TratamientoArchivo();
	private String file = "./datos/SecuencialA.txt";
	
	public SecuencialA() throws IOException {
		TratamientoArchivo t = new TratamientoArchivo();
		
		if(t.existe(file))
			registros = archivoAarrayList();
	}
	
	public void Insertar(String s) throws IOException {
		FileWriter archivo = new FileWriter(file, true);
		BufferedWriter escribir = new BufferedWriter(archivo);
		PrintWriter linea = new PrintWriter(escribir);
		
		if(!t.existe(file))
			linea.append(s);
		else
			linea.append("\n" + s);
		
		linea.close();
		escribir.close();
		archivo.close();
	}
	
	public void Modificar(String s, int indice) throws IOException {
	    FileWriter archivo = new FileWriter(file);
	    BufferedWriter escribir = new BufferedWriter(archivo);
	    PrintWriter linea = new PrintWriter(escribir);
	    
	    for (int i = 0; i < registros.size(); i++) {
	        if (i == indice) {
	            registros.set(i, s);
	            
	            if(!t.existe(file))
	    			linea.append(s);
	    		else
	    			linea.append("\n" + s);
	        } else {
	        	if(!t.existe(file))
	        		linea.append(registros.get(i));
	    		else
	    			linea.append("\n" + registros.get(i));
	        }
	        
	        linea.flush();
	    }
	    
	    linea.close();
	    escribir.close();
	    archivo.close();
	}

	public void Eliminar(String s) throws IOException {
		FileWriter archivo = new FileWriter(file, true);
		BufferedWriter escribir = new BufferedWriter(archivo);
		PrintWriter linea = new PrintWriter(escribir);
		
		registros.remove(s);
		
		for(String sAuxiliar : registros)
			linea.append(sAuxiliar);
		
		linea.close();
		archivo.close();
	}
	
	public String Buscar(int buscar) {
		for(String s : registros) {
			if(Integer.parseInt(s.split(",")[0]) == buscar)
				return s;
		}
		
		return null;
	}
	
	public void Listar() {
		for(String s : registros)
			System.out.println(s);
	}
	
	public ArrayList<String> archivoAarrayList() throws IOException {
		FileReader archivo = new FileReader(file);
		BufferedReader leer = new BufferedReader(archivo);
		String s = "";
		
		while((s=leer.readLine()) != null)
			registros.add(s);
		
		archivo.close();
		
		return registros;
	}
	
	public int validarCedulanueva(String t){ //no exista
		int cedula = 0;
		Scanner datos = new Scanner(System.in);
			
		do {
			Iterator<String> apuntador = registros.iterator();
			
			System.out.println(t);
			cedula = datos.nextInt();
			datos.nextLine();
			
			while(apuntador.hasNext()) {
				String s = apuntador.next();
				
				if(Integer.parseInt(s.split(",")[0])==cedula || cedula<=0) {
					System.out.println("Ingrese un codigo no existente o diferente de cero o no negativo");
					
					cedula = -1;
					
					break;
				}
			}
		} while(cedula == -1);
		
		return cedula;
    }
	
	public int validarCedulavieja(String t){ //exista
		int cedula = 0;
		Scanner datos = new Scanner(System.in);

		do {
			Iterator<String> apuntadorLista = registros.iterator();
			
			System.out.println(t);
			cedula = datos.nextInt();
			datos.nextLine();
			
			while(apuntadorLista.hasNext()) {
				String s = apuntadorLista.next();
				
				if (Integer.parseInt(s.split(",")[0]) == cedula) 
			        break;

			    if (!apuntadorLista.hasNext()) {
			        System.out.println("La cedula no existe, inserte otra");
			        
			        cedula = -1;
			    }
			}
		} while(cedula == -1);
		
		return cedula;
    }
	
	/*public void Modificar(String s, int indice) throws IOException {
		FileWriter archivo = new FileWriter(file, true);
		BufferedWriter escribir = new BufferedWriter(archivo);
		PrintWriter linea = new PrintWriter(escribir);
		int c = 0;
		
		for(String sAuxiliar : registros) {
			if(c == indice) {
				registros.add(indice, s);
				registros.remove(indice + 1);
				linea.append(s);
			} else
				linea.append(sAuxiliar);
		
			c++;
		}
		
		linea.close();
		archivo.close();
	}*/
	
	
}
