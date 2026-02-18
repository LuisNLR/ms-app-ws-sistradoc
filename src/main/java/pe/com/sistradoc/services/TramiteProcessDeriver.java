package pe.com.sistradoc.services;

import org.hibernate.service.spi.ServiceException;

import pe.com.sistradoc.dto.TramiteMovimientoDTO;
import pe.com.sistradoc.utils.ValidateService;

public interface TramiteProcessDeriver {
	
	public ValidateService derivarTramite(TramiteMovimientoDTO tramiteMovimientoDto) throws ServiceException;

}
