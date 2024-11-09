package pe.com.sistradoc.services.imp;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sistradoc.dto.TramiteDTO;
import pe.com.sistradoc.dto.TramiteMovimientoDTO;
import pe.com.sistradoc.model.Dependencia;
import pe.com.sistradoc.model.Solicitante;
import pe.com.sistradoc.model.TipoTramite;
import pe.com.sistradoc.model.Tramite;
import pe.com.sistradoc.model.TramiteCode;
import pe.com.sistradoc.model.TramiteMovimiento;
import pe.com.sistradoc.repository.DependenciaRepository;
import pe.com.sistradoc.repository.SolicitanteRepository;
import pe.com.sistradoc.repository.TipoTramiteRepository;
import pe.com.sistradoc.repository.TramiteMovimientoRepository;
import pe.com.sistradoc.repository.TramiteRepository;
import pe.com.sistradoc.services.DependenciaService;
import pe.com.sistradoc.services.SolicitanteService;
import pe.com.sistradoc.services.TramiteService;
import pe.com.sistradoc.utils.Utils;
import pe.com.sistradoc.utils.ValidateService;
import pe.com.sistradoc.utils.ValidateServiceImp;

@Service
public class TramiteServiceImp extends ValidateServiceImp implements TramiteService {

	public final static Logger LOGGER = LoggerFactory.getLogger(TramiteServiceImp.class);
	
	@Autowired
	private TramiteRepository tramiteRepository;
	
	@Autowired
	private TramiteMovimientoRepository movimientoRepository;
	
	@Autowired
	private SolicitanteRepository solicitanteRepository;
	
	@Autowired
	private TipoTramiteRepository tipoTramiteRepository;
	
	@Autowired
	private SolicitanteService solicitanteService;
	
	@Autowired
	private DependenciaRepository dependenciaRepository;
	
	@Autowired
	private DependenciaService dependenciaService;
	
