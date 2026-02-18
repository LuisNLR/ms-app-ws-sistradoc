package pe.com.sistradoc.services;

import pe.com.sistradoc.dto.TramiteMovimientoDTO;
import pe.com.sistradoc.utils.ValidateService;

public interface ValidateProcessFlow {
	
	public ValidateService validProcess(TramiteMovimientoDTO tramiteMovimientoDto);

}
