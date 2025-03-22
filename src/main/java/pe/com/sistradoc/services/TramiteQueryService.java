package pe.com.sistradoc.services;

import java.util.List;

import pe.com.sistradoc.model.TramiteMovimientoQueryFlujo;
import pe.com.sistradoc.model.TramiteQueryByDeriver;
import pe.com.sistradoc.model.TramiteQueryResumen;

public interface TramiteQueryService {
	
	public List<TramiteQueryByDeriver> getListTramiteByDeriver();
	
	public List<TramiteQueryByDeriver> getListTramiteByDeriver(Long idDependencia);
	
	public List<TramiteQueryByDeriver> getListTramiteByDevolver();
	
	public List<TramiteQueryByDeriver> getListTramiteByDevolver(Long idDependencia);
	
	public List<TramiteQueryByDeriver> getListTramiteByFinished();
	
	public List<TramiteQueryByDeriver> getListTramiteByFinished(Long idDependencia);
	
	public List<TramiteQueryByDeriver> getListTramiteToAttend(Long idDependencia);
	
	public List<TramiteQueryResumen> getListTramiteDelayedByNotify();
	
	public List<TramiteQueryByDeriver> getListTramiteFindByCodigoTramite(String codigoTramite);
	
	public List<TramiteQueryByDeriver> getListTramiteFindBySolicitante(String tipoDocumento, String nroDocumento);
	
	public List<TramiteQueryByDeriver> getListTramiteFindByDateRange(String tipoDocumento, String nroDocumento);
	
	public List<TramiteMovimientoQueryFlujo> getListFlujosMovimientoTramite(String codigoTramite);
	
}
