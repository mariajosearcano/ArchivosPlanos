package vista;

import java.io.IOException;
import java.util.Scanner;

import logica.*;
import utilidad.TratamientoArchivo;

public class VistaCombinado extends Combinado {

	private TratamientoArchivo t = new TratamientoArchivo();
	private Scanner datos = new Scanner(System.in);
	private String sfFile = "./datos/SecuencialF.txt";
	private String saFile = "./datos/SecuencialA.txt";
	private String afFile = "./datos/AleatorioF.txt";
	private String aaFile = "./datos/AleatorioA.txt";
	private String sa = "./datos/secuencialAaleatorio.txt";
	private String as = "./datos/aleatorioAsecuencial.txt";
	
	public void VistasecuencialAaleatorio() throws IOException {
		AleatorioF a= new AleatorioF();
		
		System.out.println("""
						Quieres convertir a:
						1. SecuencialF
						2. SecuencialA
							""");
		int r = datos.nextInt();
		if(r == 1) {
			if(t.existe(sfFile)) {
				secuencialAaleatorio(sfFile);
				a.Listar(sa);
			} else
				System.out.println("No existe archivo o esta vacio");
		} else {
			if(t.existe(saFile)) {
				secuencialAaleatorio(saFile);
				a.Listar(sa);
			} else
				System.out.println("No existe archivo o esta vacio");
		}
	}
	
	public void VistaaleatorioAsecuencial() throws IOException {
		SecuencialF s = new SecuencialF();
		
		System.out.println("""
						Quieres convertir a:
						1. AleatorioF
						2. AleatorioA
							""");
		int r = datos.nextInt();
		if(r == 1) {
			if(t.existe(afFile)) {
				aleatorioAsecuencial(afFile);
				s.Listar(as);
			} else
				System.out.println("No existe archivo o esta vacio");
		} else {
			if(t.existe(aaFile)) {
				aleatorioAsecuencial(aaFile);
				s.Listar(as);
			} else
				System.out.println("No existe archivo o esta vacio");
		}
	}
	
}
