package pe.com.sistradoc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.sistradoc.dto.DependenciaDTO;
import pe.com.sistradoc.dto.SolicitanteDTO;
import pe.com.sistradoc.dto.TipoTramiteDTO;
import pe.com.sistradoc.dto.TramiteDTO;
import pe.com.sistradoc.dto.TramiteRegisterDTO;
import pe.com.sistradoc.model.Dependencia;
import pe.com.sistradoc.model.DiaNoLaboral;
import pe.com.sistradoc.repository.DependenciaRepository;
import pe.com.sistradoc.repository.DiaNoLaboralRepository;
import pe.com.sistradoc.services.DependenciaService;
import pe.com.sistradoc.services.SolicitanteService;
import pe.com.sistradoc.services.TipoTramiteService;
import pe.com.sistradoc.services.TramiteService;
import pe.com.sistradoc.utils.ResponseService;
import pe.com.sistradoc.utils.ValidateService;

@RestController
@RequestMapping("/api")
public class MaintenanceController {
	
	public final static Logger LOGGER = LoggerFactory.getLogger(MaintenanceController.class);

	@Autowired
	private DiaNoLaboralRepository repositoryNonWorkingDays;
	
	@Autowired
	private DependenciaRepository repositoryDependency;
	
	@Autowired
	private SolicitanteService solicitanteService;
	
	@Autowired
	private TipoTramiteService tipoTramiteService;
	
	@Autowired
	private DependenciaService dependenciaService;
	
	@Autowired
	private TramiteService tramiteService;
	
	
	@GetMapping("/allNonWorkingDays")
	private List<DiaNoLaboral> allDays() {
		String p="asd";
		System.out.println(p);
		List<DiaNoLaboral> list = repositoryNonWorkingDays.findAll(); 
		
		return list;
	}
	
	@GetMapping("/allDependencys")
	private List<Dependencia> allDependencys() {
		List<Dependencia> list = repositoryDependency.findAll(); 
		System.out.println(list.size());
		return list;
	}
	
	@GetMapping("/getSolicitant/{documentNumber}")
	private SolicitanteDTO getSolicitante(@PathVariable("documentNumber") String documentNumber) {
		LOGGER.info("Mensaje de prueba desde '{}'", MaintenanceController.class.getName());
		SolicitanteDTO solicitanteDto = null;
		try {
			solicitanteDto = solicitanteService.obtenerSolicitante(documentNumber);
			if(solicitanteDto==null) {
				solicitanteDto = new SolicitanteDTO();
			}
		} catch (Exception e) {
			LOGGER.error("Error generado al intentar obtener  '{}'", documentNumber);
			LOGGER.error(e.getMessage());
			LOGGER.error(e.getLocalizedMessage(), e);
			e.printStackTrace();
		} 
		
		return solicitanteDto;
	}
	
	@PostMapping("/registerOrUpdateSolicitant")
	private ResponseService registerOrUpdateSolicitante(@RequestBody SolicitanteDTO solicDTO) {
		ResponseService response = new ResponseService();
		try {
			ValidateService validate = solicitanteService.registrarSolicitante(solicDTO);
			if(validate.isIsvalid()) {
				response.setStatus(200);
			}
			response.setMensaje(validate.getMsj());
			response.setFlag(validate.isIsvalid());
			
		} catch (Exception e) {
			response.setMensaje(e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
	
	@GetMapping("/getTipoTramite/{idTipoTramite}")
	private TipoTramiteDTO getTipoTramite(@PathVariable("idTipoTramite") Long idTipoTramite) {
		LOGGER.info("Mensaje de prueba desde '{}'", MaintenanceController.class.getName());
		TipoTramiteDTO tipoTramiteDto = null;
		try {
			tipoTramiteDto = tipoTramiteService.obtenerTipoTramite(idTipoTramite);
			if(tipoTramiteDto==null) {
				tipoTramiteDto = new TipoTramiteDTO();
			}
		} catch (Exception e) {
			LOGGER.error("Error generado al intentar obtener  '{}'", idTipoTramite);
			LOGGER.error(e.getMessage());
			LOGGER.error(e.getLocalizedMessage(), e);
			e.printStackTrace();
		} 
		
		return tipoTramiteDto;
	}
	
	@GetMapping("/getListTipoTramite")
	private List<TipoTramiteDTO> listTipoTramite() {
		List<TipoTramiteDTO> list = tipoTramiteService.listTipoTramite();
		return list;
	}
	
	@GetMapping("/getDependencia/{idDependencia}")
	private DependenciaDTO getDependencia(@PathVariable("idDependencia") Long idDependencia) {
		LOGGER.info("Mensaje de prueba desde '{}'", MaintenanceController.class.getName());
		DependenciaDTO dependenciaDto = null;
		try {
			dependenciaDto = dependenciaService.obtenerDependencia(idDependencia);
			if(dependenciaDto==null) {
				dependenciaDto = new DependenciaDTO();
			}
		} catch (Exception e) {
			LOGGER.error("Error generado al intentar obtener  '{}'", idDependencia);
			LOGGER.error(e.getMessage());
			LOGGER.error(e.getLocalizedMessage(), e);
			e.printStackTrace();
		}
		
		return dependenciaDto;
	}
	
	@GetMapping("/getDependenciaByTipoTramite/{nroPaso}/{idTipoTramite}")
	private DependenciaDTO getDependenciaByTipoTramite(@PathVariable("nroPaso") Integer nroPaso, @PathVariable("idTipoTramite") Long idTipoTramite) {
		LOGGER.info("Mensaje de prueba desde '{}'", MaintenanceController.class.getName());
		DependenciaDTO dependenciaDto = null;
		try {
			dependenciaDto = dependenciaService.obtenerDependenciaByTipoTramite(nroPaso, idTipoTramite);
			if(dependenciaDto==null) {
				dependenciaDto = new DependenciaDTO();
			}
		} catch (Exception e) {
			LOGGER.error("Error generado al intentar obtener  '{}'", idTipoTramite);
			LOGGER.error(e.getMessage());
			LOGGER.error(e.getLocalizedMessage(), e);
			e.printStackTrace();
		}
		
		return dependenciaDto;
	}
	
	@GetMapping("/getListDependencia")
	private List<DependenciaDTO> listDependencia() {
		List<DependenciaDTO> list = dependenciaService.listDependencia();
		return list;
	}
	
	@PostMapping("/registerTramite")
	private ResponseService registerTramite(@RequestBody TramiteRegisterDTO tramiteRegisterDto) {
		ResponseService response = new ResponseService();
		TramiteDTO tramiteDto = null;
		DependenciaDTO dependenciaDto = null;
		try {
			tramiteDto = tramiteRegisterDto.getTramiteDto();
			dependenciaDto = tramiteRegisterDto.getDependenciaDto();
			ValidateService validate = tramiteService.registrarTramite(tramiteDto, dependenciaDto);
			if(validate.isIsvalid()) {
				response.setStatus(200);
			}
			response.setMensaje(validate.getMsj());
			response.setFlag(validate.isIsvalid());
			
		} catch (Exception e) {
			response.setMensaje(e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
	
}
