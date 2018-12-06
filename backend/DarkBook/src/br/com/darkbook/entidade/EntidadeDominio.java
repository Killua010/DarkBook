package br.com.darkbook.entidade;

import java.time.LocalDateTime;

public class EntidadeDominio {
	protected Long id;
	protected boolean status;
	protected LocalDateTime dataHoraRegistro;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public LocalDateTime getDataHoraRegistro() {
		return dataHoraRegistro;
	}
	public void setDataHoraRegistro(LocalDateTime dataHoraRegistro) {
		this.dataHoraRegistro = dataHoraRegistro;
	}
	
}
