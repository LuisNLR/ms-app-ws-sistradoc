package pe.com.sistradoc.services;

import java.sql.SQLException;

import org.hibernate.service.spi.ServiceException;

import pe.com.sistradoc.dto.SolicitanteDTO;
import pe.com.sistradoc.utils.ValidateService;

public interface SolicitanteService /*extends ValidateService*/{
	
	public ValidateService registrarSolicitante(SolicitanteDTO solicitante) throws SQLException, ServiceException;
	
	public SolicitanteDTO obtenerSolicitante(String numeroDocumento) throws SQLException, ServiceException;

}
