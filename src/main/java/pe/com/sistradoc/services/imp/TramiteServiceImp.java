package pe.com.sistradoc.services.imp;

import java.sql.SQLException;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import pe.com.sistradoc.model.Tramite;
import pe.com.sistradoc.model.TramiteMovimiento;
import pe.com.sistradoc.repository.TramiteMovimientoRepository;
import pe.com.sistradoc.repository.TramiteRepository;
import pe.com.sistradoc.services.TramiteService;
import pe.com.sistradoc.utils.Utils;

@Service
public class TramiteServiceImp implements TramiteService {

	TramiteRepository tramiteRepository;
	
	TramiteMovimientoRepository movimientoRepository;
	
	@Override
	public void registrarTramite(Tramite tramite) throws SQLException, ServiceException {
		//Registrar el tramite
		tramite.setEstadoTramite(Utils.estadoTramiteEnTramite);
		
		//Registrar el movimiento del tramite
		TramiteMovimiento movimiento = new TramiteMovimiento();
		movimiento.setEstadoMovimiento(Utils.estadoMovimientoRegistrado);
		
		tramiteRepository.save(tramite);
		
		movimientoRepository.save(movimiento);
		
	}

	@Override
	public void derivarTramite(TramiteMovimiento movimiento) throws SQLException, ServiceException {
		// Registrar movimiento del trámite
		
		movimientoRepository.save(movimiento);
		
	}

	@Override
	public void rececepcionarTramite(TramiteMovimiento movimiento) throws SQLException, ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void devolverTramite(TramiteMovimiento movimiento) throws SQLException, ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finalizarTramite(Tramite tramite) throws SQLException, ServiceException {
		// TODO Auto-generated method stub
		
	}

}
