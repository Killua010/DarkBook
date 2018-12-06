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

public class EnderecoDAO implements IDAO {
	
	private Connection conexao;

	public EnderecoDAO() {
		this.conexao = (Connection) Conexao.getConexao();
	}

	@Override
	public void salvar(EntidadeDominio entidade) {
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
	        			+ "end_cid_id)"
	        		+ "VALUES "
	        			+ "(?, ?, ?, ?, ?,"
	        			+ "(SELECT tpl_id FROM tipo_logradouro WHERE tipo_logradouro.tpl_nome = ?),"
	        			+ "(SELECT tpr_id FROM tipo_residencia WHERE tipo_residencia.tpr_nome = ?),"
	        			+ "(SELECT cid_id FROM cidade WHERE cid_nome = ? and cid_est_id = (SELECT est_id FROM estado WHERE est_sigla = ?)));";
	        
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
		    	comandosSQL.execute();
		    	
		    	ultimoID = conexao.prepareStatement(ComandoUltimoID).executeQuery();
	        	while(ultimoID.next()) 
	        		end.setId(ultimoID.getLong(1));
		    	
		    	
			} catch (SQLException e) {
				e.printStackTrace();
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
	        			+ "end_favorido)"
	        		+ "VALUES "
	        			+ "(?, ?, ?, ?, ?,"
	        			+ "(SELECT tpl_id FROM tipo_logradouro WHERE tipo_logradouro.tpl_nome = ?),"
	        			+ "(SELECT tpr_id FROM tipo_residencia WHERE tipo_residencia.tpr_nome = ?),"
	        			+ "(SELECT cid_id FROM cidade WHERE cid_nome = ? and cid_est_id = (SELECT est_id FROM estado WHERE est_sigla = ?)),"
	        			+ "?, ?);";
			
			
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
	        	comandosSQL.execute();
	        	
	        	ultimoID = conexao.prepareStatement(ComandoUltimoID).executeQuery();
	        	while(ultimoID.next()) 
	        		end.setId(ultimoID.getLong(1));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
        
        
        
		

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
//		PreparedStatement comandosSQL = null;
//		String tabelaEnderecos = "SELECT * FROM cliente_endereco JOIN endereco ON end_id = cle_end_id AND cle_cli_id = ? "
//      			+ "JOIN cidade ON cid_id = end_cid_id JOIN estado ON cid_est_id = est_id JOIN pais ON "
//      			+ "est_pai_id = pai_id JOIN tipo_logradouro ON tpl_id = end_tpl_id JOIN tipo_residencia ON "
//      			+ "tpr_id = end_tpr_id;";
//		
//		comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaEnderecos);
//  		comandosSQL.setLong(1, c.getId());
//  		
//  		resultados = comandosSQL.executeQuery();
//      	while(resultados.next()) {
//      		Endereco enderecoCobranca;
//      		if(null != resultados.getString("end_nome_composto")) {
//      			enderecoCobranca = new EnderecoEntrega();
//      			((EnderecoEntrega) enderecoCobranca).setNomeComposto(resultados.getString("end_nome_composto"));
//      			((EnderecoEntrega) enderecoCobranca).setFavorito(resultados.getBoolean("end_favorido"));
//      		} else {
//      			enderecoCobranca = new Endereco();
//      		}
//      		
//      		enderecoCobranca.setBairro(resultados.getString("end_bairro"));
//      		enderecoCobranca.setCep(resultados.getString("end_cep"));
//      		enderecoCobranca.setCidade(new Cidade());
//      		enderecoCobranca.getCidade().setEstado(new Estado());
//      		enderecoCobranca.getCidade().getEstado().setPais(new Pais());
//      		enderecoCobranca.getCidade().getEstado().getPais().setPais(resultados.getString("pai_pais"));
//      		enderecoCobranca.getCidade().getEstado().setEstado(resultados.getString("est_sigla"));
//      		enderecoCobranca.getCidade().setCidade(resultados.getString("cid_nome"));
//      		enderecoCobranca.setLogradouro(resultados.getString("end_logradouro"));
//      		enderecoCobranca.setNumero(resultados.getInt("end_numero"));
//      		enderecoCobranca.setObservacao(resultados.getString("end_observacao"));
//      		
//      		TipoLogradouro tpl = new TipoLogradouro();
//      		tpl.setNome(resultados.getString("tpl_nome"));
//      		tpl.setId(resultados.getLong("tpl_id"));
//      		
//      		TipoResidencia tpr = new TipoResidencia();
//      		tpr.setNome(resultados.getString("tpr_nome"));
//      		tpr.setId(resultados.getLong("tpr_id"));
//      		
//      		enderecoCobranca.setTipoLogradouro(tpl);
//      		enderecoCobranca.setTipoResidencia(tpr);
//
//      		if(null != resultados.getString("end_nome_composto")) {
//      			((Cliente) c).getEnderecoEntregas().add((EnderecoEntrega) enderecoCobranca);
//      		} else {
//      			((Cliente) c).getEnderecoCobrancas().add(enderecoCobranca);
//      		}
//      	} // ./enderecos
		return null;
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		PreparedStatement comandosSQL = null;
		EnderecoEntrega end = (EnderecoEntrega) entidade;
		
        String tabelaEnderecoEntrega = ""
            	+ "UPDATE endereco "
            	+ "SET"
            	+ "(end_logradouro = ?, "
        			+ "end_numero = ?,"
        			+ "end_bairro = ?,"
        			+ "end_cep = ?,"
        			+ "end_observacao = ?,"
        			+ "end_tpl_id = (SELECT tpl_id FROM tipo_logradouro WHERE tipo_logradouro.tpl_nome = ?),"
        			+ "end_tpr_id = (SELECT tpr_id FROM tipo_residencia WHERE tipo_residencia.tpr_nome = ?),"
        			+ "end_cid_id = (SELECT cid_id FROM cidade WHERE cid_nome = ? and cid_est_id = (SELECT est_id FROM estado WHERE est_sigla = ?)),"
        			+ "end_nome_composto = ?, "
        			+ "end_favorido = ?) "
        			+ "WHERE end_id = ?";		
		
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
		}
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		// TODO Auto-generated method stub

	}

}
