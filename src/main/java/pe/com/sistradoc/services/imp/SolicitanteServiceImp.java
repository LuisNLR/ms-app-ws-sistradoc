package pe.com.sistradoc.services.imp;

import java.sql.SQLException;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sistradoc.dto.SolicitanteDTO;
import pe.com.sistradoc.model.Solicitante;
import pe.com.sistradoc.repository.SolicitanteRepository;
import pe.com.sistradoc.services.SolicitanteService;
import pe.com.sistradoc.utils.Utils;
import pe.com.sistradoc.utils.ValidateService;
import pe.com.sistradoc.utils.ValidateServiceImp;

@Service
public class SolicitanteServiceImp extends ValidateServiceImp implements SolicitanteService{
	
	public final static Logger LOGGER = LoggerFactory.getLogger(SolicitanteServiceImp.class);

	@Autowired
	SolicitanteRepository solicitanteRepository;
	
	@Override
	public ValidateService registrarSolicitante(SolicitanteDTO solicitante) throws SQLException, ServiceException {
		ValidateService validate = new ValidateServiceImp();
		validate.setIsvalid(true);
		validate.setMsj("Registro de Solicitante exitoso");
		
		if(solicitante==null) {
			validate.setIsvalid(false);
			validate.setMsj("No se ha creado el solicitante");
		}else if(solicitante.getTipoSolicitante()==null || solicitante.getTipoSolicitante().isEmpty()) {
			validate.setIsvalid(false);
			validate.setMsj("Ingrese tipo de solicitante");
		}else if(solicitante.getNumeroDocumento()==null || solicitante.getNumeroDocumento().isEmpty()) {
			validate.setIsvalid(false);
			validate.setMsj("Ingrese número de documento");
		}else if(solicitante.getTipoDocumento()==null || solicitante.getTipoDocumento().isEmpty()) {
			validate.setIsvalid(false);
			validate.setMsj("Ingrese tipo de documento");
		}else if(solicitante.getNombreSolicitante()==null || solicitante.getNombreSolicitante().isEmpty()) {
			validate.setIsvalid(false);
			validate.setMsj("Ingrese Nombre del solicitante");
		}else if(solicitante.getTipoSolicitante().equals(Utils.tipoSolicitantePersona) && 
				(solicitante.getApellidoPaterno()==null || solicitante.getApellidoPaterno().isEmpty())) {
			validate.setIsvalid(false);
			validate.setMsj("Ingrese Apellido paterno del solicitante");
		}else if(solicitante.getTipoSolicitante().equals(Utils.tipoSolicitantePersona) && 
				(solicitante.getApellidoMaterno()==null || solicitante.getApellidoMaterno().isEmpty())) {
			validate.setIsvalid(false);
			validate.setMsj("Ingrese Apellido materno del solicitante");
		}else if(solicitante.getTipoSolicitante().equals(Utils.tipoSolicitanteEntidad) && 
				(solicitante.getRepresentante()==null || solicitante.getRepresentante().isEmpty())) {
			validate.setIsvalid(false);
			validate.setMsj("Ingrese el nombre del representante");
		}
		
		Solicitante solic = new Solicitante();
		solic.setNumeroDocumento(solicitante.getNumeroDocumento());
		solic.setNombreSolicitante(solicitante.getNombreSolicitante());
		solic.setTipoDocumento(solicitante.getTipoDocumento());
		solic.setTipoSolicitante(solicitante.getTipoSolicitante());
		solic.setApellidoMaterno(solicitante.getApellidoMaterno());
		solic.setApellidoPaterno(solicitante.getApellidoPaterno());
		solic.setDireccion(solicitante.getDireccion());
		solic.setMail(solicitante.getMail());
		solic.setRepresentante(solicitante.getRepresentante());
		solic.setTelefono(solicitante.getTelefono());
		
		try {
			if(validate.isIsvalid()) {
				solicitanteRepository.save(solic);
			}
		} catch (Exception sE) {
			validate.setIsvalid(false);
			validate.setMsj(sE.getMessage());
		}
		return validate;
	}

	@Override
	public SolicitanteDTO obtenerSolicitante(String numeroDocumento) throws SQLException, ServiceException {
		LOGGER.info("Obtener información del documento '{}'", numeroDocumento);
		Solicitante solicitante = solicitanteRepository.findByNumeroDocumento(numeroDocumento);
		
		SolicitanteDTO solicDto = new SolicitanteDTO();
		try {
			if(solicitante!=null) {
				solicDto.setNumeroDocumento(solicitante.getNumeroDocumento());
				solicDto.setNombreSolicitante(solicitante.getNombreSolicitante());
				solicDto.setTipoDocumento(solicitante.getTipoDocumento()!=null ? solicitante.getTipoDocumento() : "");
				solicDto.setTipoSolicitante(solicitante.getTipoSolicitante());
				solicDto.setApellidoMaterno(solicitante.getApellidoMaterno()!= null ? solicitante.getApellidoMaterno() : "");
				solicDto.setApellidoPaterno(solicitante.getApellidoPaterno()!= null ? solicitante.getApellidoPaterno() : "");
				solicDto.setDireccion(solicitante.getDireccion()!=null ? solicitante.getDireccion() : "");
				solicDto.setMail(solicitante.getMail()!=null ? solicitante.getMail() : "");
				solicDto.setRepresentante(solicitante.getRepresentante()!=null ? solicitante.getRepresentante() : "");
				solicDto.setTelefono(solicitante.getTelefono()!=null ? solicitante.getTelefono() : "");
			}else {
				solicDto.setNumeroDocumento("");
				solicDto.setNombreSolicitante("");
				solicDto.setTipoDocumento("");
				solicDto.setTipoSolicitante("");
				solicDto.setApellidoMaterno("");
				solicDto.setApellidoPaterno("");
				solicDto.setDireccion("");
				solicDto.setMail("");
				solicDto.setRepresentante("");
				solicDto.setTelefono("");
			}
		} catch (Exception e) {
			LOGGER.error("Mensaje de errores desde '{}'", SolicitanteServiceImp.class.getName());
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		return solicDto;
	}

}
