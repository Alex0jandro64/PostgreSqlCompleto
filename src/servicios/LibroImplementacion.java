package servicios;

import java.util.ArrayList;

import dtos.LibroDto;
import util.Utilidades;

public class LibroImplementacion implements LibroInterfaz {

	public void creaLibro(ArrayList<LibroDto> listaAntigua) {

		Utilidades util = new Utilidades();
		LibroDto libro1 = new LibroDto();
		int opcion;
		int cantidadRegistros;
		System.out.println("Si va a crear un solo registro pulse 1, si va a crear varios registros pulse 2: ");
		opcion = util.capturarOpcion(1, 2);

		if (opcion == 1) {

			String titulo = util.pideTitulo();
			String autor = util.pideAutor();
			int edicion = util.pideEdicion();
			String isbn = util.pideIsbn();

			listaAntigua.add(new LibroDto(titulo, autor, isbn, edicion));

		} else {
			// Es maximo 10 pero se podria modificar cambiando el segundo campo de
			// util.capturarOpcion
			System.out.println("Cuantos Registros quiere crear (maximo 10) : ");
			cantidadRegistros = util.capturarOpcion(1, 10);

			for (int i = 0; i < cantidadRegistros; i++) {
				System.out.println((i + 1) + ".er Registro");
				String titulo = util.pideTitulo();
				String autor = util.pideAutor();
				int edicion = util.pideEdicion();
				String isbn = util.pideIsbn();

				listaAntigua.add(new LibroDto(titulo, autor, isbn, edicion));

			}

		}

	}

	public void pideId(ArrayList<LibroDto> listaAntigua) {

		Utilidades util = new Utilidades();
		LibroDto libro1 = new LibroDto();
		int opcion;
		int cantidadRegistros;
		System.out.println(
				"Si va a seleccionar un solo registro pulse 1, si va a seleccionar varios registros pulse 2: ");
		opcion = util.capturarOpcion(1, 2);

		if (opcion == 1) {
			long id_libro = util.pideId();

			listaAntigua.add(new LibroDto(id_libro));

		} else {
			// Es maximo 10 pero se podria modificar cambiando el segundo campo de
			// util.capturarOpcion
			System.out.println("Cuantos Registros quiere eliminar (maximo 10) : ");
			cantidadRegistros = util.capturarOpcion(1, 10);

			for (int i = 0; i < cantidadRegistros; i++) {
				System.out.println((i + 1) + ".er Registro");
				long id_libro = util.pideId();

				listaAntigua.add(new LibroDto(id_libro));
			}

		}

	}

	public int datosAModificar(ArrayList<LibroDto> listaAntigua) {

		Utilidades util1 = new Utilidades();
		
		int opcion;
		int cantidadRegistros;
		int datoAModificar;
		
		long id_libro = util1.pideId();
		int campo = util1.pideIdCamposAModificar();
		LibroDto libro1 = new LibroDto(id_libro);
		
			switch (campo) {
			case 1:
				libro1.setTitulo(util1.pideTitulo());
				break;
			case 2:
				libro1.setAutor(util1.pideAutor());
				break;
			case 3:
				libro1.setIsbn(util1.pideIsbn());
				break;
			case 4:
				libro1.setEdicion(util1.pideEdicion());
				break;
			}
			
			listaAntigua.add(libro1);
			return campo;
	}
}
