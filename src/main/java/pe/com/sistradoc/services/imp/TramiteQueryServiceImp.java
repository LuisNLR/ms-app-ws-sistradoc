package pe.com.sistradoc.services.imp;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.sistradoc.dto.TramiteExportAccionesDTO;
import pe.com.sistradoc.dto.TramiteExportDTO;
import pe.com.sistradoc.dto.TramiteExportFlujoDTO;
import pe.com.sistradoc.dto.TramiteExportInformationDTO;
import pe.com.sistradoc.model.TramiteMovimientoQueryFlujo;
import pe.com.sistradoc.model.TramiteQueryByDeriver;
import pe.com.sistradoc.model.TramiteQueryExport;
import pe.com.sistradoc.model.TramiteQueryResumen;
import pe.com.sistradoc.repository.TramiteMovimientoRepository;
import pe.com.sistradoc.repository.TramiteRepository;
import pe.com.sistradoc.services.TramiteQueryService;

@Service
public class TramiteQueryServiceImp implements TramiteQueryService {

	public final static Logger LOGGER = LoggerFactory.getLogger(TramiteQueryServiceImp.class);
	
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

	@Override
	public List<TramiteExportDTO> getListTramiteByExport() {
		List<TramiteExportDTO> listTramiteExports = new ArrayList<>();
		List<TramiteQueryExport> listTramiteExportQueryResult;
		
		String codigoTramite="C00";
		Integer numeroMovimiento = Integer.valueOf(0);
		boolean isLoad = false;
		
		try {
			listTramiteExportQueryResult = tramiteRepository.getListTramiteInformations();
			
			
			List<TramiteExportFlujoDTO> listTramiteFlujo = new ArrayList<>();
			List<TramiteExportAccionesDTO> listTramiteAcciones = new ArrayList<>();
			
			for(TramiteQueryExport list : listTramiteExportQueryResult) {
				TramiteExportInformationDTO tramiteInformationDto = null;
				TramiteExportFlujoDTO tramiteFlujoDto = null;
				TramiteExportAccionesDTO tramiteTareaDto = null;
				
				if(!codigoTramite.equals(list.getCodigoTramite())) {
					tramiteInformationDto = new TramiteExportInformationDTO();
					tramiteInformationDto.setAsunto(list.getAsunto());
					tramiteInformationDto.setCodigoTramite(list.getCodigoTramite());
					tramiteInformationDto.setDiasTranscurridos(list.getDiasTranscurridos());
					tramiteInformationDto.setDuracion(list.getDuracion());
					tramiteInformationDto.setEstadoTramite(list.getEstadoTramite());
					tramiteInformationDto.setFechaIngreso(list.getFechaIngreso());
					tramiteInformationDto.setFechaTermino(list.getFechaTermino());
					tramiteInformationDto.setNumeroFolios(list.getNumeroFolios());
					tramiteInformationDto.setSolicitante(list.getSolicitante());
					tramiteInformationDto.setTipoTramite(list.getTipoTramite());
					
					listTramiteFlujo = new ArrayList<>();
					listTramiteAcciones = new ArrayList<>();
					isLoad = true;
					numeroMovimiento = Integer.valueOf(0);
				}
				
				if(!numeroMovimiento.equals(list.getNumeroMovimiento())) {
					tramiteFlujoDto = new TramiteExportFlujoDTO();
					tramiteFlujoDto.setNumeroMovimiento(list.getNumeroMovimiento());
					tramiteFlujoDto.setMotivoMovimiento(list.getMotivoMovimiento());
					tramiteFlujoDto.setFechaFin(list.getFechaFin());
					tramiteFlujoDto.setFechaAsignacion(list.getFechaAsignacion());
					tramiteFlujoDto.setEstadoMovimiento(list.getEstadoMovimiento());
					tramiteFlujoDto.setDiasAsignacionTranscurrido(list.getDiasAsignacionTranscurrido());
					tramiteFlujoDto.setDependenciaOrigen(list.getDependenciaOrigen());
					tramiteFlujoDto.setDependenciadestino(list.getDependenciaDestino());
					listTramiteFlujo.add(tramiteFlujoDto);
				}
				
				if(list.getTipoTarea()!=null) {
					tramiteTareaDto = new TramiteExportAccionesDTO();
					tramiteTareaDto.setTipoTarea(list.getTipoTarea());
					tramiteTareaDto.setFechaRegistroTarea(list.getFechaRegistroTarea());
					tramiteTareaDto.setDescripcionTarea(list.getDescripcionTarea());
					tramiteTareaDto.setDependencia(list.getDependenciaOrigen());
					listTramiteAcciones.add(tramiteTareaDto);					
				}
				
				if(!codigoTramite.equals(list.getCodigoTramite()) && isLoad) {
					TramiteExportDTO tramiteExport = new TramiteExportDTO();
					tramiteExport.setCodigoTramite(list.getCodigoTramite());
					tramiteExport.setTramiteInformation(tramiteInformationDto);
					tramiteExport.setListTramiteFlujo(listTramiteFlujo);
					tramiteExport.setListrTramiteAcciones(listTramiteAcciones);
					listTramiteExports.add(tramiteExport);

				}
				codigoTramite = list.getCodigoTramite();
				numeroMovimiento = list.getNumeroMovimiento();
				
			}
		} catch (Exception e) {
			LOGGER.error("Error generado al intentar obtener codigoTramite: '{}'", codigoTramite);
			LOGGER.error(e.getMessage());
			LOGGER.error(e.getLocalizedMessage(), e);
		}
		
		return listTramiteExports;
	}

}
