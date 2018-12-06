package br.com.darkbook.dao;

import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.com.darkbook.dao.conexao.Conexao;
import br.com.darkbook.dominio.Cliente;
import br.com.darkbook.entidade.EntidadeDominio;

public abstract class AbstrDAO implements IDAO {
	
	protected Connection conexao;
	private String tabela;
	private String trigrama;
	
	public AbstrDAO(String tabela, String trigrama) {
		this.tabela = tabela;
		this.trigrama = trigrama;
	}
	
	@Override
	public void excluir(EntidadeDominio entidade) {
		PreparedStatement comandosSQL = null;
	    
	    try {
	    	this.conexao = (Connection) Conexao.getConexao();

	    	String tabelaCliente = "UPDATE " + tabela 
	    			+ " SET "
	    			+ trigrama +"_status = false"
	    			+ " WHERE "+ trigrama +"_id = ?";
	    	
	    	
			comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaCliente);
	        comandosSQL.setLong(1, entidade.getId());
	        comandosSQL.execute();
	        
	    } catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				comandosSQL.close();
				Conexao.fechar(conexao);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
