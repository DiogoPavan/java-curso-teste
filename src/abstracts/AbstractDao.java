package abstracts;

import java.sql.Connection;
import java.util.logging.Logger;

import connection.SingleConnection;

/**
 * Essa classe faz a inst�ncia da conex�o com o banco.
 * Desse modo, n�o � necess�rio realizar essa inst�ncia toda vez nas classes DAO, 
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
