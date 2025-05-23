package pe.com.sistradoc.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import pe.com.sistradoc.dto.TramiteMovimientoDTO;
import pe.com.sistradoc.dto.TramiteRegisterDTO;
import pe.com.sistradoc.dto.TramiteTareaDTO;
import pe.com.sistradoc.model.Dependencia;
import pe.com.sistradoc.model.DiaNoLaboral;
import pe.com.sistradoc.repository.DependenciaRepository;
import pe.com.sistradoc.repository.DiaNoLaboralRepository;
import pe.com.sistradoc.services.DependenciaService;
import pe.com.sistradoc.services.SolicitanteService;
import pe.com.sistradoc.services.TipoTramiteService;
import pe.com.sistradoc.services.TramiteMovimientoTareaService;
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
	
	@Autowired
	private TramiteMovimientoTareaService tramiteTareaService;
	
	
	@GetMapping("/allNonWorkingDays")
	public List<DiaNoLaboral> allDays() {
		String p="asd";
		System.out.println(p);
		List<DiaNoLaboral> list = repositoryNonWorkingDays.findAll(); 
		
		return list;
	}
	
	@GetMapping("/allDependencys")
	public List<Dependencia> allDependencys() {
		List<Dependencia> list = repositoryDependency.findAll(); 
		System.out.println(list.size());
		return list;
	}
	
	@GetMapping("/getSolicitant/{documentNumber}")
	public SolicitanteDTO getSolicitante(@PathVariable("documentNumber") String documentNumber) {
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
		} 
		
		return solicitanteDto;
	}
	
	@PostMapping("/registerOrUpdateSolicitant")
	public ResponseService registerOrUpdateSolicitante(@RequestBody SolicitanteDTO solicDTO) {
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
		}
		return response;
	}
	
	@GetMapping("/getTipoTramite/{idTipoTramite}")
	public TipoTramiteDTO getTipoTramite(@PathVariable("idTipoTramite") Long idTipoTramite) {
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
		} 
		
		return tipoTramiteDto;
	}
	
	@GetMapping("/getListTipoTramite")
	public ResponseEntity<List<TipoTramiteDTO>> listTipoTramite() {
		try {
			return new ResponseEntity<>(tipoTramiteService.listTipoTramite(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getDependencia/{idDependencia}")
	public DependenciaDTO getDependencia(@PathVariable("idDependencia") Long idDependencia) {
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
		}
		
		return dependenciaDto;
	}
	
	@GetMapping("/getDependenciaByTipoTramite/{nroPaso}/{idTipoTramite}")
	public DependenciaDTO getDependenciaByTipoTramite(@PathVariable("nroPaso") Integer nroPaso, @PathVariable("idTipoTramite") Long idTipoTramite) {
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
		}
		
		return dependenciaDto;
	}
	
	@GetMapping("/getListDependencia")
	public List<DependenciaDTO> listDependencia() {
		List<DependenciaDTO> list = dependenciaService.listDependencia();
		return list;
	}
	
	@PostMapping("/registerTramite")
	public ResponseEntity<ResponseService> registerTramite(@RequestBody TramiteRegisterDTO tramiteRegisterDto) {
		ResponseService response = new ResponseService();
		TramiteDTO tramiteDto = null;
		try {
			tramiteDto = tramiteRegisterDto.getTramiteDto();
			ValidateService validate = tramiteService.registrarTramite(tramiteDto);
			if(validate.isIsvalid()) {
				response.setStatus(200);
			}
			response.setMensaje(validate.getMsj());
			response.setFlag(validate.isIsvalid());
			response.setData(tramiteDto);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMensaje(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/deriverTramite")
	public ResponseEntity<ResponseService> deriverTramite(@RequestBody TramiteMovimientoDTO tramiteDeriverDto) {
		ResponseService response = new ResponseService();
		try {
			ValidateService validate = tramiteService.derivarTramite(tramiteDeriverDto);
			if(validate.isIsvalid()) {
				response.setStatus(200);
			}
			response.setMensaje(validate.getMsj());
			response.setFlag(validate.isIsvalid());
//			Map<String, String> datos = new HashMap<>();
//			datos.put("codigoTramite", tramiteDeriverDto.getTramiteDto().getCodigoTramite());
//			datos.put("motivoEnvio", tramiteDeriverDto.getMotivoEnvio());
			response.setData(validate.getData());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMensaje(e.getMessage());
			
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getListDependenciaDevolver/{codigoTramite}")
	public List<DependenciaDTO> listDependenciaByDevolver(@PathVariable("codigoTramite") String codigoTramite) {
		return dependenciaService.listDependenciaByDevolver(codigoTramite);
	}
	
	@PostMapping("/devolverTramite")
	public ResponseEntity<ResponseService> devolverTramite(@RequestBody TramiteMovimientoDTO tramiteDeriverDto) {
		ResponseService response = new ResponseService();
		try {
			ValidateService validate = tramiteService.devolverTramite(tramiteDeriverDto);
			if(validate.isIsvalid()) {
				response.setStatus(200);
			}
			response.setMensaje(validate.getMsj());
			response.setFlag(validate.isIsvalid());
			response.setData(validate.getData());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMensaje(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/finishedTramite")
	public ResponseEntity<ResponseService> finishedTramite(@RequestBody TramiteRegisterDTO tramiteRegisterDto) {
		ResponseService response = new ResponseService();
		TramiteDTO tramiteDto = null;
		try {
			tramiteDto = tramiteRegisterDto.getTramiteDto();
			ValidateService validate = tramiteService.finalizarTramite(tramiteDto);
			if(validate.isIsvalid()) {
				response.setStatus(200);
			}
			response.setMensaje(validate.getMsj());
			response.setFlag(validate.isIsvalid());
			response.setData(validate.getData());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMensaje(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/anullarTramiter")
	public ResponseEntity<ResponseService> anullarTramiter(@RequestBody TramiteRegisterDTO tramiteRegisterDto) {
		ResponseService response = new ResponseService();
		TramiteDTO tramiteDto = null;
		try {
			tramiteDto = tramiteRegisterDto.getTramiteDto();
			ValidateService validate = tramiteService.anularTramite(tramiteDto);
			if(validate.isIsvalid()) {
				response.setStatus(200);
			}
			response.setMensaje(validate.getMsj());
			response.setFlag(validate.isIsvalid());
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMensaje(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/registerTareaTramite")
	public ResponseEntity<ResponseService> registerTramite(@RequestBody TramiteTareaDTO tramiteTareaDto) {
		ResponseService response = new ResponseService();
		try {
			ValidateService validate = tramiteTareaService.registrarTramiteTarea(tramiteTareaDto);
			if(validate.isIsvalid()) {
				response.setStatus(200);
			}
			response.setMensaje(validate.getMsj());
			response.setFlag(validate.isIsvalid());
			response.setData(tramiteTareaDto);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMensaje(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
