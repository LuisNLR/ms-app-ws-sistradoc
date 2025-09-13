package pe.com.sistradoc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class TramiteExportInformationDTO {
	
	private String codigoTramite;
	private String fechaIngreso;
	private String fechaTermino;
	private String tipoTramite;
	private String asunto;
	private String solicitante;
	private Integer numeroFolios;
	private String estadoTramite;
	private Integer duracion;
	private Integer diasTranscurridos;
	
}
