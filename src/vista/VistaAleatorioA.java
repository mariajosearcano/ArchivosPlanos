package vista;

import java.io.IOException;
import java.util.Scanner;

import bean.*;
import logica.AleatorioA;
import utilidad.TratamientoArchivo;

public class VistaAleatorioA extends AleatorioA {

	private TratamientoArchivo t = new TratamientoArchivo();
	private String file = "./datos/AleatorioA.txt";
	private Scanner datos = new Scanner(System.in);
	
	public VistaAleatorioA() throws IOException {}
	
	public void VistaInsertar() throws IOException {
		int cedula;
		String nombre;
		
		if(t.existe(file))
			cedula = validarCedulanueva("Ingrese la cedula: ");
		else {
			System.out.println("Escriba la cedula: ");
			cedula = datos.nextInt();
			datos.nextLine();
		}

		System.out.println("Escriba el nombre: ");
		nombre = datos.nextLine();
		
		Insertar(new Usuario(cedula, nombre));
	}

	public void VistaModificar() throws IOException {
		if (t.existe(file)) {
			Usuario usuario = Buscar(validarCedulavieja("Digite la cedula de usuario a modificar: "));
			int indice = registros.indexOf(usuario);
			
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
			
			t.eliminarArchivo(file);
			Modificar(usuario, indice);
		} else
			System.out.println("No existe archivo o no esta lleno");
	}
	
	public void VistaEliminar() throws IOException {
		if (t.existe(file)) {
			Usuario usuario = Buscar(validarCedulavieja("Digite la cedula de usuario a eliminar: "));
			
			t.eliminarArchivo(file);
			Eliminar(usuario);
		} else
			System.out.println("No existe archivo o no esta lleno");
	}
	
	public void VistaBuscar() {
		if (t.existe(file))
			System.out.println(Buscar(validarCedulavieja("Ingrese la cedula de usuario a buscar: ")));
		else
			System.out.println("No existe archivo o no esta lleno");
	}
	
	public void VistaListar() {
		if (t.existe(file)) 
			Listar();
		else
			System.out.println("No existe archivo o no esta lleno");
	}
	
	
}
