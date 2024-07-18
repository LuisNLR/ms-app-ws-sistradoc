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
	public ValidateService registrarSolicitante(SolicitanteDTO solicitanteDto) throws SQLException, ServiceException {
		ValidateService validate = new ValidateServiceImp();
		validate.setIsvalid(true);
		validate.setMsj("Registro de Solicitante exitoso");
		
		if(solicitanteDto==null) {
			validate.setIsvalid(false);
			validate.setMsj("No se ha creado el solicitante");
		}else if(solicitanteDto.getTipoSolicitante()==null || solicitanteDto.getTipoSolicitante().isEmpty()) {
			validate.setIsvalid(false);
			validate.setMsj("Ingrese tipo de solicitante");
		}else if(solicitanteDto.getNumeroDocumento()==null || solicitanteDto.getNumeroDocumento().isEmpty()) {
			validate.setIsvalid(false);
			validate.setMsj("Ingrese número de documento");
		}else if(solicitanteDto.getTipoDocumento()==null || solicitanteDto.getTipoDocumento().isEmpty()) {
			validate.setIsvalid(false);
			validate.setMsj("Ingrese tipo de documento");
		}else if(solicitanteDto.getNombreSolicitante()==null || solicitanteDto.getNombreSolicitante().isEmpty()) {
			validate.setIsvalid(false);
			validate.setMsj("Ingrese Nombre del solicitante");
		}else if(solicitanteDto.getTipoSolicitante().equals(Utils.tipoSolicitantePersona) && 
				(solicitanteDto.getApellidoPaterno()==null || solicitanteDto.getApellidoPaterno().isEmpty())) {
			validate.setIsvalid(false);
			validate.setMsj("Ingrese Apellido paterno del solicitante");
		}else if(solicitanteDto.getTipoSolicitante().equals(Utils.tipoSolicitantePersona) && 
				(solicitanteDto.getApellidoMaterno()==null || solicitanteDto.getApellidoMaterno().isEmpty())) {
			validate.setIsvalid(false);
			validate.setMsj("Ingrese Apellido materno del solicitante");
		}else if(solicitanteDto.getTipoSolicitante().equals(Utils.tipoSolicitanteEntidad) && 
				(solicitanteDto.getRepresentante()==null || solicitanteDto.getRepresentante().isEmpty())) {
			validate.setIsvalid(false);
			validate.setMsj("Ingrese el nombre del representante");
		}
		
		Solicitante solicitante = new Solicitante();
		solicitante.setNumeroDocumento(solicitanteDto.getNumeroDocumento());
		solicitante.setNombreSolicitante(solicitanteDto.getNombreSolicitante());
		solicitante.setTipoDocumento(solicitanteDto.getTipoDocumento());
		solicitante.setTipoSolicitante(solicitanteDto.getTipoSolicitante());
		solicitante.setApellidoMaterno(solicitanteDto.getApellidoMaterno());
		solicitante.setApellidoPaterno(solicitanteDto.getApellidoPaterno());
		solicitante.setDireccion(solicitanteDto.getDireccion());
		solicitante.setMail(solicitanteDto.getMail());
		solicitante.setRepresentante(solicitanteDto.getRepresentante());
		solicitante.setTelefono(solicitanteDto.getTelefono());
		
		try {
			if(validate.isIsvalid()) {
				solicitanteRepository.save(solicitante);
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
