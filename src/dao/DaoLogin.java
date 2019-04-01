package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import abstracts.AbstractDao;

public class DaoLogin extends AbstractDao {
	
	public boolean validarLogin(String login, String senha) throws Exception {
		
		String sql = "select * from usuario where login = '"+login+"' and senha = '" + senha + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		
		return rs.next();
	}

}
