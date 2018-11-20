package br.com.darkbook.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import br.com.darkbook.entidade.Entidade;


public class ClienteDAO implements IDAO{
	 // a conexão com o banco de dados
    private Connection conexao;

    public ClienteDAO() {
        
    }
    
    public List<Entidade> consultar(Entidade entidade) {
    	Cliente cli = (Cliente) entidade;
    	List<Entidade> cliList = new ArrayList<>();
    	ResultSet resultados;
    	PreparedStatement preparo;
    	PreparedStatement comandosSQL = null;
    	String tabelaCliente;
    	
    	try { 
    		this.conexao = (Connection) Conexao.getConexao();
    		if(null != cli.getUsuario().getSenha() &&
	    			null != cli.getUsuario().getContato().getEmail()) {
	    		tabelaCliente = "SELECT * FROM cliente JOIN contato ON cli_senha = ? AND con_email = ? AND cli_con_id = con_id";
	    		preparo = (PreparedStatement) this.conexao.prepareStatement(tabelaCliente);
	    		preparo.setString(1, cli.getUsuario().getSenha());
	    		preparo.setString(2, cli.getUsuario().getContato().getEmail());
    		} else if(null == cli.getCpf() && null == cli.getId()) {// buscar todos
	          	tabelaCliente = "SELECT * FROM cliente";
	          	preparo = (PreparedStatement) this.conexao.prepareStatement(tabelaCliente);
	    	} else if(null != cli.getCpf()) { // buscar por cpf
	    		tabelaCliente = "SELECT * FROM cliente WHERE cli_cpf = ?";
	    		preparo = (PreparedStatement) this.conexao.prepareStatement(tabelaCliente);
	    		preparo.setString(1, cli.getCpf());
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
          		c.setCpf(resultados.getString("cli_cpf"));
          		c.setId(resultados.getLong("cli_id"));
          		c.setUsuario(new Usuario());
          		c.getUsuario().setGenero(Genero.valueOf(resultados.getString("cli_genero")));
          		c.getUsuario().setNome(resultados.getString("cli_nome"));
          		c.getUsuario().setSenha(resultados.getString("cli_senha"));
          		c.getUsuario().setSobrenome(resultados.getString("cli_sobrenome"));
          		cliList.add(c);
          	}
          	
          	if(!resultados.first()) {
				return null;
    		}
          	
          	for(Entidade cli1 : cliList) {
          		Entidade c = (Entidade) cli1;
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
              		endereco.setNumero(resultados.getInt("end_numero"));
              		endereco.setObservacao(resultados.getString("end_observacao"));
              		
              		TipoLogradouro tpl = new TipoLogradouro();
              		tpl.setNome(resultados.getString("tpl_nome"));
              		tpl.setId(resultados.getLong("tpl_id"));
              		
              		TipoResidencia tpr = new TipoResidencia();
              		tpr.setNome(resultados.getString("tpr_nome"));
              		tpr.setId(resultados.getLong("tpr_id"));
              		
              		endereco.setTipoLogradouro(tpl);
              		endereco.setTipoResidencia(tpr);
   
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
        	  
			if(null != conexao) {
				try {
					conexao.close();
					conexao = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				return null;
			}
  		}
    	
      	return cliList;
    }
    
    public void salvar(Entidade entidade)  {
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
            for(Endereco end : cliente.getEnderecos()) {
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
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
