package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Respons�vel por fazer a conex�o com o banco de dados
 * @author Diogo Reis Pavan
 *
 */
public class SingleConnection {
	
//	private static String url = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
//	private static String url = "jdbc:postgresql://banco-postgres-java.postgres.database.azure.com";
//	private static String user = "postgres";
//	private static String password = "admin";

    private static String host = "banco-java-curso.postgres.database.azure.com";
    private static String database = "postgres";
    private static String user = "postgres@banco-java-curso";
    private static String password = "Admin123";

	private static Connection connection = null;

//	jdbc:postgresql://banco-java-curso.postgres.database.azure.com:5432/{your_database}?user=postgres@banco-java-curso&password={your_password}&sslmode=required
	
	static {
		conectar();
	}
	
	public SingleConnection() {
		conectar();
	}
	
	private static void conectar() {
		try {
			
			if (connection == null) {
				//carrega o driver que vamos utilizar

//                jdbc:postgresql://banco-postgres-java.postgres.database.azure.com/banco-postgres-java
//                String url = String.format("jdbc:postgresql://%s:%s/%s", host, "5432", database);
                String url = String.format("jdbc:postgresql://%s/%s", host, database);

                Properties properties = new Properties();
                properties.setProperty("user", user);
                properties.setProperty("password", password);
                properties.setProperty("ssl", "true");

				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, properties);
				connection.setAutoCommit(false);
			}
			
		} catch (Exception e) {
			throw new RuntimeException("Erro ao conectar com o banco de dados");
		}

	}
	
	/**
	 * retornar a conex�o
	 * @return
	 */
	public static Connection getConnection() {
		return connection;
	}

}
