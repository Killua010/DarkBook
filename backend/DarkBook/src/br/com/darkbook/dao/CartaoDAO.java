package br.com.darkbook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.com.darkbook.dao.conexao.Conexao;
import br.com.darkbook.dominio.CartaoCredito;
import br.com.darkbook.entidade.EntidadeDominio;

public class CartaoDAO extends AbstrDAO{

	public CartaoDAO() {
		super("cartao_credito", "cat");
	}

	@Override
	public void salvar(EntidadeDominio entidade) {
		this.conexao = (Connection) Conexao.getConexao();
		ResultSet ultimoID = null;
		PreparedStatement comandosSQL = null;
		
		String ComandoUltimoID = "SELECT LAST_INSERT_ID()";
		
		// Tabela Cartão
        String tabelaCartao = ""
        		+ "INSERT INTO cartao_credito ("
        			+ "cat_numero, "
        			+ "cat_nome_impresso, "
        			+ "cat_codigo_seguranca, "
        			+ "cat_preferencial,"
        			+ "cat_ban_id, "
        			+ "cat_status)"
        		+ "VALUES "
        			+ "(?, ?, ?, ?, "
        			+ "(SELECT ban_id FROM bandeira WHERE ban_tipo = ?), "
        			+ "?);";
		
		CartaoCredito car = (CartaoCredito) entidade;
		
		// cartao
        try {
        	
			comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaCartao);
			
	        comandosSQL.setString(1, car.getNumero());
	    	comandosSQL.setString(2, car.getNomeImpresso());
	    	comandosSQL.setString(3, car.getCodSeguranca());
	    	comandosSQL.setBoolean(4, car.isPreferencial());
	    	comandosSQL.setString(5, car.getBandeira().getNome());
	    	comandosSQL.setBoolean(6, true);
	    	
	    	comandosSQL.execute();
	    	
	    	ultimoID = conexao.prepareStatement(ComandoUltimoID).executeQuery();
	    	while(ultimoID.next()) 
	    		car.setId(ultimoID.getLong(1));
	    	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexao.fechar(conexao);
		}
        
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		throw new UnsupportedOperationException("Em desenvolvimento.");
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		this.conexao = (Connection) Conexao.getConexao();
		PreparedStatement comandosSQL = null;
		
		// Tabela Cartão
        String tabelaCartao = "" +
        		"UPDATE cartao_credito " + 
        		"SET " + 
        		"cat_numero = ?," + 
        		"cat_nome_impresso = ?," + 
        		"cat_codigo_seguranca = ?," + 
        		"cat_preferencial = ?," + 
        		"cat_ban_id = (SELECT ban_id FROM bandeira WHERE ban_tipo = ?)" + 
        		"WHERE cat_id = ?;";
		
		CartaoCredito car = (CartaoCredito) entidade;
		
		// cartao
        try {
        	
			comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaCartao);
			
	        comandosSQL.setString(1, car.getNumero());
	    	comandosSQL.setString(2, car.getNomeImpresso());
	    	comandosSQL.setString(3, car.getCodSeguranca());
	    	comandosSQL.setBoolean(4, car.isPreferencial());
	    	comandosSQL.setString(5, car.getBandeira().getNome());
	    	comandosSQL.setLong(6, car.getId());
	    	
	    	comandosSQL.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexao.fechar(conexao);
		}
	}

}
