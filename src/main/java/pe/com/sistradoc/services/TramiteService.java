package pe.com.sistradoc.services;

import java.sql.SQLException;

import org.hibernate.service.spi.ServiceException;

import pe.com.sistradoc.dto.TramiteDTO;
import pe.com.sistradoc.model.Tramite;
import pe.com.sistradoc.model.TramiteMovimiento;
import pe.com.sistradoc.utils.ValidateService;

public interface TramiteService {

	public ValidateService registrarTramite(TramiteDTO tramite) throws SQLException, ServiceException;
	
	public ValidateService derivarTramite(TramiteMovimiento movimiento) throws SQLException, ServiceException;
	
	public ValidateService rececepcionarTramite(TramiteMovimiento movimiento) throws SQLException, ServiceException;
	
	public ValidateService devolverTramite(TramiteMovimiento movimiento) throws SQLException, ServiceException;
	
	public ValidateService finalizarTramite(Tramite tramite) throws SQLException, ServiceException;
	
}
