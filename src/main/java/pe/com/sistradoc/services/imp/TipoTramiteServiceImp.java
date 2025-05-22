package pe.com.sistradoc.services.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sistradoc.dto.TipoTramiteDTO;
import pe.com.sistradoc.model.TipoTramite;
import pe.com.sistradoc.repository.TipoTramiteRepository;
import pe.com.sistradoc.services.TipoTramiteService;

@Service
public class TipoTramiteServiceImp implements TipoTramiteService{

	@Autowired
	private TipoTramiteRepository tipoTramiteRepository;

	@Override
	public TipoTramiteDTO obtenerTipoTramite(Long id) {
		TipoTramiteDTO tipoTramiteDto = new TipoTramiteDTO();
		
		TipoTramite tipoTramite = tipoTramiteRepository.findByIdTipoTramite(id);
		
		try {
			if(tipoTramite!=null) {
				tipoTramiteDto.setIdTipoTramite(id);
				tipoTramiteDto.setNombreTipoTramite(tipoTramite.getNombreTipoTramite());
				tipoTramiteDto.setNumeroDias(tipoTramite.getNumeroDias());
				tipoTramiteDto.setMontoTipoTramite(tipoTramite.getMontoTipoTramite());
				tipoTramiteDto.setEstado(tipoTramite.getEstado());
			}else {
				tipoTramiteDto.setIdTipoTramite(Long.valueOf(0));
				tipoTramiteDto.setNombreTipoTramite("");
				tipoTramiteDto.setNumeroDias(0);
				tipoTramiteDto.setMontoTipoTramite(0.00);
				tipoTramiteDto.setEstado("");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return tipoTramiteDto;
	}

	@Override
	public List<TipoTramiteDTO> listTipoTramite() {
		
		List<TipoTramite> listTipoTramite = tipoTramiteRepository.findAll();

		List<TipoTramiteDTO> listTipoTramiteDto = listTipoTramite.stream()
													.map(tipoTramite -> new TipoTramiteDTO(tipoTramite.getIdTipoTramite(), 
																tipoTramite.getNombreTipoTramite(), 
																tipoTramite.getMontoTipoTramite(), 
																tipoTramite.getNumeroDias(), 
																tipoTramite.getEstado()))
													.filter(tipoTramite -> tipoTramite.getEstado().equals("H"))
													.collect(Collectors.toList());
		return listTipoTramiteDto;
	}
	
	
	
	
}
