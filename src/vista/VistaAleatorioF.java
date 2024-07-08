package vista;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import logica.*;
import utilidad.TratamientoArchivo;
import bean.*;

public class VistaAleatorioF extends AleatorioF{

	private TratamientoArchivo t = new TratamientoArchivo();
	private String oldFile = "./datos/AleatorioF.txt";
	private String newFile = "./datos/AleatorioFAuxiliar.txt";
	private Scanner datos = new Scanner(System.in);
	
	public void VistaInsertar() throws NumberFormatException, IOException {
		int cedula = 0;
		String nombre = "";
		
		if(t.existe(oldFile))
			cedula = validarCedulanueva("Ingrese la cedula: ");
		else {
			System.out.println("Digite la Cédula: ");
			cedula = datos.nextInt();
			datos.nextLine();
		}
		
		System.out.println("Digite el Nombre: ");
		nombre = datos.nextLine();
		
		Insertar(new Usuario(cedula, nombre));
	}
	
	public void VistaModificar() throws IOException {
		if (t.existe(oldFile)) 
		{
			int modificar = validarCedulavieja("Ingrese cedula de usuario a modificar datos: ");
			Usuario usuario = Buscar(modificar);
			
			System.out.println("¿Desea modificar cedula? 1.Si 2.No: ");
			int r = datos.nextInt();
			datos.nextLine();
			if (r == 1)
				usuario.setCedula(validarCedulanueva("Ingrese nueva cedula: "));
			
			System.out.println("¿Desea modificar el nombre? 1.Si 2.No: ");
			r = datos.nextInt();
			datos.nextLine();
			if (r == 1) {
				System.out.println("Ingrese nuevo nombre: ");
				usuario.setNombre(datos.nextLine());
			}

			Modificar(usuario, modificar);
			t.eliminarArchivo(oldFile);
			t.renombrarArchivo(oldFile, newFile);
		}
		else
			System.out.println("No existe archivo o no esta lleno");
	}
	
	public void VistaEliminar() throws IOException {
		if (t.existe(oldFile)) 
		{
			Eliminar(Buscar(validarCedulavieja("Ingrese cedula de usuario a eliminar: ")));
			t.eliminarArchivo(oldFile);
			t.renombrarArchivo(oldFile, newFile);
		}
		else
			System.out.println("No existe archivo");
	}
	
	public void VistaConsultar() throws IOException {
		if (t.existe(oldFile)) {
			System.out.println(Buscar(validarCedulavieja("Ingrese la cedula de usuario a buscar: ")));		
		}
		else
			System.out.println("No existe archivo");
	}
	
	public void VistaListar() throws IOException {
		if (t.existe(oldFile)) 
			Listar(oldFile);
		else
			System.out.println("No existe archivo o no esta lleno");
	}
	
}
