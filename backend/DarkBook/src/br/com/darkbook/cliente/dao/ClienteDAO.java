package br.com.darkbook.cliente.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.com.darkbook.cliente.Bandeira;
import br.com.darkbook.cliente.CartaoCredito;
import br.com.darkbook.cliente.Cliente;
import br.com.darkbook.conexao.Conexao;
import br.com.darkbook.contato.Contato;
import br.com.darkbook.contato.TipoTelefone;
import br.com.darkbook.endereco.Cidade;
import br.com.darkbook.endereco.Endereco;
import br.com.darkbook.endereco.EnderecoEntrega;
import br.com.darkbook.endereco.Estado;
import br.com.darkbook.endereco.Pais;
import br.com.darkbook.endereco.TipoLogradouro;
import br.com.darkbook.endereco.TipoResidencia;
import br.com.darkbook.entidade.Entidade;
import br.com.darkbook.usuario.Genero;
import br.com.darkbook.usuario.Usuario;


public class ClienteDAO {
	 // a conexão com o banco de dados
    private Connection conexao;

    public ClienteDAO() throws ClassNotFoundException, SQLException {
        this.conexao = (Connection) Conexao.getConexao();
    }
    
    public List<Entidade> consultar(Entidade entidade) throws SQLException {
    	Cliente cli = (Cliente) entidade;
    	List<Entidade> cliList = new ArrayList<>();
    	ResultSet resultados;
    	PreparedStatement preparo;
    	
    	if(null == cli.getCpf() && null == cli.getId()) {// buscar todos
	          PreparedStatement comandosSQL = null;
	          
	          try {
	          	String tabelaCliente = "SELECT * FROM cliente";
	          	
	          	String tabelaEnderecos = "SELECT * FROM cliente_endereco JOIN endereco ON end_id = cle_end_id AND cle_cli_id = ? "
	          			+ "JOIN cidade ON cid_id = end_cid_id JOIN estado ON cid_est_id = est_id JOIN pais ON "
	          			+ "est_pai_id = pai_id JOIN tipo_logradouro ON tpl_id = end_tpl_id JOIN tipo_residencia ON "
	          			+ "tpr_id = end_tpr_id;";
	          	
	          	String tabelaContato = "SELECT * FROM contato JOIN tipo_telefone ON tpt_id = con_tpt_id AND con_id = ?;";
	          	
	          	String tabelaCartoes = "SELECT * FROM cartao_credito JOIN bandeira ON cat_ban_id = ban_id JOIN cliente_cartao_credito "
	          			+ "ON ccc_cat_id = cat_id AND ccc_cli_id = ?;";
	          	
	          	
	          	resultados = (ResultSet) this.conexao.prepareStatement(tabelaCliente).executeQuery();
	          	while(resultados.next()) {
	          		Cliente c = new Cliente();
	          		c.setCpf(resultados.getString("cli_cpf"));
	          		c.setId(resultados.getLong("cli_id"));
	          		c.setUsuario(new Usuario());
	          		c.getUsuario().setGenero(Genero.valueOf(resultados.getString("cli_genero")));
	          		c.getUsuario().setNome(resultados.getString("cli_nome"));
	          		c.getUsuario().setSenha(resultados.getString("cli_senha"));
	          		c.getUsuario().setSobrenome(resultados.getString("cli_sobrenome"));
	          		cliList.add(c);
	          	}
	          	
	          	for(Entidade c : cliList) {
	          		((Cliente) c).setEnderecoEntregas(new ArrayList<>());
	          		((Cliente) c).setEnderecos(new ArrayList<>());
	          		comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaEnderecos);
	          		comandosSQL.setLong(1, c.getId());
	          		
	          		resultados = comandosSQL.executeQuery();
	              	while(resultados.next()) {
	              		Endereco endereco;
	              		if(null != resultados.getString("end_nome_composto")) {
	              			endereco = new EnderecoEntrega();
	              			((EnderecoEntrega) endereco).setNomeComposto(resultados.getString("end_nome_composto"));
	              			((EnderecoEntrega) endereco).setFavorito(resultados.getBoolean("end_favorido"));
	              		} else {
	              			endereco = new Endereco();
	              		}
	              		
	              		endereco.setBairro(resultados.getString("end_bairro"));
	              		endereco.setCep(resultados.getString("end_cep"));
	              		endereco.setCidade(new Cidade());
	              		endereco.getCidade().setEstado(new Estado());
	              		endereco.getCidade().getEstado().setPais(new Pais());
	              		endereco.getCidade().getEstado().getPais().setPais(resultados.getString("pai_pais"));
	              		endereco.getCidade().getEstado().setEstado(resultados.getString("est_sigla"));
	              		endereco.getCidade().setCidade(resultados.getString("cid_nome"));
	              		endereco.setLogradouro(resultados.getString("end_logradouro"));
	              		endereco.setNumero(resultados.getShort("end_numero"));
	              		endereco.setObservacao(resultados.getString("end_observacao"));
	              		endereco.setTipoLogradouro(TipoLogradouro.valueOf(resultados.getString("tpl_nome")));
	              		endereco.setTipoResidencia(TipoResidencia.valueOf(resultados.getString("tpr_nome")));
	   
	              		if(null != resultados.getString("end_nome_composto")) {
	              			((Cliente) c).getEnderecoEntregas().add((EnderecoEntrega) endereco);
	              		} else {
	              			((Cliente) c).getEnderecos().add(endereco);
	              		}
	              	} // ./enderecos
	              	
	              	comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaContato);
	          		comandosSQL.setLong(1, c.getId());
	          		
	          		resultados = comandosSQL.executeQuery();
	              	while(resultados.next()) {
	              		Contato con = new Contato();
	              		con.setEmail(resultados.getString("con_email"));
	              		con.setDdd(resultados.getString("con_ddd"));
	              		con.setNumero(resultados.getString("con_numero"));
	              		con.setTipoTelefone(TipoTelefone.valueOf(resultados.getString("tpt_tipo")));
	              		
	              		((Cliente) c).getUsuario().setContato(con);
	              	}// ./contatos
	              	
	              	
	              	comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaCartoes);
	          		comandosSQL.setLong(1, c.getId());
	          		
	          		((Cliente) c).setCartoes(new ArrayList<>());
	          		
	          		resultados = comandosSQL.executeQuery();
	              	while(resultados.next()) {
	              		CartaoCredito cart = new CartaoCredito();
	              		cart.setBandeira(Bandeira.valueOf(resultados.getString("ban_tipo")));
	              		cart.setCodSeguranca(resultados.getString("cat_codigo_seguranca"));
	              		cart.setNomeImpresso(resultados.getString("cat_nome_impresso"));
	              		cart.setNumero(resultados.getString("cat_numero"));
	              		cart.setPreferencial(resultados.getBoolean("cat_preferencial"));
	              		((Cliente) c).getCartoes().add(cart);
	              	}// ./cartoes
	              	
	              	
	              	
	          	}
	          	
	          	
	          }catch (SQLException e) {
	          	e.printStackTrace();		// printa a pilha de erros
	              throw new RuntimeException(e);	// lança uma exceção
	          } finally {
	          	comandosSQL.close();
	          	conexao.close();
	  		}
	      	return cliList;
    	} else if(null == cli.getId()) { // buscar por cpf
    		
    		String tabelaCliente = "SELECT * FROM cliente WHERE cli_cpf = ?";
    		preparo = (PreparedStatement) this.conexao.prepareStatement(tabelaCliente);
    		preparo.setString(1, cli.getCpf());
    		resultados = preparo.executeQuery();
    		if(!resultados.next()) {
    			conexao.close();
    			cliList.add(cli);
    			return cliList;
    		} else {
    			while(resultados.next()) {
    				cli.setId(resultados.getLong("cli_id"));
    			}
    			conexao.close();
    			cliList.add(cli);
    			return cliList;
    		}
    	} else { // buscar por id
    		
    	}
		return cliList;
    }
    
