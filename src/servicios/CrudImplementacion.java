package servicios;


import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import dtos.LibroDto;
import util.ADto;

public class CrudImplementacion implements CrudInterfaz{

	public Connection generaConexion() {
		
		Connection conexion = null;
		String[] parametrosConexion = configuracionConexion();
		
		if(!parametrosConexion[2].isEmpty()) {
			try {
				
				Class.forName("org.postgresql.Driver");
				
				conexion = DriverManager.getConnection(parametrosConexion[0],parametrosConexion[1],parametrosConexion[2]);
				boolean esValida = conexion.isValid(50000);
				if(esValida == false) {
					conexion = null;
				}
				
				System.out.println(esValida ? "[INFORMACIÓN-CrudImplementacion-generaConexion] Conexión válida" : "[ERROR-CrudImplementacion-generaConexion] Conexión no válida");
	            return conexion;
	            
			}catch (ClassNotFoundException cnfe) {
				System.out.println("[ERROR-CrudImplementacion-generaConexion] Error en registro driver: " + cnfe);
				return conexion = null;
			} catch (SQLException jsqle) {
				System.out.println("[ERROR-CrudImplementacion-generaConexion] Error en conexión (" + parametrosConexion[0] + "): " + jsqle);
				return conexion = null;
			}
			
		}else {
			System.out.println("[ERROR-CrudImplementacion-generaConexion] Los parametros de conexion no se han establecido correctamente");	
			return conexion;
		}
		
	}
	
	public ArrayList<LibroDto> selecionarTodosLibros(Connection conexionGenerada){
		
		
		
		Statement declaracionSQL = null;
		ResultSet resultadoConsulta = null;
		ArrayList<LibroDto> listaLibros = new ArrayList<>();
		ADto adto = new ADto();
		
try {
			
			//Se abre una declaración
			declaracionSQL = conexionGenerada.createStatement();
			//Se define la consulta de la declaración y se ejecuta
			resultadoConsulta = declaracionSQL.executeQuery("SELECT * FROM \"gbp_almacen\".\"gbp_alm_cat_libros\"");
		    
			//Llamada a la conversión a dtoAlumno
			listaLibros = adto.resultadoADto(resultadoConsulta);
			int i = listaLibros.size();
			System.out.println("[INFORMACIÓN-CrudImplementacion-seleccionaTodosLibros] Número libros: "+i);
			
			System.out.println("[INFORMACIÓN-CrudImplementacion-seleccionaTodosLibros] Cierre conexión, declaración y resultado");				
		    resultadoConsulta.close();
		    declaracionSQL.close();
		    conexionGenerada.close();
			
		} catch (SQLException e) {
			
			System.out.println("[ERROR-CrudImplementacion-seleccionaTodosLibros] Error generando o ejecutando la declaracionSQL: " + e);
			return listaLibros;
			
		}
		return listaLibros;
	}
	
	public void crearRegistro(Connection conexionGenerada, ArrayList<LibroDto> lista) {
		
		
		try {
			
		// Sentencia SQL para la inserción
			
			String sqlInsert = "INSERT INTO gbp_almacen.gbp_alm_cat_libros (titulo, autor, isbn, edicion) VALUES (?, ?, ?, ?)";
			for(int i=0;i<lista.size()-1;i++) {
				sqlInsert = sqlInsert + ",(?, ?, ?, ?)";
			}
			
        // Crear una declaración preparada
        PreparedStatement preparedStatement = conexionGenerada.prepareStatement(sqlInsert);
        int i=1;
        // Asignar valores a los parámetros de la sentencia SQL
        for (LibroDto libroDto : lista) {
        	
        	preparedStatement.setString(i++, libroDto.getTitulo());
            preparedStatement.setString(i++, libroDto.getAutor());
            preparedStatement.setString(i++, libroDto.getIsbn());
            preparedStatement.setInt(i++, libroDto.getEdicion());
		}
        
        // Ejecutar la inserción
        int filasInsertadas = preparedStatement.executeUpdate();

        System.out.println("Filas insertadas: " + filasInsertadas);

        // Cerrar la conexión y los recursos
        preparedStatement.close();
        conexionGenerada.close();
		}catch (Exception e) {
			System.out.println("[ERROR-CrudImplementacion-crearRegistro] Error al insertar");
		}
		
		
		
		
		
	}
	
