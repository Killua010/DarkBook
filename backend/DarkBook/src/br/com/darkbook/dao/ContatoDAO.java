package br.com.darkbook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.com.darkbook.dao.conexao.Conexao;
import br.com.darkbook.dominio.Contato;
import br.com.darkbook.entidade.Entidade;

public class ContatoDAO implements IDAO {
	
	private Connection conexao;

	public ContatoDAO() {
		this.conexao = (Connection) Conexao.getConexao();
	}

	@Override
	public void salvar(Entidade entidade) {
		ResultSet ultimoID = null;
		PreparedStatement comandosSQL = null;
		
		String ComandoUltimoID = "SELECT LAST_INSERT_ID()";
		
		Contato con = (Contato) entidade;
		
		// Contato
        String tabelaContato = ""
        		+ "INSERT INTO contato ("
        			+ "con_ddd, "
        			+ "con_numero, "
        			+ "con_tpt_id, "
        			+ "con_email) "
        		+ "VALUES (?,?,"
        		+ "(SELECT tpt_id FROM tipo_telefone WHERE tpt_tipo = ?),"
        		+ "?);";
    	
    	try {
    		
			comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaContato);
	    	comandosSQL.setString(1, con.getDdd());
			comandosSQL.setString(2, con.getNumero());
			comandosSQL.setString(3, con.getTipoTelefone().toString());
			comandosSQL.setString(4, con.getEmail());
	    	
	    	comandosSQL.execute();
	    	
	    	ultimoID = conexao.prepareStatement(ComandoUltimoID).executeQuery();
        	while(ultimoID.next()) 
        		con.setId(ultimoID.getLong(1));
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        	

	}

	@Override
	public List<Entidade> consultar(Entidade entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alterar(Entidade entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(Entidade entidade) {
		// TODO Auto-generated method stub

	}

}
