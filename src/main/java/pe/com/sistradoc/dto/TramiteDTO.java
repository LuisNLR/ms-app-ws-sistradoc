package pe.com.sistradoc.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class TramiteDTO {
	
	private String codigoTramite;
	private String archivado;
	private String asunto;
	private String observacion;
	private String motivoAnulacion;
	private Date fechaRegistro;
	private Date fechaTermino;
	private Integer numeroFolios;
	private String referencia;
	private String estadoTramite;
	private String tipoDocumento;
	private TipoTramiteDTO tipoTramiteDto;
	private SolicitanteDTO solicitanteDto;

}
