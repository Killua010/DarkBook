package br.com.darkbook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import br.com.darkbook.dao.conexao.Conexao;
import br.com.darkbook.dominio.TipoResidencia;
import br.com.darkbook.entidade.EntidadeDominio;

public class TipoResidenciaDAO implements IDAO {
	
	private Connection conexao;

	public TipoResidenciaDAO() throws ClassNotFoundException, SQLException {
		super();
		this.conexao = (Connection) Conexao.getConexao();
	}

	@Override
	public void salvar(EntidadeDominio entidade) {
		throw new UnsupportedOperationException("Em desenvolvimento.");
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		List<EntidadeDominio> tiposResidencia = new ArrayList<>();
		
		ResultSet resultados;
		
		String tabelaDadosTiposResidencia = "SELECT * FROM tipo_residencia;";
		
		
			try {
				resultados = this.conexao.prepareStatement(tabelaDadosTiposResidencia).executeQuery();
				while(resultados.next()) {
					TipoResidencia tipoResidencia = new TipoResidencia();
					tipoResidencia.setId(resultados.getLong("tpr_id"));
					tipoResidencia.setNome(resultados.getString("tpr_nome"));
					tiposResidencia.add(tipoResidencia);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return tiposResidencia;
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		throw new UnsupportedOperationException("Em desenvolvimento.");
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		throw new UnsupportedOperationException("Em desenvolvimento.");
	}

}
