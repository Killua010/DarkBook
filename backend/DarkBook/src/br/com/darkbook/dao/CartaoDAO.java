package br.com.darkbook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.com.darkbook.dao.conexao.Conexao;
import br.com.darkbook.dominio.CartaoCredito;
import br.com.darkbook.entidade.EntidadeDominio;

public class CartaoDAO implements IDAO{
	
	private Connection conexao;

	public CartaoDAO() {
		this.conexao = (Connection) Conexao.getConexao();
	}

	@Override
	public void salvar(EntidadeDominio entidade) {
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
        			+ "cat_ban_id)"
        		+ "VALUES "
        			+ "(?, ?, ?, ?, "
        			+ "(SELECT ban_id FROM bandeira WHERE ban_tipo = ?));";
		
		CartaoCredito car = (CartaoCredito) entidade;
		
		// cartao
        try {
        	
			comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaCartao);
			
	        comandosSQL.setString(1, car.getNumero());
	    	comandosSQL.setString(2, car.getNomeImpresso());
	    	comandosSQL.setString(3, car.getCodSeguranca());
	    	comandosSQL.setBoolean(4, car.isPreferencial());
	    	comandosSQL.setString(5, car.getBandeira().getNome());
	    	
	    	comandosSQL.execute();
	    	
	    	ultimoID = conexao.prepareStatement(ComandoUltimoID).executeQuery();
	    	while(ultimoID.next()) 
	    		car.setId(ultimoID.getLong(1));
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
	}

}
