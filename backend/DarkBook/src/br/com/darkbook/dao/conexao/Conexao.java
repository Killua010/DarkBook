package br.com.darkbook.dao.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private static String driver;
	private static String url;
	private static String usuario;
	private static String senha;
	
	public static Connection getConexao() {
		
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost/darkbook?createDatabaseIfNotExist=true";
		usuario = "root";
		senha = "";
		
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, usuario, senha);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void fechar(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
}