//    public List<Entidade> buscar(Entidade entidade) throws SQLException {
//    	List<Entidade> cliList = new ArrayList<>();
//        PreparedStatement comandosSQL = null;
//        try {
//        	String tabelaCliente = "SELECT * FROM cliente";
//        	
//        	String tabelaEnderecos = "SELECT * FROM cliente_endereco JOIN endereco ON end_id = cle_end_id AND cle_cli_id = ? "
//        			+ "JOIN cidade ON cid_id = end_cid_id JOIN estado ON cid_est_id = est_id JOIN pais ON "
//        			+ "est_pai_id = pai_id JOIN tipo_logradouro ON tpl_id = end_tpl_id JOIN tipo_residencia ON "
//        			+ "tpr_id = end_tpr_id;";
//        	
//        	String tabelaContato = "SELECT * FROM contato JOIN tipo_telefone ON tpt_id = con_tpt_id AND con_id = ?;";
//        	
//        	String tabelaCartoes = "SELECT * FROM cartao_credito JOIN bandeira ON cat_ban_id = ban_id JOIN cliente_cartao_credito "
//        			+ "ON ccc_cat_id = cat_id AND ccc_cli_id = ?;";
//        	
//        	ResultSet resultados;
//        	
//        	resultados = (ResultSet) this.conexao.prepareStatement(tabelaCliente).executeQuery();
//        	while(resultados.next()) {
//        		Cliente c = new Cliente();
//        		c.setCpf(resultados.getString("cli_cpf"));
//        		c.setId(resultados.getLong("cli_id"));
//        		c.setUsuario(new Usuario());
//        		c.getUsuario().setGenero(Genero.valueOf(resultados.getString("cli_genero")));
//        		c.getUsuario().setNome(resultados.getString("cli_nome"));
//        		c.getUsuario().setSenha(resultados.getString("cli_senha"));
//        		c.getUsuario().setSobrenome(resultados.getString("cli_sobrenome"));
//        		cliList.add(c);
//        	}
//        	
//        	for(Entidade c : cliList) {
//        		((Cliente) c).setEnderecoEntregas(new ArrayList<>());
//        		((Cliente) c).setEnderecos(new ArrayList<>());
//        		comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaEnderecos);
//        		comandosSQL.setLong(1, c.getId());
//        		
//        		resultados = comandosSQL.executeQuery();
//            	while(resultados.next()) {
//            		Endereco endereco;
//            		if(null != resultados.getString("end_nome_composto")) {
//            			endereco = new EnderecoEntrega();
//            			((EnderecoEntrega) endereco).setNomeComposto(resultados.getString("end_nome_composto"));
//            			((EnderecoEntrega) endereco).setFavorito(resultados.getBoolean("end_favorido"));
//            		} else {
//            			endereco = new Endereco();
//            		}
//            		
//            		endereco.setBairro(resultados.getString("end_bairro"));
//            		endereco.setCep(resultados.getString("end_cep"));
//            		endereco.setCidade(new Cidade());
//            		endereco.getCidade().setEstado(new Estado());
//            		endereco.getCidade().getEstado().setPais(new Pais());
//            		endereco.getCidade().getEstado().getPais().setPais(resultados.getString("pai_pais"));
//            		endereco.getCidade().getEstado().setEstado(resultados.getString("est_sigla"));
//            		endereco.getCidade().setCidade(resultados.getString("cid_nome"));
//            		endereco.setLogradouro(resultados.getString("end_logradouro"));
//            		endereco.setNumero(resultados.getShort("end_numero"));
//            		endereco.setObservacao(resultados.getString("end_observacao"));
//            		endereco.setTipoLogradouro(TipoLogradouro.valueOf(resultados.getString("tpl_nome")));
//            		endereco.setTipoResidencia(TipoResidencia.valueOf(resultados.getString("tpr_nome")));
// 
//            		if(null != resultados.getString("end_nome_composto")) {
//            			((Cliente) c).getEnderecoEntregas().add((EnderecoEntrega) endereco);
//            		} else {
//            			((Cliente) c).getEnderecos().add(endereco);
//            		}
//            	} // ./enderecos
//            	
//            	comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaContato);
//        		comandosSQL.setLong(1, c.getId());
//        		
//        		resultados = comandosSQL.executeQuery();
//            	while(resultados.next()) {
//            		Contato con = new Contato();
//            		con.setEmail(resultados.getString("con_email"));
//            		con.setDdd(resultados.getString("con_ddd"));
//            		con.setNumero(resultados.getString("con_numero"));
//            		con.setTipoTelefone(TipoTelefone.valueOf(resultados.getString("tpt_tipo")));
//            		
//            		((Cliente) c).getUsuario().setContato(con);
//            	}// ./contatos
//            	
//            	
//            	comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaCartoes);
//        		comandosSQL.setLong(1, c.getId());
//        		
//        		((Cliente) c).setCartoes(new ArrayList<>());
//        		
//        		resultados = comandosSQL.executeQuery();
//            	while(resultados.next()) {
//            		CartaoCredito cart = new CartaoCredito();
//            		cart.setBandeira(Bandeira.valueOf(resultados.getString("ban_tipo")));
//            		cart.setCodSeguranca(resultados.getString("cat_codigo_seguranca"));
//            		cart.setNomeImpresso(resultados.getString("cat_nome_impresso"));
//            		cart.setNumero(resultados.getString("cat_numero"));
//            		cart.setPreferencial(resultados.getBoolean("cat_preferencial"));
//            		((Cliente) c).getCartoes().add(cart);
//            	}// ./cartoes
//            	
//            	
//            	
//        	}
//        	
//        	
//        }catch (SQLException e) {
//        	e.printStackTrace();		// printa a pilha de erros
//            throw new RuntimeException(e);	// lança uma exceção
//        } finally {
//        	comandosSQL.close();
//        	conexao.close();
//		}
//    	return cliList;
//    }
    
    
    
    public void adiciona(Entidade entidade) throws SQLException {
    	Cliente cliente = (Cliente) entidade;
        PreparedStatement comandosSQL = null;
        try {
            /* COMANDOS SQL */
        	
        	// Cliente
            String tabelaCliente = ""
            		+ "insert into cliente ("
            			+ "cli_nome, "
            			+ "cli_sobrenome, "
            			+ "cli_dataNascimento, "
            			+ "cli_cpf,"
            			+ "cli_genero,"
            			+ "cli_senha,"
            			+ "cli_con_id)" 
            		+ " values (?,?,?,?,?,?,?);";
            
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
            
            // Ligação (Cliente - Contato)
            String tabelaClienteContato = ""
            		+ "INSERT INTO cliente_contato ("
            			+ "clc_cli_id, "
            			+ "clc_con_id) "
            		+ "VALUES (?, ?);";
            
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
            
            // Ligação (Endeços - Cliente)
            String tabelaEnderecoCliente = ""
            		+ "INSERT INTO cliente_endereco("
            			+ "cle_cli_id,"
            			+ "cle_end_id)"
            		+ "VALUES( ?, ?)";
            
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
            
            // Ligação (Cliente - Cartao)
            String tabelaClienteCartao = ""
            		+ "INSERT INTO cliente_cartao_credito ("
            			+ "ccc_cli_id, "
            			+ "ccc_cat_id) "
            		+ "VALUES "
            			+ "(?, ?);";
            
            String ComandoUltimoID = "SELECT LAST_INSERT_ID()";
            
            /* IDs */
            Long idCliente = null;
            Long idContato = null;
            Long idEndereco = null;
            List<Long> idsCartao = new ArrayList<>();
            ResultSet ultimoID = null;
            
            /* DADOS E EXECUÇÃO DOS COMANDOS SQL */
            
            Contato con = cliente.getUsuario().getContato();
        	
        	comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaContato);
            	
        	comandosSQL.setString(1, con.getDdd());
    		comandosSQL.setString(2, con.getNumero());
    		System.out.println(con.getTipoTelefone().toString());
    		comandosSQL.setString(3, con.getTipoTelefone().toString());
    		comandosSQL.setString(4, con.getEmail());
        	
        	comandosSQL.execute();
        	
        	ultimoID = conexao.prepareStatement(ComandoUltimoID).executeQuery();
        	while(ultimoID.next()) 
        		idContato = ultimoID.getLong(1);
            
            // cliente
            comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaCliente);
            comandosSQL.setString(1,cliente.getUsuario().getNome());
            comandosSQL.setString(2,cliente.getUsuario().getSobrenome());
            comandosSQL.setDate(3,Date.valueOf(cliente.getUsuario().getDataNascimento()));
            comandosSQL.setString(4, cliente.getCpf());
            comandosSQL.setString(5, cliente.getUsuario().getGenero().toString());
            comandosSQL.setString(6, cliente.getUsuario().getSenha());
            comandosSQL.setLong(7, idContato);
            comandosSQL.execute();//
            
            ultimoID = conexao.prepareStatement(ComandoUltimoID).executeQuery();
        	while(ultimoID.next()) 
        		idCliente = ultimoID.getLong(1);
            
            // endereco cobrança
            for(Endereco end : cliente.getEnderecos()) {
            	comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaEndereco);
            	comandosSQL.setString(1, end.getLogradouro());
            	comandosSQL.setInt(2, end.getNumero());
            	comandosSQL.setString(3, end.getBairro());
            	comandosSQL.setString(4, end.getCep());
            	comandosSQL.setString(5, end.getObservacao());
            	comandosSQL.setString(6, end.getTipoLogradouro().name());
            	comandosSQL.setString(7, end.getTipoResidencia().name());
            	comandosSQL.setString(8, end.getCidade().getCidade());
            	comandosSQL.setString(9, end.getCidade().getEstado().getEstado());
            	comandosSQL.execute();
            	
            	ultimoID = conexao.prepareStatement(ComandoUltimoID).executeQuery();
            	while(ultimoID.next()) 
        			idEndereco = ultimoID.getLong(1);
            	
        		comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaEnderecoCliente);
        		comandosSQL.setLong(1, idCliente);
        		comandosSQL.setLong(2, idEndereco);
        		comandosSQL.execute();
            }
            
            // endereco entrega
            for(EnderecoEntrega end : cliente.getEnderecoEntregas()) {
            	comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaEnderecoEntrega);
            	comandosSQL.setString(1, end.getLogradouro());
            	comandosSQL.setInt(2, end.getNumero());
            	comandosSQL.setString(3, end.getBairro());
            	comandosSQL.setString(4, end.getCep());
            	comandosSQL.setString(5, end.getObservacao());
            	comandosSQL.setString(6, end.getTipoLogradouro().name());
            	comandosSQL.setString(7, end.getTipoResidencia().name());
            	comandosSQL.setString(8, end.getCidade().getCidade());
            	comandosSQL.setString(9, end.getCidade().getEstado().getEstado());
            	comandosSQL.setString(10, end.getNomeComposto());
            	comandosSQL.setBoolean(11, end.isFavorito());
            	comandosSQL.execute();
            	
            	ultimoID = conexao.prepareStatement(ComandoUltimoID).executeQuery();
            	while(ultimoID.next()) 
        			idEndereco = ultimoID.getLong(1);
            	
        		comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaEnderecoCliente);
        		comandosSQL.setLong(1, idCliente);
        		comandosSQL.setLong(2, idEndereco);
        		comandosSQL.execute();
            }
            
            // cartao
            comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaCartao);
            for(CartaoCredito car : cliente.getCartoes()) {
            	comandosSQL.setString(1, car.getNumero());
            	comandosSQL.setString(2, car.getNomeImpresso());
            	comandosSQL.setString(3, car.getCodSeguranca());
            	comandosSQL.setBoolean(4, car.isPreferencial());
            	comandosSQL.setString(5, car.getBandeira().name());
            	comandosSQL.execute();
            	
            	ultimoID = conexao.prepareStatement(ComandoUltimoID).executeQuery();
            	while(ultimoID.next()) 
            		idsCartao.add(ultimoID.getLong(1));
            }
            
            // cartao - cliente
            comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaClienteCartao);
            for(Long id : idsCartao) {
            	comandosSQL.setLong(1, idCliente);
            	comandosSQL.setLong(2, id);
            	comandosSQL.execute();
            }
            
        } catch (SQLException e) {
        	e.printStackTrace();		// printa a pilha de erros
            throw new RuntimeException(e);	// lança uma exceção
        } finally {
        	comandosSQL.close();
        	conexao.close();
		}
    }
   
}
