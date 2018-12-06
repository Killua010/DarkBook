package br.com.darkbook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.com.darkbook.dao.conexao.Conexao;
import br.com.darkbook.dominio.Cidade;
import br.com.darkbook.dominio.Estado;
import br.com.darkbook.dominio.Pais;
import br.com.darkbook.entidade.EntidadeDominio;

public class PaisDAO implements IDAO {
	
	private Connection conexao;

	public PaisDAO() throws ClassNotFoundException, SQLException {
		super();
		this.conexao = (Connection) Conexao.getConexao();
	}

	@Override
	public void salvar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		List<EntidadeDominio> paises = new ArrayList<>();
		ResultSet resultadosPais;
		ResultSet resultadosEstado;
		ResultSet resultadosCidade;
		PreparedStatement comandosSQL;
		
		String tabelaDadosPais = "SELECT * FROM pais;";
		
		String tabelaDadosEstados = "SELECT * from estado WHERE est_pai_id = ?;";
		
		String tabelaDadosCidades = "SELECT * FROM cidade WHERE cid_est_id = ?;";
		
		try {
			resultadosPais = this.conexao.prepareStatement(tabelaDadosPais).executeQuery();
			while(resultadosPais.next()) {
				Pais pais = new Pais();
				pais.setId(resultadosPais.getLong("pai_id"));
				pais.setPais(resultadosPais.getString("pai_pais"));
				pais.setEstados(new ArrayList<>());
				
				comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaDadosEstados);
				comandosSQL.setLong(1, pais.getId());
				
				resultadosEstado = comandosSQL.executeQuery();
				
				while(resultadosEstado.next()) {
					Estado estado = new Estado();
					estado.setId(resultadosEstado.getLong("est_id"));
					estado.setEstado(resultadosEstado.getString("est_nome"));
					estado.setSigla(resultadosEstado.getString("est_sigla"));
					estado.setCidades(new ArrayList<>());
					
					comandosSQL = (PreparedStatement) this.conexao.prepareStatement(tabelaDadosCidades);
					comandosSQL.setLong(1, estado.getId());
					
					resultadosCidade = comandosSQL.executeQuery();
					
					while(resultadosCidade.next()) {
						Cidade cidade = new Cidade();
						cidade.setId(resultadosCidade.getLong("cid_id"));
						cidade.setCidade(resultadosCidade.getString("cid_nome"));
						
						estado.getCidades().add(cidade);
					}
					
					pais.getEstados().add(estado);
				}
				
				paises.add(pais);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return paises;
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
