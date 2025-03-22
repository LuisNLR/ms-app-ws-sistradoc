package pe.com.sistradoc.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sistradoc.model.TramiteMovimientoQueryFlujo;
import pe.com.sistradoc.model.TramiteQueryByDeriver;
import pe.com.sistradoc.model.TramiteQueryResumen;
import pe.com.sistradoc.repository.TramiteMovimientoRepository;
import pe.com.sistradoc.repository.TramiteRepository;
import pe.com.sistradoc.services.TramiteQueryService;

@Service
public class TramiteQueryServiceImp implements TramiteQueryService {

	@Autowired
	TramiteRepository tramiteRepository;
	
	@Autowired
	TramiteMovimientoRepository movimientoRepository;
	
	@Override
	public List<TramiteQueryByDeriver> getListTramiteByDeriver() {
		return tramiteRepository.getListTramiteDerivar();
	}
	
	@Override
	public List<TramiteQueryByDeriver> getListTramiteByDeriver(Long idDependencia) {
		return tramiteRepository.getListTramiteDerivar(idDependencia);
	}
	
	@Override
	public List<TramiteQueryByDeriver> getListTramiteByDevolver() {
		return tramiteRepository.getListTramiteDevolver();
	}
	
	@Override
	public List<TramiteQueryByDeriver> getListTramiteByDevolver(Long idDependencia) {
		return tramiteRepository.getListTramiteDevolver(idDependencia);
	}
	
	@Override
	public List<TramiteQueryByDeriver> getListTramiteByFinished() {
		return tramiteRepository.getListTramiteFinished();
	}
	
	@Override
	public List<TramiteQueryByDeriver> getListTramiteByFinished(Long idDependencia) {
		return tramiteRepository.getListTramiteFinished(idDependencia);
	}

	@Override
	public List<TramiteQueryResumen> getListTramiteDelayedByNotify() {
		return tramiteRepository.getListTramiteDeriverResumen();
	}
	
	@Override
	public List<TramiteQueryByDeriver> getListTramiteToAttend(Long idDependencia) {
		return tramiteRepository.getListTramiteToAttend(idDependencia);
	}
	
	@Override
	public List<TramiteQueryByDeriver> getListTramiteFindByCodigoTramite(String codigoTramite) {
		return tramiteRepository.getListTramiteFindByCodeTram(codigoTramite);
	}
	
	@Override
	public List<TramiteQueryByDeriver> getListTramiteFindBySolicitante(String tipoDocumento, String nroDocumento) {
		return tramiteRepository.getListTramiteFindBySolicitante(tipoDocumento, nroDocumento);
	}
	
	@Override
	public List<TramiteQueryByDeriver> getListTramiteFindByDateRange(String fechaInicio, String fechaFin) {
		return tramiteRepository.getListTramiteFindByDateRange(fechaInicio, fechaFin);
	}
	
	@Override
	public List<TramiteMovimientoQueryFlujo> getListFlujosMovimientoTramite(String codigoTramite) {
		return movimientoRepository.getListFlujosMovimientoTramite(codigoTramite);
	}


}
