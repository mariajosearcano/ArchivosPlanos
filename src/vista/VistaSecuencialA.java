package vista;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import bean.Usuario;
import logica.*;
import utilidad.*;

public class VistaSecuencialA extends SecuencialA{

	private TratamientoArchivo t = new TratamientoArchivo();
	private String file = "./datos/SecuencialA.txt";
	private Scanner datos = new Scanner(System.in);
	
	public VistaSecuencialA() throws IOException {}
	
	public void VistaInsertar() throws IOException {
		int cedula = 0;
		String nombre = "";
		
		if(t.existe(file))
			cedula = validarCedulanueva("Ingrese la cedula: ");
		else {
			System.out.println("Digite la Cédula: ");
			cedula = datos.nextInt();
			datos.nextLine();
		}
		
		System.out.println("Digite el Nombre: ");
		nombre = datos.nextLine();
		
		String s = cedula + "," + nombre;
		
		Insertar(s);
	}
	
	public void VistaModificar() throws IOException {
		if (t.existe(file)) {
			String s = Buscar(validarCedulavieja("Digite la cedula del usuario a modificar: "));
			int indice = registros.indexOf(s);
			
			System.out.println("¿Desea modificar cedula? 1.Si 2.No: ");
			int r = datos.nextInt();
			datos.nextLine();
			if (r == 1) {
				int cedula = validarCedulanueva("Ingrese nuevo codigo: ");
				s = cedula + "," + s.split(",")[1];
			}
			
			System.out.println("¿Desea modificar el nombre? 1.Si 2.No: ");
			r = datos.nextInt();
			datos.nextLine();
			if (r == 1) {
				System.out.println("Ingrese nuevo nombre: ");
				String nombre = datos.nextLine();
				s = s.split(",")[0] + "," + nombre;
			}
			
			t.eliminarArchivo(file);
			Modificar(s, indice);
		} else
			System.out.println("No existe archivo o no esta lleno");
	}
	
	public void VistaEliminar() throws IOException {
		if (t.existe(file)) {
			String s = Buscar(validarCedulavieja("Digite la cedula del usuario a eliminar: "));
			
			t.eliminarArchivo(file);
			Eliminar(s);
		} else
			System.out.println("No existe archivo o no esta lleno");
	}
	
	public void VistaBuscar() {
		if (t.existe(file)) {
			String s = Buscar(validarCedulavieja("Ingrese cedula de usuario a buscar: "));
			
			System.out.println(s);
		} else
			System.out.println("No existe archivo o no esta lleno");
	}
	
	public void VistaListar() {
		if (t.existe(file)) 
			Listar();
		else
			System.out.println("No existe archivo o no esta lleno");
	}
	
}
