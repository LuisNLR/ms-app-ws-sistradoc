package pe.com.sistradoc.services;

import java.util.List;

import pe.com.sistradoc.dto.DependenciaDTO;

public interface DependenciaService {
	
	public DependenciaDTO obtenerDependencia(Long id);
	
	public List<DependenciaDTO> listDependencia();
	
	public DependenciaDTO obtenerDependenciaByTipoTramite(Integer nroPaso, Long idTipoTramite);

}
