package logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import bean.Usuario;
import utilidad.TratamientoArchivo;

public class SecuencialF {
	
	private TratamientoArchivo t = new TratamientoArchivo();
	private String oldFile = "./datos/SecuencialF.txt";
	private String newFile = "./datos/SecuencialFAuxiliar.txt";

	public void Insertar(String s) throws IOException {
		FileWriter crear = new FileWriter(oldFile, true);
		BufferedWriter escribir = new BufferedWriter(crear);
		PrintWriter linea = new PrintWriter(escribir);
		
		if(!t.existe(oldFile))
			linea.append(s);
		else
			linea.append("\n" + s);
		
		linea.close();
		escribir.close();
		crear.close();
	}
	
	public void Modificar(String s, int modificar) throws IOException {
		FileReader archivo = new FileReader(oldFile);
		BufferedReader leer = new BufferedReader(archivo);
		FileWriter archivoAuxiliar = new FileWriter(newFile);
		BufferedWriter escribir = new BufferedWriter(archivoAuxiliar);
		PrintWriter linea = new PrintWriter(escribir);
		String sAuxiliar = "";
		
		while((sAuxiliar=leer.readLine()) != null) {
			int cedula = Integer.parseInt(sAuxiliar.split(",")[0]);
				
			if(cedula == modificar)
				sAuxiliar = s;
				
			if(!t.existe(newFile))
				linea.append(sAuxiliar);
			else
				linea.append("\n" + sAuxiliar);
			
			linea.flush();
		}
			
		linea.close();
		escribir.close();
		archivoAuxiliar.close();
		leer.close();
		archivo.close();
	}
	
	public void Eliminar(String s) throws IOException {
		FileReader archivo = new FileReader(oldFile);
		BufferedReader leer = new BufferedReader(archivo);
		FileWriter archivoAuxiliar = new FileWriter(newFile);
		BufferedWriter escribir = new BufferedWriter(archivoAuxiliar);
		PrintWriter linea = new PrintWriter(escribir);
		String sAuxiliar = "";
		
		while((sAuxiliar=leer.readLine()) != null) {
			if(!sAuxiliar.equals(s)) {
				if(!t.existe(newFile))
					linea.append(sAuxiliar);
				else
					linea.append("\n" + sAuxiliar);
				
				linea.flush();
			}
		}
		
		linea.close();
		archivo.close();
		archivoAuxiliar.close();
	}
	
	public String Buscar(int buscar) throws IOException {
		FileReader archivo = new FileReader(oldFile);
		BufferedReader leer = new BufferedReader(archivo);
		String s = "";
		
		while((s=leer.readLine()) != null) {
			int cedula = Integer.parseInt(s.split(",")[0]);
			
			if(cedula == buscar) {
				archivo.close();
				
				return s;
			}
		}
		
		archivo.close();
		
		return null;
	}
	
	public void Listar(String d) throws IOException {
		FileReader archivo = new FileReader(d);
		BufferedReader leer = new BufferedReader(archivo);
		String s = "";
		String[] registros = new String[100];
		
		while ((s=leer.readLine()) != null)
				System.out.println(s);
		
		archivo.close();
	}
	
	public int validarCedulanueva(String t) throws NumberFormatException, IOException {
	    Scanner datos = new Scanner(System.in);
	    int cedula = 0;
	    String s = "";
	    
	    do {
	        FileReader archivo = new FileReader(oldFile);
	        BufferedReader leer = new BufferedReader(archivo);

	        System.out.println(t);
	        cedula = datos.nextInt();
	        datos.nextLine();

	        while ((s = leer.readLine()) != null) {
	            if (Integer.parseInt(s.split(",")[0])==cedula || cedula<=0) {
	                System.out.println("Ingrese un codigo no existente o diferente de cero o no negativo");
	                cedula = -1;
	                break;
	            }
	        }

	        archivo.close();
	        
	    } while (cedula == -1);

	    return cedula;
	}

	
	public int validarCedulavieja(String t) throws IOException{ //exista
		int cedula = 0;
		Scanner datos = new Scanner(System.in);
		String s = "";

		do {
			FileReader archivo = new FileReader(oldFile);
			BufferedReader leer = new BufferedReader(archivo);
			
			System.out.println(t);
			cedula = datos.nextInt();
			datos.nextLine();
			
			while((s=leer.readLine()) != null) {
				if (Integer.parseInt(s.split(",")[0]) == cedula) 
			        break;

			    if (!leer.ready()) {
			        System.out.println("El código no existe, inserte otro");
			        
			        cedula = -1;
			    }
			}
		} while(cedula == -1);
		
		return cedula;
    }
	
