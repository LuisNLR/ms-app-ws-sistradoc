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
@Table(name = "TB_DEPE_ENTI")
@EntityListeners(AuditingEntityListener.class)
public class Dependencia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDX_DEPE_ENTI")
	private Long idDependencia;
	
	@Column(name = "TXT_NOMB_DEPE")
	private String nombreDependencia;
	
	@OneToOne
	@JoinColumn(name = "FK0_AREA_IDEX")
	private AreaEntidad areaEntidad;

	public Long getIdDependencia() {
		return idDependencia;
	}

	public String getNombreDependencia() {
		return nombreDependencia;
	}

	public void setNombreDependencia(String nombreDependencia) {
		this.nombreDependencia = nombreDependencia;
	}

	public AreaEntidad getAreaEntidad() {
		return areaEntidad;
	}

	public void setAreaEntidad(AreaEntidad areaEntidad) {
		this.areaEntidad = areaEntidad;
	}
	
}
