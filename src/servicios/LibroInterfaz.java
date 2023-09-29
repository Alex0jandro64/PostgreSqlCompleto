package servicios;

import java.util.ArrayList;

import dtos.LibroDto;

public interface LibroInterfaz {
/**
 * Metodo que pide los datos y crea un objeto libro para añadirlo a la lista 
 * @param listaAntigua
 */
	public void creaLibro(ArrayList<LibroDto> listaAntigua);
	
	/**
	 * Metodo que pide un id_libro para añadirlo a un objeto que posteriormente se eliminara de la bd
	 * @param listaAntigua
	 */
	public void pideId(ArrayList<LibroDto> listaAntigua);
	
	/**
	 * Metodo que pide que campo quiere modifica y lo devuelve 
	 * @param listaAntigua
	 * @return
	 */
	public int datosAModificar(ArrayList<LibroDto> listaAntigua);
}
