package servicios;

import java.sql.Connection;
import java.util.ArrayList;

import dtos.LibroDto;
/**
 * Interfaz que contiene todos los metodos relacionados con la base de datos
 */
public interface CrudInterfaz {
	/**
	 * Metodo que crea una conexion con nuestra bd
	 */
	public Connection generaConexion();
	
	/**
	 * Metodo que recibe la conexion y devuelve una lista con todos los datos de la bd
	 */
	public ArrayList<LibroDto> selecionarTodosLibros(Connection conexionGenerada);
	
	/**
	 * Metodo que recibe la conexion y una lista con los datos a insertar en la bd
	 */
	public void crearRegistro(Connection conexionGenerada, ArrayList<LibroDto> lista);
	
	/**
	 * Metodo que recibe la conexion y una lista en la que esta el id del libro a eliminar
	 */
	public void eliminarRegistro(Connection conexionGenerada, ArrayList<LibroDto> lista);
	
	/**
	 * Metodo que recibe la conexion, la lista de libros y el campo a modificar,
	 * Modifica un campo de la bd
	 */
	public void modificarRegistro(Connection conexionGenerada, ArrayList<LibroDto> lista, int campo);
}
