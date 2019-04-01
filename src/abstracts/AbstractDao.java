package abstracts;

import java.sql.Connection;
import java.util.logging.Logger;

import connection.SingleConnection;

/**
 * Essa classe faz a instância da conexão com o banco.
 * Desse modo, não é necessário realizar essa instância toda vez nas classes DAO, 
 * basta herdar dessa classe.
 * 
 * @author Diogo Reis Pavan
 */
public abstract class AbstractDao {
	
	public Connection connection;
	
	public AbstractDao() {
		connection = SingleConnection.getConnection();
	}	
	
}
