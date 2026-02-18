package pe.com.sistradoc.services.imp;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import pe.com.sistradoc.dto.TramiteMovimientoDTO;
import pe.com.sistradoc.model.Dependencia;
import pe.com.sistradoc.model.TramiteMovimiento;
import pe.com.sistradoc.repository.DependenciaRepository;
import pe.com.sistradoc.repository.TramiteMovimientoRepository;
import pe.com.sistradoc.services.TramiteProcessDeriver;
import pe.com.sistradoc.services.ValidateProcessFlow;
import pe.com.sistradoc.utils.Utils;
import pe.com.sistradoc.utils.ValidateService;
import pe.com.sistradoc.utils.ValidateServiceImp;

public class TramiteProcessDeriverImp implements TramiteProcessDeriver, ValidateProcessFlow {

	private static final Logger LOGGER = LoggerFactory.getLogger(TramiteProcessDeriverImp.class);
	
	@Autowired
	private TramiteMovimientoRepository movimientoRepository;
	
	@Autowired
	private DependenciaRepository dependenciaRepository;
	
	private String correlationId = UUID.randomUUID().toString();
	
	@Override
	public ValidateService derivarTramite(TramiteMovimientoDTO movimientoDto) throws ServiceException {
		try {
			if(validProcess(movimientoDto).isValid()) {
				Date fechaDerivacion = new Date();
				Dependencia dependenciaDestino;
				TramiteMovimiento movimientoAnterior = getPreviousMovement((movimientoDto!=null && movimientoDto.getTramiteDto()!=null) ? 
						 movimientoDto.getTramiteDto().getCodigoTramite() : "TRNULL");
				
				//Manipulación en movimiento anterior
				movimientoAnterior.setFechaDerivacionPosterior(fechaDerivacion);
				movimientoAnterior.setUbicacionActual(Utils.flagEstadoInactivo);
				movimientoRepository.save(movimientoAnterior);
				
				//Manipulación en movimiento nuevo
				if(Objects.equals(movimientoAnterior.getTramite().getTipoTramite().getIdTipoTramite(), Utils.valueDefaultLongOne)) {
					dependenciaDestino = new Dependencia(movimientoDto.getDependenciaDto().getIdDependencia(), 
														 movimientoDto.getDependenciaDto().getNombreDependencia());
				}else {
					dependenciaDestino = dependenciaRepository.findDependenciaByPasoAndTipoTramite(movimientoAnterior.getPasoActual() +1, movimientoAnterior.getTramite().getTipoTramite().getIdTipoTramite());
				}
				TramiteMovimiento movimientoNuevo = new TramiteMovimiento(fechaDerivacion, 
														movimientoDto.getMotivoEnvio(), 
														movimientoAnterior.getNumeroMovimiento() + 1, 
														movimientoAnterior.getPasoActual() + 1, 
														Utils.flagEstadoActivo, 
														Utils.estadoMovimientoDerivado, 
														dependenciaDestino, 
														movimientoAnterior.getTramite());
				movimientoRepository.save(movimientoNuevo);
			}
		} catch (Exception e) {
			LOGGER.error(correlationId + ":::: Proceso derivarTramite. Error Mensaje :::: '{}' ", e.getMessage());
			LOGGER.error(e.getLocalizedMessage(), e);
		}
		return null;
	}
	
	@Override
	public ValidateService validProcess(TramiteMovimientoDTO movimientoDto) {
		ValidateService validate = new ValidateServiceImp(true, "Registro de Derivación exitoso", Utils.STATUS_CODE_OK);

		try {
			TramiteMovimiento movimientoAnterior = getPreviousMovement((movimientoDto!=null && movimientoDto.getTramiteDto()!=null) ? 
																		movimientoDto.getTramiteDto().getCodigoTramite() : "TRNULL");
			
			Dependencia dependenciaSiguiente = dependenciaRepository.findDependenciaByPasoAndTipoTramite(movimientoAnterior.getPasoActual() +1, movimientoAnterior.getTramite().getTipoTramite().getIdTipoTramite());
			
			if(movimientoDto==null) {
				validate.setValid(false);
				validate.setMessage("No se ha creado el nuevo movimiento");
				validate.setStatusCode(Utils.STATUS_CODE_BAD_REQUEST);
			}else if(movimientoDto.getTramiteDto()==null) {
				validate.setValid(false);
				validate.setMessage("Asigne o seleccione un trámite");
				validate.setStatusCode(Utils.STATUS_CODE_BAD_REQUEST);
			}else if(movimientoDto.getTramiteDto().getCodigoTramite()==null) {
				validate.setValid(false);
				validate.setMessage("Asigne o seleccione un tipo de trámite");
				validate.setStatusCode(Utils.STATUS_CODE_BAD_REQUEST);
			}else if(movimientoAnterior==null) {
				validate.setValid(false);
				validate.setMessage("Asigne o seleccione un tramite existente");
				validate.setStatusCode(Utils.STATUS_CODE_BAD_REQUEST);
			}else if(movimientoAnterior.getPasoActual()==null ) {
				validate.setValid(false);
				validate.setMessage("No existe el paso indicado");
				validate.setStatusCode(Utils.STATUS_CODE_BAD_REQUEST);
			}else if(movimientoAnterior.getTramite().getTipoTramite().getIdTipoTramite()==null) {
				validate.setValid(false);
				validate.setMessage("El movimiento anterior no tiene ID Tipo de tramite");
				validate.setStatusCode(Utils.STATUS_CODE_BAD_REQUEST);
			}else if(movimientoDto.getMotivoEnvio()==null || movimientoDto.getMotivoEnvio().isEmpty()) {
				validate.setValid(false);
				validate.setMessage("Asigne un motivo para derivar dicho tramite");
			}else if(movimientoAnterior.getTramite().getTipoTramite().getIdTipoTramite().equals(Utils.valueDefaultLongOne) && 
					movimientoDto.getDependenciaDto()==null) {
				validate.setValid(false);
				validate.setMessage("Asigne la dependencia, este tramite no tiene efecto administrativo");
			}else if(movimientoAnterior.getTramite().getTipoTramite().getIdTipoTramite().equals(Utils.valueDefaultLongOne) && 
					dependenciaSiguiente==null) {
				validate.setValid(false);
				validate.setMessage("Dicho trámite ya no tiene más dependencias a derivar");
			}
		} catch (Exception e) {
			LOGGER.error(correlationId + ":::: Proceso derivarTramite. Error Mensaje :::: '{}' ", e.getMessage());
			LOGGER.error(e.getLocalizedMessage(), e);
		}
		return validate;
	}
	
	private TramiteMovimiento getPreviousMovement(String codigoTramite) {
		return movimientoRepository.findByTramiteCodigoTramiteAndUbicacionActual(codigoTramite, "1");
	}
	
	private Dependencia getNextDependency() {
		return null;
	}

}