	@Override
	public ValidateService registrarTramite(TramiteDTO tramiteDto) throws ServiceException {
		String correlationId = UUID.randomUUID().toString();
		LOGGER.info(correlationId + ":::: Proceso registrarTramite. Inicio :::: '{}' ", TramiteServiceImp.class.getName());
		ValidateService validate = new ValidateServiceImp();
		validate.setIsvalid(true);
		validate.setMsj("Registro de tramite exitoso");
		
		ValidateService validateSolicitante = null;
		
		//Obtención de valores necesarios
		TipoTramite tipoTramite = null;
		Solicitante solicitante = null;
		Dependencia dependencia=null;
		try {
			validateSolicitante = solicitanteService.registrarSolicitante(tramiteDto.getSolicitanteDto());
			tipoTramite = tipoTramiteRepository.findByIdTipoTramite(tramiteDto.getTipoTramiteDto().getIdTipoTramite());
			solicitante = solicitanteRepository.findByNumeroDocumento(tramiteDto.getSolicitanteDto().getNumeroDocumento());
			dependencia = dependenciaRepository.findByIdDependencia(Utils.valueDefaultLongOne);
		} catch (Exception e) {
			LOGGER.error(correlationId + ":::: Proceso registrarTramite. Error Mensaje :::: '{}' ", e.getMessage());
			LOGGER.error(e.getLocalizedMessage(), e);
		}

		//Validaciones funcionales para el registro de trámite
		if(tramiteDto==null) {
			validate.setIsvalid(false);
			validate.setMsj("No se ha creado el tramite");
		}else if(tramiteDto.getAsunto()==null || tramiteDto.getAsunto().isEmpty()) {
			validate.setIsvalid(false);
			validate.setMsj("Ingrese el asunto del trámite");
		}else if(!validateSolicitante.isIsvalid()) {
			validate.setMsj(validateSolicitante.getMsj());
			validate.setIsvalid(validateSolicitante.isIsvalid());
		}else if(solicitante==null) {
			validate.setIsvalid(false);
			validate.setMsj("Ingrese o asigne el solicitante");
		}else if(tipoTramite==null) {
			validate.setIsvalid(false);
			validate.setMsj("Asigne el tipo de tramite");
		}else if(tramiteDto.getTipoDocumento()==null || tramiteDto.getTipoDocumento().isEmpty()) {
			validate.setIsvalid(false);
			validate.setMsj("Asigne el tipo de trámite");
		}else if(tramiteDto.getNumeroFolios()<=0) {
			validate.setIsvalid(false);
			validate.setMsj("Ingrese la cantidad de folios presentados");
		}else {
			TramiteCode tramiteCode = tramiteRepository.getCodeTramite();
			String codigoTramite = generateCode(tramiteCode.getCodigoTramite());
			
			Tramite tramite = new Tramite(codigoTramite, tramiteDto.getAsunto(), 
										  new Date(), tramiteDto.getNumeroFolios(), 
										  tramiteDto.getReferencia(), Utils.estadoTramiteEnTramite, 
										  tramiteDto.getTipoDocumento(), tramiteDto.getObservacion(), 
										  tipoTramite, solicitante);
			try {
				tramiteRepository.save(tramite);				
			} catch (Exception e) {
				LOGGER.error(correlationId + ":::: Proceso registrarTramite, registro a tabla Tramite. Error Mensaje :::: '{}' ", e.getMessage());
				LOGGER.error(e.getLocalizedMessage(), e);
			}
			
			
			TramiteMovimiento movimiento = new TramiteMovimiento(tramite.getFechaRegistro(), Utils.motivoEnvioRegistro, 
																 Utils.valueDefaultIntegerOne, Utils.valueDefaultIntegerZero, 
																 Utils.flagEstadoActivo, Utils.estadoMovimientoRegistrado, 
																 dependencia, tramite);
			try {
				movimientoRepository.save(movimiento);
			} catch (Exception e) {
				LOGGER.error(correlationId + ":::: Proceso registrarTramite, registro a tabla movimiento. Error Mensaje :::: '{}' ", e.getMessage());
				LOGGER.error(e.getLocalizedMessage(), e);
			}
			
			
			tramiteDto.setCodigoTramite(codigoTramite);
			tramiteDto.setFechaRegistro(tramite.getFechaRegistro());
			
			LOGGER.info(":::: Proceso registrarTramite. Datos - CodigoTramite. '{}' ", codigoTramite);
			LOGGER.info(":::: Proceso registrarTramite. Datos - Solicitante. '{}' ", solicitante.getTipoDocumento() + " - " + solicitante.getNumeroDocumento());
			LOGGER.info(":::: Proceso registrarTramite. Datos - TipoTramite. '{}' ", tipoTramite.getIdTipoTramite() + " - " + tipoTramite.getNombreTipoTramite());
		}
		LOGGER.info(":::: Proceso registrarTramite. Resultado '{}' ", validate.isIsvalid()+ " - " + validate.getMsj());
		LOGGER.info(":::: Proceso registrarTramite. Final :::: '{}' ", TramiteServiceImp.class.getName());
		return validate;
	}

