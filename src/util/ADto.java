package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dtos.LibroDto;

public class ADto {

	/**
	 * Metodo que recibe el resultado de la consulta del select y lo pasa a una lista de objetos (DAO a DTO)
	 * @param resultadoConsulta
	 * @return
	 */
public ArrayList<LibroDto> resultadoADto(ResultSet resultadoConsulta){
		
		ArrayList<LibroDto> listaLibros = new ArrayList<>();
		
		//Leemos el resultado de la consulta hasta que no queden filas
		try {
			while (resultadoConsulta.next()) {
				
				listaLibros.add(new LibroDto(resultadoConsulta.getLong("id_libro"),
						resultadoConsulta.getString("titulo"),
						resultadoConsulta.getString("autor"),
						resultadoConsulta.getString("isbn"),
						resultadoConsulta.getInt("edicion"))
						);
			}
			
			int i = listaLibros.size();
			System.out.println("[INFORMACIÓN-ADto-resultsALibrosDto] Número libros: "+i);
			
		} catch (SQLException e) {
			System.out.println("[ERROR-ADto-resultsALibrosDto] Error al pasar el result set a lista de LibroDto" + e);
		}
		
		return listaLibros;
		
	}
}
