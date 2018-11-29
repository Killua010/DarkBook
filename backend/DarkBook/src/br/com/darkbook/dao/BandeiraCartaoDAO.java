package br.com.darkbook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import br.com.darkbook.dao.conexao.Conexao;
import br.com.darkbook.dominio.Bandeira;
import br.com.darkbook.dominio.TipoResidencia;
import br.com.darkbook.entidade.EntidadeDominio;

public class BandeiraCartaoDAO implements IDAO {
	
	private Connection conexao;

	public BandeiraCartaoDAO() throws ClassNotFoundException, SQLException {
		super();
		this.conexao = (Connection) Conexao.getConexao();
	}

	@Override
	public void salvar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		List<EntidadeDominio> bandeiras = new ArrayList<>();
		
		ResultSet resultados;
		
		String tabelaDadosBandeiraCartao = "SELECT * FROM bandeira";
		
		
			try {
				resultados = this.conexao.prepareStatement(tabelaDadosBandeiraCartao).executeQuery();
				while(resultados.next()) {
					Bandeira bandeira = new Bandeira();
					bandeira.setNome(resultados.getString("ban_tipo"));
              		bandeira.setId(resultados.getLong("ban_id"));
              		bandeiras.add(bandeira);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return bandeiras;
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
	}

}
