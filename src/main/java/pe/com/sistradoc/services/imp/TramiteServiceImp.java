package pe.com.sistradoc.services.imp;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sistradoc.dto.DependenciaDTO;
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
	private DependenciaService dependenciaService;
	
	@Autowired
	private DependenciaRepository dependenciaRepository;
	
	@Override
	public ValidateService registrarTramite(TramiteDTO tramiteDto, DependenciaDTO dependenciaDto) throws ServiceException {
		ValidateService validate = new ValidateServiceImp();
		validate.setIsvalid(true);
		validate.setMsj("Registro de tramite exitoso");
		
		ValidateService validateSolicitante = null;
		
		//Obtención de valores necesarios
		TipoTramite tipoTramite = null;
		Solicitante solicitante = null;
		try {
			validateSolicitante = solicitanteService.registrarSolicitante(tramiteDto.getSolicitanteDto());
			tipoTramite = tipoTramiteRepository.findByIdTipoTramite(tramiteDto.getTipoTramiteDto().getIdTipoTramite());
			solicitante = solicitanteRepository.findByNumeroDocumento(tramiteDto.getSolicitanteDto().getNumeroDocumento());
		} catch (Exception e) {
			// TODO: handle exception
		}

		//Validaciones funcionales para el registro de trámite
		if(tramiteDto==null) {
			validate.setIsvalid(false);
			validate.setMsj("No se ha creado el tramite");
		}else if(tramiteDto.getAsunto()==null || tramiteDto.getAsunto().isEmpty()) {
			validate.setIsvalid(false);
			validate.setMsj("Ingrese el asunto del trámite");
		}else if(solicitante==null) {
			validate.setIsvalid(false);
			validate.setMsj("Ingrese o asigne el solicitante");
		}else if(tramiteDto.getFechaRegistro()==null) {
			validate.setIsvalid(false);
			validate.setMsj("Asigne la fecha de registro");
		}else if(tipoTramite==null) {
			validate.setIsvalid(false);
			validate.setMsj("Asigne el tipo de tramite");
		}else if(tramiteDto.getTipoDocumento()==null || tramiteDto.getTipoDocumento().isEmpty()) {
			validate.setIsvalid(false);
			validate.setMsj("Asigne el tipo de trámite");
		}else if(tramiteDto.getNumeroFolios()<=0) {
			validate.setIsvalid(false);
			validate.setMsj("Ingrese la cantidad de folios presentados");
		}else if(!validateSolicitante.isIsvalid()) {
			validate.setMsj(validateSolicitante.getMsj());
			validate.setIsvalid(validateSolicitante.isIsvalid());
		}else if(tipoTramite.getIdTipoTramite().equals(Long.valueOf(1)) && dependenciaDto==null) {
			validate.setIsvalid(false);
			validate.setMsj("Asigne una dependencia para el trámite sin efecto administrativo");
		}else {
			TramiteCode tramiteCode = tramiteRepository.getCodeTramite();
			String codigoTramite = generateCode(tramiteCode.getCodigoTramite());
			
			Tramite tramite = new Tramite(codigoTramite, tramiteDto.getAsunto(), tramiteDto.getFechaRegistro(), 
										  tramiteDto.getNumeroFolios(), tramiteDto.getReferencia(), Utils.estadoTramiteEnTramite, 
										  tramiteDto.getTipoDocumento(), tipoTramite, solicitante);
			tramiteRepository.save(tramite);
			
			Dependencia dependencia=null;
			if(tipoTramite.getIdTipoTramite()!=Long.valueOf(1)) {
				DependenciaDTO dependenciaDtoTT;
				try {
					dependenciaDtoTT = dependenciaService.obtenerDependenciaByTipoTramite(Integer.valueOf(1), tipoTramite.getIdTipoTramite());
					if(dependenciaDtoTT!=null) {
						dependencia = dependenciaRepository.findByIdDependencia(dependenciaDtoTT.getIdDependencia());
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}else if(dependenciaDto!=null) {
				dependencia = dependenciaRepository.findByIdDependencia(dependenciaDto.getIdDependencia());
			}
			
			TramiteMovimiento movimiento = new TramiteMovimiento(tramite.getFechaRegistro(), Utils.motivoEnvioRegistro, 
																 Integer.valueOf(1), Integer.valueOf(1), 
																 Utils.flagEstadoActivo, Utils.estadoMovimientoRegistrado, 
																 dependencia, tramite);
			movimientoRepository.save(movimiento);
		}
		return validate;
	}

	@Override
	public ValidateService derivarTramite(TramiteMovimientoDTO movimiento) throws SQLException, ServiceException {
		// Registrar movimiento del trámite
		
		return null;
	}

	@Override
	public ValidateService rececepcionarTramite(TramiteMovimientoDTO movimiento) throws SQLException, ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidateService devolverTramite(TramiteMovimientoDTO movimiento) throws SQLException, ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidateService finalizarTramite(Tramite tramite) throws SQLException, ServiceException {
		// TODO Auto-generated method stub
		return null;
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

}
