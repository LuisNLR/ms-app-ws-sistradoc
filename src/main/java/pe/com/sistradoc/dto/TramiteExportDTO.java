package pe.com.sistradoc.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class TramiteExportDTO {
	
	private String codigoTramite;
	private TramiteExportInformationDTO tramiteInformation;
	private List<TramiteExportFlujoDTO> listTramiteFlujo;
	private List<TramiteExportAccionesDTO> listrTramiteAcciones;
	

}
