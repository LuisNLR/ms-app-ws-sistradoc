package pe.com.sistradoc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_TRAM_PADR_MOVI")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class TramiteMovimiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDX_MOVI_TRAM")
	private Long idMovimiento;
	
	@Column(name = "FEC_DERI_MOVI_POST")
	private Date fechaDerivacionPosterior;
	
	@Column(name = "FEC_DERI_MOVI")
	private Date fechaDerivacion;
	
	@Column(name = "FEC_RECE_MOVI")
	private Date fechaRecepcion;
	
	@Column(name = "TXT_MOTI_ENVI")
	private String motivoEnvio;
	
	@Column(name = "NUM_NUME_MOVI")
	private Integer numeroMovimiento;
	
	@Column(name = "NUM_PASO_ACTU")
	private Integer pasoActual;
	
	@Column(name = "TXT_RECP_DOCU")
	private String repecpcionDocumento;
	
	@Column(name = "TXT_UBIC_ACTU")
	private String ubicacionActual;
	
	@Column(name = "TXT_ESTA_MOVI")
	private String estadoMovimiento;
	
	@OneToOne
	@JoinColumn(name = "FK0_DEPE_ENTI_IDX")
	private Dependencia dependencia;
	
	@OneToOne
	@JoinColumn(name = "FK1_TRAM_IDX_TRAM")
	private Tramite tramite;

	public TramiteMovimiento() {
		super();
	}
	
	public TramiteMovimiento(Date fechaDerivacion, String motivoEnvio, Integer numeroMovimiento, Integer pasoActual,
			String ubicacionActual, String estadoMovimiento, Dependencia dependencia, Tramite tramite) {
		super();
		this.fechaDerivacion = fechaDerivacion;
		this.motivoEnvio = motivoEnvio;
		this.numeroMovimiento = numeroMovimiento;
		this.pasoActual = pasoActual;
		this.ubicacionActual = ubicacionActual;
		this.estadoMovimiento = estadoMovimiento;
		this.dependencia = dependencia;
		this.tramite = tramite;
	}

//	public Long getIdMovimiento() {
//		return idMovimiento;
//	}
//
//	public Date getFechaDerivacionPosterior() {
//		return fechaDerivacionPosterior;
//	}
//
//	public void setFechaDerivacionPosterior(Date fechaDerivacionPosterior) {
//		this.fechaDerivacionPosterior = fechaDerivacionPosterior;
//	}
//
//	public Date getFechaDerivacion() {
//		return fechaDerivacion;
//	}
//
//	public void setFechaDerivacion(Date fechaDerivacion) {
//		this.fechaDerivacion = fechaDerivacion;
//	}
//
//	public Date getFechaRecepcion() {
//		return fechaRecepcion;
//	}
//
//	public void setFechaRecepcion(Date fechaRecepcion) {
//		this.fechaRecepcion = fechaRecepcion;
//	}
//
//	public String getMotivoEnvio() {
//		return motivoEnvio;
//	}
//
//	public void setMotivoEnvio(String motivoEnvio) {
//		this.motivoEnvio = motivoEnvio;
//	}
//
//	public Integer getNumeroMovimiento() {
//		return numeroMovimiento;
//	}
//
//	public void setNumeroMovimiento(Integer numeroMovimiento) {
//		this.numeroMovimiento = numeroMovimiento;
//	}
//
//	public Integer getPasoActual() {
//		return pasoActual;
//	}
//
//	public void setPasoActual(Integer pasoActual) {
//		this.pasoActual = pasoActual;
//	}
//
//	public String getRepecpcionDocumento() {
//		return repecpcionDocumento;
//	}
//
//	public void setRepecpcionDocumento(String repecpcionDocumento) {
//		this.repecpcionDocumento = repecpcionDocumento;
//	}
//
//	public String getUbicacionActual() {
//		return ubicacionActual;
//	}
//
//	public void setUbicacionActual(String ubicacionActual) {
//		this.ubicacionActual = ubicacionActual;
//	}
//
//	public String getEstadoMovimiento() {
//		return estadoMovimiento;
//	}
//
//	public void setEstadoMovimiento(String estadoMovimiento) {
//		this.estadoMovimiento = estadoMovimiento;
//	}
//
//	public Dependencia getDependencia() {
//		return dependencia;
//	}
//
//	public void setDependencia(Dependencia dependencia) {
//		this.dependencia = dependencia;
//	}
//
//	public Tramite getTramite() {
//		return tramite;
//	}
//
//	public void setTramite(Tramite tramite) {
//		this.tramite = tramite;
//	}

}
