package pe.com.sistradoc.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class TramiteMovimientoDTO {
	
	private Long idMovimiento;
	private Date fechaDerivacionPosterior;
	private Date fechaDerivacion;
	private Date fechaRecepcion;
	private String motivoEnvio;
	private Integer numeroMovimiento;
	private Integer pasoActual;
	private String repecpcionDocumento;
	private String ubicacionActual;
	private String estadoMovimiento;
	private DependenciaDTO dependenciaDto;
	private TramiteDTO tramiteDto;

}
