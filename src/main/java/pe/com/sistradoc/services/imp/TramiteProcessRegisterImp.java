package pe.com.sistradoc.services.imp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sistradoc.utils.Utils;
import pe.com.sistradoc.dto.TramiteDTO;
import pe.com.sistradoc.model.Tramite;
import pe.com.sistradoc.model.TramiteCode;
import pe.com.sistradoc.model.TramiteMovimiento;
import pe.com.sistradoc.repository.DependenciaRepository;
import pe.com.sistradoc.repository.SolicitanteRepository;
import pe.com.sistradoc.repository.TipoTramiteRepository;
import pe.com.sistradoc.repository.TramiteMovimientoRepository;
import pe.com.sistradoc.repository.TramiteRepository;
import pe.com.sistradoc.services.SolicitanteService;
import pe.com.sistradoc.services.TramiteProcessRegister;
import pe.com.sistradoc.services.ValidateProcess;
import pe.com.sistradoc.utils.ValidateService;
import pe.com.sistradoc.utils.ValidateServiceImp;

@Service
public class TramiteProcessRegisterImp implements TramiteProcessRegister, ValidateProcess {

    public final static Logger LOGGER = LoggerFactory.getLogger(TramiteProcessRegisterImp.class);

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

    @Override
    public ValidateService registrarTramite(TramiteDTO tramiteDto) throws ServiceException {
        ValidateService validate = validProcess(tramiteDto);
        try {
            if(validate.isValid()) {
                TramiteCode tramiteCode = tramiteRepository.getCodeTramite();
                String codigoTramite = generateCode(tramiteCode.getCodigoTramite());

                Tramite tramite = new Tramite(codigoTramite, 
                                              tramiteDto.getAsunto(), 
                                              new Date(), 
                                              tramiteDto.getNumeroFolios(), 
                                              tramiteDto.getReferencia(), 
                                              Utils.estadoTramiteEnTramite, 
                                              tramiteDto.getTipoDocumento(), 
                                              tramiteDto.getObservacion(), 
                                              tipoTramiteRepository.findByIdTipoTramite(tramiteDto.getTipoTramiteDto().getIdTipoTramite()), 
                                              solicitanteRepository.findByNumeroDocumento(tramiteDto.getSolicitanteDto().getNumeroDocumento()));
                tramiteRepository.save(tramite);
                
                TramiteMovimiento movimiento = new TramiteMovimiento(tramite.getFechaRegistro(), 
                                                                     Utils.motivoEnvioRegistro, 
                                                                     Utils.valueDefaultIntegerOne, 
                                                                     Utils.valueDefaultIntegerZero, 
                                                                     Utils.flagEstadoActivo, 
                                                                     Utils.estadoMovimientoRegistrado, 
                                                                     dependenciaRepository.findByIdDependencia(Utils.valueDefaultLongOne), 
                                                                     tramite);
                movimientoRepository.save(movimiento);

            }
        } catch (Exception e) {
            LOGGER.error(":::: Error Proceso <registrarTramite>. Error Mensaje :::: '{}' ", e.getMessage());
			LOGGER.error(e.getLocalizedMessage(), e);
        }  
        return validate;  
    }

    @Override
    public ValidateService validProcess(TramiteDTO tramiteDto) {
        ValidateService validate = new ValidateServiceImp(true, "Registro de tramite exitoso", Utils.STATUS_CODE_OK);

        try {
        	ValidateService validateSolicitante = solicitanteService.registrarSolicitante(tramiteDto!=null ? tramiteDto.getSolicitanteDto() : null);
        	
            if(tramiteDto==null) {
                validate.setValid(false);
                validate.setMessage("No se ha creado el tramite");
                validate.setStatusCode(Utils.STATUS_CODE_BAD_REQUEST);
            }else if(tramiteDto.getAsunto()==null || tramiteDto.getAsunto().isEmpty()) {
                validate.setValid(false);
                validate.setMessage("Ingrese el asunto del trámite");
                validate.setStatusCode(Utils.STATUS_CODE_BAD_REQUEST);
            }else if(tramiteDto.getSolicitanteDto()==null) {
                validate.setValid(false);
                validate.setMessage("Ingrese o asigne el solicitante");
                validate.setStatusCode(Utils.STATUS_CODE_BAD_REQUEST);
            }else if(!validateSolicitante.isValid()) {
                validate.setMessage(validateSolicitante.getMessage());
                validate.setValid(validateSolicitante.isValid());
                validate.setStatusCode(Utils.STATUS_CODE_BAD_REQUEST);
            }else if(tramiteDto.getTipoTramiteDto()==null) {
                validate.setValid(false);
                validate.setMessage("Asigne el tipo de tramite");
                validate.setStatusCode(Utils.STATUS_CODE_BAD_REQUEST);
            }else if(tipoTramiteRepository.findByIdTipoTramite(tramiteDto.getTipoTramiteDto().getIdTipoTramite())==null) {
                validate.setValid(false);
                validate.setMessage("El tipo de trámite asignado no existe");
                validate.setStatusCode(Utils.STATUS_CODE_BAD_REQUEST);
            }else if(tramiteDto.getTipoDocumento()==null || tramiteDto.getTipoDocumento().isEmpty()) {
                validate.setValid(false);
                validate.setMessage("Asigne el tipo de trámite");
                validate.setStatusCode(Utils.STATUS_CODE_BAD_REQUEST);
            }else if(tramiteDto.getNumeroFolios()<=0) {
                validate.setValid(false);
                validate.setMessage("Ingrese la cantidad de folios presentados");
                validate.setStatusCode(Utils.STATUS_CODE_BAD_REQUEST);
            }
        } catch (Exception e) {
            LOGGER.error(":::: Error Proceso <registrarTramite>. Error Mensaje :::: '{}' ", e.getMessage());
			LOGGER.error(e.getLocalizedMessage(), e);
        }
        
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

}
