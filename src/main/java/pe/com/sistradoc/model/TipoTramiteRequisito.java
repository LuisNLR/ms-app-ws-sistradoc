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
@Table(name = "TB_TIPO_TRAM_REQX")
@EntityListeners(AuditingEntityListener.class)
public class TipoTramiteRequisito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDX_TIPO_TRAM_REQX")
	private Long idTipoTramiteRequisito;
	
	@OneToOne
	@JoinColumn(name = "FK0_TIPO_TRAM_IDX")
	private TipoTramite tipoTramite;
	
	@OneToOne
	@JoinColumn(name = "FK1_REQX_PADR_IDX")
	private Requisito requisito;

	public Long getIdTipoTramiteRequisito() {
		return idTipoTramiteRequisito;
	}

	public TipoTramite getTipoTramite() {
		return tipoTramite;
	}

	public void setTipoTramite(TipoTramite tipoTramite) {
		this.tipoTramite = tipoTramite;
	}

	public Requisito getRequisito() {
		return requisito;
	}

	public void setRequisito(Requisito requisito) {
		this.requisito = requisito;
	}
	
}