	public void eliminarRegistro(Connection conexionGenerada, ArrayList<LibroDto> lista) {
		
		
		try {
			
			// Sentencia SQL para la inserción
				
				String sqlInsert = "DELETE FROM gbp_almacen.gbp_alm_cat_libros WHERE id_libro IN (?";
				for(int i=0;i<lista.size()-1;i++) {
					sqlInsert = sqlInsert + ",?";
				}
				sqlInsert = sqlInsert +")";
				
	        // Crear una declaración preparada
	        PreparedStatement preparedStatement = conexionGenerada.prepareStatement(sqlInsert);
	        int i=1;
	        // Asignar valores a los parámetros de la sentencia SQL
	        for (LibroDto libroDto : lista) {
	        	preparedStatement.setLong(i++, libroDto.getIdLibro());
			}
	        
	        // Ejecutar la inserción
	        int filasEliminadas = preparedStatement.executeUpdate();

	        System.out.println("Filas Eliminadas: " + filasEliminadas);

	        // Cerrar la conexión y los recursos
	        preparedStatement.close();
	        conexionGenerada.close();
			}catch (Exception e) {
				System.out.println("[ERROR-CrudImplementacion-eliminarRegistro] Error al Borrar");
			}
	}
	
	public void modificarRegistro(Connection conexionGenerada, ArrayList<LibroDto> lista, int campo) {
		
		
		
		try {
			
			// Sentencia SQL para la inserción
				ArrayList<String> listaCampos = new ArrayList<String>();
				listaCampos.add("titulo");
				listaCampos.add("autor");
				listaCampos.add("isbn");
				listaCampos.add("edicion");

				String sqlInsert = "Update gbp_almacen.gbp_alm_cat_libros Set ";
				
				sqlInsert = sqlInsert + listaCampos.get(campo-1) + " = ? WHERE id_libro = ?";
				
				
	        // Crear una declaración preparada
	        PreparedStatement preparedStatement = conexionGenerada.prepareStatement(sqlInsert);
	        int i=1;
	        // Asignar valores a los parámetros de la sentencia SQL
	        for (LibroDto libroDto : lista) {
	        	
	        	switch (campo) {
				case 1:
					preparedStatement.setString(i++, libroDto.getTitulo());
					break;
				case 2:
					preparedStatement.setString(i++, libroDto.getAutor());
					break;
				case 3:
					preparedStatement.setString(i++, libroDto.getIsbn());
					break;
				case 4:
					preparedStatement.setInt(i++, libroDto.getEdicion());
					break;
				}
	        	
	        	preparedStatement.setLong(i++, libroDto.getIdLibro());
			}
	        
	        // Ejecutar la inserción
	        int filasModificadas = preparedStatement.executeUpdate();

	        System.out.println("Filas Modificadas: " + filasModificadas);

	        // Cerrar la conexión y los recursos
	        preparedStatement.close();
	        conexionGenerada.close();
			}catch (Exception e) {
				System.out.println("[ERROR-CrudImplementacion-crearRegistro] Error al Modificar");
			}
	}
	
	/**
	 * Metodo que captura los datos de un archivo .properties
	 */
	private String[] configuracionConexion() {
		
		String user="", pass="", port="", host="", db="", url="";	
		
		Properties propiedadesConexionPostgresql = new Properties();
		try {
			//propiedadesConexionPostgresql.load(getClass().getResourceAsStream("conexion_postgresql.properties"));
			propiedadesConexionPostgresql.load(new FileInputStream(new File("C:\\Users\\Puesto17\\Desktop\\Eclipse\\PostgreSqlCompleto\\src\\util\\conexion_postgresql.properties")));
			user = propiedadesConexionPostgresql.getProperty("user");
			pass = propiedadesConexionPostgresql.getProperty("pass");
			port = propiedadesConexionPostgresql.getProperty("port");
			host = propiedadesConexionPostgresql.getProperty("host");
			db = propiedadesConexionPostgresql.getProperty("db");
			url = "jdbc:postgresql://" + host + ":" + port + "/" + db;
			String[] stringConfiguracion = {url,user,pass};
			
			return stringConfiguracion;
			
		} catch (Exception e) {
			System.out.println("[ERROR-CrudImplementacion-configuracionConexion] - Error al acceder al fichero propiedades de conexion.");
			return null;
		}

	}
}
