package pe.com.sistradoc.dto;

public class DependenciaDTO {
	
	private Long idDependencia;
	private String nombreDependencia;
	private AreaEntidadDTO areaEntidadDto;
	
	public DependenciaDTO() {
		super();
	}

	public DependenciaDTO(Long idDependencia, String nombreDependencia, AreaEntidadDTO areaEntidad) {
		super();
		this.idDependencia = idDependencia;
		this.nombreDependencia = nombreDependencia;
		this.areaEntidadDto = areaEntidad;
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

	public AreaEntidadDTO getAreaEntidadDto() {
		return areaEntidadDto;
	}

	public void setAreaEntidadDto(AreaEntidadDTO areaEntidadDto) {
		this.areaEntidadDto = areaEntidadDto;
	}

}
