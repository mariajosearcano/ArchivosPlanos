package vista;

import java.io.IOException;
import java.util.Scanner;

import logica.AleatorioF;
import logica.Combinado;
import logica.SecuencialF;

public class Menu {

	private static Scanner datos = new Scanner(System.in);
	
	public static void MenuPrincipal() throws IOException, ClassNotFoundException {
		int opcion = -1;
		 
		do 
		{
			System.out.println();
			System.out.println("Archivos planos");
			System.out.println("1. Secuenciales");
			System.out.println("2. Aleatorios");
			System.out.println("3. Combinados");
			System.out.println("4. Serializados");
			System.out.println("0. Salir");
			System.out.println("Digite la opción deseada: ");

			opcion = datos.nextInt();
			
			switch(opcion)
			{
				case 1 :
					MenuSecuenciales();
					break;
				
				case 2:
					MenuAleatorios();
					break;
				
				case 3:
					MenuCombinados();
					break;
					
				case 4:
					MenuSerializados();
					break;
				
				case 0:
					System.out.println("Gracias por usar nuestros servicios, vuelva");
					break;
				
				default :
					System.out.println("Opción no Valida");
					break;
			}
		}
		while(opcion != 0);
	}
	
	private static void MenuSecuenciales() throws IOException {
		int opcion = -1;
		
		do 
		{
			System.out.println();
			System.out.println("Secuenciales");
			System.out.println("1. Tipo F");
			System.out.println("2. Tipo A");
			System.out.println("0. Salir");
			System.out.println("Digite la opción deseada: ");

			opcion = datos.nextInt();
			
			switch(opcion)
			{
				case 1 :
					MenuSecuencialesF();
					break;
				
				case 2:
					MenuSecuencialesA();
					break;
				
				case 0:
					System.out.println("Sera llevado al menu principal");
					break;
				
				default :
					System.out.println("Opción no Valida");
					break;
			}
		}
		while(opcion != 0);
	}
	
	private static void MenuSecuencialesF() throws IOException {
		int opcion = -1;
		VistaSecuencialF v = new VistaSecuencialF();
		
		do 
		{
			System.out.println();
			System.out.println("Secuenciales");
			System.out.println("1. Ingresar");
			System.out.println("2. Modificar");
			System.out.println("3. Eliminar");
			System.out.println("4. Consultar");
			System.out.println("5. Listar");
			System.out.println("0. Salir");
			System.out.println("Digite la opción deseada: ");

			opcion = datos.nextInt();
			
			switch(opcion)
			{
				case 1 :
					v.VistaInsertar();
					break;
				
				case 2:
					v.VistaModificar();
					break;
				
				case 3:
					v.VistaEliminar();
					break;
					
				case 4:
					v.VistaBuscar();
					break;
					
				case 5:
					v.VistaListar();
					break;
				
				case 0:
					System.out.println("Sera llevado al menu secuenciales");
					break;
				
				default :
					System.out.println("Opción no Valida");
					break;
			}
		}
		while(opcion != 0);
	}
	
	private static void MenuSecuencialesA() throws IOException {
		int opcion = -1;
		
		do 
		{
			VistaSecuencialA v = new VistaSecuencialA();
			
			System.out.println();
			System.out.println("Secuenciales");
			System.out.println("1. Ingresar");
			System.out.println("2. Modificar");
			System.out.println("3. Eliminar");
			System.out.println("4. Consultar");
			System.out.println("5. Listar");
			System.out.println("0. Salir");
			System.out.println("Digite la opción deseada: ");

			opcion = datos.nextInt();
			
			switch(opcion)
			{
				case 1 :
					v.VistaInsertar();
					break;
				
				case 2:
					v.VistaModificar();
					break;
				
				case 3:
					v.VistaEliminar();
					break;
					
				case 4:
					v.VistaBuscar();
					break;
					
				case 5:
					v.VistaListar();
					break;
				
				case 0:
					System.out.println("Sera llevado al menu secuenciales");
					break;
				
				default :
					System.out.println("Opción no Valida");
					break;
			}
		}
		while(opcion != 0);
	}
	
