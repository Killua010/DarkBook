package br.com.darkbook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.com.darkbook.dao.conexao.Conexao;
import br.com.darkbook.dominio.Cidade;
import br.com.darkbook.dominio.Cliente;
import br.com.darkbook.dominio.Endereco;
import br.com.darkbook.dominio.EnderecoEntrega;
import br.com.darkbook.dominio.Estado;
import br.com.darkbook.dominio.Pais;
import br.com.darkbook.dominio.TipoLogradouro;
import br.com.darkbook.dominio.TipoResidencia;
import br.com.darkbook.entidade.EntidadeDominio;

public class EnderecoDAO extends AbstrDAO{

	public EnderecoDAO() {
		super("endereco", "end");
	}

	@Override
	public void salvar(EntidadeDominio entidade) {
		this.conexao = (Connection) Conexao.getConexao();
		ResultSet ultimoID = null;
		PreparedStatement comandosSQL = null;
		
		String ComandoUltimoID = "SELECT LAST_INSERT_ID()";
		
		if(entidade.getClass().getName().equals(Endereco.class.getName())) {
			Endereco end = (Endereco) entidade;
			
			// Endereço Cobrança
	        String tabelaEndereco = ""
	        		+ "INSERT INTO endereco ("
	        			+ "end_logradouro, "
	        			+ "end_numero,"
	        			+ "end_bairro,"
	        			+ "end_cep,"
	        			+ "end_observacao,"
	        			+ "end_tpl_id,"
	        			+ "end_tpr_id,"
	        			+ "end_cid_id,"
	        			+ "end_status, "
	        			+ "end_favorido)"
	        		+ "VALUES "
	        			+ "(?, ?, ?, ?, ?,"
	        			+ "(SELECT tpl_id FROM tipo_logradouro WHERE tipo_logradouro.tpl_nome = ?),"
	        			+ "(SELECT tpr_id FROM tipo_residencia WHERE tipo_residencia.tpr_nome = ?),"
	        			+ "(SELECT cid_id FROM cidade WHERE cid_nome = ? and cid_est_id = (SELECT est_id FROM estado WHERE est_sigla = ?)),"
	        			+ "?, ?);";
	        
	        try {
				comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaEndereco);
				comandosSQL.setString(1, end.getLogradouro());
		    	comandosSQL.setInt(2, end.getNumero());
		    	comandosSQL.setString(3, end.getBairro());
		    	comandosSQL.setString(4, end.getCep());
		    	comandosSQL.setString(5, end.getObservacao());
		    	comandosSQL.setString(6, end.getTipoLogradouro().getNome());
		    	comandosSQL.setString(7, end.getTipoResidencia().getNome());
		    	comandosSQL.setString(8, end.getCidade().getCidade());
		    	comandosSQL.setString(9, end.getCidade().getEstado().getEstado());
		    	comandosSQL.setBoolean(10, true);
		    	comandosSQL.setBoolean(11, end.isFavorito());
		    	comandosSQL.execute();
		    	
		    	ultimoID = conexao.prepareStatement(ComandoUltimoID).executeQuery();
	        	while(ultimoID.next()) 
	        		end.setId(ultimoID.getLong(1));
		    	
		    	
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Conexao.fechar(conexao);
			}
		} else if(entidade.getClass().getName().equals(EnderecoEntrega.class.getName())) {
			EnderecoEntrega end = (EnderecoEntrega) entidade;
			// Entereço Entrega
	        String tabelaEnderecoEntrega = ""
	            	+ "INSERT INTO endereco ("
	        			+ "end_logradouro, "
	        			+ "end_numero,"
	        			+ "end_bairro,"
	        			+ "end_cep,"
	        			+ "end_observacao,"
	        			+ "end_tpl_id,"
	        			+ "end_tpr_id,"
	        			+ "end_cid_id, "
	        			+ "end_nome_composto, "
	        			+ "end_favorido, "
	        			+ "end_status)"
	        		+ "VALUES "
	        			+ "(?, ?, ?, ?, ?,"
	        			+ "(SELECT tpl_id FROM tipo_logradouro WHERE tipo_logradouro.tpl_nome = ?),"
	        			+ "(SELECT tpr_id FROM tipo_residencia WHERE tipo_residencia.tpr_nome = ?),"
	        			+ "(SELECT cid_id FROM cidade WHERE cid_nome = ? and cid_est_id = (SELECT est_id FROM estado WHERE est_sigla = ?)),"
	        			+ "?, ?, ?);";
			
			
			try {
				comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaEnderecoEntrega);
				comandosSQL.setString(1, end.getLogradouro());
	        	comandosSQL.setInt(2, end.getNumero());
	        	comandosSQL.setString(3, end.getBairro());
	        	comandosSQL.setString(4, end.getCep());
	        	comandosSQL.setString(5, end.getObservacao());
	        	comandosSQL.setString(6, end.getTipoLogradouro().getNome());
	        	comandosSQL.setString(7, end.getTipoResidencia().getNome());
	        	comandosSQL.setString(8, end.getCidade().getCidade());
	        	comandosSQL.setString(9, end.getCidade().getEstado().getEstado());
	        	comandosSQL.setString(10, end.getNomeComposto());
	        	comandosSQL.setBoolean(11, end.isFavorito());
	        	comandosSQL.setBoolean(12, true);
	        	comandosSQL.execute();
	        	
	        	ultimoID = conexao.prepareStatement(ComandoUltimoID).executeQuery();
	        	while(ultimoID.next()) 
	        		end.setId(ultimoID.getLong(1));
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Conexao.fechar(conexao);
			}
			
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
		
