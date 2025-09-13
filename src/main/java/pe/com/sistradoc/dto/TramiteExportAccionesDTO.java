package pe.com.sistradoc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class TramiteExportAccionesDTO {
	
	private String dependencia;
	private String tipoTarea;
	private String descripcionTarea;
	private String fechaRegistroTarea;

}
