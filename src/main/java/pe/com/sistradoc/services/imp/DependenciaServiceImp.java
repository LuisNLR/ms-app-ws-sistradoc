package pe.com.sistradoc.services.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sistradoc.dto.AreaEntidadDTO;
import pe.com.sistradoc.dto.DependenciaDTO;
import pe.com.sistradoc.model.Dependencia;
import pe.com.sistradoc.model.DependenciaByTipoTramite;
import pe.com.sistradoc.repository.DependenciaRepository;
import pe.com.sistradoc.services.DependenciaService;

@Service
public class DependenciaServiceImp implements DependenciaService {
	
	@Autowired
	private DependenciaRepository dependenciaRepository;
	

	@Override
	public DependenciaDTO obtenerDependencia(Long id) {
		DependenciaDTO dependenciaDto = new DependenciaDTO();
		
		Dependencia dependencia = dependenciaRepository.findByIdDependencia(id);
		try {
			if(dependencia!=null) {
				dependenciaDto.setIdDependencia(dependencia.getIdDependencia());
				dependenciaDto.setNombreDependencia(dependencia.getNombreDependencia());
				dependenciaDto.setAreaEntidadDto(new AreaEntidadDTO(dependencia.getAreaEntidad().getIdArea(), dependencia.getAreaEntidad().getNombreArea()));
			}else {
				dependenciaDto.setIdDependencia(Long.valueOf(0));
				dependenciaDto.setNombreDependencia("");
				dependenciaDto.setAreaEntidadDto(new AreaEntidadDTO(Long.valueOf(0), ""));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return dependenciaDto;
	}

	@Override
	public List<DependenciaDTO> listDependencia() {
		
		List<Dependencia> listDependencia = dependenciaRepository.findAll();
		
		List<DependenciaDTO> listDependenciaDto = listDependencia.stream()
													.map(dependencia -> new DependenciaDTO(dependencia.getIdDependencia(), 
																						   dependencia.getNombreDependencia(), 
																						   new AreaEntidadDTO(dependencia.getAreaEntidad().getIdArea(), 
																								   			  dependencia.getAreaEntidad().getNombreArea())))
													.collect(Collectors.toList());
		return listDependenciaDto;
	}

	@Override
	public DependenciaDTO obtenerDependenciaByTipoTramite(Integer nroPaso, Long idTipoTramite) {
		DependenciaDTO dependenciaDto = new DependenciaDTO();
		
		DependenciaByTipoTramite dependencia = dependenciaRepository.findDependenciaByTipoTramite(nroPaso, idTipoTramite);
		try {
			if(dependencia!=null) {
				dependenciaDto.setIdDependencia(dependencia.getIdDependencia());
				dependenciaDto.setNombreDependencia(dependencia.getNombreDependencia());
				AreaEntidadDTO areaEntidad = new AreaEntidadDTO(dependencia.getIdArea(), dependencia.getNombreArea());
				dependenciaDto.setAreaEntidadDto(areaEntidad);
			}else {
				dependenciaDto.setIdDependencia(Long.valueOf(0));
				dependenciaDto.setNombreDependencia("");
				dependenciaDto.setAreaEntidadDto(new AreaEntidadDTO(Long.valueOf(0), ""));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return dependenciaDto;
	}

}
