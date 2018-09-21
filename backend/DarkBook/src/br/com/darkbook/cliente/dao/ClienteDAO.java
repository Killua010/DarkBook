package br.com.darkbook.cliente.dao;

import com.mysql.jdbc.Connection;

import br.com.darkbook.cliente.Cliente;
import br.com.darkbook.conexao.Conexao;


public class ClienteDAO {
	 // a conexão com o banco de dados
    private Connection conexao;

    public ClienteDAO() {
        this.conexao = (Connection) new Conexao().getConexao();
    }
    
    public void adiciona(Cliente cliente) {
        String sql = "insert into contatos " +
                "(nome,email,endereco,dataNascimento)" +
                " values (?,?,?,?)";

//        try {
//            // prepared statement para inserção
//            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
//
//            // seta os valores
//            stmt.setString(1,contato.getNome());
//            stmt.setString(2,contato.getEmail());
//            stmt.setString(3,contato.getEndereco());
//            stmt.setDate(4, new Date(
//                    contato.getDataNascimento().getTimeInMillis()));
//
//            // executa
//            stmt.execute();
//            stmt.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
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
