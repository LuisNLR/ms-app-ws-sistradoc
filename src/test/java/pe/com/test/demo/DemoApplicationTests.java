package pe.com.test.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import pe.com.sistradoc.dto.DependenciaDTO;
import pe.com.sistradoc.dto.TipoTramiteDTO;
import pe.com.sistradoc.model.Dependencia;
import pe.com.sistradoc.model.MovimientoPasoByTramiteAndDependencia;
import pe.com.sistradoc.model.TramiteMovimiento;
import pe.com.sistradoc.repository.DependenciaRepository;
import pe.com.sistradoc.repository.TramiteMovimientoRepository;
import pe.com.sistradoc.services.DependenciaService;
import pe.com.sistradoc.services.TipoTramiteService;
import pe.com.sistradoc.services.imp.TipoTramiteServiceImp;
import pe.com.sistradoc.utils.Utils;

@SpringBootConfiguration
@ContextConfiguration(classes = { pe.com.sistradoc.StartApplication.class })
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private DependenciaService dependenciaService;
	
	@Autowired
	private DependenciaRepository dependenciaRepository;
	
	@Autowired
	private TramiteMovimientoRepository movimientoRepository;
	
	@Test
	void contextLoads() {
		assertTrue(true);
	}
	
	@Test
	void isEmptyDependencyList() {
		assertTrue(!dependenciaService.listDependencia().isEmpty());
	}
	
	@Test
	void getDependency() {
		String nombreDependencia = dependenciaService.obtenerDependencia(Utils.valueDefaultLongOne).getNombreDependencia();
		assertEquals(nombreDependencia, "Mesa de partes");
	}
	
	@Test
	void getDependencyByTipoTramite() {
		DependenciaDTO dependencia = dependenciaService.obtenerDependenciaByTipoTramite(Integer.valueOf(1), Long.valueOf(2));
		assertEquals(dependencia.getNombreDependencia(), "Registro civil");
	}
	
	@Test
	void getDependencyByPasoAndTipoTramite() {
		Dependencia dependencia = dependenciaRepository.findDependenciaByPasoAndTipoTramite(Integer.valueOf(1), Long.valueOf(2));
		assertEquals(dependencia.getNombreDependencia(), "Registro civil");
	}
	
	@Test
	void getTipoTramiteWithMokito() {
		TipoTramiteService tipoTramiteService = Mockito.mock(TipoTramiteServiceImp.class);
		Mockito.when(tipoTramiteService.obtenerTipoTramite(Long.valueOf(0)))
				.thenReturn(new TipoTramiteDTO(Long.valueOf(0), 
											   "Tipo Tramite Mockito", 
											   Double.valueOf(0.00), 
											   Integer.valueOf(1), 
											   Utils.estadoGenericoHabilitado));
		
		String nombreTipoTramite = tipoTramiteService.obtenerTipoTramite(Long.valueOf(0)).getNombreTipoTramite();
		assertEquals(nombreTipoTramite, "Tipo Tramite Mockito");
	}
	
	@Test
	void getMovimientoByTramiteAndUbicacion() {
		TramiteMovimiento movimiento = movimientoRepository.findByTramiteCodigoTramiteAndUbicacionActual("TR24081500001", "1");
		assertEquals(movimiento.getIdMovimiento(), Long.valueOf(232));
	}
	
	@Test
	void isEmptyDependencyByDevolverList() {
		List<Dependencia> dependenciaList = dependenciaRepository.listDependenciasByDevolver("TR24081900002");
		assertTrue(!dependenciaList.isEmpty());
	}
	
	@Test
	void getMovimientoPasoByTramiteAndDependencia() {
		MovimientoPasoByTramiteAndDependencia movimiento = movimientoRepository.findPasoByTramiteAndDependencia("TR24081900002", Long.valueOf(12));
		assertEquals(movimiento.getPasoActual(), Integer.valueOf(1));
	}
	

}