	/*public void Insertar(Usuario usuario) throws IOException {
		FileWriter crear = new FileWriter(oldFile, true);
		BufferedWriter escribir = new BufferedWriter(crear);
		PrintWriter linea = new PrintWriter(escribir);
		
		linea.append(usuario.getCedula() + "," + usuario.getNombre() + "\n");
		linea.close();
	}
	
	public void Modificar(Usuario usuario, int modificar) throws IOException { //archivo guardado en un auxiliar desde por alla en vista y eliminado original
		FileReader archivoAuxiliar = new FileReader(oldFile);
		BufferedReader leer = new BufferedReader(archivoAuxiliar);
		FileWriter archivo = new FileWriter(newFile);
		BufferedWriter escribir = new BufferedWriter(archivo);
		PrintWriter linea = new PrintWriter(escribir);
		String s = "";
		
		while((s=leer.readLine()) != null) {
			int cedula = Integer.parseInt(s.split(",")[0]);
				
			if(cedula == modificar)
				s = usuario.getCedula() + "," + usuario.getNombre();
				
			linea.append(s + "\n");
		}
			
		linea.close();
		archivoAuxiliar.close();
		archivo.close();
	}
	
	public void Eliminar(int eliminar) throws IOException {
		FileReader archivoAuxiliar = new FileReader(oldFile);
		BufferedReader leer = new BufferedReader(archivoAuxiliar);
		FileWriter archivo = new FileWriter(newFile);
		BufferedWriter escribir = new BufferedWriter(archivo);
		PrintWriter linea = new PrintWriter(escribir);
		String s = "";
		
		while((s=leer.readLine()) != null) {
			int cedula = Integer.parseInt(s.split(",")[0]);
				
			if(cedula != eliminar)
				linea.append(s + "\n");
		}
		
		linea.close();
		archivoAuxiliar.close();
		archivo.close();
	}
	
	public Usuario Buscar(int buscar) throws IOException {
		FileReader archivo = new FileReader(oldFile);
		BufferedReader leer = new BufferedReader(archivo);
		String s = "";
		
		while((s=leer.readLine()) != null) {
			int cedula = Integer.parseInt(s.split(",")[0]);
			
			if(cedula == buscar) {
				archivo.close();
				
				return new Usuario(cedula, s.split(",")[1]);
			}
		}
		
		archivo.close();
		
		return null;
	}
	
	public void Listar() throws IOException {
		FileReader archivo = new FileReader(oldFile);
		BufferedReader leer = new BufferedReader(archivo);
		String s = "";
		
		while ((s=leer.readLine()) != null) {
				System.out.println(s);
		}
		
		archivo.close();
	}
	
	public int validarCedulanueva(String t) throws NumberFormatException, IOException{ //no exista
		int codigo = 0;
		Scanner datos = new Scanner(System.in);
		FileReader archivo = new FileReader(oldFile);
		String s = "";
			
		do {
			BufferedReader leer = new BufferedReader(archivo);
			
			System.out.println(t);
			codigo = datos.nextInt();
			datos.nextLine();
			
			while((s=leer.readLine()) != null) {
				if(Integer.parseInt(s.split(",")[0])==codigo || codigo<=0) {
					System.out.println("Ingrese un codigo no existente o diferente de cero o no negativo");
					
					codigo = -1;
					
					break;
				}
			}
		} while(codigo == -1);
		
		return codigo;
    }
	
	public int validarCedulavieja(String t) throws IOException{ //exista
		int codigo = 0;
		Scanner datos = new Scanner(System.in);
		FileReader archivo = new FileReader(oldFile);
		String s = "";

		do {
			BufferedReader leer = new BufferedReader(archivo);
			
			System.out.println(t);
			codigo = datos.nextInt();
			datos.nextLine();
			
			while((s=leer.readLine()) != null) {
				if (Integer.parseInt(s.split(",")[0]) == codigo) 
			        break;

			    if (!leer.ready()) {
			        System.out.println("El código no existe, inserte otro");
			        
			        codigo = -1;
			    }
			}
		} while(codigo == -1);
		
		return codigo;
    }
	
	
	/*public void Modificar(int modificar) {
		Scanner datos = new Scanner(System.in);
		boolean b = false;
		
		try
		{
			ArrayList<String> registrosEmpleados = archivoAarrayList();
			FileWriter archivo = new FileWriter("./datos/ArchivoSecuencial.txt");
			BufferedWriter escribir = new BufferedWriter(archivo);
			PrintWriter lineaEmpleado = new PrintWriter(escribir);
			Iterator<String> apuntadorListaEmpleados = registrosEmpleados.iterator();
			
			while(apuntadorListaEmpleados.hasNext())
			{
				String itemApuntador = apuntadorListaEmpleados.next();
				int wcedula = Integer.parseInt(itemApuntador.split(",")[0]);
				
				if(wcedula == modificar)
				{
					System.out.println("¿Desea modificar cedula? 1.Si 2.No: ");
					int r = datos.nextInt();
					datos.nextLine();
					if (r == 1)
					{
						System.out.println("Ingrese nueva cedula: ");
						wcedula = datos.nextInt();
						datos.nextLine();
						
						itemApuntador = wcedula + "," + itemApuntador.split(",")[1] + "," + itemApuntador.split(",")[2];
					}
					
					System.out.println("¿Desea modificar nombre? 1.Si 2.No: ");
					r = datos.nextInt();
					datos.nextLine();
					if (r == 1)
					{
						System.out.println("Ingrese nuevo nombre: ");
						String nombre = datos.nextLine();
						
						itemApuntador = itemApuntador.split(",")[0] + "," + nombre + "," + itemApuntador.split(",")[2];
					}
					
					LocalDate fecha = LocalDate.now();
					System.out.println("¿Desea modificar la fecha? 1.Si 2.No: ");
					r = datos.nextInt();
					datos.nextLine();
					if (r == 1)
					{
						System.out.println("Ingrese nueva fecha (AAAA-MM-DD): ");
						fecha = LocalDate.parse(datos.next());
						datos.nextLine();
						
						itemApuntador = itemApuntador.split(",")[0]+ "," + itemApuntador.split(",")[1] + "," + fecha;
					}
					
					b = true;
				}

				lineaEmpleado.append(itemApuntador + "\n");
			}
			
			lineaEmpleado.close();
			archivo.close();
			
			if (!b)
			{
				System.out.println("No se encontro la cedula del empleado al cual modificar datos");
			}
		}
		catch(IOException e) 
		{
			e.printStackTrace();
			System.out.println("Ocurrio un error");
		}
	}*/
	
