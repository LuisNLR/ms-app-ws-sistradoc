package pe.com.sistradoc.services.imp;

import java.sql.SQLException;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sistradoc.dto.TramiteDTO;
import pe.com.sistradoc.model.Solicitante;
import pe.com.sistradoc.model.TipoTramite;
import pe.com.sistradoc.model.Tramite;
import pe.com.sistradoc.model.TramiteMovimiento;
import pe.com.sistradoc.repository.SolicitanteRepository;
import pe.com.sistradoc.repository.TipoTramiteRepository;
import pe.com.sistradoc.repository.TramiteMovimientoRepository;
import pe.com.sistradoc.repository.TramiteRepository;
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
	
	@Override
	public ValidateService registrarTramite(TramiteDTO tramiteDto) throws SQLException, ServiceException {
		ValidateService validate = new ValidateServiceImp();
		validate.setIsvalid(true);
		validate.setMsj("Registro de tramite exitoso");
		
		//Obtención de valores necesarios
		TipoTramite tipoTramite = null;
		Solicitante solicitante = null;
		try {
			tipoTramite = tipoTramiteRepository.findByIdTipoTramite(tramiteDto.getTipoTramite_ID());
			solicitante = solicitanteRepository.findByNumeroDocumento(tramiteDto.getSolicitante_ID());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//Validaciones funcionales
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
		}
		
		//Seteo de valores - Trámite
		Tramite tramite = new Tramite();
		tramite.setArchivado(tramiteDto.getArchivado());
		tramite.setAsunto(tramiteDto.getAsunto());
		tramite.setCodigoTramite(tramiteDto.getCodigoTramite());
		tramite.setEstadoTramite(Utils.estadoTramiteEnTramite);
		tramite.setFechaRegistro(tramiteDto.getFechaRegistro());
		tramite.setNumeroFolios(tramiteDto.getNumeroFolios());
		tramite.setSolicitante(solicitante);
		tramite.setTipoDocumento(tramiteDto.getTipoDocumento());
		tramite.setTipoTramite(tipoTramite);
		
		
		//Seteo de valores - Movimiento
		TramiteMovimiento movimiento = new TramiteMovimiento();
		movimiento.setEstadoMovimiento(Utils.estadoMovimientoRegistrado);
		movimiento.setTramite(tramite);
		
		//Registro de tramite y movimiento
		tramiteRepository.save(tramite);
		
		movimientoRepository.save(movimiento);
		return validate;
	}

	@Override
	public ValidateService derivarTramite(TramiteMovimiento movimiento) throws SQLException, ServiceException {
		// Registrar movimiento del trámite
		
		movimientoRepository.save(movimiento);
		return null;
	}

	@Override
	public ValidateService rececepcionarTramite(TramiteMovimiento movimiento) throws SQLException, ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidateService devolverTramite(TramiteMovimiento movimiento) throws SQLException, ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidateService finalizarTramite(Tramite tramite) throws SQLException, ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
