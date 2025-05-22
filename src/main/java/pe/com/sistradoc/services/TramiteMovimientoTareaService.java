package pe.com.sistradoc.services;

import java.util.List;

import org.hibernate.service.spi.ServiceException;

import pe.com.sistradoc.dto.TramiteTareaDTO;
import pe.com.sistradoc.model.TareaQueryByTramite;
import pe.com.sistradoc.model.TramiteQueryByDeriver;
import pe.com.sistradoc.utils.ValidateService;

public interface TramiteMovimientoTareaService {
	
	public ValidateService registrarTramiteTarea(TramiteTareaDTO tarea) throws ServiceException;
	
	public List<TramiteQueryByDeriver> getListTramiteAsigned(Long idDependencia);
	
	public List<TareaQueryByTramite> getListTareaByTramites(String codigoTramite);

}
