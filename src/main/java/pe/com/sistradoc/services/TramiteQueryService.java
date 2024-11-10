package pe.com.sistradoc.services;

import java.util.List;

import pe.com.sistradoc.model.TramiteByDeriver;

public interface TramiteQueryService {
	
	public List<TramiteByDeriver> getListTramiteByDeriver();
	
	public List<TramiteByDeriver> getListTramiteByDevolver();
	
	public List<TramiteByDeriver> getListTramiteByFinished();

}
