package controladores;

import java.util.ArrayList;
import java.util.Scanner;

import dtos.LibroDto;
import servicios.CrudImplementacion;
import servicios.CrudInterfaz;
import servicios.LibroImplementacion;
import servicios.LibroInterfaz;
import util.Utilidades;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner leer = new Scanner(System.in);
		CrudInterfaz bd = new CrudImplementacion();
		Utilidades util = new Utilidades();
		LibroInterfaz libroIm = new LibroImplementacion();
		
		int opcion = 1;
		
		try {
			
		do {
			ArrayList<LibroDto> listaLibros = new ArrayList<LibroDto>();
			util.imprimirMenu();
			opcion = util.capturarOpcion(0,4);
			
			switch (opcion) {
			case 1:
				util.imprimirListaLibro(bd.selecionarTodosLibros(bd.generaConexion()));
				System.out.println("Pulse una tecla para continuar");
				leer.nextLine();
				break;
			case 2:
				libroIm.creaLibro(listaLibros);
				bd.crearRegistro(bd.generaConexion(), listaLibros);
				break;
			case 3:
				int campo = libroIm.datosAModificar(listaLibros);
				bd.modificarRegistro(bd.generaConexion(), listaLibros, campo );
				break;
			case 4:
				libroIm.pideId(listaLibros);
				bd.eliminarRegistro(bd.generaConexion(), listaLibros);
				break;
			}
			
		}while(opcion!=0);
		
		}catch (Exception e) {
			System.out.println("Error General");
		}
		
		
		
	}

}
