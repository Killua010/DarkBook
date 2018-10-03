package br.com.darkbook.cliente.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.com.darkbook.cliente.CartaoCredito;
import br.com.darkbook.cliente.Cliente;
import br.com.darkbook.conexao.Conexao;
import br.com.darkbook.contato.Contato;
import br.com.darkbook.contato.Telefone;
import br.com.darkbook.endereco.Endereco;
import br.com.darkbook.endereco.EnderecoEntrega;
import br.com.darkbook.entidade.Entidade;
import br.com.darkbook.usuario.Genero;
import br.com.darkbook.usuario.Usuario;


public class ClienteDAO {
	 // a conexão com o banco de dados
    private Connection conexao;

    public ClienteDAO() {
        this.conexao = (Connection) new Conexao().getConexao();
    }
    
    public List<Entidade> buscar(Entidade entidade) throws SQLException {
    	List<Cliente> cliList = new ArrayList<>();
        PreparedStatement comandosSQL = null;
        try {
        	String tabelaCliente = "SELECT * FROM cliente";
        	
        	ResultSet resultados;
        	
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
        	
        	for(Cliente c : cliList) {
        		System.out.println(c.getUsuario().getNome() + " " + c.getUsuario().getSobrenome());
        		System.out.println(c.getCpf());
        	}
        	
        	
        }catch (SQLException e) {
        	e.printStackTrace();		// printa a pilha de erros
            throw new RuntimeException(e);	// lança uma exceção
        } finally {
        	comandosSQL.close();
        	conexao.close();
		}
    	return null;
    }
    
    
    
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
            			+ "cli_senha)" 
            		+ " values (?,?,?,?,?, ?);";
            
            // Telefone
            String tabelaTelefone = ""
            		+ "INSERT INTO telefone ("
            			+ "tel_ddd, "
            			+ "tel_numero, "
            			+ "tel_tpt_id) "
            		+ "	VALUES ("
            			+ "?, ?, "
            			+ "(SELECT tpt_id FROM tipo_telefone WHERE ? = tipo_telefone.tpt_tipo));";
            
            // Contato
            String tabelaContato = ""
            		+ "INSERT INTO contato ("
            			+ "con_email) "
            		+ "VALUES (?);";
            
            // Ligação (Telefone - Contato)
            String tabelaTelefoneContato = ""
            		+ "INSERT INTO contato_telefone ("
            			+ "cot_tel_id,"
            			+ " cot_con_id) "
            		+ "VALUES (?, ?);";
            
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
            List<Long> idsTelefone = new ArrayList<>();
            Long idContato = null;
            Long idEndereco = null;
            List<Long> idsCartao = new ArrayList<>();
            ResultSet ultimoID = null;
            
            /* DADOS E EXECUÇÃO DOS COMANDOS SQL */
            
            // cliente
            comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaCliente);
            comandosSQL.setString(1,cliente.getUsuario().getNome());
            comandosSQL.setString(2,cliente.getUsuario().getSobrenome());
            comandosSQL.setDate(3,Date.valueOf(cliente.getUsuario().getDataNascimento()));
            comandosSQL.setString(4, cliente.getCpf());
            comandosSQL.setString(5, cliente.getUsuario().getGenero().toString());
            comandosSQL.setString(6, cliente.getUsuario().getSenha());
            comandosSQL.execute();
            
            ultimoID = conexao.prepareStatement(ComandoUltimoID).executeQuery();
        	while(ultimoID.next()) 
        		idCliente = ultimoID.getLong(1);
            
        	// contatos
            for(Contato con : cliente.getUsuario().getContatos()) {
            	comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaContato);
            	comandosSQL.setString(1, con.getEmail());
            	comandosSQL.execute();
            	
            	ultimoID = conexao.prepareStatement(ComandoUltimoID).executeQuery();
            	while(ultimoID.next()) 
            		idContato = ultimoID.getLong(1);
            	
            	// telefones
            	for(Telefone tel : con.getTelefones()) {
            		comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaTelefone);
            		comandosSQL.setString(1, tel.getDdd());
            		comandosSQL.setString(2, tel.getNumero());
            		comandosSQL.setString(3, tel.getTipoTelefone().toString());
            		comandosSQL.execute();
            		
            		ultimoID = conexao.prepareStatement(ComandoUltimoID).executeQuery();
                	while(ultimoID.next()) 
            			idsTelefone.add(ultimoID.getLong(1));
            	}
            	
            	// telefone - contato
            	for(Long id : idsTelefone) {
            		comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaTelefoneContato);
            		comandosSQL.setLong(1, idContato);
            		comandosSQL.setLong(2, id);
            		comandosSQL.execute();
            	}
            	
            	//cliente - contato
                comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaClienteContato);
                comandosSQL.setLong(1, idCliente);
                comandosSQL.setLong(2, idContato);
                comandosSQL.execute();
            	
            }// contatos
            
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
