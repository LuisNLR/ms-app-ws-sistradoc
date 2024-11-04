package pe.com.sistradoc.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sistradoc.model.TramiteByDeriver;
import pe.com.sistradoc.repository.TramiteRepository;
import pe.com.sistradoc.services.TramiteQueryService;

@Service
public class TramiteQueryServiceImp implements TramiteQueryService {

	@Autowired
	TramiteRepository tramiteRepository;
	
	@Override
	public List<TramiteByDeriver> getListTramiteByDeriver() {
		return tramiteRepository.getListTramiteDerivar();
	}

}
