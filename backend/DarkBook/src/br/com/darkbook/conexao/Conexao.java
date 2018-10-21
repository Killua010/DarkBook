package br.com.darkbook.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private static String driver;
	private static String url;
	private static String usuario;
	private static String senha;
	
	public static Connection getConexao() throws ClassNotFoundException, SQLException{
		
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost/darkbook?createDatabaseIfNotExist=true";
		usuario = "darkbook";
		senha = "darkbook";
		
		Class.forName(driver);
		
		return DriverManager.getConnection(url, usuario, senha);
	}
}
