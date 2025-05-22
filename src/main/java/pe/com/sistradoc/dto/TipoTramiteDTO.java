package pe.com.sistradoc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class TipoTramiteDTO {

	private Long idTipoTramite;
	private String nombreTipoTramite;
	private Double montoTipoTramite;
	private Integer numeroDias;
	private String estado;
	
	public TipoTramiteDTO() {
		
	}
	
	public TipoTramiteDTO(Long idTipoTramite, String nombreTipoTramite, Double montoTipoTramite, Integer numeroDias,
			String estado) {
		super();
		this.idTipoTramite = idTipoTramite;
		this.nombreTipoTramite = nombreTipoTramite;
		this.montoTipoTramite = montoTipoTramite;
		this.numeroDias = numeroDias;
		this.estado = estado;
	}
	
}
