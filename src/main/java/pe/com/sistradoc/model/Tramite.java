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
		
}
