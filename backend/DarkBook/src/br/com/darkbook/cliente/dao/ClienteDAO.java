package br.com.darkbook.cliente.dao;

import java.sql.Date;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.com.darkbook.cliente.Cliente;
import br.com.darkbook.conexao.Conexao;


public class ClienteDAO {
	 // a conexão com o banco de dados
    private Connection conexao;

    public ClienteDAO() {
        this.conexao = (Connection) new Conexao().getConexao();
    }
    
    public void adiciona(Cliente cliente) {
        String sql = "insert into cliente " +
                "(cli_nome, cli_sobrenome, cli_dataNascimento, cli_cpf, cli_genero)" +
                " values (?,?,?,?,?)";

        try {
            // prepared statement para inserção
            PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql);

            // seta os valores
            stmt.setString(1,cliente.getUsuario().getNome());
            stmt.setString(2,cliente.getUsuario().getSobrenome());
            stmt.setDate(3,Date.valueOf(cliente.getUsuario().getDataNascimento()));
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getUsuario().getGenero().toString());

            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
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
