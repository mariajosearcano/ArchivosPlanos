package logica;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import bean.*;
import utilidad.TratamientoArchivo;

public class AleatorioF {
 
	private String oldFile = "./datos/AleatorioF.txt";
	private String newFile = "./datos/AleatorioFAuxiliar.txt";
	
	public void Insertar(Usuario usuario) throws IOException {
		RandomAccessFile archivo = new RandomAccessFile(oldFile,"rw");
		
		archivo.seek(archivo.length());
		archivo.writeInt(usuario.getCedula());
		archivo.writeUTF(usuario.getNombre());
		archivo.close();
	}
	
	public void Modificar(Usuario usuario, int modificar) throws IOException {
		RandomAccessFile archivo = new RandomAccessFile(oldFile,"r");
		RandomAccessFile archivoAuxiliar = new RandomAccessFile(newFile,"rw");
		int cedula = 0;
		
		archivo.seek(0);
		archivoAuxiliar.seek(0);
		
		while(archivo.getFilePointer() < archivo.length()) {
			if((cedula=archivo.readInt()) == modificar) {
				archivoAuxiliar.writeInt(usuario.getCedula());
				archivoAuxiliar.writeUTF(usuario.getNombre());
				archivo.readUTF();
			} else {
				archivoAuxiliar.writeInt(cedula);
				archivoAuxiliar.writeUTF(archivo.readUTF());
			}
		}
		
		archivo.close();
		archivoAuxiliar.close();
	}
	
	public void Eliminar(Usuario usuario) throws IOException {
		RandomAccessFile archivo = new RandomAccessFile(oldFile,"r");
		RandomAccessFile archivoAuxiliar = new RandomAccessFile(newFile,"rw");
		
		archivo.seek(0);
		archivoAuxiliar.seek(0);
		
		while(archivo.getFilePointer() < archivo.length()) {
			int cedula = archivo.readInt();
			String nombre = archivo.readUTF();
					
			if(cedula != usuario.getCedula()) {
				archivoAuxiliar.writeInt(cedula);
				archivoAuxiliar.writeUTF(nombre);
			}
		}
		
		archivo.close();
		archivoAuxiliar.close();
	}
	
	public Usuario Buscar(int buscar) throws IOException {
		RandomAccessFile archivo = new RandomAccessFile(oldFile,"r");
		
		archivo.seek(0);
		
		while(archivo.getFilePointer() < archivo.length()) {
			int cedula = archivo.readInt();
			String nombre = archivo.readUTF();
			
			if(cedula == buscar) {
				archivo.close();
	            return new Usuario(cedula, nombre);
			}
		}
		
		archivo.close();
		
		return null;
	}
	
	public void Listar(String d) throws IOException {
		RandomAccessFile archivo = new RandomAccessFile(d,"r");
		
		archivo.seek(0);
		
		while(archivo.getFilePointer() < archivo.length()) {
			Usuario usuario = new Usuario();
			usuario.setCedula(archivo.readInt());
			usuario.setNombre(archivo.readUTF());
			
			System.out.println(usuario);
		}
		
		archivo.close();
	}
	
	public int validarCedulanueva(String t) throws NumberFormatException, IOException{ //no exista
		int cedula = 0;
		Scanner datos = new Scanner(System.in);
		RandomAccessFile archivo = new RandomAccessFile(oldFile,"r");
			
		do {
			archivo.seek(0);
			
			System.out.println(t);
			cedula = datos.nextInt();
			datos.nextLine();
			
			while(archivo.getFilePointer() < archivo.length()) {
				if(archivo.readInt()==cedula || cedula<=0) {
					System.out.println("Ingrese un codigo no existente o diferente de cero o no negativo");
					
					cedula = -1;
					
					break;
				}
				
				archivo.readUTF();
			}
		} while(cedula == -1);
		
		return cedula;
    }
	
	public int validarCedulavieja(String t) throws IOException{ //exista
		int cedula = 0;
		Scanner datos = new Scanner(System.in);
		RandomAccessFile archivo = new RandomAccessFile(oldFile,"r");

		do {
			archivo.seek(0);
			
			System.out.println(t);
			cedula = datos.nextInt();
			datos.nextLine();
			
			while(archivo.getFilePointer() < archivo.length()) {
				if (archivo.readInt() == cedula) 
			        break;

				archivo.readUTF();
				
			    if (archivo.getFilePointer() == archivo.length()) {
			        System.out.println("El código no existe, inserte otro");
			        
			        cedula = -1;
			    }
			}
		} while(cedula == -1);
		
		return cedula;
    }
	
