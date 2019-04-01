package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import servlet.TelefoneServlet;
import abstracts.AbstractDao;
import beans.Telefone;
import beans.Telefone;

public class DaoTelefone extends AbstractDao {
			
	public void salvarTelefone(Telefone telefone) {
		
		try {
			
			String sql = "insert into telefone(numero, tipo, idusuario) values (?, ?, ?)";
			
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getIdUsuario());
			insert.execute();
			
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	public List<Telefone> findTelefoneByIdUsuario(Long idUsuario) throws Exception {
		
		List<Telefone> list = new ArrayList<Telefone>();
	
		String selectAll = "select * from telefone where idusuario = " + idUsuario;
		PreparedStatement select = connection.prepareStatement(selectAll);
		ResultSet rs = select.executeQuery();
		
		while(rs.next()) {
			
			Telefone telefone = new Telefone();
			telefone.setIdTelefone(rs.getLong("idtelefone"));
			telefone.setNumero(rs.getString("numero"));
			telefone.setTipo(rs.getString("tipo"));
			telefone.setIdUsuario(rs.getLong("idusuario"));
			
			list.add(telefone);
			
		}
		
		return list;
	}
	
	public void delete(String id) {
		
		try {
			String sql = "delete from telefone where idtelefone = '"+id+"'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.execute();
			
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
}