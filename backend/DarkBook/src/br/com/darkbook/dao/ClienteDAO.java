package br.com.darkbook.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.com.darkbook.dao.conexao.Conexao;
import br.com.darkbook.dominio.Bandeira;
import br.com.darkbook.dominio.CartaoCredito;
import br.com.darkbook.dominio.Cidade;
import br.com.darkbook.dominio.Cliente;
import br.com.darkbook.dominio.Contato;
import br.com.darkbook.dominio.Endereco;
import br.com.darkbook.dominio.EnderecoEntrega;
import br.com.darkbook.dominio.Estado;
import br.com.darkbook.dominio.Genero;
import br.com.darkbook.dominio.Pais;
import br.com.darkbook.dominio.TipoLogradouro;
import br.com.darkbook.dominio.TipoResidencia;
import br.com.darkbook.dominio.TipoTelefone;
import br.com.darkbook.dominio.Usuario;
import br.com.darkbook.entidade.EntidadeDominio;


public class ClienteDAO implements IDAO{
	 // a conexão com o banco de dados
    private Connection conexao;
    
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
    	Cliente cli = (Cliente) entidade;
    	List<EntidadeDominio> cliList = new ArrayList<>();
    	ResultSet resultados;
    	PreparedStatement preparo;
    	PreparedStatement comandosSQL = null;
    	String tabelaCliente;
    	
