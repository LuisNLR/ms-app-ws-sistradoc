package pe.com.sistradoc.services;

import java.sql.SQLException;

import org.hibernate.service.spi.ServiceException;

import pe.com.sistradoc.model.Tramite;
import pe.com.sistradoc.model.TramiteMovimiento;

public interface TramiteService {

	public void registrarTramite(Tramite tramite) throws SQLException, ServiceException;
	
	public void derivarTramite(TramiteMovimiento movimiento) throws SQLException, ServiceException;
	
	public void rececepcionarTramite(TramiteMovimiento movimiento) throws SQLException, ServiceException;
	
	public void devolverTramite(TramiteMovimiento movimiento) throws SQLException, ServiceException;
	
	public void finalizarTramite(Tramite tramite) throws SQLException, ServiceException;
	
}
