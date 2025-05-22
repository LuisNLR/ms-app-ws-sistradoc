package pe.com.sistradoc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "TB_TIPO_TRAM")
@EntityListeners(AuditingEntityListener.class)
public class TipoTramite {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDX_TIPO_TRAM")
	private Long idTipoTramite;
	
	@Column(name = "TXT_NOMB_TRAM")
	private String nombreTipoTramite;

	@Column(name = "NUM_MONT_IMPO")
	private Double montoTipoTramite;

	@Column(name = "NUM_NUME_DIAS")
	private Integer numeroDias;

	@Column(name = "TXT_ESTA_TIPO")
	private String estado;

	public Long getIdTipoTramite() {
		return idTipoTramite;
	}

	public String getNombreTipoTramite() {
		return nombreTipoTramite;
	}

	public void setNombreTipoTramite(String nombreTipoTramite) {
		this.nombreTipoTramite = nombreTipoTramite;
	}

	public Double getMontoTipoTramite() {
		return montoTipoTramite;
	}

	public void setMontoTipoTramite(Double montoTipoTramite) {
		this.montoTipoTramite = montoTipoTramite;
	}

	public Integer getNumeroDias() {
		return numeroDias;
	}

	public void setNumeroDias(Integer numeroDias) {
		this.numeroDias = numeroDias;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getFullName() {
		return idTipoTramite + ". " + nombreTipoTramite;
	}

}
