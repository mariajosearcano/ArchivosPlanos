package vista;

import java.io.IOException;
import java.util.Scanner;

import logica.Serializar;
import utilidad.TratamientoArchivo;

public class VistaSerializar extends Serializar {

	private TratamientoArchivo t = new TratamientoArchivo();
	private Scanner datos = new Scanner(System.in);
	private String sfFile = "./datos/SecuencialF.txt";
	private String saFile = "./datos/SecuencialA.txt";
	private String afFile = "./datos/AleatorioF.txt";
	private String aaFile = "./datos/AleatorioA.txt";
	
	public void VistaserializarSecuencial() throws IOException {
		System.out.println("""
						Quieres convertir a:
						1. SecuencialF
						2. SecuencialA
							""");
		int r = datos.nextInt();
		if(r == 1) {
			if(t.existe(sfFile))
				serializarSecuencial(sfFile);
			else
				System.out.println("No existe archivo o esta vacio");
		} else {
			if(t.existe(saFile))
				serializarSecuencial(saFile);
			else
				System.out.println("No existe archivo o esta vacio");
		}
	}
	
	public void VistaserializarAleatorio() throws IOException {
		System.out.println("""
						Quieres convertir a:
						1. AleatorioF
						2. AleatorioA
							""");
		int r = datos.nextInt();
		if(r == 1) {
			if(t.existe(afFile))
				serializarAleatorio(afFile);
			else
				System.out.println("No existe archivo o esta vacio");
		} else {
			if(t.existe(aaFile))
				serializarAleatorio(aaFile);
			else
				System.out.println("No existe archivo o esta vacio");
		}
	}
	
	public void VistadeserializarSecuencial() throws IOException, ClassNotFoundException {
		System.out.println("""
						Quieres convertir a:
						1. SecuencialF
						2. SecuencialA
							""");
		int r = datos.nextInt();
		if(r == 1) {
			if(t.existe(sfFile))
				deserializarSecuencial(sfFile);
			else
				System.out.println("No existe archivo o esta vacio");
		} else {
			if(t.existe(saFile))
				deserializarSecuencial(saFile);
			else
				System.out.println("No existe archivo o esta vacio");
		}
	}
	
	public void VistadeserializarAleatorio() throws IOException, ClassNotFoundException {
		System.out.println("""
						Quieres convertir a:
						1. AleatorioF
						2. AleatorioA
							""");
		int r = datos.nextInt();
		if(r == 1) {
			if(t.existe(afFile))
				deserializarAleatorio(afFile);
			else
				System.out.println("No existe archivo o esta vacio");
		} else {
			if(t.existe(aaFile))
				deserializarAleatorio(aaFile);
			else
				System.out.println("No existe archivo o esta vacio");
		}
	}
	
}
