package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import abstracts.AbstractDao;
import beans.Produto;

public class DaoProduto extends AbstractDao {

	public void salvarProduto(Produto produto) {
		
		try {

			String sql = "insert into produto(descricao, quantidade, valor) values (?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, produto.getDescricao());
			insert.setInt(2, produto.getQuantidade());
			insert.setBigDecimal(3, produto.getValor());

			insert.execute();

			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

	}

	public List<Produto> findAll() throws Exception {

		List<Produto> list = new ArrayList<Produto>();

		String selectAll = "select * from produto order by idproduto asc";
		PreparedStatement select = connection.prepareStatement(selectAll);
		ResultSet rs = select.executeQuery();

		while (rs.next()) {

			Produto produto = new Produto();
			produto.setIdProduto(rs.getLong("idproduto"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setQuantidade(rs.getInt("quantidade"));
			produto.setValor(rs.getBigDecimal("valor"));
			
			list.add(produto);

		}

		return list;
	}

	public Produto consultar(String id) throws Exception {
		
		String selectOne = "select * from produto where idproduto = '"+id+"'";
		PreparedStatement select = connection.prepareStatement(selectOne);
		ResultSet rs = select.executeQuery();

		Produto produto = new Produto();
		
		if (rs.next()) {
			produto.setIdProduto(rs.getLong("idproduto"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setQuantidade(rs.getInt("quantidade"));
			produto.setValor(rs.getBigDecimal("valor"));
		}
		
		return produto;
	}

	public void atualizarProduto(Produto produto) {
		
		String sql = "update produto set descricao = ?, quantidade = ?, valor = ? where idproduto = " + produto.getIdProduto();
		
		try {
			PreparedStatement update = connection.prepareStatement(sql);
			update.setString(1, produto.getDescricao());
			update.setInt(2, produto.getQuantidade());
			update.setBigDecimal(3, produto.getValor());
			
			update.executeUpdate();
			
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
				
	}
	
	public void deleteProduto(String id) {
		
		try {
			String sql = "delete from produto where idproduto = '"+id+"'";
			PreparedStatement delete = connection.prepareStatement(sql);
			delete.execute();
			
			connection.commit();			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	public boolean isDescricaoValida(String descricao) throws Exception {

		String sql = "select count(1) as qtd from produto where descricao = '"+descricao+"'";
		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet rs = select.executeQuery();
		
		return rs.next() ? rs.getInt("qtd") <= 0 : false;
	}
	
	public boolean isDescricaoValidaUpdate(String senha, String id) throws Exception {

		String sql = "select count(1) as qtd from produto where descricao = '"+senha+"' and idproduto <> " + id;
		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet rs = select.executeQuery();
		
		return rs.next() ? rs.getInt("qtd") <= 0 : false;
	}

}
