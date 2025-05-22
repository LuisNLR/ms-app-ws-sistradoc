package pe.com.sistradoc.services.imp;

import java.util.List;
import java.util.Date;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sistradoc.dto.TramiteTareaDTO;
import pe.com.sistradoc.model.TareaQueryByTramite;
import pe.com.sistradoc.model.TramiteMovimiento;
import pe.com.sistradoc.model.TramiteMovimientoTarea;
import pe.com.sistradoc.model.TramiteQueryByDeriver;
import pe.com.sistradoc.repository.TramiteMovimientoRepository;
import pe.com.sistradoc.repository.TramiteMovimientoTareaRepository;
import pe.com.sistradoc.services.TramiteMovimientoTareaService;
import pe.com.sistradoc.utils.ValidateService;
import pe.com.sistradoc.utils.ValidateServiceImp;

@Service
public class TramiteMovimientoTareaServiceImp extends ValidateServiceImp implements TramiteMovimientoTareaService {

	public static final Logger LOGGER = LoggerFactory.getLogger(TramiteMovimientoTareaServiceImp.class);
	
	@Autowired
	TramiteMovimientoTareaRepository tramiteTareaRepository;
	
	@Autowired
	private TramiteMovimientoRepository movimientoRepository;
	
	@Override
	public ValidateService registrarTramiteTarea(TramiteTareaDTO tramiteTareaDto) throws ServiceException {
		ValidateService validate = new ValidateServiceImp();
		validate.setIsvalid(true);
		validate.setMsj("Registro de tarea exitoso");
		
		if(tramiteTareaDto==null) {
			validate.setIsvalid(false);
			validate.setMsj("Tiene que crear una tarea");
		}else if(tramiteTareaDto.getTramiteMovimientoDto()==null) {
			validate.setIsvalid(false);
			validate.setMsj("La tarea creada se debe asignarse a un movimiento");
		}else if(tramiteTareaDto.getTramiteMovimientoDto().getTramiteDto()==null) {
			validate.setIsvalid(false);
			validate.setMsj("La tarea creada se debe asignarse a un tramite");
		}else if(tramiteTareaDto.getTramiteMovimientoDto().getTramiteDto().getCodigoTramite()==null || tramiteTareaDto.getTramiteMovimientoDto().getTramiteDto().getCodigoTramite().isEmpty()) {
			validate.setIsvalid(false);
			validate.setMsj("La tarea creada se debe asignarse a un tramite existente");
		}else if(tramiteTareaDto.getTipoTarea()==null) {
			validate.setIsvalid(false);
			validate.setMsj("La tarea creada se debe asignarse a un movimiento");
		}else if(tramiteTareaDto.getDescripcion()==null || tramiteTareaDto.getDescripcion().isEmpty()) {
			validate.setIsvalid(false);
			validate.setMsj("Ingrese la tarea a registrar");
		}else {
			try {
				Date fecha = new Date();
				TramiteMovimiento movimiento = movimientoRepository.findByTramiteCodigoTramiteAndUbicacionActual(tramiteTareaDto.getTramiteMovimientoDto().getTramiteDto().getCodigoTramite(), "1");
				
				TramiteMovimientoTarea tramiteMovimientoTarea = new TramiteMovimientoTarea(fecha, tramiteTareaDto.getDescripcion(), tramiteTareaDto.getTipoTarea(), movimiento);
				tramiteTareaDto.setFechaRegistro(fecha);
				tramiteTareaRepository.save(tramiteMovimientoTarea);
			} catch (Exception e) {
				LOGGER.error(":::: Proceso registrarTramite. Error Mensaje :::: '{}' ", e.getMessage());
				LOGGER.error(e.getLocalizedMessage(), e);
			}
		}
		
		return validate;
	}

	@Override
	public List<TramiteQueryByDeriver> getListTramiteAsigned(Long idDependencia) {
		return tramiteTareaRepository.getListTramiteAsigned(idDependencia);
	}

	@Override
	public List<TareaQueryByTramite> getListTareaByTramites(String codigoTramite) {
		return tramiteTareaRepository.getListTareasByTramite(codigoTramite);
	}

}
