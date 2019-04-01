package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import abstracts.AbstractDao;
import beans.BeanCurso;

public class DaoUsuario extends AbstractDao {
			
	public void salvarUsuario(BeanCurso usuario) {
		
		try {
			
			String sql = "insert into usuario(login, senha, nome, fone, cep, rua, bairro, estado, cidade, ibge, "
					+ "foto, contenttype, curriculo, contenttypecurriculo, fotominiatura, fgativo) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getFone());
			insert.setString(5, usuario.getCep());
			insert.setString(6, usuario.getRua());
			insert.setString(7, usuario.getBairro());
			insert.setString(8, usuario.getEstado());
			insert.setString(9, usuario.getCidade());
			insert.setString(10, usuario.getIbge());
			insert.setString(11, usuario.getFotoBase64());
			insert.setString(12, usuario.getContentType());
			insert.setString(13, usuario.getCurriculoBase64());
			insert.setString(14, usuario.getContentTypeCurriculo());
			insert.setString(15, usuario.getFotoBase64Miniatura());
			insert.setBoolean(16, usuario.getFgAtivo());
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
	
	public List<BeanCurso> findAll() throws Exception {
		
		List<BeanCurso> list = new ArrayList<BeanCurso>();
	
		String selectAll = "select * from usuario";
		PreparedStatement select = connection.prepareStatement(selectAll);
		ResultSet rs = select.executeQuery();
		
		while(rs.next()) {
			
			BeanCurso beanCurso = new BeanCurso();
			beanCurso.setIdBeanCurso(rs.getLong("id"));
			beanCurso.setLogin(rs.getString("login"));
			beanCurso.setSenha(rs.getString("senha"));
			beanCurso.setNome(rs.getString("nome"));
			beanCurso.setFone(rs.getString("fone"));
			beanCurso.setCep(rs.getString("cep"));
			beanCurso.setBairro(rs.getString("bairro"));
			beanCurso.setCidade(rs.getString("cidade"));
			beanCurso.setEstado(rs.getString("estado"));
			beanCurso.setIbge(rs.getString("ibge"));
			beanCurso.setRua(rs.getString("rua"));
			//beanCurso.setFotoBase64(rs.getString("foto"));
			beanCurso.setFotoBase64Miniatura(rs.getString("fotominiatura"));
			beanCurso.setContentType(rs.getString("contenttype"));
			beanCurso.setCurriculoBase64(rs.getString("curriculo"));
			beanCurso.setContentTypeCurriculo(rs.getString("contenttypecurriculo"));
			beanCurso.setFgAtivo(rs.getBoolean("fgativo"));

			list.add(beanCurso);
			
		}
		
		return list;
	}
	
	public void delete(String id) {
		
		try {
			String sql = "delete from usuario where id = '"+id+"'";
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

	public BeanCurso consultar(String id) throws Exception {

		BeanCurso beanCurso = new BeanCurso();
		
		String sql = "select * from usuario where id = '"+id+"'";
		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet rs = select.executeQuery();
		
		if (rs.next()) {
			beanCurso.setIdBeanCurso(rs.getLong("id"));
			beanCurso.setLogin(rs.getString("login"));
			beanCurso.setSenha(rs.getString("senha"));
			beanCurso.setNome(rs.getString("nome"));
			beanCurso.setFone(rs.getString("fone"));
			beanCurso.setCep(rs.getString("cep"));
			beanCurso.setBairro(rs.getString("bairro"));
			beanCurso.setCidade(rs.getString("cidade"));
			beanCurso.setEstado(rs.getString("estado"));
			beanCurso.setIbge(rs.getString("ibge"));
			beanCurso.setRua(rs.getString("rua"));
			beanCurso.setFotoBase64(rs.getString("foto"));
			beanCurso.setFotoBase64Miniatura(rs.getString("fotominiatura"));
			beanCurso.setContentType(rs.getString("contenttype"));
			beanCurso.setCurriculoBase64(rs.getString("curriculo"));
			beanCurso.setContentTypeCurriculo(rs.getString("contenttypecurriculo"));
			beanCurso.setFgAtivo(rs.getBoolean("fgativo"));
		}
		
		return beanCurso;
	}

	public void atualizaUsuario(BeanCurso usuario) {
		String sql = "update usuario set login = ?, senha = ?, nome = ?, fone = ?, cep = ?, rua = ?, bairro = ?, estado = ?, "
				+ "cidade = ?, ibge = ?, foto = ?, contenttype = ?, curriculo = ?,  contenttypecurriculo = ?,"
				+ "fotominiatura = ?, fgativo = ? where id = "+usuario.getIdBeanCurso();
		
		try {
			
			PreparedStatement update = connection.prepareStatement(sql);
			update.setString(1, usuario.getLogin());
			update.setString(2, usuario.getSenha());
			update.setString(3, usuario.getNome());
			update.setString(4, usuario.getFone());
			update.setString(5, usuario.getCep());
			update.setString(6, usuario.getRua());
			update.setString(7, usuario.getBairro());
			update.setString(8, usuario.getEstado());
			update.setString(9, usuario.getCidade());
			update.setString(10, usuario.getIbge());
			update.setString(11, usuario.getFotoBase64());
			update.setString(12, usuario.getContentType());
			update.setString(13, usuario.getCurriculoBase64());
			update.setString(14, usuario.getContentTypeCurriculo());
			update.setString(15, usuario.getFotoBase64Miniatura());
			update.setBoolean(16, usuario.getFgAtivo());
			
			update.executeUpdate();
			
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	public boolean isLoginValido(String login) throws Exception {

		String sql = "select count(1) as qtd from usuario where login = '"+login+"'";
		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet rs = select.executeQuery();
		
		return rs.next() ? rs.getInt("qtd") <= 0 : false;
	}
	
	public boolean isSenhaValida(String senha) throws Exception {

		String sql = "select count(1) as qtd from usuario where senha = '"+senha+"'";
		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet rs = select.executeQuery();
		
		return rs.next() ? rs.getInt("qtd") <= 0 : false;
	}
	
	public boolean isLoginValidoUpdate(String login, String id) throws Exception {
	
		String sql = "select count(1) as qtd from usuario where login = '"+login+"' and id <> " + id;
		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet rs = select.executeQuery();
		
		return rs.next() ? rs.getInt("qtd") <= 0 : false;
	}
	
	public boolean isSenhaValidaUpdate(String senha, String id) throws Exception {

		String sql = "select count(1) as qtd from usuario where senha = '"+senha+"' and id <> " + id;
		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet rs = select.executeQuery();
		
		return rs.next() ? rs.getInt("qtd") <= 0 : false;
	}
	
}