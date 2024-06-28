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

import pe.com.sistradoc.dto.SolicitanteDTO;
import pe.com.sistradoc.model.Dependencia;
import pe.com.sistradoc.model.DiaNoLaboral;
import pe.com.sistradoc.repository.DependenciaRepository;
import pe.com.sistradoc.repository.DiaNoLaboralRepository;
import pe.com.sistradoc.services.SolicitanteService;
import pe.com.sistradoc.utils.ResponseService;

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
	
	@GetMapping("/getRequester/{documentNumber}")
	private SolicitanteDTO getSolicitante(@PathVariable("documentNumber") String documentNumber) {
		LOGGER.info("Mensaje de prueba desde '{}'", MaintenanceController.class.getName());
		SolicitanteDTO solicitante = null;
		try {
			solicitante = solicitanteService.obtenerSolicitante(documentNumber);
			if(solicitante==null) {
				solicitante = new SolicitanteDTO();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return solicitante;
	}
	
	@PostMapping("/registerOrUpdateSolicitant")
	private ResponseService registerOrUpdateSolicitante(@RequestBody SolicitanteDTO solicDTO) {
		ResponseService response = new ResponseService();
		try {
			solicitanteService.registrarSolicitante(solicDTO);
			response.setMensaje("Registro exitoso");
		} catch (Exception e) {
			response.setMensaje(e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
	
}