	@Override
	public ValidateService derivarTramite(TramiteMovimientoDTO movimientoDto) throws SQLException, ServiceException {
		String correlationId = UUID.randomUUID().toString();
		LOGGER.info(correlationId + ":::: Proceso derivarTramite. Inicio :::: '{}' ", TramiteServiceImp.class.getName());
		ValidateService validate = new ValidateServiceImp();
		validate.setIsvalid(true);
		validate.setMsj("Registro de Derivación exitoso");
		
		TramiteMovimiento movimientoAnterior=null;
		TramiteMovimiento movimientoNuevo=null;
		Dependencia dependenciaSiguiente = null;
		try {
			movimientoAnterior = movimientoRepository.findByTramiteCodigoTramiteAndUbicacionActual(movimientoDto.getTramiteDto().getCodigoTramite(), "1");
			dependenciaSiguiente = dependenciaRepository.findDependenciaByPasoAndTipoTramite(movimientoAnterior.getPasoActual() +1, movimientoAnterior.getTramite().getTipoTramite().getIdTipoTramite());
		}catch (Exception e) {
			LOGGER.error(correlationId + ":::: Proceso derivarTramite. Error Mensaje :::: '{}' ", e.getMessage());
			LOGGER.error(e.getLocalizedMessage(), e);
		}
		
		if(movimientoAnterior==null) {
			validate.setIsvalid(false);
			validate.setMsj("No existe movimiento anterior");
		}else if(movimientoDto==null) {
			validate.setIsvalid(false);
			validate.setMsj("No se ha creado el nuevo movimiento");
		}else if(movimientoDto.getMotivoEnvio()==null || movimientoDto.getMotivoEnvio().isEmpty()) {
			validate.setIsvalid(false);
			validate.setMsj("Asigne un motivo para derivar dicho tramite");
		}else if(movimientoAnterior.getTramite().getTipoTramite().getIdTipoTramite()==Utils.valueDefaultLongOne && 
				movimientoDto.getDependenciaDto()==null) {
			validate.setIsvalid(false);
			validate.setMsj("Asigne la dependencia, este tramite no tiene efecto administrativo");
		}else if(movimientoAnterior.getTramite().getTipoTramite().getIdTipoTramite()!=Utils.valueDefaultLongOne && 
				dependenciaSiguiente==null) {
			validate.setIsvalid(false);
			validate.setMsj("Dicho trámite ya no tiene más dependencias a derivar");
		}else {
			Date fechaDerivacion = new Date();
			
			//Manipulación en movimiento anterior
			movimientoAnterior.setFechaDerivacionPosterior(fechaDerivacion);
			movimientoAnterior.setUbicacionActual(Utils.flagEstadoInactivo);
			movimientoRepository.save(movimientoAnterior);
			
			//Manipulación en movimiento nuevo
			Dependencia dependenciaDestino = null;
			if(Objects.equals(movimientoAnterior.getTramite().getTipoTramite().getIdTipoTramite(), Utils.valueDefaultLongOne)) {
				dependenciaDestino = new Dependencia(movimientoDto.getDependenciaDto().getIdDependencia(), 
													 movimientoDto.getDependenciaDto().getNombreDependencia());
			}else {
				dependenciaDestino = dependenciaRepository.findDependenciaByPasoAndTipoTramite(movimientoAnterior.getPasoActual() +1, movimientoAnterior.getTramite().getTipoTramite().getIdTipoTramite());
			}
			movimientoNuevo = new TramiteMovimiento(fechaDerivacion, 
													movimientoDto.getMotivoEnvio(), 
													movimientoAnterior.getNumeroMovimiento() + 1, 
													movimientoAnterior.getPasoActual() + 1, 
													Utils.flagEstadoActivo, 
													Utils.estadoMovimientoDerivado, 
													dependenciaDestino, 
													movimientoAnterior.getTramite());
			movimientoRepository.save(movimientoNuevo);
			Map<String, Object> data = new HashMap<>();
			data.put("codigoTramite", movimientoNuevo.getTramite().getCodigoTramite());
			data.put("asunto", movimientoNuevo.getTramite().getAsunto());
			data.put("tipoTramite", movimientoNuevo.getTramite().getTipoTramite().getIdTipoTramite() + ". " + movimientoNuevo.getTramite().getTipoTramite().getNombreTipoTramite());
			data.put("solicitante", movimientoNuevo.getTramite().getSolicitante().getTipoDocumento() + ". " +
									movimientoNuevo.getTramite().getSolicitante().getNumeroDocumento() + " - " + (
									movimientoNuevo.getTramite().getSolicitante().getTipoSolicitante().equals(Utils.tipoSolicitantePersona) ? 
													movimientoNuevo.getTramite().getSolicitante().getNombreSolicitante() + " " + movimientoNuevo.getTramite().getSolicitante().getApellidoPaterno() + " " + movimientoNuevo.getTramite().getSolicitante().getApellidoMaterno() : 
													movimientoNuevo.getTramite().getSolicitante().getNombreSolicitante()));
			data.put("estado", movimientoNuevo.getEstadoMovimiento());
			data.put("dependenciaDestino", movimientoNuevo.getDependencia().getNombreDependencia());
			validate.setData(data);
			
			LOGGER.info(correlationId + ":::: Proceso derivarTramite. Datos - CodigoTramite. '{}' ", movimientoAnterior.getTramite().getCodigoTramite());
			LOGGER.info(correlationId + ":::: Proceso derivarTramite. Datos - TipoTramite. '{}' ", movimientoAnterior.getTramite().getTipoTramite().getIdTipoTramite() + " - " + movimientoAnterior.getTramite().getTipoTramite().getNombreTipoTramite());
			LOGGER.info(correlationId + ":::: Proceso derivarTramite. Datos - Dependencia anterior. '{}' ", movimientoAnterior.getDependencia().getIdDependencia() + " - " + movimientoAnterior.getDependencia().getNombreDependencia());
			LOGGER.info(correlationId + ":::: Proceso derivarTramite. Datos - Dependencia a enviar. '{}' ", dependenciaDestino.getIdDependencia() + " - " + dependenciaDestino.getNombreDependencia());
			
		}
		LOGGER.info(correlationId + ":::: Proceso derivarTramite. Resultado '{}' ", validate.isIsvalid()+ " - " + validate.getMsj());
		LOGGER.info(correlationId + ":::: Proceso derivarTramite. Final :::: '{}' ", TramiteServiceImp.class.getName());
		return validate;
	}

