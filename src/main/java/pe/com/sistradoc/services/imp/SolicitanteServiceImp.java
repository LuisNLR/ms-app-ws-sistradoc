package pe.com.sistradoc.services.imp;

import java.sql.SQLException;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sistradoc.dto.SolicitanteDTO;
import pe.com.sistradoc.model.Solicitante;
import pe.com.sistradoc.repository.SolicitanteRepository;
import pe.com.sistradoc.services.SolicitanteService;

@Service
public class SolicitanteServiceImp implements SolicitanteService{

	@Autowired
	SolicitanteRepository solicitanteRepository;
	
	@Override
	public void registrarSolicitante(SolicitanteDTO solicitante) throws SQLException, ServiceException {
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
			solicitanteRepository.save(solic);
		} catch (ServiceException sE) {
			
		} catch (Exception sE) {
			
		}
	}

	@Override
	public SolicitanteDTO obtenerSolicitante(String numeroDocumento) throws SQLException, ServiceException {
		Solicitante solicitante = solicitanteRepository.findByNumeroDocumento(numeroDocumento);
		
		SolicitanteDTO solicDto = new SolicitanteDTO();
		try {
			if(solicitante!=null) {
				solicDto.setNumeroDocumento(solicitante.getNumeroDocumento());
				solicDto.setNombreSolicitante(solicitante.getNombreSolicitante());
				solicDto.setTipoDocumento(solicitante.getTipoDocumento());
				solicDto.setTipoSolicitante(solicitante.getTipoSolicitante());
				solicDto.setApellidoMaterno(solicitante.getApellidoMaterno());
				solicDto.setApellidoPaterno(solicitante.getApellidoPaterno());
				solicDto.setDireccion(solicitante.getDireccion());
				solicDto.setMail(solicitante.getMail());
				solicDto.setRepresentante(solicitante.getRepresentante());
				solicDto.setTelefono(solicitante.getTelefono());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return solicDto;
	}

}
