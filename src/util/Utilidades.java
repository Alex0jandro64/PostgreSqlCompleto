package util;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dtos.LibroDto;
/**
 * Clase que contiene todos los metodos utiles
 */
public class Utilidades {

	/**
	 * Metodo que recibe una lista de libros y la imprime en consola
	 * @param lista
	 */
	public void imprimirListaLibro(ArrayList<LibroDto> lista) {
		
		for (LibroDto libroDto : lista) {
			System.out.println(libroDto.getIdLibro()+", "+libroDto.getTitulo()+", "+libroDto.getEdicion()+", "+libroDto.getAutor()+", "+libroDto.getIsbn());
		}
		
	}

	/**
	 * Metodo que imprime el menu principal
	 */
	public void imprimirMenu() {
		System.out.println("1.-Ver registros");
		System.out.println("2.-Crear registro");
		System.out.println("3.-Modificar registro");
		System.out.println("4.-Borrar registro");
		System.out.println("0.-Cerrar aplicacion");
	}

	/**
	 * Metodo que captura y devuelve una opcion entre los valores minimos y maximos
	 * @param minimo
	 * @param maximo
	 * @return
	 */
	public int capturarOpcion(int minimo, int maximo) {
		Scanner leer = new Scanner(System.in);
		int opcion;
		
		while (true) {
            try {
                opcion = leer.nextInt();

                if (opcion < minimo || opcion > maximo) {
                    System.out.println("Número fuera de rango. Intente de nuevo.");
                } else {
                    break; // Salir del bucle si se ingresó un número válido
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Debe ingresar un número entero.");
                leer.next(); // Limpiar el búfer de entrada
            }
        }
		
		return opcion;
	}
	
	/**
	 * Metodo que captura y devuelve el valor titulo del objeto libro
	 * @return
	 */
	public String pideTitulo() {
		Scanner leer = new Scanner(System.in);
		String titulo;
		do {
			// Compruebo que los tipos de datos son los correctos
			System.out.print("\nTitulo: ");
			titulo = leer.nextLine();
			if (estaVacio(titulo)) {
				System.out.println("El Titulo no puede estar vacio");
			}

		} while (estaVacio(titulo));

		// Si sale del bucle es que esta correcto entonces devuelvo el dato
		return titulo;
	}
	
	/**
	 * Metodo que devuelve si una cadena de String esta vacia o no
	 * @param txt
	 * @return
	 */
	public boolean estaVacio(String txt) {
		return txt.isEmpty();
	}
	
	/**
	 * Metodo que comprueba si una cadena String contiene algun numero
	 * @param texto
	 * @return
	 */
	public boolean contieneNumeros(String texto) {
		// Define una expresión regular para buscar números en la cadena
		String patron = ".*\\d.*";

		// Crea un objeto Pattern
		Pattern pattern = Pattern.compile(patron);

		// Crea un objeto Matcher para buscar coincidencias
		Matcher matcher = pattern.matcher(texto);

		// Comprueba si hay coincidencias
		return matcher.matches();
	}

	/**
	 * Metodo que captura y devuelve el valor autor del objeto libro
	 * @return
	 */
	public String pideAutor() {
		Scanner leer = new Scanner(System.in);
		String autor;
		do {
			// Compruebo que los tipos de datos son los correctos
			System.out.print("\nAutor: ");
			autor = leer.nextLine();
			if (estaVacio(autor)) {
				System.out.println("El autor no puede estar vacio");
			} else if (contieneNumeros(autor)) {
				System.out.println("El autor no puede contener numero");
			}

		} while (estaVacio(autor) || contieneNumeros(autor));

		// Si sale del bucle es que esta correcto entonces devuelvo el dato
		return autor;
	}
	
	/**
	 * Metodo que captura y devuelve el valor Edicion del objeto libro
	 * @return
	 */
	public int pideEdicion() {
		boolean ok;
		int edicion = 0;
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.print("\nNumero de Edicion: ");
			String input = scanner.nextLine();

			try {
				edicion = Integer.parseInt(input);
				ok = true;
			} catch (NumberFormatException e) {
				ok = false;
				System.out.println("El Numero de Edicion no puede tener caracteres no numericos");
			}
		} while (!ok);

		return edicion;
	}

	/**
	 * Metodo que captura y devuelve el valor isbn del objeto libro
	 * @return
	 */
	public String pideIsbn() {
		Scanner leer = new Scanner(System.in);
		String isbn;
		do {
			// Compruebo que los tipos de datos son los correctos
			System.out.print("\nIsbn: ");
			isbn = leer.nextLine();
			if (estaVacio(isbn)) {
				System.out.println("El isbn no puede estar vacio");
			}

		} while (estaVacio(isbn));

		// Si sale del bucle es que esta correcto entonces devuelvo el dato
		return isbn;
	}
	
	/**
	 * Metodo que captura y devuelve el valor id del objeto libro
	 * @return
	 */
	public int pideId() {
		boolean ok;
		int id = 0;
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.print("\nId del libro: ");
			String input = scanner.nextLine();

			try {
				id = Integer.parseInt(input);
				ok = true;
			} catch (NumberFormatException e) {
				ok = false;
				System.out.println("El Id no puede tener caracteres no numericos");
			}
		} while (!ok);

		return id;
	}
	
	/**
	 * Metodo que imprime y captura el campo que quiere modificar
	 * @return
	 */
	public int pideIdCamposAModificar() {
		System.out.println("Que campo quiere seleccionar");
		System.out.println("1.-Titulo");
		System.out.println("2.-Autor");
		System.out.println("3.-Isbn");
		System.out.println("4.-Edicion");
		
		int opcion = capturarOpcion(1, 4);
		return opcion;
		
	}
}
