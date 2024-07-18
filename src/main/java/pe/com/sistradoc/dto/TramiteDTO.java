package pe.com.sistradoc.dto;

import java.util.Date;

public class TramiteDTO {
	
	private String codigoTramite;
	private String archivado;
	private String asunto;
	private String observador;
	private String motivoAnulacion;
	private Date fechaRegistro;
	private Date fechaTermino;
	private Integer numeroFolios;
	private String referencia;
	private String estadoTramite;
	private String tipoDocumento;
	private Long tipoTramiteId;
	private String solicitanteId;
	
	public String getCodigoTramite() {
		return codigoTramite;
	}
	public void setCodigoTramite(String codigoTramite) {
		this.codigoTramite = codigoTramite;
	}
	public String getArchivado() {
		return archivado;
	}
	public void setArchivado(String archivado) {
		this.archivado = archivado;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getObservador() {
		return observador;
	}
	public void setObservador(String observador) {
		this.observador = observador;
	}
	public String getMotivoAnulacion() {
		return motivoAnulacion;
	}
	public void setMotivoAnulacion(String motivoAnulacion) {
		this.motivoAnulacion = motivoAnulacion;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Date getFechaTermino() {
		return fechaTermino;
	}
	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}
	public Integer getNumeroFolios() {
		return numeroFolios;
	}
	public void setNumeroFolios(Integer numeroFolios) {
		this.numeroFolios = numeroFolios;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getEstadoTramite() {
		return estadoTramite;
	}
	public void setEstadoTramite(String estadoTramite) {
		this.estadoTramite = estadoTramite;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public Long gettipoTramiteId() {
		return tipoTramiteId;
	}
	public void settipoTramiteId(Long tipoTramiteId) {
		this.tipoTramiteId = tipoTramiteId;
	}
	public String getsolicitanteId() {
		return solicitanteId;
	}
	public void setsolicitanteId(String solicitanteId) {
		this.solicitanteId = solicitanteId;
	}

}
