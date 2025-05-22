package pe.com.sistradoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.sistradoc.model.Dependencia;
import pe.com.sistradoc.model.DependenciaByTipoTramite;

@Repository
public interface DependenciaRepository extends JpaRepository<Dependencia, Long> {
	
	Dependencia findByIdDependencia(Long id);
	
	
	@Query(value = "select d.idx_depe_enti as iddependencia, \r\n"
			   + "         d.txt_nomb_depe as nombredependencia, \r\n"
			     + "       a.txt_nomb_area as nombrearea, \r\n"
			     + "       a.idx_area_enti as idarea \r\n"
			     + "  from tb_depe_enti d \r\n"
			     + " inner join tb_area_enti a      on a.idx_area_enti=d.fk0_area_idex \r\n"
			     + " inner join tb_fluj_tram_depe f on d.idx_depe_enti=f.fk0_depe_enti_idx \r\n"
			     + "                               and f.num_orde_fluj=:nroPaso \r\n"
			     + " inner join tb_tipo_tram tt     on tt.idx_tipo_tram=f.fk1_tipo_tram_idx \r\n"
			     + "                               and tt.idx_tipo_tram=:idTipoTramite \r\n"
			     + " limit 1  "
			       , nativeQuery = true)
	DependenciaByTipoTramite findDependenciaByTipoTramite(
	      @Param("nroPaso") Integer nroPasoObtener,
	      @Param("idTipoTramite") Long idTipoTramite);
	
	
	@Query(value = " SELECT ft.dependencia FROM FlujoTramiteDependencia ft  " + 
	               "  WHERE ft.ordenFlujo=:nroPaso" + 
				   "    AND ft.tipoTramite.idTipoTramite=:idTipoTramite ")
	Dependencia findDependenciaByPasoAndTipoTramite(
		      @Param("nroPaso") Integer nroPasoObtener,
		      @Param("idTipoTramite") Long idTipoTramite);
	
	@Query(value = " SELECT mt.dependencia FROM TramiteMovimiento mt  " + 
                   "  WHERE mt.tramite.codigoTramite=:codigoTramite "  + 
			       "    AND mt.ubicacionActual != '1' " )
	List<Dependencia> listDependenciasByDevolver(
			  @Param("codigoTramite") String codigoTramite);

}
