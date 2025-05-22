package pe.com.sistradoc.dto;

import java.util.Date;

public class TramiteTareaDTO {
	
	private String descripcion;
	private String tipoTarea;
	private Date fechaRegistro;
	private TramiteMovimientoDTO tramiteMovimientoDto;
	
	public TramiteTareaDTO() {
		super();
	}
	
	public TramiteTareaDTO(String descripcion, String tipoTarea, Date fechaRegistro,
			TramiteMovimientoDTO tramiteMovimientoDto) {
		super();
		this.descripcion = descripcion;
		this.tipoTarea = tipoTarea;
		this.fechaRegistro = fechaRegistro;
		this.tramiteMovimientoDto = tramiteMovimientoDto;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTipoTarea() {
		return tipoTarea;
	}
	public void setTipoTarea(String tipoTarea) {
		this.tipoTarea = tipoTarea;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public TramiteMovimientoDTO getTramiteMovimientoDto() {
		return tramiteMovimientoDto;
	}
	public void setTramiteMovimientoDto(TramiteMovimientoDTO tramiteMovimientoDto) {
		this.tramiteMovimientoDto = tramiteMovimientoDto;
	}

}
