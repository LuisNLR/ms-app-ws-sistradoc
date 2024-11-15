package pe.com.sistradoc.services;

import java.util.List;

import pe.com.sistradoc.model.TramiteQueryByDeriver;
import pe.com.sistradoc.model.TramiteQueryResumen;

public interface TramiteQueryService {
	
	public List<TramiteQueryByDeriver> getListTramiteByDeriver();
	
	public List<TramiteQueryByDeriver> getListTramiteByDevolver();
	
	public List<TramiteQueryByDeriver> getListTramiteByFinished();
	
	public List<TramiteQueryResumen> getListTramiteDeriverDelayed();
	
	public List<TramiteQueryResumen> getListTramiteFinishedDelayed();
	
}
