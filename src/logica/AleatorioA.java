package logica;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import bean.Usuario;
import utilidad.TratamientoArchivo;

public class AleatorioA {

	public ArrayList<Usuario> registros = new ArrayList<>();
	private String file = "./datos/AleatorioA.txt";

	public AleatorioA() throws IOException {
		TratamientoArchivo t = new TratamientoArchivo();
		
		if(t.existe(file))
			registros = archivoAarrayList();
	}
	
	public void Insertar(Usuario usuario) throws IOException {
		RandomAccessFile archivo = new RandomAccessFile(file,"rw");
		
		archivo.seek(archivo.length());
		archivo.writeInt(usuario.getCedula());
		archivo.writeUTF(usuario.getNombre());
		archivo.close();
	}
	
	public void Modificar(Usuario usuario, int indice) throws IOException {
		RandomAccessFile archivo = new RandomAccessFile(file,"rw");
		
		archivo.seek(0);
		
		for (int i = 0; i < registros.size(); i++) {
			if(i == indice) {
				registros.set(i, usuario);
				archivo.writeInt(usuario.getCedula());
				archivo.writeUTF(usuario.getNombre());
			} else {
				archivo.writeInt(registros.get(i).getCedula());
				archivo.writeUTF(registros.get(i).getNombre());
			}
		}
		
		archivo.close();
	}
	
	public void Eliminar(Usuario usuario) throws IOException {
		RandomAccessFile archivo = new RandomAccessFile(file,"rw");
		
		archivo.seek(0);
		registros.remove(usuario);
		
		for(Usuario usuarioAuxiliar : registros) {
			archivo.writeInt(usuarioAuxiliar.getCedula());
			archivo.writeUTF(usuarioAuxiliar.getNombre());
		}
		
		archivo.close();
	}
	
	public Usuario Buscar(int buscar) {
		for(Usuario usuario : registros) {
			if(usuario.getCedula() == buscar)
				return usuario;
		}
		
		return null;
	}
	
	public void Listar() {
		for(Usuario usuario : registros)
			System.out.println(usuario);
	}
	
	public ArrayList<Usuario> archivoAarrayList() throws IOException {
		RandomAccessFile archivo = new RandomAccessFile(file,"r");
		
		archivo.seek(0);
		
		while(archivo.getFilePointer() < archivo.length())
			registros.add(new Usuario(archivo.readInt(), archivo.readUTF()));
		
		archivo.close();
		
		return registros;
	}
	
	public int validarCedulanueva(String t){ //no exista
		int cedula = 0;
		Scanner datos = new Scanner(System.in);
			
		do {
			Iterator<Usuario> apuntador = registros.iterator();
			
			System.out.println(t);
			cedula = datos.nextInt();
			datos.nextLine();
			
			while(apuntador.hasNext()) {
				Usuario usuario = apuntador.next();
				
				if(usuario.getCedula()==cedula || cedula<=0) {
					System.out.println("Ingrese una cedula no existente o diferente de cero o no negativa");
					
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
			Iterator<Usuario> apuntadorLista = registros.iterator();
			
			System.out.println(t);
			cedula = datos.nextInt();
			datos.nextLine();
			
			while(apuntadorLista.hasNext()) {
				Usuario usuario = apuntadorLista.next();
				
				if (usuario.getCedula() == cedula) 
			        break;

			    if (!apuntadorLista.hasNext()) {
			        System.out.println("La cedula no existe, inserte otra");
			        
			        cedula = -1;
			    }
			}
		} while(cedula == -1);
		
		return cedula;
    }
	
	/*public void Modificar(Usuario usuario, int indice) throws IOException {
		RandomAccessFile archivo = new RandomAccessFile(file,"rw");
		int c = 0;
		
		archivo.seek(0);
		
		for(Usuario usuarioAuxiliar : registros) {
			if(c == indice) {
				registros.add(indice, usuario);
				registros.remove(indice + 1);
				archivo.writeInt(usuario.getCedula());
				archivo.writeUTF(usuario.getNombre());
			} else {
				archivo.writeInt(usuarioAuxiliar.getCedula());
				archivo.writeUTF(usuarioAuxiliar.getNombre());
			}
			
			c++;
		}
		
		archivo.close();
	}*/
	
	
}
