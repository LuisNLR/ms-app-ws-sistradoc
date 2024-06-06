package pe.com.sistradoc.model;

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

@Entity
@Table(name = "TB_FLUJ_TRAM_DEPE")
@EntityListeners(AuditingEntityListener.class)
public class FlujoTramiteDependencia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDX_FLUJ_TRAM")
	private Long idTipoTramiteRequisito;
	
	@Column(name = "NUM_DURA_DIAS")
	private Integer duracionDias;
	
	@Column(name = "NUM_ORDE_FLUJ")
	private Integer ordenFlujo;
	
	@OneToOne
	@JoinColumn(name = "FK0_DEPE_ENTI_IDX")
	private Dependencia dependencia;
	
	@OneToOne
	@JoinColumn(name = "FK1_TIPO_TRAM_IDX")
	private TipoTramite tipoTramite;

	public Long getIdTipoTramiteRequisito() {
		return idTipoTramiteRequisito;
	}

	public Integer getDuracionDias() {
		return duracionDias;
	}

	public void setDuracionDias(Integer duracionDias) {
		this.duracionDias = duracionDias;
	}

	public Integer getOrdenFlujo() {
		return ordenFlujo;
	}

	public void setOrdenFlujo(Integer ordenFlujo) {
		this.ordenFlujo = ordenFlujo;
	}

	public Dependencia getDependencia() {
		return dependencia;
	}

	public void setDependencia(Dependencia dependencia) {
		this.dependencia = dependencia;
	}

	public TipoTramite getTipoTramite() {
		return tipoTramite;
	}

	public void setTipoTramite(TipoTramite tipoTramite) {
		this.tipoTramite = tipoTramite;
	}
	
}