	/*public void Modificar(int wid) {
		Scanner pedir = new Scanner(System.in);
		boolean b = false;
			
		try
		{
			RandomAccessFile archivo = new RandomAccessFile("./datos/ArchivoAleatorio.txt","r");
			RandomAccessFile archivoAuxiliar = new RandomAccessFile("./datos/ArchivoAleatorioAuxiliar.txt","rw");
			
			archivo.seek(0);
			archivoAuxiliar.seek(0);
			
			while(archivo.getFilePointer() < archivo.length())
			{
				int pos = archivo.readInt();
				if(pos == wid)
				{
					System.out.println("¿Desea modificar cedula? 1.Si 2.No: ");
					int r = pedir.nextInt();
					pedir.nextLine();
					if (r == 1)
					{
						System.out.println("Ingrese nueva cedula: ");
						pos = pedir.nextInt();
						pedir.nextLine();
						
						archivoAuxiliar.writeInt(pos);
					}
					else
					{
						archivoAuxiliar.writeInt(pos);
					}
					
					System.out.println("¿Desea modificar nombre? 1.Si 2.No: ");
					r = pedir.nextInt();
					pedir.nextLine();
					if (r == 1)
					{
						System.out.println("Ingrese nuevo nombre: ");
						String nombre = pedir.nextLine();
						
						archivo.readUTF();
						archivoAuxiliar.writeUTF(nombre);
					}
					else
					{
						archivoAuxiliar.writeUTF(archivo.readUTF());
					}
					
					System.out.println("¿Desea modificar fecha? 1.Si 2.No: ");
					r = pedir.nextInt();
					pedir.nextLine();
					if (r == 1)
					{
						System.out.println("Ingrese nueva fecha (AAAA-MM-DD): ");
						String fecha = pedir.nextLine();
						
						archivo.readUTF();
						archivoAuxiliar.writeUTF(fecha);
					}
					else
					{
						archivoAuxiliar.writeUTF(archivo.readUTF());
					}
					
					b = true;
				}
				else
				{
					archivoAuxiliar.writeInt(pos);
					archivoAuxiliar.writeUTF(archivo.readUTF());
					archivoAuxiliar.writeUTF(archivo.readUTF());
					
					if ((archivo.getFilePointer()==archivo.length()) && !b)
					{
						System.out.println("No se encontro al usuario");
					}
				}
			}
			
			archivo.close();
			archivoAuxiliar.close();
			
			eliminarYrenombrarArchivo();
		}
		catch(IOException e) 
		{
			System.out.println(e);
			System.out.println("Ocurrio un error");
		}
	}*/
	
	/*public void Eliminar(int wid) {
		Scanner pedir = new Scanner(System.in);
		boolean b = false;
			
		try
		{
			RandomAccessFile archivo = new RandomAccessFile("./datos/ArchivoAleatorio.txt","r");
			RandomAccessFile archivoAuxiliar = new RandomAccessFile("./datos/ArchivoAleatorioAuxiliar.txt","rw");
			
			archivo.seek(0);
			archivoAuxiliar.seek(0);
			
			while(archivo.getFilePointer() < archivo.length())
			{
				int pos = archivo.readInt();
				if(pos == wid)
				{
					archivo.readUTF();
					archivo.readUTF();
					
					b = true;
				}
				else
				{
					archivoAuxiliar.writeInt(pos);
					archivoAuxiliar.writeUTF(archivo.readUTF());
					archivoAuxiliar.writeUTF(archivo.readUTF());
				}
				
				if ((archivo.getFilePointer()==archivo.length()) && (!b))
				{
					System.out.println("No se encontro al usuario");
				}
			}
			
			archivo.close();
			archivoAuxiliar.close();
			
			eliminarYrenombrarArchivo();
		}
		catch(IOException e) 
		{
			System.out.println(e);
			System.out.println("Ocurrio un error");
		}
	}*/
	
	/*public void Consultar(int wid) {
		Scanner pedir = new Scanner(System.in);
		boolean b = false;
			
		try
		{
			RandomAccessFile archivo = new RandomAccessFile("./datos/ArchivoAleatorio.txt","r");
			archivo.seek(0);
			
			while(archivo.getFilePointer() < archivo.length())
			{
				if(archivo.readInt() == wid)
				{
					System.out.println(archivo.readUTF());
					System.out.println(archivo.readUTF());
					archivo.seek(archivo.length());
					
					b = true;
				}
				else
				{
					archivo.readUTF();
					archivo.readUTF();
				}
				
				if ((archivo.getFilePointer()==archivo.length()) && !b)
				{
					System.out.println("No se encontro al usuario");
				}
			}
			
			archivo.close();
		}
		catch(IOException e) 
		{
			System.out.print(e);
			System.out.println("Ocurrio un error");
		}
	}*/
	
	/*public void Listar() {
		String wcadena = "";

		try
		{
			RandomAccessFile archivo = new RandomAccessFile("./datos/ArchivoAleatorio.txt","r");
			
			archivo.seek(0);
			
			while(archivo.getFilePointer() < archivo.length())
			{
				wcadena = archivo.readInt() + " ";
				wcadena += archivo.readUTF() + " ";
				wcadena += archivo.readUTF() + " ";
				
				System.out.println(wcadena);
			}
			
			archivo.close();
		}
		catch(IOException e) 
		{
			System.out.print(e);
			System.out.println("Ocurrio un error");
		}
	}*/
	
	
}
