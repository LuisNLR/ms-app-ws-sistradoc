package pe.com.sistradoc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class TramiteExportFlujoDTO {
	
	private Integer numeroMovimiento;
	private String estadoMovimiento;
	private String motivoMovimiento;
	private String dependenciaOrigen;
	private String dependenciadestino;
	private String fechaAsignacion;
	private String fechaFin;
	private Integer diasAsignacionTranscurrido;
	
}
