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


public class ClienteDAO {
	 // a conexão com o banco de dados
    private Connection conexao;

    public ClienteDAO() {
        this.conexao = (Connection) new Conexao().getConexao();
    }
    
    public void adiciona(Entidade entidade) {
    	Cliente cliente = (Cliente) entidade;
        try {
            // prepared statement para inserção
            PreparedStatement tabelaCliente = (PreparedStatement) conexao.prepareStatement(""
            		+ "insert into cliente ("
            			+ "cli_nome, "
            			+ "cli_sobrenome, "
            			+ "cli_dataNascimento, "
            			+ "cli_cpf, cli_genero)" 
            		+ " values (?,?,?,?,?);");

            // seta os valores
            tabelaCliente.setString(1,cliente.getUsuario().getNome());
            tabelaCliente.setString(2,cliente.getUsuario().getSobrenome());
            tabelaCliente.setDate(3,Date.valueOf(cliente.getUsuario().getDataNascimento()));
            tabelaCliente.setString(4, cliente.getCpf());
            tabelaCliente.setString(5, cliente.getUsuario().getGenero().toString());

            // executa
            tabelaCliente.execute();
            
            Long idCliente = null;
            
            ResultSet teste = conexao.prepareStatement("SELECT LAST_INSERT_ID()").executeQuery();
        	while(teste.next()) {
        		idCliente = teste.getLong(1);
        	}
            
            PreparedStatement tabelaTelefone = (PreparedStatement) conexao.prepareStatement(""
            		+ "INSERT INTO telefone ("
            			+ "tel_ddd, "
            			+ "tel_numero, "
            			+ "tel_tpt_id) "
            		+ "	VALUES ("
            			+ "?, ?, "
            			+ "(SELECT tpt_id FROM tipo_telefone WHERE ? = tipo_telefone.tpt_tipo));");
           
            PreparedStatement tabelaContato = (PreparedStatement) conexao.prepareStatement(""
            		+ "INSERT INTO contato ("
            			+ "con_email) "
            		+ "VALUES (?);");
            
            PreparedStatement tabelaTelefoneContato = (PreparedStatement) conexao.prepareStatement(""
            		+ "INSERT INTO contato_telefone ("
            			+ "cot_tel_id,"
            			+ " cot_con_id) "
            		+ "VALUES (?, ?);");
            List<Long> idsTelefone = new ArrayList<>();
            Long idContato = null;
            
            for(Contato con : cliente.getUsuario().getContatos()) {
            	tabelaContato.setString(1, con.getEmail());
            	tabelaContato.execute();
            	teste = conexao.prepareStatement("SELECT LAST_INSERT_ID()").executeQuery();
            	while(teste.next()) {
            		idContato = teste.getLong(1);
            	}
            	
            	for(Telefone tel : con.getTelefones()) {
            		tabelaTelefone.setString(1, tel.getDdd());
            		tabelaTelefone.setString(2, tel.getNumero());
            		tabelaTelefone.setString(3, tel.getTipoTelefone().toString());
            		tabelaTelefone.execute();
            		teste = conexao.prepareStatement("SELECT LAST_INSERT_ID()").executeQuery();
            		while(teste.next()) {
            			idsTelefone.add(teste.getLong(1));
                	}
            	}
            	for(Long id : idsTelefone) {
            		tabelaTelefoneContato.setLong(1, idContato);
            		tabelaTelefoneContato.setLong(2, id);
            		tabelaTelefoneContato.execute();
            	}
            }// contatos
            
            PreparedStatement tabelaClienteContato = (PreparedStatement) conexao.prepareStatement(""
            		+ "INSERT INTO cliente_contato ("
            			+ "clc_cli_id, "
            			+ "clc_con_id) "
            		+ "VALUES (?, ?);");
            tabelaClienteContato.setLong(1, idCliente);
            tabelaClienteContato.setLong(2, idContato);
            tabelaClienteContato.execute();
            
            PreparedStatement tabelaEnderecoCliente = (PreparedStatement) conexao.prepareStatement("INSERT INTO cliente_endereco("
            		+ "cle_cli_id,"
            		+ "cle_end_id)"
            		+ "VALUES( ?, ?)");
            Long idEndereco = null;
            
            PreparedStatement tabelaEndereco = (PreparedStatement) conexao.prepareStatement(""
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
            			+ "(SELECT cid_id FROM cidade WHERE cid_nome = ? and cid_est_id = (SELECT est_id FROM estado WHERE est_sigla = ?)));");
            
            for(Endereco end : cliente.getEnderecos()) {
            	tabelaEndereco.setString(1, end.getLogradouro());
            	tabelaEndereco.setInt(2, end.getNumero());
            	tabelaEndereco.setString(3, end.getBairro());
            	tabelaEndereco.setString(4, end.getCep());
            	tabelaEndereco.setString(5, end.getObservacao());
            	tabelaEndereco.setString(6, end.getTipoLogradouro().name());
            	tabelaEndereco.setString(7, end.getTipoResidencia().name());
            	tabelaEndereco.setString(8, end.getCidade().getCidade());
            	tabelaEndereco.setString(9, end.getCidade().getEstado().getEstado());
            	tabelaEndereco.execute();
            	teste = conexao.prepareStatement("SELECT LAST_INSERT_ID()").executeQuery();
        		while(teste.next()) {
        			idEndereco = teste.getLong(1);
            	}
        		tabelaEnderecoCliente.setLong(1, idCliente);
        		tabelaEnderecoCliente.setLong(2, idEndereco);
        		tabelaEnderecoCliente.execute();
            }
            
            PreparedStatement tabelaEnderecoEntrega = (PreparedStatement) conexao.prepareStatement(""
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
        			+ "?, ?);");
            for(EnderecoEntrega end : cliente.getEnderecoEntregas()) {
            	tabelaEnderecoEntrega.setString(1, end.getLogradouro());
            	tabelaEnderecoEntrega.setInt(2, end.getNumero());
            	tabelaEnderecoEntrega.setString(3, end.getBairro());
            	tabelaEnderecoEntrega.setString(4, end.getCep());
            	tabelaEnderecoEntrega.setString(5, end.getObservacao());
            	tabelaEnderecoEntrega.setString(6, end.getTipoLogradouro().name());
            	tabelaEnderecoEntrega.setString(7, end.getTipoResidencia().name());
            	tabelaEnderecoEntrega.setString(8, end.getCidade().getCidade());
            	tabelaEnderecoEntrega.setString(9, end.getCidade().getEstado().getEstado());
            	tabelaEnderecoEntrega.setString(10, end.getNomeComposto());
            	tabelaEnderecoEntrega.setBoolean(11, end.isFavorito());
            	tabelaEnderecoEntrega.execute();
            	teste = conexao.prepareStatement("SELECT LAST_INSERT_ID()").executeQuery();
        		while(teste.next()) {
        			idEndereco = teste.getLong(1);
            	}
        		tabelaEnderecoCliente.setLong(1, idCliente);
        		tabelaEnderecoCliente.setLong(2, idEndereco);
        		tabelaEnderecoCliente.execute();
            }
            
            List<Long> idsCartao = new ArrayList<>();
            
            PreparedStatement tabelaCartao = (PreparedStatement) conexao.prepareStatement(""
            		+ "INSERT INTO cartao_credito ("
            			+ "cat_numero, "
            			+ "cat_nome_impresso, "
            			+ "cat_codigo_seguranca, "
            			+ "cat_preferencial,"
            			+ "cat_ban_id)"
            		+ "VALUES "
            			+ "(?, ?, ?, ?, "
            			+ "(SELECT ban_id FROM bandeira WHERE ban_tipo = ?));");
            for(CartaoCredito car : cliente.getCartoes()) {
            	tabelaCartao.setString(1, car.getNumero());
            	tabelaCartao.setString(2, car.getNomeImpresso());
            	tabelaCartao.setString(3, car.getCodSeguranca());
            	tabelaCartao.setBoolean(4, car.isPreferencial());
            	tabelaCartao.setString(5, car.getBandeira().name());
            	tabelaCartao.execute();
            	
            	teste = conexao.prepareStatement("SELECT LAST_INSERT_ID()").executeQuery();
            	while(teste.next()) {
            		idsCartao.add(teste.getLong(1));
            	}
            }
            PreparedStatement tabelaClienteCartao = (PreparedStatement) conexao.prepareStatement(""
            		+ "INSERT INTO cliente_cartao_credito ("
            			+ "ccc_cli_id, "
            			+ "ccc_cat_id) "
            		+ "VALUES "
            			+ "(?, ?);");
            
            for(Long id : idsCartao) {
            	tabelaClienteCartao.setLong(1, idCliente);
            	tabelaClienteCartao.setLong(2, id);
            	tabelaClienteCartao.execute();
            }            
            
            tabelaCliente.close();
        } catch (SQLException e) {
        	e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
//    
//    public List<Contato> getLista() {
//        try {
//            List<Contato> contatos = new ArrayList<Contato>();
//            PreparedStatement stmt = (PreparedStatement) this.connection.
//                    prepareStatement("select * from contatos");
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                // criando o objeto Contato
//                Contato contato = new Contato();
//                contato.setId(rs.getLong("id"));
//                contato.setNome(rs.getString("nome"));
//                contato.setEmail(rs.getString("email"));
//                contato.setEndereco(rs.getString("endereco"));
//
//                // montando a data através do Calendar
//                Calendar data = Calendar.getInstance();
//                data.setTime(rs.getDate("dataNascimento"));
//                contato.setDataNascimento(data);
//
//                // adicionando o objeto à lista
//                contatos.add(contato);
//            }
//            rs.close();
//            stmt.close();
//            return contatos;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
