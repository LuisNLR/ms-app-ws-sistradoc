package pe.com.sistradoc.services;

import java.util.List;

import pe.com.sistradoc.dto.DependenciaDTO;

public interface DependenciaService {
	
	public DependenciaDTO obtenerDependencia(Long id);
	
	public List<DependenciaDTO> listDependencia();
	
	public List<DependenciaDTO> listDependenciaByDevolver(String codigoTramite);
	
	public DependenciaDTO obtenerDependenciaByTipoTramite(Integer nroPaso, Long idTipoTramite);
	
	public DependenciaDTO obtenerDependenciaByPasoAndTipoTramite(Integer nroPaso, Long idTipoTramite);
	
	public boolean isExistDependenciainFlujoTramite(List<DependenciaDTO> listDependencia, DependenciaDTO dependencia);

}
