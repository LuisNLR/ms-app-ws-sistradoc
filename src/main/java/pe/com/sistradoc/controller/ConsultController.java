package pe.com.sistradoc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.sistradoc.model.TramiteQueryByDeriver;
import pe.com.sistradoc.model.TramiteQueryResumen;
import pe.com.sistradoc.services.TramiteQueryService;

@RestController
@RequestMapping("/querys")
public class ConsultController {

	@Autowired
	private TramiteQueryService tramiteQueryService;
	
	@GetMapping("/getListTramiteByDeriver")
	public ResponseEntity<List<TramiteQueryByDeriver>> getListTramiteByDeriver() {
		try {
			return new ResponseEntity<>(tramiteQueryService.getListTramiteByDeriver(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getListTramiteByDevolver")
	public ResponseEntity<List<TramiteQueryByDeriver>> getListTramiteByDevolver() {
		try {
			return new ResponseEntity<>(tramiteQueryService.getListTramiteByDevolver(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getListTramiteByFinished")
	public ResponseEntity<List<TramiteQueryByDeriver>> getListTramiteByFinished() {
		try {
			return new ResponseEntity<>(tramiteQueryService.getListTramiteByFinished(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getListTramiteDelayedByNotify")
	public ResponseEntity<List<TramiteQueryResumen>> getListTramiteDelayedByNotify() {
		try {
			return new ResponseEntity<>(tramiteQueryService.getListTramiteDelayedByNotify(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
