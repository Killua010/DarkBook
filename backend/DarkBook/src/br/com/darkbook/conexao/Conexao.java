package br.com.darkbook.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public Connection getConexao(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			return DriverManager.getConnection(
					"jdbc:mysql://localhost/darkbook?createDatabaseIfNotExist=true", "root", "");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
