package pe.com.sistradoc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_TRAM_PADR")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Tramite {
	
	@Id
	@Column(name = "TXT_CODI_TRAM")
	private String codigoTramite;
	
	@Column(name = "TXT_ARCH_TRAM")
	private String archivado;
	
	@Column(name = "TXT_ASUN_TRAM")
	private String asunto;
	
	@Column(name = "TXT_OBSE_TRAM")
	private String observacion;
	
	@Column(name = "TXT_MOTI_ANUL")
	private String motivoAnulacion;
	
	@Column(name = "FEC_INGR_TRAM")
	private Date fechaRegistro;
	
	@Column(name = "FEC_TERM_TRAM")
	private Date fechaTermino;
	
	@Column(name = "NUM_FOLI_TRAM")
	private Integer numeroFolios;
	
	@Column(name = "TXT_REFE_TRAM")
	private String referencia;
	
	@Column(name = "TXT_ESTA_TRAM")
	private String estadoTramite;
	
	@Column(name = "TXT_TIPO_DOCU")
	private String tipoDocumento;
	
	@OneToOne
	@JoinColumn(name = "FK0_TIPO_TRAM_IDX")
	private TipoTramite tipoTramite;
	
	@OneToOne
	@JoinColumn(name = "FK1_SOLI_NUME_DOCU")
	private Solicitante solicitante;
	
	@Column(name = "NUM_IDX_DEPE")
	private Long idDependencia;

	public Tramite() {
		super();
	}
	
	public Tramite(String codigoTramite, String asunto, Date fechaRegistro, Integer numeroFolios, 
				   String referencia, String estadoTramite, String tipoDocumento, String observacion, 
				   TipoTramite tipoTramite, Solicitante solicitante) {
		super();
		this.codigoTramite = codigoTramite;
		this.asunto = asunto;
		this.fechaRegistro = fechaRegistro;
		this.numeroFolios = numeroFolios;
		this.referencia = referencia;
		this.estadoTramite = estadoTramite;
		this.tipoDocumento = tipoDocumento;
		this.observacion = observacion;
		this.tipoTramite = tipoTramite;
		this.solicitante = solicitante;
	}
	
//	public String getCodigoTramite() {
//		return codigoTramite;
//	}
//
//	public void setCodigoTramite(String codigoTramite) {
//		this.codigoTramite = codigoTramite;
//	}
//
//	public String getArchivado() {
//		return archivado;
//	}
//
//	public void setArchivado(String archivado) {
//		this.archivado = archivado;
//	}
//
//	public String getAsunto() {
//		return asunto;
//	}
//
//	public void setAsunto(String asunto) {
//		this.asunto = asunto;
//	}
//
//	public String getObservacion() {
//		return observacion;
//	}
//
//	public void setObservacion(String observacion) {
//		this.observacion = observacion;
//	}
//
//	public String getMotivoAnulacion() {
//		return motivoAnulacion;
//	}
//
//	public void setMotivoAnulacion(String motivoAnulacion) {
//		this.motivoAnulacion = motivoAnulacion;
//	}
//
//	public Date getFechaRegistro() {
//		return fechaRegistro;
//	}
//
//	public void setFechaRegistro(Date fechaRegistro) {
//		this.fechaRegistro = fechaRegistro;
//	}
//
//	public Date getFechaTermino() {
//		return fechaTermino;
//	}
//
//	public void setFechaTermino(Date fechaTermino) {
//		this.fechaTermino = fechaTermino;
//	}
//
//	public Integer getNumeroFolios() {
//		return numeroFolios;
//	}
//
//	public void setNumeroFolios(Integer numeroFolios) {
//		this.numeroFolios = numeroFolios;
//	}
//
//	public String getReferencia() {
//		return referencia;
//	}
//
//	public void setReferencia(String referencia) {
//		this.referencia = referencia;
//	}
//
//	public String getEstadoTramite() {
//		return estadoTramite;
//	}
//
//	public void setEstadoTramite(String estadoTramite) {
//		this.estadoTramite = estadoTramite;
//	}
//
//	public String getTipoDocumento() {
//		return tipoDocumento;
//	}
//
//	public void setTipoDocumento(String tipoDocumento) {
//		this.tipoDocumento = tipoDocumento;
//	}
//
//	public TipoTramite getTipoTramite() {
//		return tipoTramite;
//	}
//
//	public void setTipoTramite(TipoTramite tipoTramite) {
//		this.tipoTramite = tipoTramite;
//	}
//
//	public Solicitante getSolicitante() {
//		return solicitante;
//	}
//
//	public void setSolicitante(Solicitante solicitante) {
//		this.solicitante = solicitante;
//	}
//
//	public Long getIdDependencia() {
//		return idDependencia;
//	}
//
//	public void setIdDependencia(Long idDependencia) {
//		this.idDependencia = idDependencia;
//	}
	
}