	@Override
	public ValidateService rececepcionarTramite(TramiteMovimientoDTO movimiento) throws SQLException, ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidateService devolverTramite(TramiteMovimientoDTO movimientoDto) throws SQLException, ServiceException {
		String correlationId = UUID.randomUUID().toString();
		LOGGER.info(correlationId + ":::: Proceso devolverTramite. Inicio :::: '{}' ", TramiteServiceImp.class.getName());
		ValidateService validate = new ValidateServiceImp();
		validate.setIsvalid(true);
		validate.setMsj("Registro de Devolución exitoso");
		
		TramiteMovimiento movimientoAnterior=null;
		TramiteMovimiento movimientoNuevo=null;
		
		try {
			movimientoAnterior = movimientoRepository.findByTramiteCodigoTramiteAndUbicacionActual(movimientoDto.getTramiteDto().getCodigoTramite(), "1");
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		if(movimientoAnterior==null) {
			validate.setIsvalid(false);
			validate.setMsj("No existe movimiento anterior");
		}else if(movimientoDto==null) {
			validate.setIsvalid(false);
			validate.setMsj("No existe movimiento anterior");
		}else if(movimientoDto.getMotivoEnvio()==null || movimientoDto.getMotivoEnvio().isEmpty()) {
			validate.setIsvalid(false);
			validate.setMsj("Asigne un motivo para derivar dicho tramite");
		}else if(movimientoDto.getDependenciaDto()==null) {
			validate.setIsvalid(false);
			validate.setMsj("Asigne la dependencia a la que devolverá el documento");
		}else if(!dependenciaService.isExistDependenciainFlujoTramite(dependenciaService.listDependenciaByDevolver(movimientoDto.getTramiteDto().getCodigoTramite()), 
																	  movimientoDto.getDependenciaDto())) {
			validate.setIsvalid(false);
			validate.setMsj("La dependencia indicada no existe en el flujo de este trámite");
		}else if(movimientoAnterior.getTramite().getEstadoTramite().equals(Utils.estadoTramiteFinalizadoAprobado) || 
				 movimientoAnterior.getTramite().getEstadoTramite().equals(Utils.estadoTramiteFinalizadoDesaprobado)) {
			validate.setIsvalid(false);
			validate.setMsj("No se puede devolver un tramite ya finalizado");
		}else {
			Date fechaDerivacion = new Date();
			
			//Manipulación en movimiento anterior
			movimientoAnterior.setFechaDerivacionPosterior(fechaDerivacion);
			movimientoAnterior.setUbicacionActual(Utils.flagEstadoInactivo);
			movimientoRepository.save(movimientoAnterior);
			
			//Manipulación en movimiento nuevo
			Dependencia dependenciaDestino = new Dependencia(movimientoDto.getDependenciaDto().getIdDependencia(), 
					 										 movimientoDto.getDependenciaDto().getNombreDependencia());
			
			movimientoNuevo = new TramiteMovimiento(fechaDerivacion, 
													movimientoDto.getMotivoEnvio(), 
													movimientoAnterior.getNumeroMovimiento() + 1, 
													movimientoRepository.findPasoByTramiteAndDependencia(movimientoAnterior.getTramite().getCodigoTramite(), dependenciaDestino.getIdDependencia()).getPasoActual(),
													Utils.flagEstadoActivo, 
													Utils.estadoMovimientoDevuelto, 
													dependenciaDestino, 
													movimientoAnterior.getTramite());
			movimientoRepository.save(movimientoNuevo);
			
			LOGGER.info(correlationId + ":::: Proceso devolverTramite. Datos - CodigoTramite. '{}' ", movimientoAnterior.getTramite().getCodigoTramite());
			LOGGER.info(correlationId + ":::: Proceso devolverTramite. Datos - TipoTramite. '{}' ", movimientoAnterior.getTramite().getTipoTramite().getIdTipoTramite() + " - " + movimientoAnterior.getTramite().getTipoTramite().getNombreTipoTramite());
			LOGGER.info(correlationId + ":::: Proceso devolverTramite. Datos - Dependencia anterior. '{}' ", movimientoAnterior.getDependencia().getIdDependencia() + " - " + movimientoAnterior.getDependencia().getNombreDependencia());
			LOGGER.info(correlationId + ":::: Proceso devolverTramite. Datos - Dependencia devolver. '{}' ", dependenciaDestino.getIdDependencia() + " - " + dependenciaDestino.getNombreDependencia());
			
		}
		LOGGER.info(correlationId + ":::: Proceso devolverTramite. Resultado '{}' ", validate.isIsvalid()+ " - " + validate.getMsj());
		LOGGER.info(correlationId + ":::: Proceso devolverTramite. Final :::: '{}' ", TramiteServiceImp.class.getName());
		return validate;
	}

	@Override
	public ValidateService finalizarTramite(TramiteDTO tramiteDto) throws SQLException, ServiceException {
		String correlationId = UUID.randomUUID().toString();
		LOGGER.info(correlationId + ":::: Proceso finalizarTramite. Inicio :::: '{}' ", TramiteServiceImp.class.getName());
		ValidateService validate = new ValidateServiceImp();
		
		validate.setIsvalid(true);
		validate.setMsj("Se dió por finalizado dicho trámite");
		Tramite tramite = null;
		try {
			tramite = tramiteRepository.findByCodigoTramite(tramiteDto.getCodigoTramite());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(tramiteDto==null) {
			validate.setIsvalid(false);
			validate.setMsj("No existe el tramite a finalizar");
		}else if(tramiteDto.getCodigoTramite()==null || tramiteDto.getCodigoTramite().isEmpty()) {
			validate.setIsvalid(false);
			validate.setMsj("Ingrese el código de tramite");
		}else if(tramite==null) {
			validate.setIsvalid(false);
			validate.setMsj("No eixste el tramite");
		}else if(tramite.getCodigoTramite()==null || tramite.getCodigoTramite().isEmpty()) {
			validate.setIsvalid(false);
			validate.setMsj("El trámite ingresado no se encuentra registrado");
		}else if(tramite.getEstadoTramite().equals(Utils.estadoTramiteFinalizadoAprobado) || 
				 tramite.getEstadoTramite().equals(Utils.estadoTramiteFinalizadoDesaprobado)) {
			validate.setIsvalid(false);
			validate.setMsj("El trámite indicado previamente ya ha sido finalizado");
		}else if(tramite.getEstadoTramite().equals(Utils.estadoTramiteAnulado)) {
			validate.setIsvalid(false);
			validate.setMsj("El trámite indicado previamente ha sido anulado");
		}else if(tramiteDto.getEstadoTramite()==null || tramiteDto.getEstadoTramite().isEmpty()) {
			validate.setIsvalid(false);
			validate.setMsj("Asigne el estado del trámite");
		}else if(!isEstadoTramiteFinalized(tramiteDto.getEstadoTramite())) {
			validate.setIsvalid(false);
			validate.setMsj("El estado asignado no es permitido, solo puede asignarse " + 
							 Utils.estadoTramiteFinalizadoAprobado + " ó " +
							 Utils.estadoTramiteFinalizadoDesaprobado);
		}else {
			try {
				tramite.setEstadoTramite(tramiteDto.getEstadoTramite());
				tramite.setObservacion(tramite.getObservacion()!=null?tramite.getObservacion()+ "\n" : "" + tramiteDto.getObservacion());
				tramite.setFechaTermino(new Date());
				
				tramiteRepository.save(tramite);
				
				LOGGER.info(correlationId + ":::: Proceso finalizarTramite. Datos - CodigoTramite. '{}' ", tramite.getCodigoTramite());
				LOGGER.info(correlationId + ":::: Proceso finalizarTramite. Datos - TipoTramite. '{}' " ,  tramite.getTipoTramite().getIdTipoTramite() + " - " + tramite.getTipoTramite().getNombreTipoTramite());
				LOGGER.info(correlationId + ":::: Proceso finalizarTramite. Datos - Estado Tramite. '{}' ", tramite.getEstadoTramite());
				LOGGER.info(correlationId + ":::: Proceso finalizarTramite. Datos - Fecha Termino. '{}' ", tramite.getFechaTermino());
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		LOGGER.info(correlationId + ":::: Proceso finalizarTramite. Resultado '{}' ", validate.isIsvalid()+ " - " + validate.getMsj());
		LOGGER.info(correlationId + ":::: Proceso finalizarTramite. Final :::: '{}' ", TramiteServiceImp.class.getName());
		return validate;
	}
	
	private static String generateCode(String nro) {
		//Obtener y formatear fecha
		LocalDate fechaActual = LocalDate.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyMMdd");
        String fechaFormateada = fechaActual.format(formato);
        //Obtener numero correlativo
        String ceros = "00000";
        int longitudNro = nro.length();
        String numero = ceros.substring(longitudNro) + nro;
        
        return "TR".concat(fechaFormateada).concat(numero);
	}
	
	private static boolean isEstadoTramiteFinalized(String estadoTramite) {
		List<String> listEstadosTramiteFinalized = new ArrayList<>();
		listEstadosTramiteFinalized.add(Utils.estadoTramiteFinalizadoAprobado);
		listEstadosTramiteFinalized.add(Utils.estadoTramiteFinalizadoDesaprobado);
		for(String estado: listEstadosTramiteFinalized) {
			if(estado.equals(estadoTramite)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public ValidateService anularTramite(TramiteDTO tramiteDto) throws SQLException, ServiceException {
		String correlationId = UUID.randomUUID().toString();
		LOGGER.info(correlationId + ":::: Proceso anularTramite. Inicio :::: '{}' ", TramiteServiceImp.class.getName());
		ValidateService validate = new ValidateServiceImp();
		
		validate.setIsvalid(true);
		validate.setMsj("Se dió por finalizado dicho trámite");
		Tramite tramite = null;
		try {
			tramite = tramiteRepository.findByCodigoTramite(tramiteDto.getCodigoTramite());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(tramiteDto==null) {
			validate.setIsvalid(false);
			validate.setMsj("No existe el tramite a finalizar");
		}else if(tramiteDto.getCodigoTramite()==null || tramiteDto.getCodigoTramite().isEmpty()) {
			validate.setIsvalid(false);
			validate.setMsj("Ingrese el código de tramite");
		}else if(tramite==null) {
			validate.setIsvalid(false);
			validate.setMsj("No eixste el tramite");
		}else if(tramite.getCodigoTramite()==null || tramite.getCodigoTramite().isEmpty()) {
			validate.setIsvalid(false);
			validate.setMsj("El trámite ingresado no se encuentra registrado");
		}else if(tramite.getEstadoTramite().equals(Utils.estadoTramiteFinalizadoAprobado) || 
				 tramite.getEstadoTramite().equals(Utils.estadoTramiteFinalizadoDesaprobado)) {
			validate.setIsvalid(false);
			validate.setMsj("El trámite indicado previamente ya ha sido finalizado");
		}else if(tramite.getEstadoTramite().equals(Utils.estadoTramiteAnulado)) {
			validate.setIsvalid(false);
			validate.setMsj("El trámite indicado anteriormente ha sido anulado");
		}else if(tramiteDto.getMotivoAnulacion()==null || tramiteDto.getMotivoAnulacion().isEmpty()) {
			validate.setIsvalid(false);
			validate.setMsj("Ingrese el motivo por el que se anula dicho tramite");
		}else {
			try {
				tramite.setEstadoTramite(Utils.estadoTramiteAnulado);
				tramite.setMotivoAnulacion(tramiteDto.getMotivoAnulacion());
				tramite.setFechaTermino(new Date());
				
				tramiteRepository.save(tramite);
				
				LOGGER.info(correlationId + ":::: Proceso anularTramite. Datos - CodigoTramite. '{}' ", tramite.getCodigoTramite());
				LOGGER.info(correlationId + ":::: Proceso anularTramite. Datos - TipoTramite. '{}' " ,  tramite.getTipoTramite().getIdTipoTramite() + " - " + tramite.getTipoTramite().getNombreTipoTramite());
				LOGGER.info(correlationId + ":::: Proceso anularTramite. Datos - Estado Tramite. '{}' ", tramite.getEstadoTramite());
				LOGGER.info(correlationId + ":::: Proceso anularTramite. Datos - Fecha Termino. '{}' ", tramite.getFechaTermino());
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		LOGGER.info(correlationId + ":::: Proceso anularTramite. Resultado '{}' ", validate.isIsvalid()+ " - " + validate.getMsj());
		LOGGER.info(correlationId + ":::: Proceso anularTramite. Final :::: '{}' ", TramiteServiceImp.class.getName());
		
		return validate;
	}

}
