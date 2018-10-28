package br.com.darkbook.endereco;

public class Endereco {
	private Cidade cidade;
	private TipoLogradouro tipoLogradouro;
	private TipoResidencia tipoResidencia;
	private String logradouro;
	private Integer numero;
	private String bairro;
	private String cep;
	private String observacao;
	
	public String validarEndereco() {
		String erro = "";
		
		if(null == bairro || bairro.isEmpty()) {
			erro += "O dado do bairro do endereço de cobrança é obrigatorio\n";
		}
		
		if(null == cep || cep.isEmpty()) {
			erro += "O dado do cep do endereço de cobrança é obrigatorio\n";
		}
		
		if(cep.length() != 8) {
			erro += "O dado do cep do endereço de cobrança teve ter 8 caracteres\n";
		}
		
		if(null == cidade) {
			erro += "O dado da cidade do endereço de cobrança é obrigatorio\n";
			erro += "O dado do estado do endereço de cobrança é obrigatorio\n";
			erro += "O dado do pais do endereço de cobrança é obrigatorio\n";
		}

		if(cidade.getCidade().isEmpty()) {
			erro += "O dado da cidade do endereço de cobrança é obrigatorio\n";
		}
		
		if(cidade.getEstado().getEstado().isEmpty()) {
			erro += "O dado do estado do endereço de cobrança é obrigatorio\n";
		}
		
		if(cidade.getEstado().getPais().getPais().isEmpty()) {
			erro += "O dado do pais do endereço de cobrança é obrigatorio\n";
		}
		
		if(null == logradouro || logradouro.isEmpty()) {
			erro += "O dado do logradouro do endereço de cobrança é obrigatorio\n";
		}
		
		if(null == numero) {
			erro += "O dado do numero do endereço de cobrança é obrigatorio\n";
		}
		
		if(null == tipoLogradouro) {
			erro += "O dado do tipo de logradouro do cobrança de entrega é obrigatorio\n";
		}
		
		if(null == tipoResidencia){
			erro += "O dado do tipo de residencia do cobrança de entrega é obrigatorio\n";
		}
		
		return erro;
	}
	
	/*
	 * Métodos GETs e SETs
	 */
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public TipoLogradouro getTipoLogradouro() {
		return tipoLogradouro;
	}
	public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}
	public TipoResidencia getTipoResidencia() {
		return tipoResidencia;
	}
	public void setTipoResidencia(TipoResidencia tipoResidencia) {
		this.tipoResidencia = tipoResidencia;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
