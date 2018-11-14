package br.com.darkbook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import br.com.darkbook.dao.conexao.Conexao;
import br.com.darkbook.dominio.TipoLogradouro;
import br.com.darkbook.entidade.Entidade;

public class TipoLogradouroDAO implements IDAO {
	
	private Connection conexao;

	public TipoLogradouroDAO() throws ClassNotFoundException, SQLException {
		super();
		this.conexao = (Connection) Conexao.getConexao();
	}

	@Override
	public void salvar(Entidade entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Entidade> consultar(Entidade entidade) {
		List<Entidade> tiposLogradouro = new ArrayList<>();
		ResultSet resultados;
		
		String tabelaDadosTiposLogradouro = "SELECT * FROM tipo_logradouro;";
		
		
			try {
				resultados = this.conexao.prepareStatement(tabelaDadosTiposLogradouro).executeQuery();
				while(resultados.next()) {
					TipoLogradouro tipoLogradouro = new TipoLogradouro();
					tipoLogradouro.setId(resultados.getLong("tpl_id"));
					tipoLogradouro.setNome(resultados.getString("tpl_nome"));
					tiposLogradouro.add(tipoLogradouro);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return tiposLogradouro;
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
