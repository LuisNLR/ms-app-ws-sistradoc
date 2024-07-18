package pe.com.test.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import pe.com.sistradoc.dto.TipoTramiteDTO;
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
		String nombreDependencia = dependenciaService.obtenerDependencia(Long.valueOf(1)).getNombreDependencia();
		assertEquals(nombreDependencia, "Mesa de partes");
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

}
