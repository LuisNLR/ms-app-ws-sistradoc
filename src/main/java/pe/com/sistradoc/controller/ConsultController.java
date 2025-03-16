package pe.com.sistradoc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.sistradoc.model.TareaQueryByTramite;
import pe.com.sistradoc.model.TramiteQueryByDeriver;
import pe.com.sistradoc.model.TramiteQueryResumen;
import pe.com.sistradoc.services.TramiteMovimientoTareaService;
import pe.com.sistradoc.services.TramiteQueryService;

@RestController
@RequestMapping("/querys")
public class ConsultController {

	@Autowired
	private TramiteQueryService tramiteQueryService;
	
	@Autowired
	private TramiteMovimientoTareaService tareaService;
	
	@GetMapping("/getListTramiteByDeriver")
	public ResponseEntity<List<TramiteQueryByDeriver>> getListTramiteByDeriver() {
		try {
			return new ResponseEntity<>(tramiteQueryService.getListTramiteByDeriver(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getListTramiteToDeriverByDependency/{idDependencia}")
	public ResponseEntity<List<TramiteQueryByDeriver>> getListTramiteByDeriver(@PathVariable("idDependencia")Long idDependencia) {
		try {
			return new ResponseEntity<>(tramiteQueryService.getListTramiteByDeriver(idDependencia), HttpStatus.OK);
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
	
	@GetMapping("/getListTramiteToDevolverByDependency/{idDependencia}")
	public ResponseEntity<List<TramiteQueryByDeriver>> getListTramiteByDevolver(@PathVariable("idDependencia")Long idDependencia) {
		try {
			return new ResponseEntity<>(tramiteQueryService.getListTramiteByDevolver(idDependencia), HttpStatus.OK);
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
	
	@GetMapping("/getListTramiteToFinishedByDependency/{idDependencia}")
	public ResponseEntity<List<TramiteQueryByDeriver>> getListTramiteByFinished(@PathVariable("idDependencia")Long idDependencia) {
		try {
			return new ResponseEntity<>(tramiteQueryService.getListTramiteByFinished(idDependencia), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getListTramiteToAttendByDependency/{idDependencia}")
	public ResponseEntity<List<TramiteQueryByDeriver>> getListTramiteToAttend(@PathVariable("idDependencia")Long idDependencia) {
		try {
			return new ResponseEntity<>(tramiteQueryService.getListTramiteToAttend(idDependencia), HttpStatus.OK);
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
	
	@GetMapping("/getListTramiteFindByCodigoTramite/{codigoTramite}")
	public ResponseEntity<List<TramiteQueryByDeriver>> getListTramiteFindByCodigoTramite(@PathVariable("codigoTramite")String codigoTramite) {
		try {
			return new ResponseEntity<>(tramiteQueryService.getListTramiteFindByCodigoTramite(codigoTramite), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getListTramiteFindBySolicitante/{tipoDocumento}/{nroDocumento}")
	public ResponseEntity<List<TramiteQueryByDeriver>> getListTramiteFindBySolicitante(@PathVariable("tipoDocumento")String tipoDocumento, 
																					   @PathVariable("nroDocumento")String nroDocumento) {
		try {
			return new ResponseEntity<>(tramiteQueryService.getListTramiteFindBySolicitante(tipoDocumento, nroDocumento), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getListTramiteFindByDateRange/{fechaInicio}/{fechaFin}")
	public ResponseEntity<List<TramiteQueryByDeriver>> getListTramiteFindByDateRange(@PathVariable("fechaInicio")String fechaInicio, 
																					 @PathVariable("fechaFin")String fechaFin) {
		try {
			return new ResponseEntity<>(tramiteQueryService.getListTramiteFindByDateRange(fechaInicio, fechaFin), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getListTareasByCodigoTramite/{codigoTramite}")
	public ResponseEntity<List<TareaQueryByTramite>> getListTareasByCodigoTramite(@PathVariable("codigoTramite")String codigoTramite) {
		try {
			return new ResponseEntity<>(tareaService.getListTareaByTramites(codigoTramite), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
