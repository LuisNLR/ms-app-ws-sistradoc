package pe.com.sistradoc.services;

import java.util.List;

import pe.com.sistradoc.dto.TipoTramiteDTO;

public interface TipoTramiteService {

	public TipoTramiteDTO obtenerTipoTramite(Long id);
	
	public List<TipoTramiteDTO> listTipoTramite();
	
}
