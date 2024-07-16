package pe.com.sistradoc.dto;

import pe.com.sistradoc.model.AreaEntidad;

public class DependenciaDTO {
	
	private Long idDependencia;
	private String nombreDependencia;
	private AreaEntidad areaEntidad;
	
	public DependenciaDTO() {
		super();
	}

	public DependenciaDTO(Long idDependencia, String nombreDependencia, AreaEntidad areaEntidad) {
		super();
		this.idDependencia = idDependencia;
		this.nombreDependencia = nombreDependencia;
		this.areaEntidad = areaEntidad;
	}

	public Long getIdDependencia() {
		return idDependencia;
	}

	public void setIdDependencia(Long idDependencia) {
		this.idDependencia = idDependencia;
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
