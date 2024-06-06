package pe.com.sistradoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.sistradoc.model.Dependencia;
import pe.com.sistradoc.model.DiaNoLaboral;
import pe.com.sistradoc.repository.DependenciaRepository;
import pe.com.sistradoc.repository.DiaNoLaboralRepository;

@RestController
@RequestMapping("/api")
public class MaintenanceController {

	@Autowired
	private DiaNoLaboralRepository repositoryNonWorkingDays;
	
	@Autowired
	private DependenciaRepository repositoryDependency;
	
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
	
}
