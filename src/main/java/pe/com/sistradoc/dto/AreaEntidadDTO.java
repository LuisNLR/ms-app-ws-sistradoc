package pe.com.sistradoc.dto;

public class AreaEntidadDTO {
	
	private Long idArea;
	private String nombreArea;
	
	public AreaEntidadDTO() {
		super();
	}

	public AreaEntidadDTO(Long idArea, String nombreArea) {
		super();
		this.idArea = idArea;
		this.nombreArea = nombreArea;
	}

	public Long getIdArea() {
		return idArea;
	}

	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}

	public String getNombreArea() {
		return nombreArea;
	}

	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}
	
}
