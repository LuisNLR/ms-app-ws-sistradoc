package pe.com.sistradoc.services;

import java.sql.SQLException;

import org.hibernate.service.spi.ServiceException;

import pe.com.sistradoc.dto.TramiteDTO;
import pe.com.sistradoc.dto.TramiteMovimientoDTO;
import pe.com.sistradoc.utils.ValidateService;

public interface TramiteService {

	public ValidateService registrarTramite(TramiteDTO tramiteDto) throws ServiceException;
	
	public ValidateService derivarTramite(TramiteMovimientoDTO movimiento) throws SQLException, ServiceException;
	
	public ValidateService rececepcionarTramite(TramiteMovimientoDTO movimiento) throws SQLException, ServiceException;
	
	public ValidateService devolverTramite(TramiteMovimientoDTO movimiento) throws SQLException, ServiceException;
	
	public ValidateService finalizarTramite(TramiteDTO tramiteDto) throws SQLException, ServiceException;
	
}