		if(entidade.getClass().getName().equals(Endereco.class.getName())) {
			Endereco end = (Endereco) entidade;
	        String tabelaEndereco = ""
	            	+ "UPDATE endereco SET "
	            	+ "end_logradouro = ?, "
	            	+ "end_numero = ?, "
	            	+ "end_bairro = ?, "
	            	+ "end_cep = ?, "
	            	+ "end_observacao = ?, "
	            	+ "end_tpl_id = (SELECT tpl_id FROM tipo_logradouro WHERE tipo_logradouro.tpl_nome = ?), "
	            	+ "end_tpr_id = (SELECT tpr_id FROM tipo_residencia WHERE tipo_residencia.tpr_nome = ?), "
	            	+ "end_cid_id = (SELECT cid_id FROM cidade WHERE cid_nome = ? and cid_est_id = (SELECT est_id FROM estado WHERE est_sigla = ?)),"
	            	+ "end_favorido = ? " 
	            	+ "WHERE end_id = ?;";		
			
			try {
				comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaEndereco);
				comandosSQL.setString(1, end.getLogradouro());
	        	comandosSQL.setInt(2, end.getNumero());
	        	comandosSQL.setString(3, end.getBairro());
	        	comandosSQL.setString(4, end.getCep());
	        	comandosSQL.setString(5, end.getObservacao());
	        	comandosSQL.setString(6, end.getTipoLogradouro().getNome());
	        	comandosSQL.setString(7, end.getTipoResidencia().getNome());
	        	comandosSQL.setString(8, end.getCidade().getCidade());
	        	comandosSQL.setString(9, end.getCidade().getEstado().getEstado());
	        	comandosSQL.setBoolean(10, end.isFavorito());
	        	comandosSQL.setLong(11, end.getId());
	        	comandosSQL.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Conexao.fechar(conexao);
			}
		} else if(entidade.getClass().getName().equals(EnderecoEntrega.class.getName())) {
			EnderecoEntrega end = (EnderecoEntrega) entidade;
	        String tabelaEnderecoEntrega = " " +
	        		"UPDATE endereco " + 
	            	"SET end_logradouro = ?, " + 
	            	"	 end_numero = ?, " + 
	            	"    end_bairro = ?, " + 
	            	"    end_cep = ?, " + 
	            	"    end_observacao = ?, " + 
	            	"    end_tpl_id = (SELECT tpl_id FROM tipo_logradouro WHERE tipo_logradouro.tpl_nome = ?), " + 
	            	"    end_tpr_id = (SELECT tpr_id FROM tipo_residencia WHERE tipo_residencia.tpr_nome = ?), " + 
	            	"    end_cid_id = (SELECT cid_id FROM cidade WHERE cid_nome = ? and cid_est_id = (SELECT est_id FROM estado WHERE est_sigla = ?)), " + 
	            	"    end_nome_composto = ?, " + 
	            	"    end_favorido = ? " + 
	            	"    WHERE end_id = ?;";		
			
			try {
				comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaEnderecoEntrega);
				comandosSQL.setString(1, end.getLogradouro());
	        	comandosSQL.setInt(2, end.getNumero());
	        	comandosSQL.setString(3, end.getBairro());
	        	comandosSQL.setString(4, end.getCep());
	        	comandosSQL.setString(5, end.getObservacao());
	        	comandosSQL.setString(6, end.getTipoLogradouro().getNome());
	        	comandosSQL.setString(7, end.getTipoResidencia().getNome());
	        	comandosSQL.setString(8, end.getCidade().getCidade());
	        	comandosSQL.setString(9, end.getCidade().getEstado().getEstado());
	        	comandosSQL.setString(10, end.getNomeComposto());
	        	comandosSQL.setBoolean(11, end.isFavorito());
	        	comandosSQL.setLong(12, end.getId());
	        	comandosSQL.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Conexao.fechar(conexao);
			}
		}
		
		
	}

}