	/*public void Eliminar(int eliminar) {
		boolean b = false;

		try
		{
			ArrayList<String> registrosEmpleados = archivoAarrayList();
			FileWriter archivo = new FileWriter("./datos/ArchivoSecuencial.txt");
			BufferedWriter escribir = new BufferedWriter(archivo);
			PrintWriter lineaEmpleado = new PrintWriter(escribir);
			Iterator<String> apuntadorListaEmpleados = registrosEmpleados.iterator();
			
			while(apuntadorListaEmpleados.hasNext())
			{
				String itemApuntador = apuntadorListaEmpleados.next();
				int wcedula = Integer.parseInt(itemApuntador.split(",")[0]);
				
				if(wcedula != eliminar)
				{
					lineaEmpleado.append(itemApuntador + "\n");
				}
				else
				{
					b = true;
				}
			}
			
			lineaEmpleado.close();
			archivo.close();
			
			if (!b)
			{
				System.out.println("No se encontro la cedula del empleado el cual eliminar");
			}
		}
		catch(IOException e) 
		{
			e.printStackTrace();
			System.out.println("Ocurrio un error");
		}

	}
	
	public void Consultar(int ced) {
		boolean b = false;
		ArrayList<String> registrosEmpleados = archivoAarrayList();
		Iterator<String> apuntadorListaEmpleados = registrosEmpleados.iterator();
		
		while(apuntadorListaEmpleados.hasNext())
		{
			String itemApuntador = apuntadorListaEmpleados.next();
			int wcedula = Integer.parseInt(itemApuntador.split(",")[0]);
			
			if(wcedula == ced)
			{
				System.out.println(itemApuntador);
				
				b = true;
			}
		}
		
		if (!b)
		{
			System.out.println("No se encontro la cedula del empleado");
		}
	}*/
	
	/*public void Listar() {
		String linea = "";
		
		try
		{
			FileReader archivo = new FileReader("src/Datos/ArchivoSecuencial.txt");
			BufferedReader bufferdatos = new BufferedReader(archivo);
			
			while (linea != null)
			{
				linea = bufferdatos.readLine();
				
				if(linea != null)
				{
					System.out.println(linea);
				}
			}
			
			archivo.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("Ocurrio un error");
		}
	}*/
	
	/*public ArrayList<String> archivoAarrayList() {
		ArrayList<String> registrosEmpleados = new ArrayList<String>();
		String linea = "";
		
		try
		{
			FileReader datosLeidos = new FileReader("./datos/ArchivoSecuencial.txt");
			BufferedReader bufferdatos = new BufferedReader(datosLeidos);
			
			while (linea != null)
			{
				linea = bufferdatos.readLine();
				
				if(linea != null)
				{
					registrosEmpleados.add(linea);
				}
			}
			
			datosLeidos.close();
		}
		catch(IOException e) 
		{
			e.printStackTrace();
			System.out.println("Ocurrio un error");
		}
		
		return registrosEmpleados;
	}*/
	
	
}
