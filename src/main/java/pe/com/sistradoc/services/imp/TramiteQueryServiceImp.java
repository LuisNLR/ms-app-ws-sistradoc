package pe.com.sistradoc.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sistradoc.model.TramiteQueryByDeriver;
import pe.com.sistradoc.model.TramiteQueryResumen;
import pe.com.sistradoc.repository.TramiteRepository;
import pe.com.sistradoc.services.TramiteQueryService;

@Service
public class TramiteQueryServiceImp implements TramiteQueryService {

	@Autowired
	TramiteRepository tramiteRepository;
	
	@Override
	public List<TramiteQueryByDeriver> getListTramiteByDeriver() {
		return tramiteRepository.getListTramiteDerivar();
	}
	
	@Override
	public List<TramiteQueryByDeriver> getListTramiteByDevolver() {
		return tramiteRepository.getListTramiteDevolver();
	}
	
	@Override
	public List<TramiteQueryByDeriver> getListTramiteByFinished() {
		return tramiteRepository.getListTramiteFinished();
	}

	@Override
	public List<TramiteQueryResumen> getListTramiteDelayedByNotify() {
		return tramiteRepository.getListTramiteDeriverResumen();
	}

}
