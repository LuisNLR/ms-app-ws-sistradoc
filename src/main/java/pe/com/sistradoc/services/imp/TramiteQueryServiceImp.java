package pe.com.sistradoc.services.imp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
//		DateTimeFormatter formatoDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
//		LocalDate fechaInicioL = LocalDate.parse(fechaInicio);
//		String fechaFormateadaInicio = fechaInicioL.format(formatoDateTime);
//		
//		LocalDate fechaFinL = LocalDate.parse(fechaFin);
//		String fechaFormateadaFin = fechaFinL.format(formatoDateTime);
//		
//		return tramiteRepository.getListTramiteFindByDateRange(fechaFormateadaInicio, fechaFormateadaFin);
		
		return tramiteRepository.getListTramiteFindByDateRange(fechaInicio, fechaFin);
	}


}