	private static void MenuAleatorios() throws IOException {
		int opcion = -1;
		VistaAleatorioF v = new VistaAleatorioF();
		
		do 
		{
			System.out.println();
			System.out.println("Aleatorios");
			System.out.println("1. Tipo F");
			System.out.println("2. Tipo A");
			System.out.println("0. Salir");
			System.out.println("Digite la opción deseada: ");

			opcion = datos.nextInt();
			
			switch(opcion)
			{
			case 1 :
				MenuAleatoriosF();
				break;
			
			case 2:
				MenuAleatoriosA();
				break;
				
			case 0:
				System.out.println("Sera llevado al menu principal");
				break;
			
			default :
				System.out.println("Opción no Valida");
				break;
			}
		}
		while(opcion != 0);
	}
	
	private static void MenuAleatoriosF() throws IOException {
		int opcion = -1;
		VistaAleatorioF v = new VistaAleatorioF();
		
		do 
		{
			System.out.println();
			System.out.println("Aleatorios");
			System.out.println("1. Ingresar");
			System.out.println("2. Modificar");
			System.out.println("3. Eliminar");
			System.out.println("4. Consultar");
			System.out.println("5. Listar");
			System.out.println("0. Salir");
			System.out.println("Digite la opción deseada: ");

			opcion = datos.nextInt();
			
			switch(opcion)
			{
			case 1 :
				v.VistaInsertar();
				break;
			
			case 2:
				v.VistaModificar();
				break;
			
			case 3:
				v.VistaEliminar();
				break;
				
			case 4:
				v.VistaConsultar();
				break;
				
			case 5:
				v.VistaListar();
				break;
				
			case 0:
				System.out.println("Sera llevado al menu aleatorios");
				break;
			
			default :
				System.out.println("Opción no Valida");
				break;
			}
		}
		while(opcion != 0);
	}
	
	private static void MenuAleatoriosA() throws IOException {
		int opcion = -1;
		
		do 
		{
			VistaAleatorioA v = new VistaAleatorioA();
			
			System.out.println();
			System.out.println("Aleatorios");
			System.out.println("1. Ingresar");
			System.out.println("2. Modificar");
			System.out.println("3. Eliminar");
			System.out.println("4. Consultar");
			System.out.println("5. Listar");
			System.out.println("0. Salir");
			System.out.println("Digite la opción deseada: ");

			opcion = datos.nextInt();
			
			switch(opcion)
			{
			case 1 :
				v.VistaInsertar();
				break;
			
			case 2:
				v.VistaModificar();
				break;
			
			case 3:
				v.VistaEliminar();
				break;
				
			case 4:
				v.VistaBuscar();
				break;
				
			case 5:
				v.VistaListar();
				break;
				
			case 0:
				System.out.println("Sera llevado al menu aleatorios");
				break;
			
			default :
				System.out.println("Opción no Valida");
				break;
			}
		}
		while(opcion != 0);
	}
	
	private static void MenuCombinados() throws IOException {
		int opcion = -1;
		VistaCombinado v = new VistaCombinado();
		
		do 
		{
			System.out.println();
			System.out.println("Combinados");
			System.out.println("1. Secuencial a Aleatorio");
			System.out.println("2. Aleatorio a Secuencial");
			System.out.println("0. Salir");
			System.out.println("Digite la opción deseada: ");

			opcion = datos.nextInt();
			
			switch(opcion)
			{
				case 1 :
					v.VistasecuencialAaleatorio();
					break;
				
				case 2:
					v.VistaaleatorioAsecuencial();
					break;
				
				case 0:
					System.out.println("Sera llevado al menu principal");
					break;
				
				default :
					System.out.println("Opción no Valida");
					break;
			}
		}
		while(opcion != 0);
	}
	
	private static void MenuSerializados() throws IOException, ClassNotFoundException {
		int opcion = -1;
		VistaSerializar v = new VistaSerializar();
		
		do 
		{
			System.out.println();
			System.out.println("Serializados");
			System.out.println("1. Serializar Secuencial");
			System.out.println("2. Serializar Aleatorio");
			System.out.println("3. Deserializar Secuencial");
			System.out.println("4. Deserializar Aleatorio");
			System.out.println("0. Salir");
			System.out.println("Digite la opción deseada: ");

			opcion = datos.nextInt();
			
			switch(opcion)
			{
				case 1 :
					v.VistaserializarSecuencial();
					break;
				
				case 2:
					v.VistaserializarAleatorio();
					break;
					
				case 3 :
					v.VistadeserializarSecuencial();
					break;
				
				case 4:
					v.VistadeserializarAleatorio();
					break;
				
				case 0:
					System.out.println("Sera llevado al menu principal");
					break;
				
				default :
					System.out.println("Opción no Valida");
					break;
			}
		}
		while(opcion != 0);
	}
	
	
}