    	try {
    		this.conexao = (Connection) Conexao.getConexao();
    		if(null != cli.getCpf()) { // buscar por cpf
	    		tabelaCliente = "SELECT * FROM cliente WHERE cli_cpf = ?";
	    		preparo = (PreparedStatement) this.conexao.prepareStatement(tabelaCliente);
	    		preparo.setString(1, cli.getCpf());
    		} else if(null != cli.getUsuario().getSenha() &&
	    			null != cli.getUsuario().getContato().getEmail()) {
	    		tabelaCliente = "SELECT * FROM cliente JOIN contato ON cli_senha = ? AND con_email = ? AND cli_con_id = con_id";
	    		preparo = (PreparedStatement) this.conexao.prepareStatement(tabelaCliente);
	    		preparo.setString(1, cli.getUsuario().getSenha());
	    		preparo.setString(2, cli.getUsuario().getContato().getEmail());
    		} else if(null == cli.getCpf() && null == cli.getId()) {// buscar todos
	          	tabelaCliente = "SELECT * FROM cliente";
	          	preparo = (PreparedStatement) this.conexao.prepareStatement(tabelaCliente);
	    	} else { // buscar por id 
	    		
	          	tabelaCliente = "SELECT * FROM cliente WHERE cli_id = ?";
	          	preparo = (PreparedStatement) this.conexao.prepareStatement(tabelaCliente);
	          	preparo.setLong(1, cli.getId());
	          	
	    	}
    	
    		String tabelaEnderecos = "SELECT * FROM cliente_endereco JOIN endereco ON end_id = cle_end_id AND cle_cli_id = ? "
          			+ "JOIN cidade ON cid_id = end_cid_id JOIN estado ON cid_est_id = est_id JOIN pais ON "
          			+ "est_pai_id = pai_id JOIN tipo_logradouro ON tpl_id = end_tpl_id JOIN tipo_residencia ON "
          			+ "tpr_id = end_tpr_id;";
          	
          	String tabelaContato = "SELECT * FROM contato JOIN tipo_telefone ON tpt_id = con_tpt_id AND con_id = ?;";
          	
          	String tabelaCartoes = "SELECT * FROM cartao_credito JOIN bandeira ON cat_ban_id = ban_id JOIN cliente_cartao_credito "
          			+ "ON ccc_cat_id = cat_id AND ccc_cli_id = ?;";
          	
    		resultados = preparo.executeQuery();

          	while(resultados.next()) {
          		Cliente c = new Cliente();
          		c.setUsuario(new Usuario());
          		c.getUsuario().setContato(new Contato());
          		c.setCpf(resultados.getString("cli_cpf"));
          		c.setId(resultados.getLong("cli_id"));
          		c.getUsuario().setGenero(Genero.valueOf(resultados.getString("cli_genero")));
          		c.getUsuario().setNome(resultados.getString("cli_nome"));
          		c.getUsuario().setSenha(resultados.getString("cli_senha"));
          		c.getUsuario().setSobrenome(resultados.getString("cli_sobrenome"));
          		c.getUsuario().getContato().setId(Long.parseLong(resultados.getString("cli_con_id")));
          		c.getUsuario().setDataNascimento(LocalDate.parse(resultados.getString("cli_dataNascimento")));
          		cliList.add(c);
          	}
          	
          	if(!resultados.first()) {
				return null;
    		}
          	
          	for(EntidadeDominio cli1 : cliList) {
          		EntidadeDominio c = (EntidadeDominio) cli1;
          		((Cliente) c).setEnderecoEntregas(new ArrayList<>());
          		((Cliente) c).setEnderecoCobrancas(new ArrayList<>());
          		comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaEnderecos);
          		comandosSQL.setLong(1, c.getId());
          		
          		resultados = comandosSQL.executeQuery();
              	while(resultados.next()) {
              		Endereco enderecoCobranca;
              		if(null != resultados.getString("end_nome_composto")) {
              			enderecoCobranca = new EnderecoEntrega();
              			((EnderecoEntrega) enderecoCobranca).setNomeComposto(resultados.getString("end_nome_composto"));
              			((EnderecoEntrega) enderecoCobranca).setFavorito(resultados.getBoolean("end_favorido"));
              		} else {
              			enderecoCobranca = new Endereco();
              		}
              		
              		enderecoCobranca.setBairro(resultados.getString("end_bairro"));
              		enderecoCobranca.setCep(resultados.getString("end_cep"));
              		enderecoCobranca.setCidade(new Cidade());
              		enderecoCobranca.getCidade().setEstado(new Estado());
              		enderecoCobranca.getCidade().getEstado().setPais(new Pais());
              		enderecoCobranca.getCidade().getEstado().getPais().setPais(resultados.getString("pai_pais"));
              		enderecoCobranca.getCidade().getEstado().setEstado(resultados.getString("est_sigla"));
              		enderecoCobranca.getCidade().setCidade(resultados.getString("cid_nome"));
              		enderecoCobranca.setLogradouro(resultados.getString("end_logradouro"));
              		enderecoCobranca.setNumero(resultados.getInt("end_numero"));
              		enderecoCobranca.setObservacao(resultados.getString("end_observacao"));
              		
              		TipoLogradouro tpl = new TipoLogradouro();
              		tpl.setNome(resultados.getString("tpl_nome"));
              		tpl.setId(resultados.getLong("tpl_id"));
              		
              		TipoResidencia tpr = new TipoResidencia();
              		tpr.setNome(resultados.getString("tpr_nome"));
              		tpr.setId(resultados.getLong("tpr_id"));
              		
              		enderecoCobranca.setTipoLogradouro(tpl);
              		enderecoCobranca.setTipoResidencia(tpr);
   
              		if(null != resultados.getString("end_nome_composto")) {
              			((Cliente) c).getEnderecoEntregas().add((EnderecoEntrega) enderecoCobranca);
              		} else {
              			((Cliente) c).getEnderecoCobrancas().add(enderecoCobranca);
              		}
              	} // ./enderecos
              	
              	comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaContato);
          		comandosSQL.setLong(1, ((Cliente) c).getUsuario().getContato().getId());
          		
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
              		Bandeira bandeira =  new Bandeira();
              		bandeira.setNome(resultados.getString("ban_tipo"));
              		bandeira.setId(resultados.getLong("ban_id"));
              		cart.setBandeira(bandeira);
              		cart.setCodSeguranca(resultados.getString("cat_codigo_seguranca"));
              		cart.setNomeImpresso(resultados.getString("cat_nome_impresso"));
              		cart.setNumero(resultados.getString("cat_numero"));
              		cart.setPreferencial(resultados.getBoolean("cat_preferencial"));
              		((Cliente) c).getCartoes().add(cart);
              	}// ./cartoes
              	
              	
              	
          	}
          	
          	
          }catch (SQLException e) {
        	  e.printStackTrace();		// printa a pilha de erros
          } finally {
//  				comandosSQL.close();
  				Conexao.fechar(conexao);
  		  }
    	
      	return cliList;
    }
    
    public void salvar(EntidadeDominio entidade)  {
    	IDAO enderecoDAO = new EnderecoDAO();
        IDAO contatoDAO = new ContatoDAO();
        IDAO cartaoDAO = new CartaoDAO();
    	
        PreparedStatement comandosSQL = null;
        ResultSet ultimoID = null;
        Cliente cliente = (Cliente) entidade;
        
        try {
        	this.conexao = (Connection) Conexao.getConexao();
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
            
            // Ligação (Endeços - Cliente)
            String tabelaEnderecoCliente = ""
            		+ "INSERT INTO cliente_endereco("
            			+ "cle_cli_id,"
            			+ "cle_end_id)"
            		+ "VALUES( ?, ?)";
            
            // Ligação (Cliente - Cartao)
            String tabelaClienteCartao = ""
            		+ "INSERT INTO cliente_cartao_credito ("
            			+ "ccc_cli_id, "
            			+ "ccc_cat_id) "
            		+ "VALUES "
            			+ "(?, ?);";
            
            String ComandoUltimoID = "SELECT LAST_INSERT_ID()";            
            
            /* DADOS E EXECUÇÃO DOS COMANDOS SQL */            
            contatoDAO.salvar(cliente.getUsuario().getContato());
            
            // cliente
            comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaCliente);
            comandosSQL.setString(1,cliente.getUsuario().getNome());
            comandosSQL.setString(2,cliente.getUsuario().getSobrenome());
            comandosSQL.setDate(3,Date.valueOf(cliente.getUsuario().getDataNascimento()));
            comandosSQL.setString(4, cliente.getCpf());
            comandosSQL.setString(5, cliente.getUsuario().getGenero().toString());
            comandosSQL.setString(6, cliente.getUsuario().getSenha());
            comandosSQL.setLong(7, cliente.getUsuario().getContato().getId());
            comandosSQL.execute();//
            
            ultimoID = conexao.prepareStatement(ComandoUltimoID).executeQuery();
        	while(ultimoID.next()) 
        		cliente.setId(ultimoID.getLong(1));            
        	
            // endereco cobrança
            for(Endereco end : cliente.getEnderecoCobrancas()) {
            	enderecoDAO.salvar(end);
            	
        		comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaEnderecoCliente);
        		comandosSQL.setLong(1, cliente.getId());
        		comandosSQL.setLong(2, end.getId());
        		comandosSQL.execute();
            }
            
            // endereco entrega
            for(EnderecoEntrega end : cliente.getEnderecoEntregas()) {
            	enderecoDAO.salvar(end);
            	
        		comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaEnderecoCliente);
        		comandosSQL.setLong(1, cliente.getId());
        		comandosSQL.setLong(2, end.getId());
        		comandosSQL.execute();
            }
            
            // cartao
            for(CartaoCredito car : cliente.getCartoes()) {
            	cartaoDAO.salvar(car);
            	
                comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaClienteCartao);
                comandosSQL.setLong(1, cliente.getId());
            	comandosSQL.setLong(2, car.getId());
            	comandosSQL.execute();
            }
            
            
        } catch (SQLException e) {
        	e.printStackTrace();		// printa a pilha de erros
            throw new RuntimeException(e);	// lança uma exceção
        } finally {
        	try {
				comandosSQL.close();
				Conexao.fechar(conexao);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
    }

@Override
public void alterar(EntidadeDominio entidade) {
	PreparedStatement comandosSQL = null;
    Cliente cliente = (Cliente) entidade;
    
    IDAO enderecoDAO = new EnderecoDAO();
    IDAO cartaoDAO = new CartaoDAO();
    
    try {
    	this.conexao = (Connection) Conexao.getConexao();

    	String tabelaCliente = "UPDATE cliente "
    			+ " join contato ON"
    			+ " contato.con_id = cliente.cli_con_id"
    			+ " SET"
    			+ " cliente.cli_nome = ?,"
    			+ " cliente.cli_sobrenome = ?,"
    			+ " contato.con_email = ?,"
    			+ " cliente.cli_genero = ?,"
    			+ " cliente.cli_cpf = ?,"
    			+ " cliente.cli_dataNascimento = ?,"
    			+ " contato.con_tpt_id = (SELECT tpt_id FROM tipo_telefone WHERE tpt_tipo = ?),"
    			+ " contato.con_ddd = ?,"
    			+ " contato.con_numero = ?,"
    			+ " cliente.cli_senha = ?"
    			+ " WHERE cliente.cli_id = ?";
    	
    	 // Ligação (Endeços - Cliente)
        String tabelaEnderecoCliente = ""
        		+ "INSERT INTO cliente_endereco("
        			+ "cle_cli_id,"
        			+ "cle_end_id)"
        		+ "VALUES( ?, ?)";
        
        // Ligação (Cliente - Cartao)
        String tabelaClienteCartao = ""
        		+ "INSERT INTO cliente_cartao_credito ("
        			+ "ccc_cli_id, "
        			+ "ccc_cat_id) "
        		+ "VALUES "
        			+ "(?, ?);";
    	
    	
		comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaCliente);
        comandosSQL.setString(1,cliente.getUsuario().getNome());
        comandosSQL.setString(2,cliente.getUsuario().getSobrenome());
        comandosSQL.setString(3,cliente.getUsuario().getContato().getEmail());
        comandosSQL.setString(4, cliente.getUsuario().getGenero().toString());
        comandosSQL.setString(5, cliente.getCpf());
        comandosSQL.setDate(6, Date.valueOf(cliente.getUsuario().getDataNascimento()));
        comandosSQL.setString(7, cliente.getUsuario().getContato().getTipoTelefone().toString());
        comandosSQL.setString(8, cliente.getUsuario().getContato().getDdd());
        comandosSQL.setString(9, cliente.getUsuario().getContato().getNumero());
        comandosSQL.setString(10, cliente.getUsuario().getSenha());
        comandosSQL.setLong(11, cliente.getId());
        comandosSQL.execute();//
        
        // endereco cobrança
        for(Endereco end : cliente.getEnderecoCobrancas()) {
        	if(null != end.getId()) {
        		enderecoDAO.alterar(end);
        	} else {
	        	enderecoDAO.salvar(end);
	        	
	    		comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaEnderecoCliente);
	    		comandosSQL.setLong(1, cliente.getId());
	    		comandosSQL.setLong(2, end.getId());
	    		comandosSQL.execute();
        	}
        }
        
        // endereco entrega
        for(EnderecoEntrega end : cliente.getEnderecoEntregas()) {
        	
        	if(null != end.getId()) {
        		enderecoDAO.alterar(end);
        	} else {
	        	enderecoDAO.salvar(end);
	        	
	    		comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaEnderecoCliente);
	    		comandosSQL.setLong(1, cliente.getId());
	    		comandosSQL.setLong(2, end.getId());
	    		comandosSQL.execute();
        	}
        }
        
        // cartao
        for(CartaoCredito car : cliente.getCartoes()) {
        	
        	if(null != car.getId()) {
        		cartaoDAO.alterar(car);
        	} else {
	        	cartaoDAO.salvar(car);
	        	
	            comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaClienteCartao);
	            comandosSQL.setLong(1, cliente.getId());
	        	comandosSQL.setLong(2, car.getId());
	        	comandosSQL.execute();
        	}
        }
        
        
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
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

@Override
public void excluir(EntidadeDominio entidade) {
	// TODO Auto-generated method stub
	
}
   
}
