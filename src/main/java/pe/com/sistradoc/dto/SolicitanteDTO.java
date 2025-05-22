package pe.com.sistradoc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class SolicitanteDTO {
	
	private String numeroDocumento;
	private String tipoDocumento;
	private String tipoSolicitante;
	private String nombreSolicitante;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String mail;
	private String direccion;
	private String telefono;
	private String representante;

}
